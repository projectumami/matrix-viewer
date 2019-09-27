/*
Copyright 2019 Orchidware Studios LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package project.umami;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import project.umami.parser.Parser;

public class BondElectronMatrix 
{	
//    private static final Logger logger = LogManager.getLogger(BondElectronMatrix.class);
    
	private LinkedHashMap<Integer, LinkedHashMap<Integer, Integer>> bonds = 
		new LinkedHashMap<Integer, LinkedHashMap<Integer, Integer>>(); 
	
	/**
	 * 
	 */
	public BondElectronMatrix()
	{
		
	}
	
	/**
	 * 
	 * @param src
	 * @param dest
	 * @param bondOrder
	 */
	public void createBond(Atom src, Atom dest, int bondOrder)
	{
//		logger.info("CONNECTION TABLE createBond");
	
//		logger.debug("createBond " + src.toString() + " " + dest.toString() + " " + bondOrder);
		
		int index1 = src.getId();
		int index2 = dest.getId();
		
		if (!bonds.containsKey(index1))
		{
			bonds.put(index1, new LinkedHashMap<Integer, Integer>());
		}
		
		if (!bonds.containsKey(index2))
		{
			bonds.put(index2, new LinkedHashMap<Integer, Integer>());
		}
		
		bonds.get(index1).put(index2, bondOrder);		
	}
	
	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		
		for (Map.Entry<Integer, LinkedHashMap<Integer, Integer>> column : bonds.entrySet())
		{
			LinkedHashMap<Integer, Integer> rows = column.getValue();
			
			for (Map.Entry<Integer, Integer> row : rows.entrySet())
			{ 
				buffer.append("[" + column.getKey() + "," + row.getKey() + "," + row.getValue() + "]");
			}			
		}
		
		return buffer.toString();
	}
}
