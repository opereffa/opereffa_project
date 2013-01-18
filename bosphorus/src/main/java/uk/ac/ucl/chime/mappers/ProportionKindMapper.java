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
package uk.ac.ucl.chime.mappers;

import java.math.BigInteger;

public class ProportionKindMapper {
	public static BigInteger getProportionKind(String pKind){
		if(pKind.equals("pk_ratio"))
			return BigInteger.valueOf(0);
		else if (pKind.equals("pk_unitary"))
			return BigInteger.valueOf(1);
		else if(pKind.equals("pk_percent"))
			return BigInteger.valueOf(2);
		else if(pKind.equals("pk_fraction"))
			return BigInteger.valueOf(3);
		else if(pKind.equals("pk_integer_fraction"))
			return BigInteger.valueOf(4);
		else 
			return BigInteger.valueOf(-1);
	}
}
