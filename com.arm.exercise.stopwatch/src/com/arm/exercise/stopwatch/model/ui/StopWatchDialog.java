package com.arm.exercise.stopwatch.model.ui;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

import com.arm.exercise.stopwatch.actions.SplitsExportThreadExecutor;
import com.arm.exercise.stopwatch.model.Period;
import com.arm.exercise.stopwatch.model.StopwatchModel;
import com.arm.exercise.stopwatch.nls.Messages;

public class StopWatchDialog extends Dialog {
	
	Logger logger = Logger.getLogger( StopWatchDialog.class.getName() );
	
	private Composite clockGropu_col0 = null;
	private Composite top = null;
	private Table table = null;
	
	
	
	StopwatchModel stopwatchModel = null;
	private Text text;
	private TableColumn column_1;
	
	private Button btnPauseResume;
	private Button btnStart;
	private Button btnReset;
	private Button btnSplit;
	private Button btnExportSplits;
	private Properties properties;
	
    public StopWatchDialog(Shell parentShell, Properties properties) {
        super(parentShell);
        setBlockOnOpen(false);
        setShellStyle(SWT.SHELL_TRIM | SWT.BORDER);
       
        this.properties =properties;
        stopwatchModel = new StopwatchModel();
        
    }
    
    @Override
    protected void createButtonsForButtonBar(final Composite parent)
    { 
      GridLayout layout = (GridLayout)parent.getLayout();
      layout.marginHeight = 0;
    }

    @Override
    protected Control createDialogArea(Composite parent) {
    	parent.setTouchEnabled(true);
    	parent.setTextDirection(0);
    	parent.setFont(SWTResourceManager.getFont(Messages.StopWatchDialog_0, 12, SWT.BOLD));
    	
    	parent.getShell().setMinimumSize(500, 300); 
    	
        top = new Composite(parent, SWT.NONE);
        top.setBackground(SWTResourceManager.getColor(248, 248, 255));
        GridLayout gl_top = new GridLayout(1, false);
        gl_top.verticalSpacing = 0;
        gl_top.marginHeight = 0;
        top.setLayout(gl_top);
        top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        
        Composite composit_row = new Composite(top, SWT.NONE);
        composit_row.setLayout(new GridLayout(2, false));
        GridData gd_composit_row = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
        gd_composit_row.heightHint = 206;
        gd_composit_row.widthHint = 528;
        composit_row.setLayoutData(gd_composit_row);
        
        
    	
    	clockGropu_col0 = new Composite(composit_row, SWT.NONE);
    	clockGropu_col0.setLayout(new GridLayout(1, false));
    	GridData gd_clockGropu_col0 = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
    	gd_clockGropu_col0.heightHint = 179;
    	gd_clockGropu_col0.widthHint = 234;
    	clockGropu_col0.setLayoutData(gd_clockGropu_col0);
    	clockGropu_col0.setForeground(SWTResourceManager.getColor(248, 248, 255));
    	clockGropu_col0.setBackground(SWTResourceManager.getColor(248, 248, 255));
    	
    	text = new Text(clockGropu_col0, SWT.BORDER | SWT.READ_ONLY);
    	GridData gd_text = new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1);
    	gd_text.widthHint = 211;
    	text.setLayoutData(gd_text);
    	text.setBackground(SWTResourceManager.getColor(248, 248, 255));
    	text.setEnabled(false);
    	text.setTouchEnabled(true);
    	text.setTextDirection(0);
    	text.setFont(SWTResourceManager.getFont(Messages.StopWatchDialog_1, 35, SWT.BOLD));
    	text.setText(Messages.StopWatchDialog_2);
    	
    	Composite clockGropu_col1 = new Composite(composit_row, SWT.NONE);
    	GridData gd_clockGropu_col1 = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
    	gd_clockGropu_col1.heightHint = 221;
    	clockGropu_col1.setLayoutData(gd_clockGropu_col1);
    	clockGropu_col1.setLayout(new GridLayout(1, false));
    	
    	table = new Table(clockGropu_col1, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL);
    	GridData gd_table = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
    	gd_table.widthHint = 248;
    	gd_table.heightHint = 169;
    	table.setLayoutData(gd_table);
    	
    	table.setFont(SWTResourceManager.getFont(Messages.StopWatchDialog_3, 12, SWT.BOLD));
    	table.setLinesVisible(true);

    	table.setHeaderVisible(true);
    	
    	TableColumn column = new TableColumn(table, SWT.CENTER);
    	column.setMoveable(true);
    	column.setText(Messages.StopWatchDialog_4);
      	
    	
    	column_1 = new TableColumn(table, SWT.NULL);
    	column_1.setMoveable(true);
    	column_1.setText(Messages.StopWatchDialog_5);

    	
    	table.getColumn(0).pack();
    	table.getColumn(1).pack();
  	
    	
    	Group composite_1 = new Group(top, SWT.NONE);
    	GridData gd_composite_1 = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
    	gd_composite_1.widthHint = 500;
    	composite_1.setLayoutData(gd_composite_1);
    	GridLayout gl_composite_1 = new GridLayout(5, true);
    	gl_composite_1.horizontalSpacing = 0;
    	gl_composite_1.verticalSpacing = 0;
    	gl_composite_1.marginWidth = 0;
    	gl_composite_1.marginHeight = 0;
    	composite_1.setLayout(gl_composite_1);
    	
    	
    	btnStart = new Button(composite_1, SWT.NONE);
    	btnStart.setToolTipText(Messages.StopWatchDialog_18);
    	btnStart.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
    	btnStart.setImage(ResourceManager.getPluginImage(Messages.StopWatchDialog_13, Messages.StopWatchDialog_20));
    	btnStart.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			if(!stopwatchModel.isRunning())
    			{
    				stopwatchModel.resume();
    				//System.out.println(Messages.StopWatchDialog_21);
    				logger.log(Level.INFO, Messages.StopWatchDialog_21);
    				startStopWatchDisplayRunner();

    				stopwatchModel.resume();
        			//btnPauseResume.setText(Messages.StopWatchDialog_12);
        			btnPauseResume.setImage(ResourceManager.getPluginImage(Messages.StopWatchDialog_13, Messages.StopWatchDialog_14));

        			btnStart.setEnabled(false);
        			btnPauseResume.setEnabled(true);
    			}
    		}
    	});
    	//btnStart.setText(Messages.StopWatchDialog_25);
    	
    	
    	btnPauseResume = new Button(composite_1, SWT.NONE);
    	btnPauseResume.setToolTipText(Messages.StopWatchDialog_6);
    	btnPauseResume.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
    	btnPauseResume.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			
    			//if(btnPauseResume.getText().equals(Messages.StopWatchDialog_12)){
    				if(stopwatchModel.isRunning()) {
    				stopwatchModel.stop();
    				//btnPauseResume.setText(Messages.StopWatchDialog_8);
    				btnPauseResume.setImage(ResourceManager.getPluginImage(Messages.StopWatchDialog_13, Messages.StopWatchDialog_10));
    				logger.log(Level.INFO, Messages.StopWatchDialog_12);
    				}
    			//}
    			//else if(btnPauseResume.getText().equals(Messages.StopWatchDialog_11)){
    				
    				else if(!stopwatchModel.isRunning()) {
    					stopwatchModel.resume();
    					//btnPauseResume.setText(Messages.StopWatchDialog_12);
    					btnPauseResume.setImage(ResourceManager.getPluginImage(Messages.StopWatchDialog_13, Messages.StopWatchDialog_14));
    					startStopWatchDisplayRunner();
    					logger.log(Level.INFO, Messages.StopWatchDialog_11);
    				}
    			//}
    		}
    	});
    	btnPauseResume.setImage(ResourceManager.getPluginImage(Messages.StopWatchDialog_13, Messages.StopWatchDialog_14));
    	//btnPauseResume.setText(Messages.StopWatchDialog_12);
    	
    	btnReset = new Button(composite_1, SWT.NONE);
    	btnReset.setToolTipText(Messages.StopWatchDialog_26);
    	btnReset.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
    	btnReset.setImage(ResourceManager.getPluginImage(Messages.StopWatchDialog_13, Messages.StopWatchDialog_28));
    	btnReset.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			
    			 stopwatchModel.stop();
    			 // If export is in progress, the reset should wait till,
    			 //The Export thread submit all the Export Tasks to Executor Queue
    			 
    			 synchronized (stopwatchModel) {
    				 stopwatchModel.reset();
        			 resetStopWatchDisplay();
    			 }
    			 
    			 btnStart.setEnabled(true);
    			 //btnPauseResume.setText(Messages.StopWatchDialog_12);
 				 btnPauseResume.setImage(ResourceManager.getPluginImage(Messages.StopWatchDialog_13, Messages.StopWatchDialog_14));
 				 
    			 btnPauseResume.setEnabled(false);
    			 logger.log(Level.INFO, Messages.StopWatchDialog_29);
    	
    		 
    		}
    	});
    	//btnReset.setText(Messages.StopWatchDialog_29);
    	btnPauseResume.setEnabled(false);
    	
    	
        btnSplit = new Button(composite_1, SWT.NONE);
    	btnSplit.setToolTipText(Messages.StopWatchDialog_32);
    	btnSplit.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			if(stopwatchModel.isRunning()) {
    				stopwatchModel.split();
    				refreshSplitDisplayArea();
    			}
    			else {
    	    		 MessageBox messageBox = new MessageBox(top.getShell(), SWT.ICON_WARNING);
    	    		 messageBox.setMessage(Messages.StopWatchDialog_33);
    	    		 messageBox.open();
    	    	}
    		}

			
    	});
    	btnSplit.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
    	btnSplit.setImage(ResourceManager.getPluginImage(Messages.StopWatchDialog_13, Messages.StopWatchDialog_35));
    	//btnSplit.setText(Messages.StopWatchDialog_36);
    	
    	btnExportSplits = new Button(composite_1, SWT.NONE);
    	btnExportSplits.setToolTipText(Messages.StopWatchDialog_37);
    	btnExportSplits.addSelectionListener(new SelectionAdapter() {
    		@Override
    		public void widgetSelected(SelectionEvent e) {
    			//System.out.println(Messages.StopWatchDialog_38);
    			logger.log(Level.INFO, Messages.StopWatchDialog_38);
    			exportSplits();
    		}
    	});
    	btnExportSplits.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
    	

    	btnExportSplits.setImage(ResourceManager.getPluginImage(Messages.StopWatchDialog_13, Messages.StopWatchDialog_41));

 
        return top;
    }
    
    protected void resetStopWatchDisplay(){    
    	
    	text.setText(stopwatchModel.getWatchTime().toString());
    	table.setVisible(false);
    	table.removeAll();
    	table.setVisible(true);
    		
		
    }
    
    protected void startStopWatchDisplayRunner(){ 
    	
    	top.getDisplay().timerExec(1, stopWatchDisplayRunner);
    }
    
    private void refreshSplitDisplayArea() {
		
    	int split = stopwatchModel.getSplitTimes().size();
    	TableItem item = new TableItem(table, SWT.NULL);
    	item.setText(0, Integer.toString(split));   
    	item.setText(1, stopwatchModel.getSplitTimes().get(split-1).toString());
    	
    	
    	table.getColumn(0).pack();
    	table.getColumn(1).pack();
    	
	}

    // overriding this methods allows you to set the
    // title of the custom dialog
    @Override
    protected void configureShell(Shell newShell) {

        super.configureShell(newShell);
        newShell.setText(Messages.StopWatchDialog_43);
    }
    
    
    @Override
    protected Point getInitialSize() {
        return new Point(483, 333);
    }
    
    @Override
    protected boolean isResizable() {
        return true;
    }
  
    private Runnable stopWatchDisplayRunner = new Runnable() {
    	@Override
		public void run() {
    	  
    		try {
    			if(stopwatchModel.isRunning()&& !top.getDisplay().isDisposed()) {
    			
    				text.setText(stopwatchModel.getWatchTime().toString());
    				top.redraw();
    			
    				top.getDisplay().timerExec(1, this);
    		
    			}
    		}catch(SWTException e) {
    			
    			logger.log(Level.SEVERE, Messages.StopWatchDialog_44);
    			
    		}


    	   }
    	};
	
	public void exportSplits()
	{
		//Display Warning message if the splints are zero
		
		//Stop the reset action from the UI till the 
		//export thread push all the splint items to SplintExportThreadExecutor Service
		
		synchronized(stopwatchModel) {
			
			List<Period> splints =stopwatchModel.getSplitTimes();
			int size = splints.size();
			if(size == 0) {
				table.getDisplay().asyncExec(() ->{
				MessageBox messageBox = new MessageBox(table.getShell(), SWT.ICON_WARNING);
				messageBox.setMessage(Messages.StopWatchDialog_48);
				messageBox.open();});
				return;
			}
		
			String saveFile = createFileDialog();
			final Display display = table.getDisplay();
			final Shell shell = new Shell(display);
			SplitExportProgressBar splitExportProgressBar = new SplitExportProgressBar(shell);

			logger.log(Level.INFO, NLS.bind(Messages.StopWatchDialog_49, saveFile));

			// Separate thread to for exporting the Splits to a file
			Thread exportThread = new Thread(() -> {
					SplitsExportThreadExecutor splitsExportThreadExecutor = 
					new SplitsExportThreadExecutor(splitExportProgressBar, saveFile);
					
					String threadPoolSize =properties.getProperty(Messages.StopWatchDialog_9);
					logger.log(Level.SEVERE,NLS.bind(Messages.StopWatchDialog_15, threadPoolSize ));
					splitsExportThreadExecutor.setThreadPoolSize(Integer.parseInt(threadPoolSize));
					
					String splitWorkerDelay =properties.getProperty(Messages.StopWatchDialog_16);
					logger.log(Level.SEVERE,NLS.bind(Messages.StopWatchDialog_17,splitWorkerDelay) );
					splitsExportThreadExecutor.setSplitWorkerDelay(Long.parseLong(splitWorkerDelay));
					
					 try {
						splitsExportThreadExecutor.doExport(splints, size);
					 }	 
					 catch(IOException | IndexOutOfBoundsException  e){
							
							logger.log(Level.SEVERE, NLS.bind(Messages.StopWatchDialog_7, saveFile ));
							table.getDisplay().asyncExec(() ->{
								MessageBox messageBox = new MessageBox(table.getShell(), SWT.ICON_WARNING);
								messageBox.setMessage(NLS.bind(Messages.StopWatchDialog_7, saveFile ));
								messageBox.open();});
					}
						
					finally{
							
						splitsExportThreadExecutor.complete();
						splitExportProgressBar.dispose();
						
					}
					
				
					 					 
					 logger.log(Level.INFO, NLS.bind(Messages.StopWatchDialog_50, new Object[] {size,saveFile }));
				
					 table.getDisplay().asyncExec(() ->{
						 MessageBox messageBox = new MessageBox(table.getShell(), SWT.ICON_WARNING);
						 messageBox.setMessage(NLS.bind(Messages.StopWatchDialog_50, new Object[] {size,saveFile }));
						 messageBox.open();});
		
			});
		

				splitExportProgressBar.open(size);
				exportThread.start();
			
		}
	}

	
	
	public String createFileDialog()
	{
		final Display display = table.getDisplay();
		final Shell shell = new Shell(display);
		Composite composit = new Composite(shell, SWT.NONE);
		
		FileDialog fileDialog = new FileDialog(composit.getShell(), SWT.SAVE);
	    fileDialog.setFilterExtensions(new String[] { Messages.StopWatchDialog_54 });
	    String selected = fileDialog.open();
	    return selected;
	}

}