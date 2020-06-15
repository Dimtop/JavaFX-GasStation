package org.openjfx.assignmentFX;

import java.util.ArrayList;

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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FuelScene extends ProgramScene {
	
	private static ArrayList<Button> buttons = new ArrayList<Button>();
	private static FuelType fuelType = FuelType.PETROL;
	
	public static Scene getSceneContent(Stage stage, String cardNumber) {
		//LAYOUT
        VBox baseContainer = new VBox();
        baseContainer.setSpacing(1);
  
        baseContainer.getStyleClass().add("baseContainer");
       
        //PROMT
        Label fuelPromt = new Label("Please choose the fule type:");
        baseContainer.setMargin(fuelPromt,new Insets(10,10,10,10));
        fuelPromt.getStyleClass().add("fuelPrompt");
        
        
        //PETROL
        Button petrolButton = new Button("Petrol");
        petrolButton.setMaxWidth(200);
        petrolButton.getStyleClass().add("petrolButton");
        buttons.add(petrolButton);
        baseContainer.setMargin(petrolButton,new Insets(10,10,10,10));
        petrolButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            	if(!petrolButton.getStyleClass().contains("pressedButton")) {
	        		for(Button b: buttons) {
	        			b.getStyleClass().remove("pressedButton");
	        			
	        		}
	        		petrolButton.getStyleClass().add("pressedButton");
	        		fuelType = FuelType.PETROL;
            	}
            	else {
            		petrolButton.getStyleClass().remove("pressedButton");
            	}
            }
        });
        //OIL
        Button oilButton = new Button("Oil");
        oilButton.setMaxWidth(200);
        oilButton.getStyleClass().add("oilButton");
        buttons.add(oilButton);
        baseContainer.setMargin(oilButton,new Insets(10,10,10,10));
        oilButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            	if(!oilButton.getStyleClass().contains("pressedButton")) {
	        		for(Button b: buttons) {
	        			b.getStyleClass().remove("pressedButton");
	        		
	        		}
	        		oilButton.getStyleClass().add("pressedButton");
	        		fuelType = FuelType.OIL;
            	}
            	else {
            		oilButton.getStyleClass().remove("pressedButton");
            	}
            }
        });
        //GAS
        Button gasButton = new Button("Gas");
        gasButton.setMaxWidth(200);
        gasButton.getStyleClass().add("gasButton");
        buttons.add(gasButton);
        baseContainer.setMargin(gasButton,new Insets(10,10,10,10));
        gasButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	if(!gasButton.getStyleClass().contains("pressedButton")) {
            		for(Button b: buttons) {
            			b.getStyleClass().remove("pressedButton");
            			
            		}
            		gasButton.getStyleClass().add("pressedButton");
            		fuelType = FuelType.GAS;
            	}
            	else {
            		gasButton.getStyleClass().remove("pressedButton");
            	}
            }
        });
        
        //PROMT
        Label moneyPromt = new Label("Please enter the amount of money:");
        baseContainer.setMargin(moneyPromt,new Insets(10,10,10,10));
        moneyPromt.getStyleClass().add("moneyPrompt");
        
      //WARNING
        Label moneyDecimalWarning = new Label("For decimal values use . (not ,)");
        baseContainer.setMargin(moneyDecimalWarning,new Insets(10,10,10,10));
        moneyDecimalWarning.getStyleClass().add("moneyDecimalWarning");
        
        //INPUT
        TextField moneyAmount = new TextField();
        moneyAmount.setMaxWidth(200);
        baseContainer.setMargin(moneyAmount,new Insets(10,10,10,10));
        moneyAmount.getStyleClass().add("moneyAmount");
        moneyAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
            	 if(!newValue.matches("^[0-9.]*$")) {
            		 moneyAmount.setText(oldValue);
                 }
            }
        });
        
        //WARNING
        Label moneyWarning = new Label("");
        baseContainer.setMargin(moneyWarning,new Insets(10,10,10,10));
        moneyWarning.getStyleClass().add("error");
        
        //START
        Button startButton = new Button("Start");
        startButton.setMaxWidth(100);
        startButton.getStyleClass().add("startButton");
        baseContainer.setMargin(startButton,new Insets(10,10,10,10));
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            	boolean isFuelTypeSelected = false;
            	
            	for(Button b:buttons) {
            		if(b.getStyleClass().contains("pressedButton")) {
            			isFuelTypeSelected = true;
            			break;
            		}
            	}
            	if(moneyAmount.getText().equals("")) {
            		moneyWarning.setText("Please enter the ammount of money.");
            	}
            	else if(!(isFuelTypeSelected)) {
            		moneyWarning.setText("Please select fuel type.");
            	}
            	else {
            		Scene fillingScene = FillingScene.getSceneContent(stage, fuelType, moneyAmount.getText(),cardNumber);
            		fillingScene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
                    stage.setScene(fillingScene);
            	}
            	
            }
        });
        
    
        
      //retrieving the observable list of the VBox 
        ObservableList list = baseContainer.getChildren(); 
        
        //Adding all the nodes to the observable list 
        list.addAll(fuelPromt,petrolButton,oilButton,gasButton,moneyPromt,moneyDecimalWarning,moneyAmount,startButton,moneyWarning);
        
        var scene = new Scene(baseContainer, 640, 480);

   
      
        
        return scene;
	}
		
		
	
	
}
