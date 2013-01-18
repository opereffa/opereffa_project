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

public class OperatorKindMapper {
	public static BigInteger getOperatorType(String pOperator){
		if (pOperator.equals("equal"))
			return BigInteger.valueOf(2001);
		else if (pOperator.equals("not_equal"))
			return BigInteger.valueOf(2002); 
		else if (pOperator.equals("less_than_or_equal"))
			return BigInteger.valueOf(2003);
		else if (pOperator.equals("less_than"))
			return BigInteger.valueOf(2004);
		else if (pOperator.equals("greater_than_or_equal"))
			return BigInteger.valueOf(2005);
		else if (pOperator.equals("greater_than"))
			return BigInteger.valueOf(2006);
		else if (pOperator.equals("matches"))
			return BigInteger.valueOf(2007);
		else if (pOperator.equals("not"))
			return BigInteger.valueOf(2010);
		else if (pOperator.equals("and"))
			return BigInteger.valueOf(2011);
		else if (pOperator.equals("or"))
			return BigInteger.valueOf(2012);
		else if (pOperator.equals("xor"))
			return BigInteger.valueOf(2013);
		else if (pOperator.equals("implies"))
			return BigInteger.valueOf(2014);
		else if (pOperator.equals("for_all"))
			return BigInteger.valueOf(2015);
		else if (pOperator.equals("exists"))
			return BigInteger.valueOf(2016);
		else if (pOperator.equals("plus"))
			return BigInteger.valueOf(2020);
		else if (pOperator.equals("minus"))
			return BigInteger.valueOf(2021);
		else if (pOperator.equals("multiply"))
			return BigInteger.valueOf(2022);
		else if (pOperator.equals("divide"))
			return BigInteger.valueOf(2023);
		else if (pOperator.equals("exponent"))
			return BigInteger.valueOf(2024);
		else
			return BigInteger.valueOf(-1);
	}
}
