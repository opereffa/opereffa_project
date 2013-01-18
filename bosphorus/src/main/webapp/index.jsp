<%@page import="java.util.Iterator"%>
<%@page language="java" %>
<%@page import="uk.ac.ucl.chime.bosphorus.messages.ArchetypesList"%>
<%@page import="uk.ac.ucl.chime.bosphorus.client.BosphorusClientFactory"%>
<%@page import="uk.ac.ucl.chime.bosphorus.client.IBosphorusClient"%>
<html>
<body>
<h3>The following is a(n ugly) list of archetypes in the repository <a href="resteasy/openehr/getarchetypeslist">(XML)</a>&nbsp;<a href="resteasy/openehr/getarchetypeslistjson">(Json)</a>&nbsp;<a href="jsonFormattedArchetypeList.jsp">(Json Pretty Print)</a>
<a href="resteasy/openehr/getarchetypeslistyaml">(YAML)</a></h3>
<p>This simple page uses Bosphorus web services to create the following list. The web services for XML and Json output of Archetype parser
 can be accessed simply by calling the URLs, and the links next to Archetype names just do that. The list itself is again served by a web service, in both 
 XML and Json form.</p>
<%!
	IBosphorusClient client = BosphorusClientFactory.getInstance();
	ArchetypesList list = client.getArchetypesList();
	void writeArchetypeNames(javax.servlet.jsp.JspWriter out){
		try{
			Iterator<String> names = list.getName().iterator();
			while(names.hasNext()){
				String aName = names.next();
				out.println(aName + " " + "<a href=\"resteasy/openehr/getarchetype?archetypeName=" + aName + "\">(XML)</a>"
						+ "&nbsp;"
						+ "<a href=\"resteasy/openehr/getarchetypejson?archetypeName=" + aName + "\">(Json)</a>&nbsp;"
					    + "<a href=\"jsonFormatter.jsp?archetypeName=" + aName + "\">(Json Pretty Print)</a>&nbsp;"
					    + "<a href=\"resteasy/openehr/getarchetypeyaml?archetypeName=" + aName + "\">(YAML)</a><br>");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}		
	}
	
	void testGson(){
		
	}
%>

<%writeArchetypeNames(out); %>

</body>
</html>
