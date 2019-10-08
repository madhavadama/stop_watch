package com.arm.exercise.stopwatch.actions;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import com.arm.exercise.stopwatch.model.Period;
import com.arm.exercise.stopwatch.model.ui.SplitExportProgressBar;
import com.arm.exercise.stopwatch.nls.Messages;

public class SplitsExportThreadExecutor {

	//Creating only Executor Thread pool with Single Thread to simulate the progress bar
	
	private ExecutorService executorService;
	private SplintWriter splintWriter;
	private String saveFile;
	private SplitExportProgressBar splitExportProgressBar;
	private int threadPoolSize = 1;
	private long splitWorkerDelay = 500; //default 500 milli seconds
	
	

	Logger logger = Logger.getLogger( SplitsExportThreadExecutor.class.getName() );
	
	
	public SplitsExportThreadExecutor(SplitExportProgressBar splitExportProgressBar, 
			                           String saveFile) {
		
		
		splintWriter = SplintWriter.getInstance();
		this.splitExportProgressBar = splitExportProgressBar;
		this.saveFile = saveFile;
	}
	
	public void doExport(List<Period>  splits, int size) throws IOException
	{
		
		// Thread count to be 1, to preserve the order of Splits while writing to file
		executorService = Executors.newFixedThreadPool(threadPoolSize);
		splintWriter.openSplitWriter(saveFile);
		splintWriter.setSplitExportProgressBar(splitExportProgressBar);
		
		
		for (int i = 0; i < size; i++) {
			
			String splitContent = (i+1) +Messages.SplitsExportWorker_0+splits.get(i).toString();
			Runnable worker = new SplitsExportWorker(splintWriter, splitContent, splitWorkerDelay );
			executorService.execute(worker);
            
		}
		
		executorService.shutdown();
		
		while (!executorService.isTerminated()) {
        	
		}
	}

	public void complete() {
		splintWriter.complete();
		
	}
	public int getThreadPoolSize() {
		return threadPoolSize;
	}

	public void setThreadPoolSize(int threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
	}
	public long getSplitWorkerDelay() {
		return splitWorkerDelay;
	}

	public void setSplitWorkerDelay(long splitWorkerDelay) {
		this.splitWorkerDelay = splitWorkerDelay;
	}
	
	
}
