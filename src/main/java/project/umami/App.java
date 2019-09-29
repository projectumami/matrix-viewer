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
	
	private float rowInterval = 0.0f;
	private float columnInterval = 0.0f;
	
	private LinkedHashMap<String, HashMap<String, String>> matrix =
		new LinkedHashMap<String, HashMap<String, String>>();
	
	private ArrayList<String> values = new ArrayList<String>();
	
	private int numColumns = 0; // 20;
	private int numRows = 0; // 20;
	
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
//		createMatrix();
		

		Pane root = new Pane();
		
/*		
		int border = 5;

		int previousLevel = -1;
		int currentLevel = 0;

		int column = 0;

		for (TreeTableNode node : treeTableNodes) 
		{
			currentLevel = node.getLevel();

			if (previousLevel != currentLevel) 
			{
				column = 0;

				previousLevel = currentLevel;
			}

			Group lineGroup = null;
			Group textGroup = null;
			Group textDataGroup = null;
			Rectangle r = null;
						
			// Internal nodes
			Color nodeColor = Color.rgb(0, 0, 200, 0.90);
			
			
			int myId = node.getChildId();
			HashMap<Integer, Integer> myChildren = adjacencyMatrix.get(myId);
			
			if (myChildren != null)
			{
				if (myChildren.keySet().size() == 1)
				{
					Set<Integer> children = myChildren.keySet();
					
					Iterator<Integer> it = children.iterator(); 
					
					if (it.hasNext())					
					{
						Integer childId = it.next();
						
						HashMap<Integer, Integer> childsChildren = adjacencyMatrix.get(childId);
							
						if (childsChildren == null)
						{
							// Node immediately above a leaf.
							nodeColor = Color.rgb(0, 200, 0, 1.0);									
						}	
					}
				}
			}			
			
			
						
			float width = columnInterval * 0.75f; 
			float height = ((columnInterval > rowInterval) ? rowInterval : columnInterval) * .55f;
			float x = border + column * columnInterval;
			float y = border + currentLevel * rowInterval;

			locations.put(node.getChildId(), new LocationNode(x, y));

			r = new Rectangle();
			r.setX(x);
			r.setY(y);
			r.setWidth(width);
			r.setHeight(height);
			r.setArcWidth(10);
			r.setArcHeight(10);

			LocationNode parentLocation = locations.get(node.getParentId());

			Line line = new Line(
					x + width / 2.0f, 
					y, 
					parentLocation.getX() + width / 2.0f,
					parentLocation.getY() + height);
			
			line.setStrokeWidth(1.0f);

			lineGroup = new Group(line);

			Text text = new Text();
			text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
			text.setText(Integer.toString(node.getChildId()));
			text.setX(x + width * 0.05f);
			text.setY(y + height * 0.4f);
			text.setFill(Color.YELLOW);
			textGroup = new Group(text);
			
			Text textData = new Text();
			textData.setFont(Font.font("verdana", FontWeight.THIN, FontPosture.REGULAR, 10));
			textData.setText(node.getData());
			textData.setX(x + width * 0.05f);
			textData.setY(y + height * 0.75f);
			textData.setFill(Color.BEIGE);
			textDataGroup = new Group(textData);
			
			r.setStrokeWidth(2.0);	
			r.setStroke(Color.GRAY);
			
			if (node.getNumChildren() == 0 &&
				node.getChildId() != 0) 
			{
				if (node.getData().compareTo("<epsilon>") == 0)
				{
					// Epsilon node
					r.setFill(Color.rgb(25, 25, 25, 1.0));
				}				
				else
				{
					// Leaf node
					r.setFill(Color.rgb(255, 0, 0, 1.0));
				}
			} 
			else 
			{
				r.setFill(nodeColor); 
			}
			
			column++;

			if (r != null)
			{
				root.getChildren().add(r);
			}
			
			if (lineGroup != null)
			{
				root.getChildren().add(lineGroup);
			}

			if (textGroup != null)
			{
				root.getChildren().add(textGroup);
			}
			
			if (textDataGroup != null)
			{
				root.getChildren().add(textDataGroup);
			}

			if (lineGroup != null)
			{
				lineGroup.toBack();
			}
		}

*/
		float columnInterval = sceneWidth / numColumns;
		float rowInterval = sceneHeight / numRows;
		
		for (int i = 0; i <= numColumns; i++)
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
		
		for (int i = 0; i <= numRows; i++)
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
		
		
/*		
		for (Map.Entry<String, LinkedHashMap<String, Integer>> column : matrix.entrySet())
		{
			LinkedHashMap<String, Integer> rows = column.getValue();
			
			for (Map.Entry<String, Integer> row : rows.entrySet())
			{ 
				retVal.add(
					new MatrixNode(
						column.getKey(), 
						row.getKey(), 
						row.getValue()));
			}			
		}
*/
		
//		if (matrix.get(key))
/*		
		for (int j = 0; j < numRows; j++)
		{
			Group lineGroup = null;
				
			Line line = new Line(					
				x + width / 2.0f, 
				y, 
				parentLocation.getX() + width / 2.0f,
				parentLocation.getY() + height);		
				
			line.setStrokeWidth(1.0f);

			lineGroup = new Group(line);
				
			root.getChildren().add(lineGroup);
		}
*/		
		

		

		
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
/*	
	private void createMatrix()
	{
		for (TreeTableNode node : treeTableNodes)
		{
			HashMap<Integer, Integer> parent = adjacencyMatrix.get(node.getParentId());
			
			if (parent == null)
			{
				adjacencyMatrix.put(node.getParentId(), new HashMap<Integer, Integer>());
				parent = adjacencyMatrix.get(node.getParentId());				
			}
			
			parent.put(node.getChildId(), 1);			
		}
	}
*/	
	
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
				
				matrix.get(matrixNode.getColumn()).
					put(matrixNode.getRow(), matrixNode.getData());
			}		
			
	        Set set = matrix.entrySet();
	        Iterator iterator = set.iterator();

	        while (iterator.hasNext()) 
	        {
	            Map.Entry item = (Map.Entry) iterator.next();

	            values.add((String)item.getKey());
//	            System.out.println("Key = " + item.getKey() + " Value = " + item.getValue());
	        }		
	        
	        numRows = values.size();
	        numColumns = values.size();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}	
	}		
}