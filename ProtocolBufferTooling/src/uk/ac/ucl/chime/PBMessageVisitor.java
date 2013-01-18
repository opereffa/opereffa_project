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
package uk.ac.ucl.chime;

public class PBMessageVisitor implements IPBNodeVisitor {

	private StringBuffer _output = new StringBuffer("");
	private int _depth = 0;
	
	@Override
	public void visit(PBNode pNode) {
		System.out.println("Generic pNode visited, should not be used normally");
	}

	@Override
	public void visit(MessageNode pNode) throws Exception {
		System.out.println("visiting " + pNode.getDescriptor().getFullName());
		_depth++;
		
		addLineToOutput("<MessageNode>");
		for(PBNode child : pNode.getChildren()){
			child.accept(this);
		}
		addLineToOutput("</MessageNode>");
		
		_depth--;
	}

	@Override
	public void visit(DataNode pNode) {
		System.out.println("visiting " + pNode.getFieldDescriptor().getName());
		_depth++;

		addLineToOutput("<DataNode  isRepeated=\"" + (pNode.isRepeated() ? "true" : "false") + "\">");
		_depth++;
		addLineToOutput("<name>" + pNode.getFieldDescriptor().getName() + "</name>");
		_depth--;
		addLineToOutput("</DataNode>");
		
		_depth--;

	}
	
	private void addLineToOutput(String pString){
		_output.append(getTabs() + pString + "\n");
	}

	//http://stackoverflow.com/questions/1235179/simple-way-to-repeat-a-string-in-java
	private String getTabs(){
		return String.format(String.format("%%0%dd", _depth), 0).replace("0","\t");
	}

	public String getOutput(){
		return _output.toString();
	}
}
