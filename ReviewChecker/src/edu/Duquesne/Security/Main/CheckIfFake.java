package edu.Duquesne.Security.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.eclipse.jface.dialogs.MessageDialog;

public class CheckIfFake extends MainMenu{
	private int allCapsWordCount = 0, wordTotal = 0, totalWordLength = 0, misspelled = 0;
	private boolean caps = false, wordLength = false, misspell = false;

	public void beginProcess() {
		
		PythonRunner pr = new PythonRunner();
		separateData(pr.connectToPython());
		//non python stuff 
		Scanner scan = null;
		try {
	        scan = new Scanner(new File(sourcePath));
	    } catch (FileNotFoundException e) {
	    	MessageDialog.openError(shell, "Error", "The file" + sourcePath + " does not exist.");
	    }
		 try{ 
			 while (scan.hasNextLine()) {
	            String line = scan.next();
	            caps = checkForCaps(line);}}
		 catch(NoSuchElementException ex){}       
		wordLength = wordLength();
		misspell = updateMisspelled();
		updateResult();
		scan.close();
	}
	
	
	private void separateData(String[] data){
		misspelled = Integer.parseInt(data[0]);
		allCapsWordCount = Integer.parseInt(data[1]);
		totalWordLength = Integer.parseInt(data[2]);
		wordTotal = Integer.parseInt(data[3]);
	}
	
	private boolean checkForCaps(String line){
		StringTokenizer st = new StringTokenizer(line);
	            while(st.hasMoreTokens()){
	                    String a = st.nextToken();
	                    wordTotal++;
	                    totalWordLength = totalWordLength + a.length();
	                    if(a.equals(a.toUpperCase())){
	                       allCapsWordCount++;}
	                    }
	            
	  if(allCapsWordCount > 3){return true;}
	  else{return false;}
	}
	
	private boolean wordLength(){
		double avgLength;
		avgLength = totalWordLength/wordTotal;
		if (avgLength > 5){return false;}
		else {return true;}
	}
	
	private boolean updateMisspelled(){
		
		if(misspelled >2){return true;}
		else{return false;}
	}
	
	
	private void updateResult(){
		boolean result;
		int trueTally = 0;
		
		if(caps == true){trueTally++;}
		if(wordLength == true){trueTally++;}
		if(misspell == true){trueTally++;}
		if(trueTally > 1){result = true;}
		else{ result = false;}
		if(result){
			answer = "This review appears to be real.";
			red = 2; green = 136; blue = 59;
		}
		else{
			answer = "This review appears to be fake.";
			red = 205; green = 0; blue = 0;
			}
		
	}
	
	
}
