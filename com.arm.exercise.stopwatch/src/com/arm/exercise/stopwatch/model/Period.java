// Copyright (C) 2015 ARM Limited. All rights reserved.
package com.arm.exercise.stopwatch.model;

/**
 * This class represents a period of time.
 */
public class Period {

	static final int A_SECOND = 1000;
	static final int A_MINUTE = 60 * A_SECOND;
	
	private final int minutes;
	private final int seconds;
	private final int millis;

	/**
	 * Constructs a period of time from the given number of milliseconds.
	 * @param totalsMillis number of milliseconds corresponding to the period of time to create.
	 */
	public Period(long totalsMillis) {
		this.minutes = (int)(totalsMillis / A_MINUTE);
		this.seconds =  (int)((totalsMillis % A_MINUTE) / A_SECOND);
		this.millis = (int)(totalsMillis % A_SECOND);
	}
	
	/**
	 * Returns the number of minutes in this period of time.
	 * @return the minutes component of this period of time.
	 */
	public int getMinutes() {
		return minutes;
	}
	
	/**
	 * Returns the number of seconds in this period of time, minus full minutes; 
	 * this always is < 60.
	 * @return the seconds component of this period of time.
	 */
	public int getSeconds() {
		return seconds;
	}
	
	/**
	 * Returns the number of milliseconds in this period of time, minus full seconds; 
	 * this always is < 1000.
	 * @return the milliseconds component of this period of time.
	 */
	public int getMilliseconds() {
		return millis;
	}
	
	@Override
	public int hashCode() {
		return minutes ^ seconds ^ millis;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean equals;
		
		if (obj == this) {
			equals =  true;
		} else if (obj instanceof Period) {
			Period p = (Period)obj;
			equals = (p.minutes == minutes && p.seconds == seconds && p.millis == millis);
		} else {
			equals = false;
		}
		
		return equals;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(minutes);
		sb.append(':');
		sb.append(seconds);
		sb.append(':');
		sb.append(millis);
		
		return sb.toString(); 
	}
}
