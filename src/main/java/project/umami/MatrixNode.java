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
