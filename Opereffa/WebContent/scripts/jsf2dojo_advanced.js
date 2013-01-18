dojo.require("dijit.form.Button");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("dijit.form.CheckBox");
dojo.require("dijit.form.ComboBox");
dojo.require("dijit.form.Textarea");
dojo.require("dijit.form.FilteringSelect");
dojo.require("dojo.io.iframe");
dojo.require("dijit.layout.SplitContainer");
dojo.require("dijit.layout.ContentPane");
dojo.require("dijit.layout.AccordionContainer");
dojo.require("dijit.layout.TabContainer");
dojo.require("dijit.form.Button");
dojo.require("dijit.Dialog");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.Button");
dojo.require("dijit.Menu");
dojo.require("dijit.form.DateTextBox");
dojo.require("dojo.dnd.Container");
dojo.require("dojo.dnd.Manager");
dojo.require("dojo.dnd.Source");
dojo.require("dojox.grid.Grid");
dojo.require("dojo.parser");				
dojo.require("dojox.data.JsonRestStore");
dojo.require("dojo.dnd.move");
dojo.require("dojo.parser");


function initCustomDate(){
//	dojo.declare("OpereffaDateBox",[dijit.form.DateTextBox], {
//	    serialize: function(d, options) {
//	       return dojo.date.locale.format(d, {selector:'date', datePattern:'yyyy-MMM-dd'}).toLowerCase();
//	     }
//	 });
	dojo.declare("OpereffaDateBox", dijit.form.DateTextBox, {
		serialize: function(d, options) {
		
	       return dojo.date.locale.format(d, {selector:'date', datePattern:'yyyy-MM-dd', formatLength: 'short'}).toLowerCase();
	     }
	 ,
        postMixInProperties: function(){
                this.inherited(arguments);
                this.constraints.datePattern = "yyyy-MM-dd";
                if(this.srcNodeRef){
                        // reparse the value attribute using constraints.datePattern
                        // instead of calling dojo.date.stamp.fromISOString
                        var item = this.srcNodeRef.attributes.getNamedItem('value');
                        if(item){
                        		console.log('item for date ' + item.value);
                                this.value = dojo.date.locale.parse(item.value, {selector:'date', datePattern:'yyyy-MM-dd', formatLength: 'short'});
                        		//this.value = item.value;
                                console.log('item for date value set to' + this.value);
                        }
                }
        }
});
}

var DOJO_TYPE = 'dojoType';
var MOCK_POSTIFX = '_mock';

var TYPE_MAP = {
	submit   : 'dijit.form.Button',
	checkbox : 'dijit.form.CheckBox',
	radio    : 'dijit.form.RadioButton',
	text	 : 'dijit.form.ValidationTextBox',
	select   : 'dijit.form.FilteringSelect',
	textarea : 'dijit.form.Textarea'
};

var mockFuncMap = [];
var termRubricTextBoxes = [];
var dijitWidgets = [];
var dndHandlers = [];
var pendingDNDSelections = [];
var initializedDNDContainers = [];
var extraParamsToSubmit = null;
var sendingOrReceiving = false;
var dndInitComplete = false;
var reportName = '';
var initialLoad = false;

//a grid view is used to arrange layout of columns
var view1 = {
	cells: [[
		{name: 'SnCT Code'}, {name: 'Anatomical Area'}, {name: 'DOH Code'}, {name: 'Preferred Name', width: "75px"}, {name: 'Terms', width: "100px"}
	]]
};
// a grid layout is an array of views.
var layout = [ view1 ];
var timer;
var keywordVal = '';
var gridKeyHandler;
var gridClickHandler;

//////////////////////////TERMINOLOGY MANAGEMENT RELATED FUNCTIONS
function cleanGrid(pTooltipPostfix){
	if (dijit.byId('grid' + pTooltipPostfix).rowCount < 1)
		return;
	var emptymodel = new dojox.grid.data.Table(null, []);
	dijit.byId('grid' + pTooltipPostfix).setModel(emptymodel);
	console.debug('cleaning grid');
}

function setDataFromRest(keyword, pTooltipPostfix){
	var store = new dojox.data.JsonRestStore({target: '/Opereffa/resteasy/terminologyrestservice/codes/mapped?keyword=' + keyword});
	  callback = function(items, request){
		  		var terminologyModel = [];
				var tItemList = store.getValue(items, 'terminologyitemlist');
				if (!tItemList || tItemList == '' ){
					cleanGrid();
					return;
				}						
				var tItems = store.getValue(tItemList, 'terminologyitems');						
				for(var i = 0; i < tItems.length; i++){
					var code = store.getValue(tItems[i], 'code');
					var anotomicalArea = store.getValue(tItems[i], 'anotomicalArea');
					var diagnosisCode = store.getValue(tItems[i], 'diagnosisCode');
					var snomedPreferredName = store.getValue(tItems[i], 'snomedPreferredName');
					var snomedTerms = store.getValue(tItems[i], 'snomedterms');
					var snomedTermsJoined = '';
					for (var j = 0; j < snomedTerms.length; j++){
						if (j < snomedTerms.length - 1)
							snomedTermsJoined += snomedTerms[j] + ', ';
						else
							snomedTermsJoined += snomedTerms[j];
					}
					terminologyModel.push([code, anotomicalArea, diagnosisCode, snomedPreferredName, snomedTerms]);
				}
				dijit.byId('grid' + pTooltipPostfix).selection.clear();
				model = new dojox.grid.data.Table(null, terminologyModel);
				dijit.byId('grid' + pTooltipPostfix).setModel(model);															
		  };
	  store.fetch({onComplete: callback}); 			
}

function selectFirstRowInSnomedGrid(pTooltipPostfix){
	if (dijit.byId('grid' + pTooltipPostfix).rowCount < 1)
		return;
	dijit.byId('grid' + pTooltipPostfix).selection.setSelected(0,true);						
	dijit.byId('grid' + pTooltipPostfix).focus.setFocusCell(dijit.byId('grid' + pTooltipPostfix).getCell(0,0),0);
}

function closeTerminologyDialog(pTooltipPostfix){
	var dialog = dijit.byId('dlgChooseTermItem' + pTooltipPostfix);			
	dijit.popup.close(dialog);
}

function assignTerminologyValues(pTooltipPostfix){
	if ( dijit.byId('grid' + pTooltipPostfix) && dijit.byId('grid' + pTooltipPostfix).selection 
			&& dijit.byId('grid' + pTooltipPostfix).model && dijit.byId('grid' + pTooltipPostfix).model.count > 0){
		var modelTouse = dijit.byId('grid' + pTooltipPostfix).model;
		var rowIndex = dijit.byId('grid' + pTooltipPostfix).selection.getSelected()[0];
		if (rowIndex == null)
			return;
		console.debug('rowindex' + rowIndex);
        var code = dijit.byId('grid' + pTooltipPostfix).model.getRow(rowIndex)[0];
        var rubric = dijit.byId('grid' + pTooltipPostfix).model.getRow(rowIndex)[3];
        //var targetId = dijit.byId('hiddenTargetId' + pTooltipPostfix).getValue();
        //get target textbox and hiddenField, and set code and rubric
        //var txtBoxRubric = dojo.byId('termRubric' + pTooltipPostfix).childNodes[0].childNodes[0];
        //var queryStr = "[txtRubricId=" + "'termRubric" + pTooltipPostfix + "']";
        //var txtBoxRubric = dojo.query(queryStr)[0];
        var txtBoxRubric = termRubricTextBoxes['termRubric' + pTooltipPostfix];
        //txtBoxRubric.setAttribute('value', rubric);
        txtBoxRubric.setValue(rubric);
        var hiddenCode = dojo.byId('termCode' + pTooltipPostfix).childNodes[0];
        hiddenCode.setAttribute('value', code);
        //alert('value set to ' + code + ' : ' + rubric);
	}			                                                      
}

function changeSelectedRow(nextRow, pTooltipPostfix){
	dijit.byId('grid' + pTooltipPostfix).selection.clear();
	dijit.byId('grid' + pTooltipPostfix).selection.setSelected(nextRow,true);
	dijit.byId('grid' + pTooltipPostfix).focus.setFocusCell(grid.getCell(0,nextRow),nextRow);
}

function monitorKeyword(pTooltipPostfix){
	var txtKeyword = dojo.byId('txtkeyword' + pTooltipPostfix);	
	keywordVal = 'change here';			 		
	timer = setInterval(dojo.hitch(window, processTextBox, txtKeyword), 1000);
}

function processTextBox(txtBoxToProcess, pTooltipPostfix){
	console.debug('process textbox called for ' + pTooltipPostfix);
	console.debug('txtBox value: ' + txtBoxToProcess.value);
	console.debug('keyword value: ' + keywordVal);
	//clear grid if value == '' or too short
	if(txtBoxToProcess.value == '' || txtBoxToProcess.value.length < 3){
		cleanGrid(pTooltipPostfix);
	}
	if(txtBoxToProcess.value != '' && txtBoxToProcess.value != keywordVal && txtBoxToProcess.value.length > 2){
		//update last used keyword val so that you wont requery the server every 1 sec, with same text
		keywordVal = txtBoxToProcess.value; 
		//console.debug(txtBoxToProcess.value + ' lastOne: ' + keywordVal);
		setDataFromRest(keywordVal, pTooltipPostfix);				
	}
}

function performTooltipDialogShutdown(pTooltipPostfix){
	assignTerminologyValues(pTooltipPostfix);
	clearHandlers();
	//clean textbox
    dijit.byId('txtkeyword' + pTooltipPostfix).setValue('');
    //clean keyword cache that prevents queries for same text over and over again, every 1 second
    keywordVal = '';
    //clean grid    
    cleanGrid(pTooltipPostfix);
	closeTerminologyDialog(pTooltipPostfix);
}

function configureTooltipHandlers(pTooltipPostfix){
	console.debug('configuring handlers for id: ' + pTooltipPostfix); 
	//row click handler
	 //dojo.connect(dijit.byId('snomedGrid' + pTooltipPostfix), 'onRowClick', 'gridRowClicked');
	//keyword typing handler
	//keywordTextBoxHandler = dojo.connect(dijit.byId('dlgChooseTermItem' + pTooltipPostfix), 'onShow', function(){
		//								var txtKeyword = dojo.byId('txtkeyword' + pTooltipPostfix);	
			//							//attach the timer to keyword field of this tooltip dialog
				//						timer = setInterval(dojo.hitch(window, processTextBox, txtKeyword), 1000);
					//					});
	var txtKeyword = dojo.byId('txtkeyword' + pTooltipPostfix);	
	//attach the timer to keyword field of this tooltip dialog
	timer = setInterval(dojo.hitch(window, processTextBox, txtKeyword, pTooltipPostfix), 1000);
	gridKeyHandler = dojo.connect(dijit.byId('grid' + pTooltipPostfix), 'onKeyPress', function(evnt){
										var currentRow = parseInt(dijit.byId('grid' + pTooltipPostfix).selection.getSelected()[0]);
										if(evnt.charOrCode == dojo.keys.DOWN_ARROW){
											console.debug("down");
											var nextRow = currentRow + 1;
											if (nextRow <= dijit.byId('grid' + pTooltipPostfix).rowCount - 1){
												changeSelectedRow(nextRow, pTooltipPostfix);
											}
										}				
										else if(evnt.charOrCode == dojo.keys.UP_ARROW){
											console.debug("up");
											var nextRow = currentRow - 1;
											if (nextRow >= 0){
												changeSelectedRow(nextRow, pTooltipPostfix);	
											}				
										}
										else if(evnt.charOrCode == dojo.keys.ENTER){	
												performTooltipDialogShutdown(pTooltipPostfix);
											}
										});
	//mouse click handler
	
	gridClickHandler = dojo.connect(dijit.byId('grid' + pTooltipPostfix), 'onClick', function() {performTooltipDialogShutdown(pTooltipPostfix);});			
}

function clearHandlers(){
	console.debug('clearing handlers');
	//kill textbox monitoring thread
    clearInterval(timer);
    //disconnect onshow connection to keyword textbox
	//dojo.disconnect(keywordTextBoxHandler);
	dojo.disconnect(gridKeyHandler);
	dojo.disconnect(gridClickHandler);
}

function clearCodeandRubric(pTooltipPostfix){
	var txtBoxRubric = termRubricTextBoxes['termRubric' + pTooltipPostfix];
    txtBoxRubric.setValue('');
    var hiddenCode = dojo.byId('termCode' + pTooltipPostfix).childNodes[0];
    hiddenCode.setAttribute('value', '');
}

//////////////////////////TERMINOLOGY MANAGEMENT RELATED FUNCTIONS END


function showHelp(){
	dojo.xhrGet( {
	     url: 'opereffahelp.jsf',
	     handleAs: "text",
	     preventCache: true,
	     load: function(response){ 			
			dojo.byId('openEHRContainer').innerHTML = response;						
		}
	   });
}

var functionLoader = {
		reportLoader : function loadReport(reportName, startDate, endDate, patientId) {
				console.log('reportloader: ' + startDate + '  ' + endDate);
			   dojo.byId('openEHRContainer').innerHTML = '<iframe style="width:100%;height:95%;border:none;" src="../birtViewer/frameset?__report=' + reportName + '.rptdesign&paramPatientId=' + patientId + '&paramDateStart=' + startDate + '&paramDateEnd=' + endDate + '" id="rpc" name="rpc"></iframe>';
			}
};


	function DraggableItem(name,code, originalId){
		this.name = name;
		this.code = code;
		this.originalId = originalId;
	}
	
	DraggableItem.prototype.toString = function conditionToString(){var ret = this.name  ; return ret;};
	
	function attachDDListeners(){
		//should be executed ONCE 
		if (dndInitComplete)
			return;
		var startHandler = dojo.subscribe("/dnd/start", function(source){
		  //console.debug("Starting the drop", source);
		});
		
		
		var dropBeforeHandler = dojo.subscribe("/dnd/drop/before", function(source, nodes, copy, target){		  
		    //console.debug(copy ? "Before Copying from" : "Before Moving from", source, "to", target, "before", target.before);		  
		});
		
		
		var dropHandler = dojo.subscribe("/dnd/drop", function(source, nodes, copy, target){
//			var dlg = dijit.byId('dialog1');
//			dlg.closeButtonNode.style.display='none';
//			dlg.show();
		 // console.debug("Drop Moving from", source, "to", copy, nodes, "target:" , target);
		  dojo.forEach(nodes, function(node){
			 // console.log(node.name + " : " + node.code + "id: " + node.originalId + "sourceContainerId: " + node.sourceContainerId);
			  //submitSingleSimpleAdd(node.originalId, node.code);
			  
			  if(source.node.id == target.node.id) //just reordering
				return;  
			  if (node.sourceContainerId == source.node.id && node.targetContainerId == target.node.id)//normal add
				  //submitSingleElement(node.originalId, node.code, 'add');
				  listSelectedItems('add', node);
			  if (node.sourceContainerId == target.node.id && node.targetContainerId == source.node.id )
				  //submitSingleElement(node.originalId, node.code, 'delete');
				  listSelectedItems('remove', node);
		});  
	});
		dndInitComplete = true;
	}//hoba
	
	function getSelectedItems(){
		listofitems = dojo.byId('mainform:itemsList');
		options = listofitems.options;
		selectedItems = [];
		dojo.forEach(options, function(option){
			if (option.selected){
				//alert('option selected ' + option.text + ' ' + option.value);
				console.log(option.text + ' : ' + option.value);
				selectedItems.push(option);
			}			
		});
	}
	
	function initDND(container, unSelectedItems, selectedItems){
		//alert(container.id.replace('dndSource',''));		
		var typesArr = [container.id.replace('dndSource','dndTarget')];
		//alert(typesArr[0]);
		var customCreator = function(draggableItem){			
			var node = dojo.doc.createElement("div");
			dojo.addClass(node, "dojoDndItem");
			node.innerHTML = draggableItem.name;
			node.name = draggableItem.name;
			node.code = draggableItem.code;
			node.originalId = draggableItem.originalId;
			node.sourceContainerId = container.id;
			node.targetContainerId = node.sourceContainerId.replace('dndSource','dndTarget');
			node.id = dojo.dnd.getUniqueId();
			node.ondblclick = function(e){domove(node);};
			//dojo.connect(node.domNode, "onDblClick",window, testDblClick);
			return {node:node, data:draggableItem, type:typesArr};
		};
		var limitingBox = container.parentNode;		
		var params = {accept:typesArr,creator: customCreator};
		var unselectedItemsList = new dojo.dnd.Source(container, params);//this may be container.id, check it out
		//unselectedItemsList.constrainTo(limitingBox);
		//now find the target for this DND source
		var targetDNDContainer = dojo.byId(container.id.replace('dndSource','dndTarget'));
		//targetDNDContainer.constrainTo(limitingBox);
		var selectedItemsList = new dojo.dnd.Source(container.id.replace('dndSource','dndTarget'), params);//this may be container.id, check it out		
		//alert(itemsList.id);
		//tempsrc = dojo.getObject(itemsList.id);
		//alert(tempsrc);
		if(unSelectedItems.length > 0)
			unselectedItemsList.insertNodes(false, unSelectedItems);
		if (selectedItems.length > 0)
			selectedItemsList.insertNodes(false, selectedItems);
		var initedContainers = {'source':unselectedItemsList, 'target':selectedItemsList};
		return initedContainers;
};

function domove(node){
	sourceContainer = dojo.byId(node.sourceContainerId);
	//dataItem = sourceContainer.getNode(node.id);
	dataItem = dojo.byId(node.id);
	nodestoInsert = [];
	nodestoInsert[0] = dataItem;
	target = dijit.byId('parsed' + node.sourceContainerId.replace('dndSource','dndTarget'));
	target.insertNodes(false, nodestoInsert);
}

function transformSimpleAdder(){
	//reset inited containers before constructing fresh ones
	initializedDNDContainers = [];
	var simpleAdders = dojo.query("[multipleInstanceWidget='simpleAdderSource']");
	dojo.forEach(simpleAdders, function(simpleAdder){
		var multiSelect = simpleAdder.childNodes[0];
		console.log(multiSelect);
		var options = multiSelect.options;
		var unselectedItems = [];
		var selectedItems = [];
		dojo.forEach(options, function(o){
			dItem = new DraggableItem(o.text, o.value, multiSelect.name);
			//dojo.addClass(dItem, "dojoDndItem");
			console.log(o.text + o.value);
			//dojo.connect(dItem.domNode, "onDblClick",testDblClick);
			//dItem.ondblclick = function(e){alert('hald');testDblClick();};
			if (o.selected){
				//alert('option selected ' + o.text + ' ' + o.value);
				selectedItems.push(dItem);
				console.log(o.selected);
			}				
			else{
				//alert('option NOT selected ' + o.text + ' ' + o.value);
				unselectedItems.push(dItem);
			}
				
		});
		//clear out container before adding d&d items
		//multiSelect.parentNode.removeChild(multiSelect);
		dojo.query(simpleAdder.childNodes[0]).orphan();
		
		var initedContainerMap = initDND(simpleAdder, unselectedItems, selectedItems);
		initializedDNDContainers.push(initedContainerMap);
	});
	//simpleAdders.orphan();
}

//test func. delete later
function listSelectedItems(operation, nodeToProcess){
	//this is tricky, since the drop is not complete yet, so we can't get our new node from containers
	//we add it manually, and populate the rest from containers
	if (operation == 'add'){
		//add this one, and populate the rest from the page
		pendingDNDSelections = [];
		pendingDNDSelections.push(nodeToProcess)
		dojo.forEach(initializedDNDContainers, function(containerMap){
			var targetContainer = containerMap['target'];
			dojo.forEach(targetContainer.getAllNodes(),function(node){
				pendingDNDSelections.push(node);
			});
		});
	}
	else if (operation = 'remove'){
		pendingDNDSelections = [];
		//add all except selected one
		dojo.forEach(initializedDNDContainers, function(containerMap){
			var targetContainer = containerMap['target'];
			dojo.forEach(targetContainer.getAllNodes(),function(node){
				if (nodeToProcess.code != node.code)
					pendingDNDSelections.push(node);
			});
		});
	}
	else return;
	console.log('These are the selected items at the moment');
	dojo.forEach(pendingDNDSelections, function(node){
		console.log(node.originalId + ' - ' + node.code );
	});	
}

function testDblClick(){
	alert('hadi bakalim');
}

function archLinkMouseOver(element){
	element.className='archetypeLink-hover';
}

function archLinkMouseOut(element){
	element.className='archetypeLink';
}

function headerLinkMouseOver(element){
	element.className='headerLink-hover';
}

function headerLinkMouseOut(element){
	element.className='headerLink';
}

function resetDojoWidgets(elementId){
	dojo.forEach(
			//for each of the results of the following
			  dojo.query('[widgetId]', document.getElementById(elementId)),
			    function(widget) {
			      // For some reason I can't access widget.widgetid; trim the "widget_" prefix off of the id
			      var widgetId=widget.id.substring(7);
			      if (dijit.byId(widgetId)) {
			        dijit.byId(widgetId).destroy();
			      }
			    }
			);
}

function resetRegisteredWidgets(){
	dojo.forEach(dijitWidgets, 
			function(widget) {
	      // For some reason I can't access widget.widgetid; trim the "widget_" prefix off of the id
	      //var widgetId=widget.id.substring(7);
		var widgetId=widget.id;
	      if (dijit.byId(widgetId)) {
	        dijit.byId(widgetId).destroy();
	      }
	    });
}

function callDojoParser(){
	dojo.parser.parse();
}

function testForm(){
//	var form = dojo.byId('mainform');
//	for(i = 0; i < form.childNodes.length; i++){
//		console.log(form.childNodes[i]);
//	}
	submitFormParts();
}



//obselete
//function submitFormParts(fieldName) {
//	var form = dojo.clone(dojo.byId('mainform'));
//	var children = form.elements;
//	var inputs = [];
//	for(j = 0; j < children.length; j++){
//		console.log(children[j].name);
//		if (children[j].name.indexOf(':') < 0 || children[j].name == fieldName){
//			inputs.push(children[j]);
//		}
//	}
//	while(form.childNodes.length > 1 ){
//		form.removeChild(form.childNodes[0]);
//	}
//	for(i = 0; i < inputs.length; i++){
//		form.appendChild(inputs[i]);
//	}
//	dojo.xhrPost ({
//            // The page that parses the POST request
//            url: form.action,
//   
//            // Name of the Form we want to submit
//            form: form,
//            content : extraParamsToSubmit,
//            executeScripts : true,
////            // Loads this function if everything went ok
//            load: function (data) {
//					//console.log(data);
//				//dojo.byId('hiddendiv').innerHTML = data;
//				init();
//            },
//            // Call this function if an error happened
//            error: function (error) {
//                    console.error ('Error: ', error);
//            }
//});
//}

function submitSingleElement(fieldName, fieldValue, operationName){	
	var tempForm = dojo.doc.createElement("form");
	tempForm.id = 'tempForm';
	tempForm.name = 'tempForm';
	var clonedForm = dojo.clone(dojo.byId('mainform'));
	tempForm.action = clonedForm.action;
	var clonedFormElements = clonedForm.elements;
	var elementsToCopy = [];
	//create and insert element using incoming name and value
	var fieldToPost = dojo.doc.createElement("input");
	fieldToPost.name = fieldName;
	fieldToPost.value = fieldValue;
	elementsToCopy.push(fieldToPost);
	
	var jsfFormNameInput = dojo.doc.createElement("input");
	jsfFormNameInput.name = 'mainform';
	jsfFormNameInput.value = 'mainform';
	elementsToCopy.push(jsfFormNameInput);
	
	//console.log("element sent: " + fieldToPost.name);
	
	for(var clonedFElIndex = 0; clonedFElIndex < clonedFormElements.length; clonedFElIndex++){
					
		//if (clonedFormElements[clonedFElIndex].name.indexOf(':') < 0 && (clonedFormElements[clonedFElIndex].id && clonedFormElements[clonedFElIndex].id.indexOf('dijit') < 0) ){
		if (clonedFormElements[clonedFElIndex].name.indexOf('ViewState') > 0 ){
			console.log("now adding" + clonedFormElements[clonedFElIndex].name);
			elementsToCopy.push(clonedFormElements[clonedFElIndex]);
			break;
		}						
	}
	
	
	dojo.forEach(elementsToCopy, function(el){
		//console.log(el);
		tempForm.appendChild(el);
	});
	//var paramsToSend = dojo.clone(extraParamsToSubmit);
	//In the single submit DO NOT send the button field, that causes 
	//processing of all other fields too
	var paramsToSend = [];	
	paramsToSend['operation'] = operationName;
	paramsToSend['sessionId'] = dojo.byId('sessionId').innerHTML;
	paramsToSend['contextId'] = dojo.byId('contextId').innerHTML;
	paramsToSend['createdAt'] = dojo.byId('createdAt').innerHTML;	
	paramsToSend['fieldToProcess'] = fieldName;
	dojo.xhrPost ({
        // The page that parses the POST request
        url: tempForm.action,

        // Name of the Form we want to submit
        form: tempForm,
        content : paramsToSend,
        headers : {'Ajax-Request':'openehr'},
        executeScripts : true,
//        // Loads this function if everything went ok
        load: function (data) {
				//console.log(data);
			//dojo.byId('hiddendiv').innerHTML = data;
			
        },
        // Call this function if an error happened
        error: function (error) {
                console.error ('Error: ', error);
        }
});
}
	
//the original code for this function, and the core method for dojo integration came from 
//http://www.ibm.com/developerworks/library/wa-aj-jsfdojo/
function init(){	
	var form = dojo.byId('mainform');
	//in case there are widgets converted into dojo widgets, delete them
	//because a form post will bring them back again, and they can't be registered in dojo two times
	resetRegisteredWidgets();
	//reset funcmap too
	mockFuncMap = [];
	//also reset term rubric text boxes
	termRubricTextBoxes = [];
	//reset submitButton 
	extraParamsToSubmit = new Object();
//	//parse dojo artefacts which are not marked with divs, like layout panes etc
//	var res = dojo.parser.parse();
//	//and save them seperately
//	dojo.forEach(res, function(w){dijitWidgets.push(w)});	
	
	var jsfContainerList = dojo.query("[jsf2dojo='true']"); // get jsf widgets to be converted to dojo
	
	for(var i = 0 ; i < jsfContainerList.length; i++){
		jsfContainerList[i].style.display = 'inline';
		
		var jsfList = _filterJsf(jsfContainerList[i]);
		
		for(var j = 0; j < jsfList.length; j++){
			
			var jsfWidgets = _getJsfWidgets(jsfList[j]);//get jsf wrapper node		
			if(!jsfWidgets || jsfWidgets.length <= 0) continue;
			if (dijit.byId(jsfWidgets.id)) {
		        var wtf = dijit.byId(jsfWidgets.id);
		        alert(wtf.id);
		      }
			for(var n = 0; n < jsfWidgets.length; n++){
				var dojoWidget = null;
				var mockFunc = null;
				var jsfWidget = jsfWidgets[n];
				var tagname = jsfWidget.tagName.toLowerCase();
				if ('select' == jsfWidget.tagName.toLowerCase()){ // select
					jsfWidget.setAttribute(DOJO_TYPE, TYPE_MAP[jsfWidget.tagName.toLowerCase()]);
					mockFunc = _mockSelect;					
					console.log("select name" + jsfWidget.name);
				}else if('textarea' == jsfWidget.tagName.toLowerCase()){//textarea
					jsfWidget.setAttribute(DOJO_TYPE, TYPE_MAP[jsfWidget.tagName.toLowerCase()]);
					
				}else if('input' == jsfWidget.tagName.toLowerCase()){ // input 
					var type = jsfWidget.type.toLowerCase();
					
					if('submit' == type){												
						extraParamsToSubmit[jsfWidget.id] = jsfWidget.value;
						var params = {label: jsfWidget.value};
						var dojoWidget = new dijit.form.Button(params, jsfWidget.id);
						//var dojoWidget = dojo.parser.parse(jsfList[j])[0];
						dojo.connect(dojoWidget, 'onClick', window, 'submitPartialForm');
//						dojo.connect(dojoWidget, 'onClick', window, 'formSubmit');
						mockFunc = _mockSubmitInput;						
						//form.appendChild(dojoWidget);		
					}
					else if ('button' == type){
						var params = {label: jsfWidget.value};
						var dojoWidget = new dijit.form.Button(params, jsfWidget.id);
						//javascript var scoping is tricky!! if you use jsfWidget.id for the function below, it'll resolve to last value of jsfWidget
						//that's whey we use buttonId and force evaluation now, rather than later, when the button is clicked
						var buttonId =  jsfWidget.id;
						dojo.connect(dojoWidget, 'onClick', window, function(){alert('\''+ buttonId.replace('openTermSearch','')  + '\'');});
					}
					else if('text' == type){						
						jsfWidget.setAttribute(DOJO_TYPE, TYPE_MAP[type]);
						jsfWidget.setAttribute('promptMessage',"Please Enter your information");
						jsfWidget.setAttribute('required',"true");									
					
					}else if ('checkbox' == type || 'radio' == type){	
						jsfWidget.setAttribute(DOJO_TYPE, TYPE_MAP[type]);
					}
//					else if('hidden' == type && 'archetypeFileName' == jsfWidget.id ){
//						extraParamsToSubmit['archetypeFileName'] = jsfWidget.value; 
//					}
				}
		
				if(!dojoWidget){					
					dojoWidget = dojo.parser.parse(jsfList[j])[0];//parse wrapper node
					if(jsfContainerList[i].id.indexOf('termRubric') > -1){
						termRubricTextBoxes[jsfContainerList[i].id] = dojoWidget;						
					}
				}
				
				if(dojoWidget && mockFunc){
					mockFuncMap.push({'dojoWidget': dojoWidget, 'mockFunc': mockFunc});
				}
				
				if(dojoWidget)
					dijitWidgets.push(dojoWidget);
			}
		}
	}
	attachDDListeners();
	transformSimpleAdder();
//	dojo.forEach(dojo.byId('mainform').elements, function(element){
//		alert(element.name + ' :: id-> ' + element.id );
//	});
   }

//	function test(){
//		var frm = new document.createElement("form");
//		for (i = 0; i < document.getElement)
//	}

function submitPartialForm() {
	var form = dojo.clone(dojo.byId('mainform'));
	
	for(var i = 0 ; i < mockFuncMap.length; i++){
   		mockFuncMap[i]['mockFunc']( mockFuncMap[i]['dojoWidget'] ); //invoke corresponding mock functions  	
   	}
//    
	var extraInfoToSend = dojo.clone(extraParamsToSubmit);
	extraInfoToSend['sessionId'] = dojo.byId('sessionId').innerHTML;	
	extraInfoToSend['contextId'] = dojo.byId('contextId').innerHTML;
	extraInfoToSend['createdAt'] = dojo.byId('createdAt').innerHTML;
	extraInfoToSend['fieldToProcess'] = 'allfields';
	dojo.forEach(initializedDNDContainers, function(dndContainer){
		var programmaticSelect;		
		try{
			programmaticSelect = dojo.doc.createElement('<select multiple>');
		}
		catch(ex){
			programmaticSelect = dojo.doc.createElement('select');
		}
		programmaticSelect.multiple = true;
		
		//add unselected options
		var unselectedOptions = dndContainer['source'];
		for(var uoindex = 0; uoindex < unselectedOptions.getAllNodes().length; uoindex++){
			programmaticSelect.name = unselectedOptions.getAllNodes()[uoindex].originalId;
			programmaticSelect.options[uoindex] = new Option(unselectedOptions.getAllNodes()[uoindex].name, unselectedOptions.getAllNodes()[uoindex].code);									
		}
//		dojo.forEach(unselectedOptions.getAllNodes(), function(uo){
//			 //var usOption = document.createElement('option');
////			 usOption.text = uo.name;
////			 usOption.value = uo.code;
//			var usOption = new Option(uo.name, uo.code);
//			 //alert('unselected code' + uo.code + ' ' + uo.name);
//			 programmaticSelect.name = uo.originalId;
//			 try {
//				 programmaticSelect.add(usOption, null); // standards compliant; doesn't work in IE
//				  }
//				  catch(ex) {
//					  programmaticSelect.add(usOption); // IE only
//				  }
//
//		});		
		//and now selected options
		var currentLength = programmaticSelect.options.length;
		var selectedOptions = dndContainer['target'];
		for(var soindex = 0; soindex < selectedOptions.getAllNodes().length; soindex++){			
			programmaticSelect.name = selectedOptions.getAllNodes()[soindex].originalId;
			programmaticSelect.options[currentLength + soindex] = new Option(selectedOptions.getAllNodes()[soindex].name, selectedOptions.getAllNodes()[soindex].code);
			//programmaticSelect.options[soindex].selected = true;
		}
		//now arrange selected/unselected items
		programmaticSelect.selectedIndex = -1;
		for(var uoindex = 0; uoindex < unselectedOptions.getAllNodes().length; uoindex++){			
			programmaticSelect.options[uoindex].selected = false;						
		}
		for(var soindex = 0; soindex < selectedOptions.getAllNodes().length; soindex++){						
			programmaticSelect.options[currentLength + soindex].selected = true;
		}				 
		
//		dojo.forEach(selectedOptions.getAllNodes(), function(so){
////			 var soOption = document.createElement('option');
////			 soOption.text = so.name;
////			 soOption.value = so.code;
//			var soOption = new Option(so.name, so.code);
//			 soOption.selected = true;
//			 //alert('selected code' + so.code + ' ' + so.name);
//			 console.log('has set up selected = true for ' + soOption.text + soOption.selected);
//			 programmaticSelect.name = so.originalId;
//			 try {
//				 programmaticSelect.add(soOption, null); // standards compliant; doesn't work in IE
//				  }
//				  catch(ex) {
//					  programmaticSelect.add(soOption); // IE only
//				  }
//
//		});
//		alert('added multiselect' + programmaticSelect.name);
//		dojo.forEach(programmaticSelect.options, function(opt){
//			alert('multiselect ' + programmaticSelect.name + ' option selected' + opt.selected);
//		});
//		programmaticSelect.size = 5;
		form.appendChild(programmaticSelect);
		//dojo.byId('testField').appendChild(programmaticSelect);
	});
//	return;
//	dojo.forEach(form.elements, function(element){
//		alert(element.name + ' :: id-> ' + element.id );
//	});
	var dlg = dijit.byId('processingDialog');
	dlg.closeButtonNode.style.display='none';
	dlg.show();
	dojo.xhrPost ({
            // The page that parses the POST request
            url: dojo.byId('mainform').action,
   
            // Name of the Form we want to submit
            form: form,
            content : extraInfoToSend,
            headers : {'Ajax-Request':'openehr'},
//            // Loads this function if everything went ok
            load: function (data) {
            		dlg.hide();
					dojo.style('openEHRContainer','display','none');	
                    // Put the data into the appropriate <div>						
                    dojo.byId('openEHRContainer').innerHTML = data;
                    init();
                    dojo.style('openEHRContainer','display','inline');
                    //this next bit is interesting. if returned content includes native dojo widgets, 
                    //not like the html wigets we process in init(), than they are not parsed. 
                    //the following check parses snomed ct code selector widgets
                    var snomedCTWidgets = dojo.query("div[id*='dropDownContainerMarker']");
                    if (snomedCTWidgets.length > 0){
                    	dojo.forEach(snomedCTWidgets, function(w){
                    		console.debug('parsing ' + w.id);                    	
                    		dojo.parser.parse(dojo.byId('dropDownContainer' + dojo.byId(w.id).innerHTML));                    	                    			
                    		});
                    	
                    }
                    
                    	
                    getSavedRecords();
            },
            // Call this function if an error happened
            error: function (error) {
                    console.error ('Error: ', error);
            }
});
}

	function getArchetypeList(urlToGet,nodeToDisplay){
		dojo.xhrGet( {
		     url: urlToGet,
		     handleAs: "text",
		     preventCache: true,
		     load: function(response){ dojo.byId(nodeToDisplay).innerHTML = response; }
		   });
	}
	
	function getSavedRecords(){
		var contextId = dojo.byId('contextId').innerHTML;
		if (contextId == null || contextId == undefined){
			alert('Context Id not found, aborting');
			return;
		}
		var contents = [];
		contents['contextId'] = contextId;
		var dlg = dijit.byId('processingDialog');
		dlg.closeButtonNode.style.display='none';
		dlg.show();		
		dojo.xhrGet( {
		     url: 'Records.jsf',
		     handleAs: "text",
		     content: contents,
		     preventCache: true,
		     load: function(response){ 
				dlg.hide();
				dojo.byId('recordsContainer').innerHTML = response;
				var containerToAnimateId = 'summary' + dojo.byId('mainform:sessionId').value;
				if (dojo.byId(containerToAnimateId) != null){
					dojo.anim(dojo.byId(containerToAnimateId),{backgroundColor: "#efefef"}); 
					//dojo.anim(dojo.byId(containerToAnimateId),{backgroundColor: "#fff"});
				}
			}
		   });
	}
	
	function setPatientId(dialogFields) {
        if (dialogFields.patientId != ''){
        	dojo.byId('recordsContainer').innerHTML = '';
        	if (!initialLoad)
        		dijit.byId('openEHRContainer').setContent('');
        	else{
        		showHelp();
        		initialLoad = false;
        	}
           dojo.byId('contextId').innerHTML = dialogFields.patientId;
           dojo.byId('changePatientLink').innerHTML = 'Change Patient [' + dialogFields.patientId + ']'; 
        }
        else
        {
        	alert('You did not provide a patient name, a test name will be used');
        	return;
        }
     }
	
	function displayReport(dialogFields) {
        if (dialogFields.reportEndDate != '' && dialogFields.reportStartDate != ''){
        	var datefldStart = dijit.byId('reportStartDate').toString();
        	console.log('datefldStart' + datefldStart);
        	var datefldEnd = dijit.byId('reportEndDate').toString();
        	console.log('datefldEnd' + datefldEnd);
        	//loadReport(reportName, dialogFields.reportStartDate, dialogFields.reportEndDate, dojo.byId('contextId').innerHTML);
        	setTimeout(dojo.hitch(functionLoader, "reportLoader", reportName, datefldStart, datefldEnd, dojo.byId('contextId').innerHTML), 400);
        }
        else
        {
        	alert('You did not provide a valid date interval');
        	return;
        }
     }
	
//	function displayReport2(dialogFields) {
//        if (dialogFields.reportEndDate != '' && dialogFields.reportStartDate != '')
//        	loadReport('TonyReport', ialogFields.reportStartDate, dialogFields.reportEndDate, dojo.byId('contextId').innerHTML);
//        else
//        {
//        	alert('You did not provide a valid date interval');
//        	return;
//        }
//     }

	function refreshArchetypePanes(){
		var paneTemplates = [];
		paneTemplates.push({'listContainerName':'observationArchetypeList','listURL':'observationArchetypes.jsf'});
		paneTemplates.push({'listContainerName':'instructionArchetypeList','listURL':'instructionArchetypes.jsf'});
		paneTemplates.push({'listContainerName':'evaluationArchetypeList','listURL':'evaluationArchetypes.jsf'});		
		dojo.forEach(paneTemplates, function callGetList(p){getArchetypeList(p['listURL'], p['listContainerName']);});
	}
	
	function getHTML()
	{
//		var url = '/Tolven/five/xhtml/openEHR-bak4.jsf';
		var url = '/Tolven/five/xhtml/openEHRContent.jsf';
		var pars = 'someParameter=ABC';
		dojo.style('openEHRContainer','display','none');		
		var myAjax = new Ajax.Updater(
			'openEHRContainer', 
			url, 
			{
				method: 'get', 
				parameters: pars,
				evalscripts:true,
				onComplete: function() {
				init();															
				dojo.style('openEHRContainer','display','inline');
				}
			});		
	}
	
	function getArchetypeForm(urlOfForm)
	{
		var dlg = dijit.byId('processingDialog');
		dlg.closeButtonNode.style.display='none';
		dlg.show();
		dojo.xhrGet( {
	     url: urlOfForm,
	     handleAs: "text", 
	     preventCache: true,
	     load: function(response){ 
			dojo.style('openEHRContainer','display','none');
			dijit.byId('openEHRContainer').setContent(response);
			init();
			dojo.style('openEHRContainer','display','inline');
			dlg.hide(); 
			},
		error: function (error) {
                console.error ('Error: ', error);
        }
	   });	
	}
	
	 
		
	function getArchetypeFormToEdit(sessionId, urlOfForm)
	{
		var contents = [];
		contents['sessionId'] = sessionId;
		contents['contextId'] = dojo.byId('contextId').innerHTML;
		contents['loadForEdit'] = 'true';
		var dlg = dijit.byId('processingDialog');
		dlg.closeButtonNode.style.display='none';
		dlg.show();
		dojo.xhrGet( {
	     url: urlOfForm,
	     handleAs: "text", 
	     preventCache: true,
	     content:contents,
	     load: function(response){ 
			dlg.hide();
			dojo.style('openEHRContainer','display','none');
			dijit.byId('openEHRContainer').setContent(response);
			init();
			dojo.style('openEHRContainer','display','inline');
			
			},
		error: function (error) {
                console.error ('Error: ', error);
        }
	   });	
	}
   
   function formSubmit(event){
	   	for(var i = 0 ; i < mockFuncMap.length; i++){
	   		mockFuncMap[i]['mockFunc']( mockFuncMap[i]['dojoWidget'] ); //invoke corresponding mock functions  	
	   	}
		event.target.form.submit(); //event.target - submit button
   }

   function _filterJsf(node){
   		var result = [];
   		var jsfType = node.getAttribute('jsfType');
   		if(!jsfType){ // default is single one
   			result.push(node);
   		}else if('radio' == jsfType){ //special case for radio and checkbox 
   			result = result.concat(_getRadioCheckBoxJSFWidgets(node));
   		}
   		return result;
   }
   
   function _mockSubmitInput(dojoWidget){
		//var index = dojoWidget.id.lastIndexOf(":");
		//var targetId = (index < 0 ? dojoWidget.id : dojoWidget.id.substring(index+1)) + MOCK_POSTIFX;
		//var mockedSubmit = dojo.byId(targetId);
	   var mockedSubmit = dojo.byId(dojoWidget.id);//added by sa
		mockedSubmit.name = dojoWidget.id;
		mockedSubmit.value = dojoWidget.label;	    
   }
         
   function _mockSelect(dojoWidget){
		var mockedSelect = dojo.byId(dojoWidget.id);
		mockedSelect.value = (dojoWidget.item) ? dojoWidget.item.value : dojoWidget.store.root.value;
   }
   
   function _getJsfWidgets(node){
   		var widgets = [];
		var childNodes = node.childNodes;
		for(var i = 0; i < childNodes.length; i++){
			if(1 == childNodes[i].nodeType){
				widgets.push(childNodes[i]);
			}
		}			
		return widgets;		
   }
   
   function _getRadioCheckBoxJSFWidgets(node /* table */){
		var reslutList = dojo.query('label', node);
		return reslutList;
   }
