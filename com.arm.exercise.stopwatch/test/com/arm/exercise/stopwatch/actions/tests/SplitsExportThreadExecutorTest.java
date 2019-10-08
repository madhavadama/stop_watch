package com.arm.exercise.stopwatch.actions.tests;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.arm.exercise.stopwatch.actions.SplitsExportThreadExecutor;
import com.arm.exercise.stopwatch.model.Period;




public class SplitsExportThreadExecutorTest {
	
	SplitsExportThreadExecutor splitsExportThreadExecutor;
	
	@Before
	
	public void setup()
	{
		
		
				
	}

	@Test
	public void testSplitsExportThreadExecute100() {
		
		
		splitsExportThreadExecutor = new SplitsExportThreadExecutor(null, "./test100.csv");
		splitsExportThreadExecutor.setThreadPoolSize(1);
		splitsExportThreadExecutor.setSplitWorkerDelay(200);

		List<Period> list = new ArrayList<Period>();
		Period period;
		for(int i = 0; i <100; i++)
		{
			period = new Period(50*i);
			list.add(period);
		}
		
		try {
			splitsExportThreadExecutor.doExport(list, list.size());
		} catch (IOException | IndexOutOfBoundsException e) {

			
		}

		finally {

			splitsExportThreadExecutor.complete();

		}

	}
	@Test
	public void testSplitsExportThreadExecute500() {
		
		
		splitsExportThreadExecutor = new SplitsExportThreadExecutor(null, "./test500.csv");
		splitsExportThreadExecutor.setThreadPoolSize(1);
		splitsExportThreadExecutor.setSplitWorkerDelay(200);

		List<Period> list = new ArrayList<Period>();
		Period period;
		for(int i = 0; i <500; i++)
		{
			period = new Period(50*i);
			list.add(period);
		}
		
		try {
			splitsExportThreadExecutor.doExport(list, list.size());
		} catch (IOException | IndexOutOfBoundsException e) {

			
		}

		finally {

			splitsExportThreadExecutor.complete();

		}

	}

}
