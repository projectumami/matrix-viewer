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
 * Parse Tree visualization
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
	
	private LinkedHashMap<String, HashMap<String, String>> matrix =
		new LinkedHashMap<String, HashMap<String, String>>();
	
	private ArrayList<String> values = new ArrayList<String>();
	
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
	@Override
	public void start(Stage primaryStage) 
	{		
		createModel();

		Pane root = new Pane();

		float columnInterval = sceneWidth / (values.size() + 1);
		float rowInterval = sceneHeight / (values.size() + 1);
		
		for (int i = 0; i <= values.size(); i++)
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
				text.setText(values.get(i - 1));
			}
			
			text.setX(columnInterval * (float)i + (columnInterval / 2.0f));
			text.setY(rowInterval / 2.0f);
			text.setFill(Color.BLACK);
			textGroup = new Group(text);
			
			if (textGroup != null)
			{
				root.getChildren().add(textGroup);
			}
		}
		
		for (int i = 0; i <= values.size(); i++)
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
				text.setText(values.get(i - 1));
			}
			
			text.setX(columnInterval / 2.0f);
			text.setY(rowInterval * (float)i + (rowInterval / 2.0f));
			text.setFill(Color.BLACK);
			textGroup = new Group(text);
			
			if (textGroup != null)
			{
				root.getChildren().add(textGroup);
			}
		}	
		        
		for (int column = 0; column < values.size(); column++)			
		{
			for (int row = 0; row < values.size(); row++)
			{			
				Group textGroup = null;
				
				Text text = new Text();
				text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
				
				text.setText(
					matrix.get(
						values.get(column)).get(values.get(row)));
				
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
		
		Scene scene = new Scene(root, sceneWidth, sceneHeight);

		primaryStage.setTitle("Treeviewer");
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
				System.out.println(matrixNode.toString());
				
				if (matrix.get(matrixNode.getColumn()) == null)
				{
					matrix.put(matrixNode.getColumn(), new LinkedHashMap<String, String>());
				}
				
				if (matrix.get(matrixNode.getRow()) == null)
				{
					matrix.put(matrixNode.getRow(), new LinkedHashMap<String, String>());
				}				
				
				matrix.get(matrixNode.getColumn()).
					put(matrixNode.getRow(), matrixNode.getData());
			}		
			
	        Set set = matrix.entrySet();
	        Iterator iterator = set.iterator();

	        while (iterator.hasNext()) 
	        {
	            Map.Entry item = (Map.Entry) iterator.next();

	            values.add((String)item.getKey());
	            System.out.println("Key = " + item.getKey() + " Value = " + item.getValue());
	        }		
	        
	        Collections.sort(values);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}		
}