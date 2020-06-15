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

public class CardScene extends ProgramScene {
		
	public CardScene() {
		
	}
	
	public static Scene getSceneContent(Stage stage) {
		//LAYOUT
        VBox baseContainer = new VBox();
        baseContainer.setSpacing(20);
  
        baseContainer.getStyleClass().add("baseContainer");
       
        //PROMT
        Label cardPromt = new Label("Please enter your credit card number:");
        baseContainer.setMargin(cardPromt,new Insets(20,20,20,20));
        cardPromt.getStyleClass().add("cardPromt");
        
        //WARNING
        Label cardWarning = new Label("Please do not include symbols or letters. ONLY numbers.");
        baseContainer.setMargin(cardWarning,new Insets(20,20,20,20));
        cardWarning.getStyleClass().add("cardWarning");
        
        //ERROR
        Label error = new Label("");
        baseContainer.setMargin(error,new Insets(20,20,20,20));
        error.getStyleClass().add("error");
        
        //INPUT
        TextField cardNumber = new TextField();
        baseContainer.setMargin(cardNumber,new Insets(20,20,20,20));
        cardNumber.getStyleClass().add("cardNumber");
        cardNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                if(!newValue.matches("^[0-9]*$")) {
                	cardNumber.setText(oldValue);
                }
            }
        });
        
        
        //SUBMIT
        Button submitButton = new Button("Submit");
        submitButton.getStyleClass().add("submitButton");
        baseContainer.setMargin(submitButton,new Insets(20,20,20,20));
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	if(	cardNumber.getText().equals("")) {
            		error.setText("Please enter a credit card number.");
                	cardNumber.setText("");
            	}
            	else {
            		LuhnAlgorithmImplementation impl = new LuhnAlgorithmImplementation(cardNumber.getText());
                    if(!impl.isCardNumberValid()) {
                    	error.setText("Your credit card number is invalid.");
                    	cardNumber.setText("");
                    }
                    else {
                    	Scene fuelScene = FuelScene.getSceneContent(stage,cardNumber.getText());
                    	fuelScene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
                        stage.setScene(fuelScene);
                    }
            	}
            	
            }
        });
        
        
      //retrieving the observable list of the VBox 
        ObservableList list = baseContainer.getChildren(); 
        
        //Adding all the nodes to the observable list 
        list.addAll(cardPromt,cardWarning,cardNumber,error,submitButton);
        
        var scene = new Scene(baseContainer, 640, 480);

   
      
        
        return scene;
	}

}
