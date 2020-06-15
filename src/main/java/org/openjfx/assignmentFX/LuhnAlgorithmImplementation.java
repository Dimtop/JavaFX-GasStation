package org.openjfx.assignmentFX;

import java.util.ArrayList;

public class LuhnAlgorithmImplementation {

	
	private String cardNumberString;
	private ArrayList<Integer> parsedCardNumber;
	
	
	public LuhnAlgorithmImplementation(String cardNumberStringToSet) {
		this.cardNumberString = cardNumberStringToSet;
		this.parsedCardNumber = this.parseCardNumber();
	}
	
	private ArrayList<Integer> parseCardNumber(){
		
		String[] parsingHelper = this.cardNumberString.split("");
		ArrayList<Integer> parsedCardNumber = new ArrayList<Integer>();
		
		for(int i=0;i<parsingHelper.length;i++) {
			parsedCardNumber.add(Integer.parseInt(parsingHelper[i]));
		}
		
		return parsedCardNumber;
	}
	
	public boolean isCardNumberValid() {
		int sum = 0;
		
		for(int i=0;i<this.parsedCardNumber.size()-1;i++) {
			
	
			if(i%2!=0) {
				if( this.parsedCardNumber.get(i)*2>=10) {
					sum+=this.parsedCardNumber.get(i)*2-9;
				}
				else {
					sum+=this.parsedCardNumber.get(i)*2;
				}
			
			}
			else {
				sum+=this.parsedCardNumber.get(i);
			}
			
	
		}
		
	
		sum+=this.parsedCardNumber.get(this.parsedCardNumber.size()-1);
	
		
		if(sum%10==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public  ArrayList<Integer> getParsedCardNumber() {
		return this.parsedCardNumber;
	}
	
}
