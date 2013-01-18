<%@page import="org.jboss.resteasy.client.ClientRequest"%>
<%@page import="java.util.Iterator"%>
<%@page language="java" %>
<%@page import="uk.ac.ucl.chime.bosphorus.messages.ArchetypesList"%>
<%@page import="uk.ac.ucl.chime.bosphorus.client.BosphorusClientFactory"%>
<%@page import="uk.ac.ucl.chime.bosphorus.client.IBosphorusClient"%>
<html>
<body>
<h3>Formatted JSON output for <%=request.getParameter("archetypeName") %></h3>

<%!
	
	String getJsonForArchetype(String pArchetypeName){
	try{
		if(pArchetypeName == null || pArchetypeName.equals(""))
			return null;
		ClientRequest request = new ClientRequest("http://localhost/bosphorus/resteasy/openehr/getarchetypejson");
		request.accept("application/json").queryParameter("archetypeName", pArchetypeName);
		String response = request.getTarget( String.class); //get response and automatically unmarshall to a string.		
		String formatted = new org.json.JSONObject(response).toString(4); 
		return formatted;		
	}
	catch(Exception ex){
		ex.printStackTrace();
		return null;
	}
}
%>

<% out.println(getJsonForArchetype(request.getParameter("archetypeName")).replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;")); %>


</body>
</html>
