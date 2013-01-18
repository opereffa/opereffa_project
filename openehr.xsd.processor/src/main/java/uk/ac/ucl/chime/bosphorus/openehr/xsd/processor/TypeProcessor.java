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
package uk.ac.ucl.chime.bosphorus.openehr.xsd.processor;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.FieldNode;

/*
 * This class contains methods to extract type information from an Initial class
 * The initial class is expected to be a class in the XSD generated namespace
 * The methods in this class traverse the initial class's parent type(s) and 
 * initial class's fields for types. Types of fields are also traversed for field types and
 * their parent types 
 */
public class TypeProcessor {

	public static String packageName = "org.openehr.schemas.v1";
	
	/*
	 * This field is used to avoid infinite recursions. When a type's field type names are 
	 * processed, this HashSet is used to save this information
	 */
	private HashSet<String> fieldParseCompletedNodes = new HashSet<String>();

	static Hashtable<String, JavaTypeFamily> typeH;
	
	public LinkedHashSet<String> traverseParentTypes(String pTypeName) throws Exception{
		LinkedHashSet<String> parentTypes = new LinkedHashSet<String>();
    	Class<?> c = Class.forName(pTypeName);
    	Class<?> parent = c.getSuperclass();
    	if(parent == null || parent.equals(c) || parent.equals(Object.class))
    		return parentTypes;
    	parentTypes.add(parent.getName());
    	parentTypes.addAll(traverseParentTypes(parent.getName()));
    	return parentTypes;    	
    }
    
    public HashSet<String> traverseClassNodeForComplexTypes(ClassNodePB pClassNode) throws Exception{
    	HashSet<String> typeNames = new HashSet<String>();
    	//include the name of the current ClassNodePB 
    	typeNames.add(pClassNode.name.replace('/', '.'));

    	if(fieldParseCompletedNodes.contains(pClassNode.name))
    		return typeNames;
    	
    	ArrayList<ClassReader> classReaders = new ArrayList<ClassReader>();
    	ListIterator<FieldNode> fields = pClassNode.fields.listIterator();
    	while(fields.hasNext()){
    		FieldNode field = fields.next();
    		String typeName = "";

    		//process only complex types and only ones from the xsd generated namespace
    		if(field.desc.startsWith("L")){
    			if(field.desc.replace('/','.').toUpperCase().indexOf(packageName.toUpperCase()) >= 0){
        			typeName = convertByteCodeNametoFriendlyName(field.desc);    			
        		}
        		else if(field.desc.substring(field.desc.lastIndexOf('/') + 1, field.desc.lastIndexOf(';')).toUpperCase().equals("LIST")){
        			String listType = cleanSignature(extractGenericType(field.signature));
        			if(listType.toUpperCase().indexOf(packageName.toUpperCase()) >= 0)
        				typeName = listType;
        		}
        		else{
//        			System.out.println("NonXSD type: " + field.desc);    			
        		}
    		}
    		else{
//    			System.out.println("Primitive type: " + field.desc);
    		}
    		    		
    		if(typeName != ""){
    			typeNames.add(typeName);    			
        		//create a reader to fetch further types from fields of the the current type
    			ClassReader reader = new ClassReader(typeName);
    			classReaders.add(reader);

    			//process parent types in the same way
    			HashSet<String> parentTypeHiear = traverseParentTypes(typeName);
    			for(String parentType : parentTypeHiear){
    				typeNames.add(parentType);
    				
    				ClassReader parentReader = new ClassReader(parentType);
        			classReaders.add(parentReader);
    			}
    		}    		    		
    	}
    	//once the fields of a node are parsed, save it to avoid infinite recursion
    	fieldParseCompletedNodes.add(pClassNode.name);
    	
    	//traversing of fields is over, now walk the hieararchy of the current ClassNodePB
    	
    	HashSet<String> parentsOfCurrentClassNode = traverseParentTypes(pClassNode.name.replace('/', '.'));
		for(String parentType : parentsOfCurrentClassNode){
			typeNames.add(parentType);
			
			ClassReader parentReader = new ClassReader(parentType);
			classReaders.add(parentReader);
		}
    	
    	for(ClassReader reader : classReaders){
    		ClassNodePB childFieldTypeNode = new ClassNodePB();
    		reader.accept(childFieldTypeNode, 0);
    		HashSet<String> childrenTypeNames = traverseClassNodeForComplexTypes(childFieldTypeNode);
    		typeNames.addAll(childrenTypeNames);
    	}
    	return typeNames;
    }
    
    public String convertByteCodeNametoFriendlyName(String pByteCodeName) throws Exception{
    	if(pByteCodeName.startsWith("L"))
    		pByteCodeName = pByteCodeName.replaceFirst("L", "").replace('/', '.').replace(";", "");
    	else
    		throw new Exception("Unexpected format for bytecode name");
    	return pByteCodeName;
    }
    
    public String extractGenericType(String pByteCodeName){
    	String genTypeName = pByteCodeName.substring(pByteCodeName.indexOf("List<L") + 6, pByteCodeName.indexOf(';'));
    	return genTypeName;
    }
    
    
    public PBMessageCreator generatePBMessage(ClassNodePB pClassNode) throws Exception{
    	PBMessageCreator creator = new PBMessageCreator();
    	creator.setMessageName(pClassNode.name.substring(pClassNode.name.lastIndexOf('/') + 1));
    	
    	ListIterator<FieldNode> fieldsIterator = pClassNode.fields.listIterator();    	
		while(fieldsIterator.hasNext()){
			FieldNode fieldNode = fieldsIterator.next();
			
			String fieldType = "";
			String fieldKind = "optional"; //unless there is a required=true annotation in java 
			String fieldName = fieldNode.name.toLowerCase();
			
			if(fieldNode.desc.startsWith("L")){
    			if(fieldNode.desc.replace('/','.').toUpperCase().indexOf(packageName.toUpperCase()) >= 0){
    				String javaFieldType = convertByteCodeNametoFriendlyName(fieldNode.desc);   
    				if(isAbstractType(javaFieldType))//abstract type fields are handled by special container types for possible subtypes
    					javaFieldType = javaFieldType.substring(javaFieldType.lastIndexOf('.') + 1) + "ALTERNATIVES";
    				else
    					javaFieldType = javaFieldType.substring(javaFieldType.lastIndexOf('.') + 1);    				
        			fieldType = javaFieldType;        			
        		}
        		else if(fieldNode.desc.substring(fieldNode.desc.lastIndexOf('/') + 1, fieldNode.desc.lastIndexOf(';')).toUpperCase().equals("LIST")){
        			String collectionType = cleanSignature(extractGenericType(fieldNode.signature));        			
        			String collectionTypeShort = collectionType.substring(collectionType.lastIndexOf('.') + 1);
        			if(collectionType.toUpperCase().indexOf(packageName.toUpperCase()) >= 0){
        				//abstract type fields are handled by special container types for possible subtypes
        				//also, if an abstract type has no descendants, it is NOT handled via a container
        				if(isAbstractType(collectionType) && 
        						getTypeHierarchy().get(collectionType).getChildren() != null && 
        						getTypeHierarchy().get(collectionType).getChildren().length > 0)        					
        					fieldType = collectionTypeShort + "ALTERNATIVES";
        				else
        					fieldType = collectionTypeShort;
        			}
        				
        			else{//collection of a primitive        				
        				fieldType = convertByteCodeTypetoPBType(collectionType);
        			}
        			fieldKind = "repeated";
        		}
        		
        		else{
        			fieldType = convertByteCodeTypetoPBType(fieldNode.desc);    			
        		}
    		}
			else if(fieldNode.desc.startsWith("[")){//this means an array of something
    			fieldType = convertByteCodeTypetoPBType(fieldNode.desc.substring(1));
    			fieldKind = "repeated";//due to array decleration expressed with [
    		}
    		else{    			
    			fieldType = convertByteCodeTypetoPBType(fieldNode.desc);
    		}
			
			
			if(fieldNode.visibleAnnotations == null || fieldNode.visibleAnnotations.size() < 1){
				creator.addField(fieldName, fieldType, fieldKind);
				continue;
			}
				
			ListIterator<AnnotationNode> annotationsIterator = fieldNode.visibleAnnotations.listIterator();
			while(annotationsIterator.hasNext()){					
				AnnotationNode an = annotationsIterator.next();
				if(an.desc.endsWith("XmlElement;")){ //process xmlElement Annotation
					for(int i = 0; i < an.values.size(); i+=2){
						String name = (String) an.values.get(i);
						Object val = an.values.get(i+1);
						
						if(name.equals("name") && val instanceof String){
//							System.out.println(name + " : " + val);
						}
						else if(name.equals("required") && val instanceof Boolean){
//							System.out.println(name + " : " + ((Boolean)val));
							if((Boolean)val && !fieldKind.equals("repeated")){//repeated fields are lists, can't turn them into requied, which would make them single instances...
								fieldKind = "required";
							}
						}else
							System.out.println("WARNING: UNKNOWN NAME/VALUE IN XMLELEMENT ANNOTATION" + 
									"\nClassNode.name: " + pClassNode.name +
									"\n field name: " + name + 
									"\nfield val: " + val.toString());
					}
				}					
			}//done with annotations, now add field to creator
			creator.addField(fieldName, fieldType, fieldKind);			
		}
		return creator;
    }
    
    private boolean isAbstractType(String javaFieldType) throws Exception {
		Class<?> c = Class.forName(javaFieldType);
		return Modifier.isAbstract(c.getModifiers());
	}

	public String convertByteCodeTypetoPBType(String pByteCodeTName){    	
    	if(pByteCodeTName.equals("Ljava/lang/String;"))
    		return "string";
    	else if(pByteCodeTName.equals("java.lang.String"))
    		return "string";
    	else if(pByteCodeTName.equals("java.lang.Integer"))
    		return "int32";
    	else if(pByteCodeTName.equals("java.lang.Float"))//TODO:must add handling float to Bosphorus Eiffel generators
    		return "string";
    	else if(pByteCodeTName.equals("Ljava/lang/Object;"))
//    		return "string";//TODO: MUST HANDLE OBJECT type fields in pb messaging
    		return "RAWDATA";
    	else if(pByteCodeTName.equals("Ljava/lang/Integer;"))
    		return "int32";
    	else if(pByteCodeTName.equals("I"))
    		return "int32";
    	else if(pByteCodeTName.equals("J"))//TODO:must add handling long to Bosphorus Eiffel generators
    		return "int32";
    	else if(pByteCodeTName.equals("Ljava/lang/Boolean;"))
    		return "bool";
    	else if(pByteCodeTName.equals("Z"))
    		return "bool";
    	else if(pByteCodeTName.equals("Ljava/lang/Float;"))//TODO:must add handling float to Bosphorus Eiffel generators
    		return "string";
    	else if(pByteCodeTName.equals("F"))//TODO:must add handling float to Bosphorus Eiffel generators
    		return "string";
    	else if(pByteCodeTName.equals("D"))//TODO:must add handling double to Bosphorus Eiffel generators
    		return "string";
    	else if(pByteCodeTName.equals("Ljava/math/BigInteger;"))//TODO:must add handling biginteger to Bosphorus Eiffel generators
    		return "string";
    	else if(pByteCodeTName.equals("B"))
    		return "string";//TODO:must add handling byte to Bosphorus Eiffel generators
    	else
    		return "UNKNOWN_TYPE_IN_BYTE_CODE_TYPE_TO_PB_TYPE_CONVERSION";
    }
    
    private String cleanSignature(String pSignature){
    	return pSignature.replaceAll(";","").replaceAll("<", "").replaceAll(">", "").replace('/','.');
    }
    
    /*
     * This method uses the type hieararchy to create a set of
     * pb message creator objects for each type in the hieararchy. 
     * It uses the hieararchy to inject inherited fields to child types
     */
    public  Hashtable<String, PBMessageCreator> getMessageCreatorRegistry()
			throws Exception {		
		Hashtable<String, PBMessageCreator> messageCreatorRegistry = new Hashtable<String, PBMessageCreator>();
		Hashtable<String, JavaTypeFamily> typeHierarchy = getTypeHierarchy();
		
		for(String parentType : typeHierarchy.keySet()){
			JavaTypeFamily parentTypeFamily = typeHierarchy.get(parentType);
			if(parentType.equals(packageName + ".ObjectFactory") || parentType.equals(packageName + ".package-info"))
				continue;
			
			//message creator for parent type
			PBMessageCreator parentMsgCreator = null;
			if(!messageCreatorRegistry.containsKey(parentType)){
				parentMsgCreator = getMessageCreatorForType(parentType);
				messageCreatorRegistry.put(parentType, parentMsgCreator);
			}
			else
				parentMsgCreator = messageCreatorRegistry.get(parentType);
			
			//message creators for child types
	    	for(String childType : parentTypeFamily.getChildren()){
	    		//message creator for child type
	    		PBMessageCreator childMsgCreator = null;
	    		if(!messageCreatorRegistry.containsKey(childType)){
	    			childMsgCreator = getMessageCreatorForType(childType);
	    			messageCreatorRegistry.put(childType, childMsgCreator);
	    		}
	    		else
	    			childMsgCreator = messageCreatorRegistry.get(childType);
	    		
	    		//add an instance of parent to child's inheritence fields containers section
	    		//NOT THE PREFERRED METHOD AT THE MOMENT; WE'LL USE FLATTENING OF PARENT TYPES' FIELDS INSTEAD
	    		//childMsgCreator.addInheritedFieldsContainer(parentTypeFamily.getShortTypeName());    		
	    		
	    		List<StringTemplate> fieldsOfParent = parentMsgCreator.getFields();
	    		if(fieldsOfParent != null){//there may be types with no fields at all
	    			Iterator<StringTemplate> fopIterator = fieldsOfParent.iterator();
		    		while(fopIterator.hasNext()){
		    			childMsgCreator.addInheritedField(fopIterator.next());
		    		}
	    		}	    		
	    	}
		}
		return messageCreatorRegistry;
	}        

    public  Hashtable<String, PBAbstractMsgContainerCreator> getAbstractMessageCreatorRegistry() throws Exception{
    	Hashtable<String, PBAbstractMsgContainerCreator> messageCreatorRegistry = new Hashtable<String, PBAbstractMsgContainerCreator>();
		Hashtable<String, JavaTypeFamily> typeHierarchy = getTypeHierarchy();
		for(String typeName : typeHierarchy.keySet()){
			fillRegistryWithAbstractTypes(messageCreatorRegistry, typeName);
		}
		
		return messageCreatorRegistry;
    }

	private void fillRegistryWithAbstractTypes(Hashtable<String, PBAbstractMsgContainerCreator> messageCreatorRegistry,
												String pTypeName) throws Exception {
		
			JavaTypeFamily javaTypeFamily = getTypeHierarchy().get(pTypeName);
			if(pTypeName.equals(packageName + ".ObjectFactory") || pTypeName.equals(packageName + ".package-info"))
				return;
			if(!isAbstractType(pTypeName))
				return;
			if(javaTypeFamily.getChildren() == null || javaTypeFamily.getChildren().length < 1)
				return;//abstract type with no concrete subtypes
			
			String typeNameShort = pTypeName.substring(pTypeName.lastIndexOf('.') + 1);
			PBAbstractMsgContainerCreator creator = new PBAbstractMsgContainerCreator();
			creator.setMessageName(typeNameShort);
			for(String childType: javaTypeFamily.getChildren()){
				String childTypeShort = childType.substring(childType.lastIndexOf('.') + 1);
				if(isAbstractType(childType) && getTypeHierarchy().get(childType).getChildren().length > 0){	
					/*
					 * Do we use another layer of abstraction via another alternatives here? At the moment
					 * the following is disabled, all descendants of the abstract subtype are included anyway
					 */
					//creator.addField( childTypeShort + "ALTERNATIVES", childTypeShort.toLowerCase() + "field");									
				}
				else
					creator.addField(childTypeShort , childTypeShort.toLowerCase() + "field");
			}						
			messageCreatorRegistry.put(pTypeName, creator);
		
	}
    
	public static PBMessageCreator getMessageCreatorForType(String pType) throws Exception{
		ClassReader clsReader = new ClassReader(pType);
	    ClassNodePB clsNode = new ClassNodePB();
	    clsReader.accept(clsNode, 0);
	    TypeProcessor processor = new TypeProcessor();
	    PBMessageCreator msgCreator = processor.generatePBMessage(clsNode);
	    return msgCreator;
	}

	public static void generateMessagesForPBCreators(Hashtable<String, PBMessageCreator> pCreators, Hashtable<String, PBAbstractMsgContainerCreator> pAbstractContainers) throws Exception{
		StringTemplateGroup group = new StringTemplateGroup("PBMessages", "st", DefaultTemplateLexer.class);
		StringTemplate messageST = group.getInstanceOf("pbmessages");
		
		ArrayList<StringTemplate> messages = new ArrayList<StringTemplate>();
	    for(String s : pCreators.keySet()){
	        messages.add(pCreators.get(s).getPBMessage());
	    }
	    
	    ArrayList<StringTemplate> abstractMsgs = new ArrayList<StringTemplate>();
	    if(pAbstractContainers != null){
	    	for(String s : pAbstractContainers.keySet()){
		    	abstractMsgs.add(pAbstractContainers.get(s).getPBMessage());
		    }
	    }
	    
	    messages.addAll(abstractMsgs);
	    messageST.setAttribute("message", messages);
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("st/aomgen.proto")));		
		out.write(messageST.toString().getBytes());
		out.close();
	}

	public static void generateMessagesForTypeNames(HashSet<String> pTypeNames) throws Exception{
		StringTemplateGroup group = new StringTemplateGroup("PBMessages", "st", DefaultTemplateLexer.class);
		StringTemplate messageST = group.getInstanceOf("pbmessages");
		
		ArrayList<StringTemplate> messages = new ArrayList<StringTemplate>();
	    for(String s : pTypeNames){
	        messages.add(TypeProcessor.getMessageCreatorForType(s).getPBMessage());
	    }
	    
	    messageST.setAttribute("message", messages);
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("st/output.txt")));		
		out.write(messageST.toString().getBytes());
		out.close();
	}

	public static Hashtable<String,JavaTypeFamily> buildTypeHierarchy(HashSet<String> pTypeNames, Hashtable<String,JavaTypeFamily> pTypeHierarchy) throws Exception{
		Iterator<String> typeIterator = pTypeNames.iterator();
	    while(typeIterator.hasNext()){
	    	String typeName = typeIterator.next();
	    	JavaTypeFamily family = null;
	    	if(!pTypeHierarchy.containsKey(typeName)){
	    		family = new JavaTypeFamily(typeName);
	    		pTypeHierarchy.put(typeName, family);
	    	}
	    	else
	    		family = pTypeHierarchy.get(typeName);
	    	TypeProcessor extractor = new TypeProcessor();
	    	LinkedHashSet<String> parentsList = extractor.traverseParentTypes(typeName);
	    	Iterator<String> parentsIterator = parentsList.iterator();
	    	while(parentsIterator.hasNext()){
	    		String parentType = parentsIterator.next();
	    		JavaTypeFamily parentTypeFamily = null;
	    		if(!pTypeHierarchy.containsKey(parentType)){
	    			parentTypeFamily = new JavaTypeFamily(parentType);
	    			pTypeHierarchy.put(parentType, parentTypeFamily);
	    		}
	    		else
	    			parentTypeFamily = pTypeHierarchy.get(parentType);
	    		
	    		parentTypeFamily.addChild(typeName);//the underlying children container is a set, so multiple add calls would do no harm        			
	    	}
	    }
	    return pTypeHierarchy;
	}

	public static Hashtable<String, JavaTypeFamily> getTypeHierarchy() throws Exception{
	    	if(TypeProcessor.typeH != null)
	    		return TypeProcessor.typeH;
	    	
	    	//use all classes in the package to build type hieararchy information
	    	Hashtable<String,JavaTypeFamily> typeHieararchy = new Hashtable<String, JavaTypeFamily>();
	    	
	    	ArrayList<String> files = getClassNamesInPackage(packageName);
	    	for(String classname : files){    		
	    		System.out.println(classname);
	//    		ClassReader reader = new ClassReader(ARCHETYPE.class.getName());
	    		TypeProcessor extractor = new TypeProcessor();
	    		ClassReader reader = new ClassReader(packageName + "." + classname);
	            ClassNodePB archetypeClassNode = new ClassNodePB();
	            reader.accept(archetypeClassNode, 0);                
	            HashSet<String> typeNamesFound =  extractor.traverseClassNodeForComplexTypes(archetypeClassNode);
	            Iterator<String> foundNames = typeNamesFound.iterator();
	//            while(foundNames.hasNext()){
	//            	System.out.println("\t\t found types: " + foundNames.next());
	//            }
	            typeHieararchy = TypeProcessor.buildTypeHierarchy(typeNamesFound, typeHieararchy);
	    	}
	    	TypeProcessor.typeH = typeHieararchy;
	    	return TypeProcessor.typeH;
	    }

	public static ArrayList<String> getClassNamesInPackage(String packageName) throws IOException{
        ArrayList<String> names = new ArrayList<String>();
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName.replace('.','/'));
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            File dir = new File(url.getFile());
            for (File f : dir.listFiles()) {
                names.add(f.getName().substring(0,f.getName().lastIndexOf('.')));
            }
        }
        return names;
    }
}
