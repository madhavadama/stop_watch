package com.arm.exercise.stopwatch.model.ui;

import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.arm.exercise.stopwatch.nls.Messages;

public class SplitExportProgressBar {
	
	private Shell shell;
	private ProgressBar bar;
	private int increment;
	
	Logger logger = Logger.getLogger( SplitExportProgressBar.class.getName() );

	public SplitExportProgressBar(Shell shell) {
		
	    
	 	this.shell = shell;
		
		shell.setBounds(500, 400, 400, 100);
		
		bar = new ProgressBar(shell, SWT.SMOOTH);
		//shell.setSize(250,250);
		bar.setBounds(10,10,200,20);
		
	    shell.setLayout(new GridLayout());
	    
	    bar.setState(SWT.NORMAL);
	    
	    bar.setMinimum(0);
	    bar.setMaximum(100);
	    shell.setText(Messages.StopWatchDialog_45);;
	    
	    shell.setLayout(new GridLayout(1, false));
    	GridData gd_progressbar_shell = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);

    	shell.setLayoutData(gd_progressbar_shell);
    	shell.setForeground(SWTResourceManager.getColor(248, 248, 255));
    	shell.setBackground(SWTResourceManager.getColor(248, 248, 255));
    	
    	GridData gd_progressbar = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);

    	bar.setLayoutData(gd_progressbar);
    	bar.setForeground(SWTResourceManager.getColor(248, 248, 255));
    	bar.setBackground(SWTResourceManager.getColor(248, 248, 255));
    	
    	
    	
	    bar.addPaintListener(new PaintListener() {
	    	  @Override
			public void paintControl(PaintEvent e) {
	    	  DecimalFormat df = new DecimalFormat(Messages.StopWatchDialog_46);
	    	  
	    	  double completion = 
	    	   (bar.getSelection() * 1.0 /
	    	  (bar.getMaximum()-bar.getMinimum()) * 100);
	    	  String string = df.format(completion) + Messages.StopWatchDialog_47;
	    	  Point point = bar.getSize();
	    	  
	    	  FontMetrics fontMetrics = e.gc.getFontMetrics();
	    	  int width = 
	    	   fontMetrics.getAverageCharWidth() * string.length();
	    	  int height = fontMetrics.getHeight();
	    	  e.gc.setForeground
	    	 (shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
	    	  e.gc.drawString
	    	   (string, (point.x-width)/2 , (point.y-height)/2, true);
	    	  }
	    	  });
}

public void open(int splits)
{
	increment=1;
	
	bar.setMaximum(splits);
	bar.setVisible(true);
	shell.pack();
	shell.open();
}

public void incrementProgress()
{
	try {
		shell.getDisplay().asyncExec(() ->{
			if (shell.isDisposed()) {
				return;
			}
			bar.setSelection(bar.getSelection() + increment); 
		});
	}catch(SWTException e) {

		logger.log(Level.SEVERE, Messages.SplitExportProgressBar_0);
		
		return;
	}
}

public void dispose()
{
	try {
		shell.getDisplay().asyncExec(() -> {
			if (!shell.isDisposed()) {
				shell.dispose();
			}});
	}
	catch(SWTException e) {

		logger.log(Level.SEVERE, Messages.SplitExportProgressBar_0);
	}
}

public int getIncrement() {
	return increment;
}

public void setIncrement(int increment) {
	this.increment = increment;
}
	
}
