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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Matrix visualization
 * 
 * To compile and run: 
 * 		mvn clean compile javafx:run
 */
public class App extends Application 
{
	private static Scene scene;
	private int sceneWidth = 500;
	private int sceneHeight = 500;
	
	private List<MatrixNode> matrixNodes = null;
	
	private LinkedHashMap<Atom, LinkedHashMap<Atom, Float>> matrix =
		new LinkedHashMap<Atom, LinkedHashMap<Atom, Float>>();
	
	private ArrayList<Atom> uniqueAtoms = new ArrayList<Atom>();
	private Pane root = new Pane();
	
	float columnInterval = 0.0f; 
	float rowInterval = 0.0f; 
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		launch();
	}
	
	/**
	 * 
	 */
	public void drawGrid()
	{		
		for (int i = 0; i <= uniqueAtoms.size(); i++)
		{
			Group lineGroup = null;
			
			Line line = new Line(	
				columnInterval * (float)i,
				0,
				columnInterval * (float)i,
				sceneHeight);		
				
			line.setStrokeWidth(1.0f);

			lineGroup = new Group(line);
				
			root.getChildren().add(lineGroup);			
			
			Group textGroup = null;
			
			Text text = new Text();
			text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
			
			if (i > 0)
			{
				text.setText(uniqueAtoms.get(i - 1).toString());
			}
			
			text.setX(columnInterval * (float)i + (columnInterval / 4.0f));
			text.setY(rowInterval / 2.0f);
			text.setFill(Color.BLACK);
			textGroup = new Group(text);
			
			if (textGroup != null)
			{
				root.getChildren().add(textGroup);
			}
		}
		
		for (int i = 0; i <= uniqueAtoms.size(); i++)
		{
			Group lineGroup = null;
			
			Line line = new Line(	
				0, 
				rowInterval * (float)i,
				sceneWidth,
				rowInterval * (float)i);		
				
			line.setStrokeWidth(1.0f);

			lineGroup = new Group(line);
				
			root.getChildren().add(lineGroup);			
			
			Group textGroup = null;
			
			Text text = new Text();
			text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
			
			if (i > 0)
			{
				text.setText(uniqueAtoms.get(i - 1).toString());
			}
			
			text.setX(columnInterval / 4.0f);
			text.setY(rowInterval * (float)i + (rowInterval / 2.0f));
			text.setFill(Color.BLACK);
			textGroup = new Group(text);
			
			if (textGroup != null)
			{
				root.getChildren().add(textGroup);
			}
		}	
	}
	
	/**
	 * 
	 */
	private void drawValues()
	{
		for (int column = 0; column < uniqueAtoms.size(); column++)			
		{
			for (int row = 0; row < uniqueAtoms.size(); row++)
			{ 
				Float value = 
						matrix.get(
							uniqueAtoms.get(column)).
							get(uniqueAtoms.get(row));
				
				if (value != null)
				{
					Group textGroup = null;
					
					Text text = new Text();
					
					// Differentiate the bonds from the electrons
					if (column == row)
					{
						text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));						
					}
					else
					{
						text.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 10));
					}
					
					text.setText(value.toString());								
					
					text.setX((columnInterval * ((float)column + 1)) + (columnInterval / 2.0f));
					text.setY((rowInterval * ((float)row + 1)) + (rowInterval / 2.0f));
					text.setFill(Color.BLACK);
					textGroup = new Group(text);
					
					if (textGroup != null)
					{
						root.getChildren().add(textGroup);
					}
				}		
			}
		}	
	}
	
	/**
	 * 
	 */
	@Override
	public void start(Stage primaryStage) 
	{		
		createModel();
		
		drawGrid();
		drawValues();	
		        		
		Scene scene = new Scene(root, sceneWidth, sceneHeight);

		primaryStage.setTitle("Matrix Viewer");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * 
	 * @param fxml
	 * @throws IOException
	 */
	static void setRoot(String fxml) throws IOException 
	{
		scene.setRoot(loadFXML(fxml));
	}

	/**
	 * 
	 * @param fxml
	 * @return
	 * @throws IOException
	 */
	private static Parent loadFXML(String fxml) throws IOException 
	{
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}
	
	/**
	 * 
	 */	
	private void createModel()
	{
		ObjectMapper mapper = new ObjectMapper();
		
		try 
		{
			InputStream fileInputStream = new FileInputStream("C:\\ProjectUmami\\data\\matrix.json");
						
			matrixNodes = Arrays.asList(mapper.readValue(fileInputStream, MatrixNode[].class));
			fileInputStream.close();
			
			for (MatrixNode matrixNode : matrixNodes) 
			{
				if (matrix.get(matrixNode.getAtom1()) == null)
				{
					matrix.put(matrixNode.getAtom1(), new LinkedHashMap<Atom, Float>());
				}
				
				if (matrix.get(matrixNode.getAtom2()) == null)
				{				
					matrix.put(matrixNode.getAtom2(), new LinkedHashMap<Atom, Float>());
				}							
				
				matrix.get(
					matrixNode.getAtom1()).
					put(matrixNode.getAtom2(), 
					matrixNode.getBondOrder());
			}		
			
	        Set set = matrix.entrySet();
	        Iterator iterator = set.iterator();
	        
	        for (Map.Entry<Atom, LinkedHashMap<Atom, Float>> entry : matrix.entrySet())
	        {   
	            uniqueAtoms.add((Atom)entry.getKey());
	        }		
	        
	        Collections.sort(uniqueAtoms);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
		
		columnInterval = sceneWidth / (uniqueAtoms.size() + 1);
		rowInterval = sceneHeight / (uniqueAtoms.size() + 1);
	}		
}