package edu.Duquesne.Security.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.eclipse.jface.dialogs.MessageDialog;

public class CheckIfFake extends MainMenu{
	private int allCapsWordCount = 0;
	

	public void beginProcess() {
		checkForCaps();
		updateResult();
		
	}
	

	private boolean checkForCaps(){
		Scanner scan = null;
		boolean result = false;
		try {
	        scan = new Scanner(new File(sourcePath));
	    } catch (FileNotFoundException e) {
	    	MessageDialog.openError(shell, "Error", "The file" + sourcePath + " does not exist.");
	    	return false;
	    }
	    try{ while (scan.hasNextLine()) {
	            String line = scan.next();
	            StringTokenizer st = new StringTokenizer(line);

	            while(st.hasMoreTokens()){
	                    String a = st.nextToken();
	                    if(a.equals(a.toUpperCase())){
	                       allCapsWordCount++;
	                       result = true;}
	                    }}}
	            catch(NoSuchElementException ex){}
	    scan.close();
	    return result;
	}
	
	private void updateResult(){
		if(allCapsWordCount > 3){
			answer = "This review appears to be real.";
			red = 2; green = 136; blue = 59;
			}
		else{
			answer = "This review appears to be fake.";
			red = 205;
			}
		
	}
	
}
