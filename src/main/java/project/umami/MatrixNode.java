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

public class MatrixNode implements Comparable<MatrixNode>
{
	private Atom atom1;
	private Atom atom2;
	private float bondOrder;
	
	/**
	 * 
	 */
	public MatrixNode()
	{
		
	}
	
	/**
	 * 
	 * @param atom1
	 * @param atom2
	 * @param bondOrder
	 */
	public MatrixNode(Atom atom1, Atom atom2, float bondOrder) 
	{
		this.atom1 = atom1;
		this.atom2 = atom2;
		this.bondOrder = bondOrder;
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
		
		MatrixNode matrixNode = (MatrixNode)o;
		
		if ((this.atom1.compareTo(matrixNode.getAtom1()) == 0) &&
				(this.atom2.compareTo(matrixNode.getAtom2()) == 0) &&
				this.bondOrder == matrixNode.getBondOrder())
		{
			return true;
		}

		return false;
	}
	
	/**
	 * 
	 * @param atom
	 * @return
	 */
	public int compareTo(MatrixNode matrixNode)
	{
		int retVal = 0;
		
		if ((this.atom1.compareTo(matrixNode.getAtom1()) == 0) &&
			(this.atom2.compareTo(matrixNode.getAtom2()) == 0) &&
			this.bondOrder == matrixNode.getBondOrder())
		{
			retVal = 0;
		}
		else
		{
			retVal = -1;
		}
		
		return retVal;
	}
	
	public int compare(MatrixNode matrixNode1, MatrixNode matrixNode2)
	{
		int retVal = 0;
		
		if ((matrixNode1.atom1.compareTo(matrixNode2.getAtom1()) == 0) &&
			(matrixNode1.atom2.compareTo(matrixNode2.getAtom2()) == 0) &&
			matrixNode1.bondOrder == matrixNode2.getBondOrder())
		{
			retVal = 0;
		}
		else
		{
			retVal = -1;
		}
		
		return retVal;
	}
	
	/**
	 * 
	 * @return
	 */
	public Atom getAtom1() 
	{
		return atom1;
	}

	/**
	 * 
	 * @param atom1
	 */
	public void setAtom1(Atom atom1) 
	{
		this.atom1 = atom1;
	}

	/**
	 * 
	 * @return
	 */
	public Atom getAtom2() 
	{
		return atom2;
	}

	/**
	 * 
	 * @param atom2
	 */
	public void setAtom2(Atom atom2) 
	{
		this.atom2 = atom2;
	}

	/**
	 * 
	 * @return
	 */
	public float getBondOrder() 
	{
		return bondOrder;
	}

	/**
	 * 
	 * @param bondOrder
	 */
	public void setBondOrder(float bondOrder) 
	{
		this.bondOrder = bondOrder;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString()
	{
		return "(" + this.atom1 + "," + this.atom2 + "," + this.bondOrder + ")";
	}	
}
