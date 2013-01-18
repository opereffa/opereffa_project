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
package uk.ac.ucl.chime.service.bosphorus;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class ThreadLocalZMQContext {
	
	private static ThreadLocal contxt = new ThreadLocal()  ;
	private static ThreadLocal socket = new ThreadLocal();
	private static ThreadLocal<String> targetAdr = new ThreadLocal<String>();
	private static ThreadLocal jc = new ThreadLocal<JAXBContext>();
	private static ThreadLocal marshaller = new ThreadLocal<Marshaller>();
	
	private static void Init(){
		if(contxt.get() == null || targetAdr.get() == null || targetAdr.get() == ""){
			contxt.set(ZMQ.context (1));
			targetAdr.set("tcp://127.0.0.1:5560");
			socket.set(((Context)contxt.get()).socket(ZMQ.REQ));
			((Socket)socket.get()).connect(targetAdr.get());
			
			try {
				jc.set(JAXBContext.newInstance("org.openehr.schemas.v1"));
				marshaller.set(((JAXBContext)jc.get()).createMarshaller());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          

	        //Create marshaller
//	        Marshaller m = jc.createMarshaller();
		}
	}
	
	public static Context getCtx(){
		Init();
		return (Context) contxt.get();
	}
	
	public static String getTargetAdr(){
		Init();
		return targetAdr.get();
	}
	
	public static Socket getSocket(){
		Init();
		return (Socket) socket.get();
		
	}
	
	public static Marshaller getMarshaller(){
		Init();
		return (Marshaller) marshaller.get();
	}

}
