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

public class MatrixNode 
{
	private String row;
	private String column;
	private String data;
	
	public MatrixNode()
	{
		
	}
	
	public MatrixNode(String row, String column, String data) 
	{
		this.row = row;
		this.column = column;
		this.data = data;
	}
	
	@Override
	public String toString()
	{
		return "(" + this.row + "," + this.column + "," + this.data + ")";
	}

	public String getRow() 
	{
		return row;
	}

	public void setRow(String row) 
	{
		this.row = row;
	}

	public String getColumn() 
	{
		return column;
	}

	public void setColumn(String column) 
	{
		this.column = column;
	}

	public String getData() 
	{
		return data;
	}

	public void setData(String data) 
	{
		this.data = data;
	}
}
