package bosphorus;

import javax.xml.bind.annotation.XmlElement;

public abstract class TestParent {
	protected String someVal;
	
	@XmlElement(name="someval")
	public String getSomeVal(){
		return someVal;
	}
	
	public void setSomeVal(String pSomeVal){
		someVal = pSomeVal;
	}

}
