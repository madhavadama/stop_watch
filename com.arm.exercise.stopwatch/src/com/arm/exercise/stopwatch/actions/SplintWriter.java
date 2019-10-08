package com.arm.exercise.stopwatch.actions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.arm.exercise.stopwatch.model.ui.SplitExportProgressBar;
import com.arm.exercise.stopwatch.nls.Messages;

public class SplintWriter {

	private static final SplintWriter inst= new SplintWriter();
	
	PrintWriter writer;
	SplitExportProgressBar splitExportProgressBar;
	String saveFile;

	Logger logger = Logger.getLogger( SplintWriter.class.getName() );
	
    private SplintWriter() {
      
	    
    }
    public void openSplitWriter(String saveFile) throws IOException {
    	writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(saveFile), true)));
    	this.saveFile =saveFile;
    }

    public synchronized void writeSplitTOCsvFile(String splitContent, long delay) throws IOException {
					    
    	if( writer != null) {
    	
    		writer.append(splitContent);
    		writer.append('\n');
    	}
    	
			try {
				//To simulate the progress bar
				//adding a delay for the split exporting task
				
				Thread.sleep(delay);
				
				if( splitExportProgressBar != null)
					splitExportProgressBar.incrementProgress();
				
			} catch (InterruptedException e1) {
				
				logger.log(Level.SEVERE, Messages.SplitWriter_0);
			}
			
		    
    }
    public void complete()
    {
    	if( writer != null ) {
    		writer.flush();
        	writer.close();
        	writer = null;
    	}
    }

    public static SplintWriter getInstance() {
        return inst;
    }
    
    public void setSplitExportProgressBar(SplitExportProgressBar splitExportProgressBar) {
    	
    	this.splitExportProgressBar =  splitExportProgressBar;
    }
    
    public SplitExportProgressBar getSplitExportProgressBar() {
    	return this.splitExportProgressBar;
    }

}
