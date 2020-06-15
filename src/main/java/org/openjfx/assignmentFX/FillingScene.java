package org.openjfx.assignmentFX;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
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


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;  

public class FillingScene extends ProgramScene{
	
    private static Double fuelRate = 0.000;
    private static String fuelName = "PETROL";
    
	public static Scene getSceneContent(Stage stage, FuelType fuelType, String moneyAmount, String cardNumber) {
		
		   switch(fuelType) {
	    	case PETROL:
	    		fuelRate = 1.104;
	    		fuelName = "PETROL";
	    		break;
	    	case OIL:
	    		fuelRate = 1.005;
	    		fuelName = "OIL";
	    		break;
	    	case GAS:
	    		fuelRate = 0.978;
	    		fuelName = "GAS";
	    		break;
	    		
	    }
		
		
		
		
		//LAYOUT
        VBox baseContainer = new VBox();
        baseContainer.setSpacing(5);
  
        baseContainer.getStyleClass().add("baseContainer");
       
        //Indicator
        Label fuelIndicator = new Label("Fuel amount in liters:");
        baseContainer.setMargin(fuelIndicator,new Insets(20,20,20,20));
        fuelIndicator.getStyleClass().add("fuelIndicator");
        
        //FUEL AMOUNT
        Label fuelAmount = new Label("0.000");
        baseContainer.setMargin(fuelAmount,new Insets(20,20,20,20));
        fuelAmount.getStyleClass().add("fuelAmount");
        
        //Indicator
        Label moneyIndicator = new Label("Money amount:");
        baseContainer.setMargin(moneyIndicator,new Insets(20,20,20,20));
        moneyIndicator.getStyleClass().add("moneyIndicator");
        
        //MONEY COST
        Label moneyCost = new Label("0.000");
        baseContainer.setMargin(moneyCost,new Insets(20,20,20,20));
        moneyCost.getStyleClass().add("moneyCost");
        
        
        //TIMER
        Timer timer = new Timer();
        
        
		timer.schedule(new TimerTask() {
		@Override
		  public void run() {
			
			Platform.runLater(new Runnable() {
				@Override
				 public void run() {
					
						if(Double.parseDouble(moneyCost.getText()) >Double.parseDouble(moneyAmount)) {
							timer.cancel();
							moneyCost.setText(moneyAmount);
							DecimalFormat dfFinal = new DecimalFormat("#.###");
							fuelAmount.setText(dfFinal.format(Double.parseDouble(moneyCost.getText())/fuelRate));
							
							Scene finalScene = FinalScene.getSceneContent(stage);
							finalScene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
		                    stage.setScene(finalScene);
						}
						else {
							Double currFuelAmount = Double.parseDouble(fuelAmount.getText());
							Double increasedAmount = currFuelAmount + 0.125;
							Double increasedMoneyAmount = increasedAmount*fuelRate;
							DecimalFormat dfFuel = new DecimalFormat("#.###");
							fuelAmount.setText(dfFuel.format(increasedAmount));
							
							DecimalFormat dfMoney = new DecimalFormat("#.###");
							moneyCost.setText(dfMoney.format(increasedMoneyAmount));
					
						}
						
				
				}
			});
		
		  }
		},500,500);
		
		
        //STOP
        Button stopButton = new Button("Stop");
        stopButton.setMaxWidth(200);
        stopButton.getStyleClass().add("stopButton");
        baseContainer.setMargin(stopButton,new Insets(10,10,10,10));
        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
            		timer.cancel();
            
            		createReceiptFile(moneyCost.getText(),fuelName,fuelAmount.getText(),cardNumber);
            		
            		Scene finalScene = FinalScene.getSceneContent(stage);
					finalScene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());
                    stage.setScene(finalScene);
      
            }
        });
        
        
        
        
      //retrieving the observable list of the VBox 
        ObservableList list = baseContainer.getChildren(); 
        
        //Adding all the nodes to the observable list 
        list.addAll(fuelIndicator,fuelAmount,moneyIndicator,moneyCost,stopButton);
        
  
		
		
        var scene = new Scene(baseContainer, 640, 480);

   
      
        
        return scene;
	}
	
	
	
	private static void createReceiptFile(String moneyCost, String fuelType,String fuelAmount,String cardNumber) {
		   
		SimpleDateFormat formatter= new SimpleDateFormat("HH-mm-ss");
		Date date = new Date(System.currentTimeMillis());
		String dateStr = formatter.format(date).toString();
		
			try {
			   	System.out.println("receipt"+dateStr+".txt");
				   File f = new File("./receipt"+dateStr+".txt");
				   if(!f.exists()){
				     f.createNewFile();
				   }else{
				     System.out.println("File already exists");
				   }
			      FileWriter writer = new FileWriter("./receipt"+dateStr+".txt");
			      writer.write("Time: " + dateStr +'\n');
			      writer.write("Cost: " + moneyCost+" EUR"+'\n');
			      writer.write("Fuel type: " + fuelType +'\n');
			      writer.write("Fuel amount: " + fuelAmount+ " L" +'\n');
			      writer.write("Card last 4 digits: " + cardNumber.substring(cardNumber.length()-4)+'\n');
			      writer.close();
		   } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		   }
	}
}
	
	
		
