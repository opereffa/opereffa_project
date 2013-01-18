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

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.protobuf.DescriptorProtos.FileDescriptorProto;
import com.google.protobuf.DescriptorProtos.FileDescriptorSet;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.Type;
import com.google.protobuf.Descriptors.FileDescriptor;

import uk.ac.ucl.chime.Test.AddressBook;

public class MainOne {

	public static Map<String, PBNode> typesMap = new HashMap<String, PBNode>();
	public static Map<String, MessageNode> messageRegistry = new HashMap<String, MessageNode>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		
//		FileDescriptorSet set = FileDescriptorSet.parseFrom(new FileInputStream("c:\\tmp\\test.proto.desc"));
//		FileDescriptorSet set = FileDescriptorSet.parseFrom(new FileInputStream("c:\\tmp\\othertest\\test2.desc"));
		FileDescriptorSet set = FileDescriptorSet.parseFrom(new FileInputStream("c:\\tmp\\AOM_PROTO\\aom.proto.desc"));
		String baseDir = "C:\\work\\data\\eiffel\\Eiffel-PB-ZMQ-Wrappers\\generated-pb-proxies";
		
		ArrayList<PBNode> rootMessageNodes = new ArrayList<PBNode>();
		
		for(FileDescriptorProto fdp : set.getFileList()){
			FileDescriptor fd = FileDescriptor.buildFrom(fdp, new FileDescriptor[]{});			
			List<Descriptor> rootMessages = fd.getMessageTypes();

			for(Descriptor rootMessage : rootMessages){
				rootMessageNodes.add(traverseDescriptor(rootMessage, fd.getPackage()));
			}
		}		
		
		for(String key : messageRegistry.keySet()){
			MessageNode node = messageRegistry.get(key);
			System.out.println(node.getDescriptor().getFullName());
			EiffelWrapperCreatorVisitor visitor = new EiffelWrapperCreatorVisitor();
			node.accept(visitor);
			visitor.writeOutput(baseDir + "\\PBWrappers\\" + visitor.getMainEiffelType() + ".e");
		}
		
		//now write proj file
		String projTargetName = "protocol_puffer_wrappers";
		String projFilePath = baseDir + "\\PBWrappers\\" + "Protocol_Buffer_Wrappers" + ".ecf";
		String mainClassPath = baseDir + "\\PBWrappers\\" + "MAIN_PB_WRAPPER.e";
		String pbObjWrapperPath = baseDir + "\\PBWrappers\\" + "pb_object_wrapper.e";
		EiffelWrapperGenerator.createProject(projTargetName, projFilePath, mainClassPath, pbObjWrapperPath );
	}
	
	/*
	 * Recursively traverse a Descriptor, which represents a message
	 * and creates a hieararchy of PBNode objects. It adds message types to message
	 * registry as it encounters them
	 * @param pDescriptor The Descriptor instance that provides information about the message type
	 * @param pPackageName The package name used in proto language. 
	 * @return PBNode A PBNode instance, with child nodes
	 */
	public static PBNode traverseDescriptor(Descriptor pDescriptor, String pPackageName) throws Exception{
		//create node for this descriptor
		MessageNode mtypeNode = new MessageNode();
		mtypeNode.setDescriptor(pDescriptor);
		mtypeNode.setPackageName(pPackageName);
		
		//add it to registry
		registerMessageType(pDescriptor, mtypeNode);
		
		List<FieldDescriptor> fields = pDescriptor.getFields();
		if(fields.size() > 0){
			for(FieldDescriptor field : fields){
				if(field.getType().compareTo(Type.ENUM) == 0){
					System.out.println("field type: " + field.getType().name());
					System.out.println(field.getEnumType().getName());
				}
				
				DataNode childNode = new DataNode();
				childNode.setFieldDescriptor(field);
				childNode.setPackageName(pPackageName);
				
				if(field.getType().compareTo(Type.MESSAGE) == 0){//this is a message, so it may have children										
					 //this field is of type of another message, so add a link to that message type node
					 if(!isMessageTypeRegistered(field.getMessageType()))
						 traverseDescriptor(field.getMessageType(), pPackageName);//this will lead to registration of message type					 
					 childNode.setMessageNode(getMessageNode(field.getMessageType().getFullName()));					 
				}
				
				//once child node is created, add it to current node as parent
				mtypeNode.addChild(childNode);					
			}
		}
		return mtypeNode;
	}
	
	public static boolean isMessageTypeRegistered(Descriptor pDescriptor){
		return (messageRegistry.containsKey(pDescriptor.getFullName()));
	}
	
	public static void registerMessageType(Descriptor pDescriptor, MessageNode pMTypeNode){
		if(!messageRegistry.containsKey(pDescriptor.getFullName()))
			 messageRegistry.put(pDescriptor.getFullName(), pMTypeNode);
	}
	
	public static MessageNode getMessageNode(String pFullTypeName){
		return messageRegistry.get(pFullTypeName);
	}
	
	public static void doTestLoad(){
//		String path = "C:\\work\\data\\cpp\\ProtoBufTests\\Debug\\deneme";
//		AddressBook.Builder builder = AddressBook.newBuilder();
//		builder.mergeFrom(new FileInputStream(path));
//		int count = builder.getPersonCount();
//		if(count > 0){
//			String email = builder.getPerson(1).getEmail();
//			System.out.println(email);
//		}

	}

}
