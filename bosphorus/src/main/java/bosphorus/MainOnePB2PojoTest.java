package bosphorus;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.google.protobuf.InvalidProtocolBufferException;

import bosphorus.Aom.RequestHeader;

public class MainOnePB2PojoTest {

//	c:\\tmp\\expritemalts.pb
	
	/**
	 * @param args
	 * @throws InvalidProtocolBufferException 
	 * @throws Exception 
	 */ 
	public static void main(String[] args) throws InvalidProtocolBufferException, Exception {
		testSerializedWrapperRead();
//		testSerializedWrapperEXPITEMALTS();
//		testSerializedCStringRead();
//		testSerializedCStringPureRead();
	}

	private static void testSerializedCStringPureRead() throws Exception {
		try{
		File file = new File("c:\\tmp\\cstringpure.pb");
		FileInputStream iostr = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		iostr.read(bytes);
		String s = new String(bytes);
		bosphorus.Aom.CSTRING.Builder cstringBuilderMain = bosphorus.Aom.CSTRING.newBuilder();
		cstringBuilderMain.mergeFrom(s.getBytes());
		System.out.println(cstringBuilderMain.getPattern());
//		s = "<serialized_type:C_STRING>" + s;
		System.out.println(s);
//		byte[] bytes2 = s.substring("<serialized_type:C_STRING>".length()).getBytes();
		byte[] bytes2 = cstringBuilderMain.getPattern().getBytes();
		bosphorus.Aom.CSTRING.Builder cstringBuilder = bosphorus.Aom.CSTRING.newBuilder();
		
			cstringBuilder.mergeFrom(bytes2);
			System.out.println(cstringBuilder.getPattern());
		}catch(Exception ex){
			ex.printStackTrace();
		}

		
	}

	private static void testSerializedCStringRead() throws Exception {
//		cstring.pb
		File file = new File("c:\\tmp\\cstring.pb");
		FileInputStream iostr = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		iostr.read(bytes);
		String s = new String(bytes);
		s = "<serialized_type:C_STRING>" + s;
		System.out.println(s);
		byte[] bytes2 = s.substring("<serialized_type:C_STRING>".length()).getBytes();
		bosphorus.Aom.CSTRING.Builder cstringBuilder = bosphorus.Aom.CSTRING.newBuilder();
		try{
			cstringBuilder.mergeFrom(bytes2);
			System.out.println(cstringBuilder.getPattern());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	private static void testSerializedWrapperRead() throws Exception {
//		File file = new File("c:\\tmp\\pbstringserializetest");
		File file = new File("c:\\tmp\\pbserialized.txt");
//		File file = new File("c:\\tmp\\eiffelpbstring.txt");
		FileInputStream iostr = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		iostr.read(bytes);
		String s = new String(bytes);
		String prefix = "<serialized_type:C_STRING>";
		
				
		
		
		bosphorus.Aom.EXPRLEAF.Builder leafBuilder = bosphorus.Aom.EXPRLEAF.newBuilder();
		bosphorus.Aom.CSTRING.Builder cstringBuilder = bosphorus.Aom.CSTRING.newBuilder();
		
		try{
			leafBuilder.mergeFrom(bytes);
			
//			cstringBuilder.mergeFrom(bytes);
//			System.out.println(cstringBuilder.getPattern());
//			System.out.println(cstringBuilder.getListopen());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
//        System.out.println(header.getUrl());
		
	}
	
	private static void testSerializedWrapperEXPITEMALTS() throws Exception {
//		File file = new File("c:\\tmp\\expritemalts.pb");
//		FileInputStream iostr = new FileInputStream(file);
//		byte[] bytes = new byte[(int) file.length()];
//		iostr.read(bytes);
//		
//		String prefix = "<serialized_type:C_STRING>";
//		
//		
//		bosphorus.Aom.EXPRITEMALTERNATIVES.Builder expritemAlts = bosphorus.Aom.EXPRITEMALTERNATIVES.newBuilder();
//		try{
//			expritemAlts.mergeFrom(bytes);
//			if(expritemAlts.hasExprleaffield()){
//				byte[] bytes2 = expritemAlts.getExprleaffield().getItem().substring("<serialized_type:C_STRING>".length()).getBytes();
//				bosphorus.Aom.CSTRING.Builder cstringBuilder = bosphorus.Aom.CSTRING.newBuilder();
//				cstringBuilder.mergeFrom(bytes2);
//				System.out.println(cstringBuilder.getPattern());
//			}
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
		
//        System.out.println(header.getUrl());
		
	}

}
