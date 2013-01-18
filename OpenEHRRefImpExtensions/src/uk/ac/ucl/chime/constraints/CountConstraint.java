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

import javax.swing.text.html.InlineView;

public class CountConstraint extends ConstraintWithValue {
	protected double minVal = 0;
	protected double maxVal = 0;
	protected double assumedValue;
	protected boolean hasMinVal;
	protected boolean hasMaxVal;
	protected boolean includeMax = true;
	protected boolean includeMin = true;
	
	public ConstraintType getType(){
		return ConstraintType.Count;
	}
	
	public Object getAssumedValue(){
		if (hasAssumedValue)
			return new Long(Double.valueOf(assumedValue).longValue());
		else
			return null;
	}
	
	public void setAssumedValue(Object val){
		//let it blow if the cast does not work
		assumedValue = ((Long)val).doubleValue();
	}
	
	public boolean getHasMinimum(){
		return hasMinVal;
	}
	
	public void setHasMinimum(boolean val){
		hasMinVal = val;
	}
	
	public boolean getHasMaximum(){
		return hasMaxVal;
	}
	
	public void setHasMaximum(boolean val){
		hasMaxVal = val;
	}
	
	public double getMinimumValue(){
		return minVal;
	}
	
	public void setMinimumValue(double val){
		minVal = val;
	}
	
	public double getMaximumValue(){
		return maxVal;
	}
	
	public void setMaximumValue(double val){
		maxVal = val;
	}
	
	public boolean getIncludeMaximum(){
		return includeMax;
	}
	
	public void setIncludeMax(boolean val){
		includeMax = val;
	}
	
	public boolean getIncludeMin(){
		return includeMin;
	}
	
	public void setIncludeMin(boolean val){
		includeMin = val;
	}
	
	public void setFromReal(RealConstraint r){
		//TODO implement...
	}
