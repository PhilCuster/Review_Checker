package edu.Duquesne.Security.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.eclipse.jface.dialogs.MessageDialog;

public class PythonRunner extends MainMenu{
	private File file = null;
	private String connection = "src/connection.txt", idled = "src/completed.txt", pythonPath = "C:/Python 27", pythonScriptPath = "src/check_review.py";
	private String[] data = new String[10];
	
	public String[] connectToPython(){
		makeFiles();
		startPython();
		idle();
		extractData();
		return data;
	}
	
	private void startPython(){
		try {
			//ProcessBuilder pb = new ProcessBuilder(pythonScriptPath, pythonPath);
			//Process p = pb.start();
			String[] givePerms = {pythonScriptPath, "chmod a+x"};
			String[] command = {pythonScriptPath, "python3"};
			Process p = Runtime.getRuntime().exec(givePerms);
			try {
				p.waitFor();
			} catch (InterruptedException e) {
			}
			p = Runtime.getRuntime().exec(command);
			extractData(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void idle(){
		boolean sleep = true;
		File waiting = new File(idled);
		while(sleep){
			sleep = waiting.exists();
		}
		waiting.delete();
	}
	
	
	private void makeFiles(){
	try {
	      file = new File(connection);
	      file.createNewFile();
	      FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.flush();
			bw.write(sourcePath);
			bw.newLine();
			bw.close();
   	} catch (Exception e) {e.printStackTrace();}
	}
	
	private void extractData(Process p){
		BufferedReader in = new BufferedReader(new 

				InputStreamReader(p.getInputStream()));
				String line;
				int position = 0;
				try {
					while ((line = in.readLine()) != null) {
						data[position] = line;
					    position++;
					}
				} catch (IOException e) {}
				try {
					p.waitFor();
				} catch (InterruptedException e) {}
	}
	
	private void extractData(){
	Scanner scan = null;
	try {
        scan = new Scanner(new File(connection));
    } catch (FileNotFoundException e) {
    	MessageDialog.openError(shell, "Error", "Could not connect to python script");
    }
	 try{ 
		 while (scan.hasNextLine()) {
            String line = scan.next();
            line = scan.next();
            StringTokenizer st = new StringTokenizer(line);
            int position = 0;
            while(st.hasMoreTokens()){
                    data[position] = st.nextToken();
                    position++;
                    }
            }}
	 catch(NoSuchElementException ex){}       
	scan.close();

	
}
}
