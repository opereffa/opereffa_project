/*******************************************************************************
 * Copyright 2012 Sevket Seref Arikan, David Ingram
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package uk.ac.ucl.chime.utilities;

public class HTMLText {
	public static String TR_Begin = "<tr>";
	public static String TR_End = "</tr>";
	public static String TD_Begin = "<td>";
	public static String TD_End = "</td>";
	public static String Table_Begin = "<table>";
	public static String Table_End = "</table>";
	public static String Div_Begin = "<div>";
	public static String Div_End = "</div>";
	public static String NewLine = "\n";
	public static String Tab = "\t";
	public static String Div_Dojo_Marker_Begin = "<div jsf2dojo=\"true\" style=\"display:inline;\" >";
	public static String Div_Dojo_Marker_End = "</div>";
	public static String TR_Submit_Button = "<tr>\r\n" + 
			"				<td colspan=\"2\" align=\"right\">\r\n" +  
			"				<div jsf2dojo=\"true\" style=\"display:inline;\" ><h:commandButton id=\"postit\"  type=\"submit\" value=\"#{connectorBean.modeLabel}\" action=\"#{archetypeBinder.performAction}\"/></div>\r\n" + 
			"				</td>\r\n" + 
			"			</tr>";
	
	public static String getTabs(int tabAmount){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tabAmount; i++) {
			sb.append("\t");			
		}
		return sb.toString();
	}
	
	public static String getTDBeginWithColspan(int pColspan){
		String text = "<td colspan=\"" + String.valueOf(pColspan) + "\">";
		return text;
	}
}
