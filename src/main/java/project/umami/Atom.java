/*
Copyright 2019 Richard Bernardino

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

public class Atom implements Comparable<Atom>
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
	
	/**
	 * 
	 */
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
