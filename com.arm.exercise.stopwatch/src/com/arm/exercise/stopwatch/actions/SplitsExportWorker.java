package com.arm.exercise.stopwatch.actions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.arm.exercise.stopwatch.nls.Messages;

public class SplitsExportWorker implements Runnable {

	private SplintWriter splintWriter;
	private String splintContent;
	private long delay;
	Logger logger = Logger.getLogger( SplitsExportWorker.class.getName() );

	
	public SplitsExportWorker( SplintWriter splintWriter, String splintContent, long delay) {

		this.splintWriter = splintWriter;
		this.splintContent = splintContent;
		this.delay = delay;
		
	}
	
	
	@Override
	public void run() {
		
		try {
			if(splintWriter != null)
				splintWriter.writeSplitTOCsvFile(splintContent,delay);
		} catch (IOException e) {
			logger.log(Level.SEVERE, Messages.SplitsExportWorker_0);
		}

		
	}
}
