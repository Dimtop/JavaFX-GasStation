package org.openjfx.assignmentFX;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FinalScene extends ProgramScene  {
	
	public static Scene getSceneContent(Stage stage) {
		//LAYOUT
        VBox baseContainer = new VBox();
        baseContainer.setSpacing(1);
  
        baseContainer.getStyleClass().add("baseContainer");
       
        //PROMT
        Label finalPromt = new Label("Thanks for using our software." + '\n' + "You can get your receipt at the same folder with the executable.");
        baseContainer.setMargin(finalPromt,new Insets(10,10,10,10));
        finalPromt.getStyleClass().add("finalPromt");
        
        
       
    
        
      //retrieving the observable list of the VBox 
        ObservableList list = baseContainer.getChildren(); 
        
        //Adding all the nodes to the observable list 
        list.addAll(finalPromt);
        
        var scene = new Scene(baseContainer, 640, 480);

   
      
        
        return scene;
	}
		

}
