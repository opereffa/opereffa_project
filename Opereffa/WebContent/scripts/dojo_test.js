dojo.require("dijit.form.Button");
dojo.require("dijit.form.ValidationTextBox");
dojo.require("dijit.form.CheckBox");
dojo.require("dijit.form.ComboBox");
dojo.require("dijit.form.Textarea");
dojo.require("dijit.form.FilteringSelect");

dojo.require("dojo.parser");


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

//dojo.addOnLoad(init);

function init(){		
	var jsfContainerList = dojo.query("[jsf2dojo='true']"); // get jsf widgets to be converted to dojo
	
	for(var i = 0 ; i < jsfContainerList.length; i++){
		jsfContainerList[i].style.display = 'inline';
		
		var jsfList = _filterJsf(jsfContainerList[i]);
		
		for(var j = 0; j < jsfList.length; j++){
			var jsfWidgets = _getJsfWidgets(jsfList[j]);//get jsf wrapper node		
			if(!jsfWidgets || jsfWidgets.length <= 0) continue;
			
			for(var n = 0; n < jsfWidgets.length; n++){
				var dojoWidget = null;
				var mockFunc = null;
				var jsfWidget = jsfWidgets[n];
				
				if ('select' == jsfWidget.tagName.toLowerCase()){ // select
					jsfWidget.setAttribute(DOJO_TYPE, TYPE_MAP[jsfWidget.tagName.toLowerCase()]);
					mockFunc = _mockSelect;
					
				}else if('textarea' == jsfWidget.tagName.toLowerCase()){//textarea
					jsfWidget.setAttribute(DOJO_TYPE, TYPE_MAP[jsfWidget.tagName.toLowerCase()]);
					
				}else if('input' == jsfWidget.tagName.toLowerCase()){ // input 
					var type = jsfWidget.type.toLowerCase();
					
					if('submit' == type){
						var params = {label: jsfWidget.value};
						var dojoWidget = new dijit.form.Button(params, jsfWidget.id);
						dojo.connect(dojoWidget, 'onClick', window, 'submitPartialForm');
						mockFunc = _mockSubmitInput;
		
					}else if('text' == type){
						jsfWidget.setAttribute(DOJO_TYPE, TYPE_MAP[type]);
						jsfWidget.setAttribute('promptMessage',"Please Enter your information");
						jsfWidget.setAttribute('required',"true");				
					
					}else if ('checkbox' == type || 'radio' == type){	
						jsfWidget.setAttribute(DOJO_TYPE, TYPE_MAP[type]);
					}
				}
		
				if(!dojoWidget){
					dojoWidget = dojo.parser.parse(jsfList[j])[0];//parse wrapper node
				}
				
				if(dojoWidget && mockFunc){
					mockFuncMap.push({'dojoWidget': dojoWidget, 'mockFunc': mockFunc});
				}
			}
		}
	}
   }

	function submitPartialForm() {
	    dojo.xhrPost ({
	            // The page that parses the POST request
	            url: 'main.jsf',
	   
	            // Name of the Form we want to submit
	            form: 'mainform',
	   
	            // Loads this function if everything went ok
	            load: function (data) {
	                    // Put the data into the appropriate <div>
	                    dojo.byId('container').innerHTML = 'done!';
	                    //destroy all registered widgets here, if you want to reuse this container with these components
	                    //actually, destroy them in every case just to make sure...
	            },
	            // Call this function if an error happened
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
