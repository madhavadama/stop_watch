// Copyright (C) 2015 ARM Limited. All rights reserved.
package com.arm.exercise.stopwatch.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StopwatchModel {

	private boolean isRunning;
	private long startTime;
	private long elapsedTime;
	private long recordedSplitsTime;
	private List<Period> splits = new ArrayList<>();

	/**
	 * Indicates whether the stopwatch is running or stopped.
	 * @return <code>true</code> is the stopwatch is running, <code>false</code> otherwise.
	 */
	public boolean isRunning() {
		return isRunning;
	}
	/**
	 * Starts or resumes the stopwatch.
	 */
	public void resume() {
		if (!isRunning) {
			startTime = now();
			isRunning = true;
		}
	}
	
	/**
	 * Stops the stopwatch.
	 */
	public void stop() {
		if (isRunning) {
			elapsedTime = computeElapsedTime();
			isRunning = false;
		}
	}
	
	/**
	 * Resets the stopwatch and deletes split times.
	 */
	public void reset() {
		if (!isRunning) {
			startTime = now();
			elapsedTime = 0;
			recordedSplitsTime = 0;
			splits.clear();
		}
	}
	
	/**
	 * Records a split time.
	 */
	public void split() {
		if (isRunning) {
			long splitTime = computeElapsedTime() - recordedSplitsTime;
			
			if (splitTime > 0) {
				splits.add(new Period(splitTime));
				recordedSplitsTime += splitTime;
			}
		}
	}

	/**
	 * Returns the stopwatch's time.
	 * @return the stopwatch's time.
	 */
	public Period getWatchTime() {
		return new Period(computeElapsedTime());
	}

	/**
	 * Returns recorded split times.
	 * @return a list of recorded split times.
	 */
	public List<Period> getSplitTimes() {
		return Collections.unmodifiableList(splits);
	}
	
	/**
	 * Returns the current time in milliseconds. Sub-classes can override this to control time (for
	 * testing purposes for instance)
	 * @return current time in milliseconds.
	 */
	protected long now() {
		return System.currentTimeMillis();
	}

	private long computeElapsedTime() {
		return elapsedTime + (isRunning ? now() - startTime : 0);
	}	
}
