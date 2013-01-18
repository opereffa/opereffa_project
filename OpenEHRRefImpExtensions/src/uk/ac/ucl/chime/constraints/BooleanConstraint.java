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
package uk.ac.ucl.chime.constraints;

public class BooleanConstraint extends Constraint {
	
	private boolean boolAssumed;
	private boolean boolHasAssumed;
	private int boolValAsInt;
	
	public ConstraintType geType(){
		return ConstraintType.Boolean;
	}
	
	public Boolean getAssumedValue(){
		if (boolHasAssumed)
			return new Boolean(boolAssumed);
		else
			return null;
	}
	
	public void setAssumedValue(boolean val){
		//TODO implement...
	}
	
	public boolean getTrueAllowed(){
		return (boolValAsInt == 1);
	}
	
	public void setTrueAllowed(boolean val){
		boolValAsInt = 1;
		if (boolHasAssumed && !boolAssumed)
			boolHasAssumed = false;
	}
	
	public boolean getFalseAllowed(){
		return (boolValAsInt == 2);
	}
	
	public void setFalseAllowed(boolean val){
		//TODO check this, looks nonsense in vb impl.
		boolValAsInt = 2;
		if (boolHasAssumed && boolAssumed)
			boolHasAssumed = false;
	}
	
	public boolean getTrueFalseAllowed(){
		return (boolValAsInt == 0);
	}
	
	public void setTrueFalseAllowed(){
		boolValAsInt = 0;
	}
	
	public boolean getHasAssumedValue(){
		return boolHasAssumed;
	}
	
	public void setHasAssumedValue(boolean val){
		boolHasAssumed = val;
	}

}
