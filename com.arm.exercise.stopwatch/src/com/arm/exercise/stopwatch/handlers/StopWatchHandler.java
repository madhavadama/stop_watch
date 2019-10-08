package com.arm.exercise.stopwatch.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import com.arm.exercise.stopwatch.model.ui.StopWatchDialog;
import com.arm.exercise.stopwatch.nls.Messages;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class StopWatchHandler extends AbstractHandler {
	
	Logger logger = Logger.getLogger( StopWatchHandler.class.getName() );

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		
        final Shell shell = window.getShell();
        
        String filename = Messages.StopWatchHandler_0;
        Properties properties = new Properties();
    	InputStream inputStream = StopWatchHandler.class.getClassLoader().getResourceAsStream(filename);
		if(inputStream==null){
			logger.log(Level.SEVERE, NLS.bind(Messages.StopWatchHandler_1, filename ));
			
		    return null;
		}

		//load a properties file from class path, inside static method
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			
			logger.log(Level.SEVERE, NLS.bind(Messages.StopWatchHandler_2, filename ));
			
			
			return null;
		}
		

        StopWatchDialog dialog = new StopWatchDialog(shell, properties);
        dialog.open();
        
		
		return null;
	}
}
