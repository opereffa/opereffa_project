function testlb(){
	var programmaticSelect;
	try{
		programmaticSelect = dojo.doc.createElement('<select multiple>');
	}
	catch(ex){
		programmaticSelect = dojo.doc.createElement('select');
	}
	programmaticSelect.multiple = true;
	programmaticSelect.size = 10;
	programmaticSelect.selectedIndex = -1;
	dojo.byId('mainform').appendChild(programmaticSelect);
				
	for(var optionsArrInd = 0; optionsArrInd < 5; optionsArrInd++){
		programmaticSelect.options[optionsArrInd] = new Option(optionsArrInd, optionsArrInd);		
	}
	

//	for(var optionsArrInd = 0; optionsArrInd < 5; optionsArrInd++){
//		programmaticSelect.options[optionsArrInd].selected = true;		
//	}
	
}

function tst2(){
	var sb = document.createElement('select');

	sb.multiple=true;

	sb.size = 10;

	for(var i=0; i<10; i++)
	 sb.options[ sb.options.length ] = new Option('Choice_'+i, i);

	document.forms.mainform.appendChild(sb);
	 
	for(var i=0, len=sb.options.length; i<len; i++)
	 sb.options[ i ].selected = true;
	
	for(var i=0, len=sb.options.length; i<len; i++)
		 alert(sb.options[ i ].selected);

}

function testStatic(){
	var existingSelect = dojo.byId('static')	
		
//	var optionsArr = [];
//	for(var optionsArrInd = 0; optionsArrInd < 5; optionsArrInd++){
//		existingSelect.options[optionsArrInd] = new Option(optionsArrInd, optionsArrInd);		
//	}
	//existingSelect.selectedIndex = -1;
//	for(var optionsArrInd = 0; optionsArrInd < 5; optionsArrInd++){
//		programmaticSelect.options[optionsArrInd].selected = false;		
//	}
	for(var optionsArrInd = 0; optionsArrInd < 5; optionsArrInd++){
		existingSelect.options[optionsArrInd].selected = true;		
	}
}