/*
 * Copyright (C) 2019-2021 Orchidware Studios LLC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
