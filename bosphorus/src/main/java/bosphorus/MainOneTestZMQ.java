//package bosphorus;
//
//import java.io.IOException;
//
//import org.zeromq.ZMQ;
//
//import com.google.protobuf.InvalidProtocolBufferException;
//
//import bosphorus.Aom.ArchetypeList;
//
//import bosphorus.Aom.Parameters;
//import bosphorus.Aom.RequestHeader;
//import bosphorus.Aom.ResponseHeader;
//import bosphorus.Aom.ResponseHeader.Status;
//
//
//public class MainOneTestZMQ {
//	
//	public static void main(String[] args) throws IOException{
//		if(args.length < 1){
//			System.out.println("Please provide the number of calls to test");
//			System.exit(0);
//		}
//		ZMQ.Context ctx = ZMQ.context (1);
//        ZMQ.Socket s = ctx.socket (ZMQ.REQ);
//        System.out.println("connecting socket");
//        String bindTo = "tcp://127.0.0.1:5560";
//        s.connect(bindTo);        
//        
//        long start = System.nanoTime();
//        for(int i = 0; i < Integer.parseInt(args[0]); i++){
//
//        	String[] archetypeList = getArchetypeList(s);
//                
//	        for(int j = 0; i < archetypeList.length; i++){
//	//        	System.out.println(i);
//	        	CComplexObject archetypeDef = getArchetypeDefinition(s, archetypeList[j]);
//	//            if(archetypeDef != null)
//	//            	System.out.println(archetypeDef.getPath());
//	//            else
//	//            	System.out.println("null definition");
//	        }
//        
//        }
//        
//        System.out.println("total milliseconds for processing " + String.valueOf(Integer.parseInt(args[0])) + " request/responses  " + (System.nanoTime() - start) / 1000000D);
//        System.out.println("Per request/response took " + (System.nanoTime() - start) / 1000000D / Integer.parseInt(args[0]) + " milliseconds");
//        System.out.println("done");                                                
//	}
//
//	public static String[] getArchetypeList(ZMQ.Socket pSocket){
//		try {
//			RequestHeader.Builder bld = RequestHeader.newBuilder();
//	        bld.setUrl("get_archetype_list");
//	        RequestHeader hd = bld.build();        
//	        byte[] requestHeader = hd.toByteArray();
//	        
//	        //empty parameter
//	        Parameters.Builder paramsBld = Parameters.newBuilder();
//	        byte[] parameters = paramsBld.build().toByteArray();
//	        
//	        pSocket.send(requestHeader, ZMQ.SNDMORE);        
//	        pSocket.send(parameters, 0);
//	        
//	        //multipart, so receive both before processing
//	        byte[] respHeader = pSocket.recv(0);
//	        byte[] archList = pSocket.recv(0);
//	        
//	        ResponseHeader.Builder responseBuilder = ResponseHeader.newBuilder();
//	        
//	        ResponseHeader responseHeader = responseBuilder.mergeFrom(respHeader).build();
//	    	if(responseHeader.getResponsestatus().getNumber() != Status.PB_OK_VALUE){
//	    		System.out.println(responseHeader.getContent());
//	    		return null;
//	    	}	    	
//	    	
//	        ArchetypeList.Builder objBuilder = ArchetypeList.newBuilder();
//	    	ArchetypeList obj = objBuilder.mergeFrom(archList).build();
//	    	return (String[])(obj.getNamesList().toArray(new String[obj.getNamesList().size()]));
//	    	
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	public static CComplexObject getArchetypeDefinition(ZMQ.Socket pSocket, String pArchetypeName){
//		try {
//			//header
//			RequestHeader.Builder bld = RequestHeader.newBuilder();
//	        bld.setUrl("get_archetype");
//	        RequestHeader hd = bld.build();        
//	        byte[] requestHeader = hd.toByteArray();
//	        
//	        //parameters
//	        Parameters.Builder paramsBld = Parameters.newBuilder();
//	        paramsBld.addContent(pArchetypeName);
//	        byte[] parameters = paramsBld.build().toByteArray();
//	        
//	        //send header & params
//	        pSocket.send(requestHeader, ZMQ.SNDMORE);        
//	        pSocket.send(parameters, 0);
//	        
//	        //multipart, so receive both before processing
//	        byte[] respHeader = pSocket.recv(0);
//	        byte[] archList = pSocket.recv(0);
//	        
//	        //build response header
//	        ResponseHeader.Builder responseBuilder = ResponseHeader.newBuilder();	        
//	        ResponseHeader responseHeader = responseBuilder.mergeFrom(respHeader).build();
//	    	if(responseHeader.getResponsestatus().getNumber() != Status.PB_OK_VALUE){
////	    		System.out.println(responseHeader.getContent());
//	    		return null;
//	    	}	    	
//	    	
//	    	//build response object
//	        CComplexObject.Builder objBuilder = CComplexObject.newBuilder();
//	        CComplexObject obj = objBuilder.mergeFrom(archList).build();
//	    	return obj;
//	    	
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//}
