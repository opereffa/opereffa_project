<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<style type="text/css">
@import "script/dojo/dijit/themes/tundra/tundra.css";
@import "script/dojo//dojo/resources/dojo.css";
</style>

<style type="text/css">
  @import "script/dojo/dojox/grid/_grid/Grid.css";
  @import "script/dojo/dojox/grid/_grid/nihiloGrid.css";
</style>


<SCRIPT TYPE="text/javascript" SRC="script/dojo/dojo/dojo.js" djConfig="parseOnLoad: true"></script>
<title>Insert title here</title>
<script type="text/javascript">
  dojo.require("dijit.Dialog");
  dojo.require("dijit.form.TextBox");
  dojo.require("dijit.form.TimeTextBox");
  dojo.require("dijit.form.Button");
  dojo.require("dijit.form.DateTextBox");
  dojo.require("dojox.data.JsonRestStore");
  dojo.require("dojox.grid.Grid");
  //dojo.require("dojox.grid._data.model");
  //dojo.require("dojox.grid._data.dijitEditors");
  dojo.require("dojo.parser");
  
</script>
<script type="text/javascript">
  function test(){
	  var store = new dojox.data.JsonRestStore({target: '/DojoRESTTest/resteasy/terminologyrestservice/codes/mapped'});
	  callback = function(items, request){
				var tItemList = store.getValue(items, 'terminologyitemlist');
				var tItems = store.getValue(tItemList, 'terminologyitems');
				var terminologyModel = [];
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
				return terminologyModel;				
		  };
	  store.fetch({onComplete: callback}); 
  }
</script>
</head>
<body class="tundra">
<f:view>

<button dojoType="dijit.form.Button" onclick="test();">Show Dialog</button>



</f:view>
</body>
</html>