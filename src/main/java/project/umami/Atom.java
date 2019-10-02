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

import java.util.Comparator;

public class Atom implements Comparator<Atom>, Comparable<Atom>
{
	private int id;
	private String element;
	
	/**
	 * 
	 */
	public Atom()
	{
		
	}
	
	/**
	 * 
	 * @param id
	 * @param element
	 */
	public Atom(int id, String element)
	{
		this.id = id;
		this.element = element;
	}
	
	@Override
	public boolean equals(Object o) 
	{
		if (this == o)
		{
			return true;			
		}
		
		if (o == null || getClass() != o.getClass()) 
		{
			return false;
		}
		
		Atom atom = (Atom)o;
		
		if ((this.id == atom.id) && (this.element.compareTo(atom.element) == 0))
		{
			return true;
		}

		return false;
	}
	
	@Override
	public int compareTo(Atom atom) 
	{
		int retVal = 0;
		
		if ((this.id == atom.id) && (this.element.compareTo(atom.element) == 0))
		{
			retVal = 0;
		}
		else
		{
			if (this.id == atom.id) 
			{
				retVal = this.element.compareTo(atom.element);
			}
			else
			{
				retVal = this.id > atom.id ? 1 : -1;
			}
		}
		
		return retVal;
	}
	
	/**
	 * 
	 */
	public int hashCode()
	{
		return this.id;
	}
	
	/**
	 * 
	 */
	@Override
	public int compare(Atom atom1, Atom atom2)
	{
		int retVal = 0;
		
		if ((atom1.id == atom2.id) && (atom1.element.compareTo(atom2.element) == 0))
		{
			retVal = 0;
		}
		else
		{
			if (atom1.id == atom2.id) 
			{
				retVal = atom1.element.compareTo(atom2.element);
			}
			else
			{
				retVal = atom1.id > atom2.id ? 1 : -1;
			}
		}
		
		return retVal;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getId() 
	{
		return id;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setId(int id) 
	{
		this.id = id;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getElement() 
	{
		return element;
	}
	
	/**
	 * 
	 * @param element
	 */
	public void setElement(String element) 
	{
		this.element = element;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString()
	{
		return "" + this.id + " " + this.element;
	}
}
