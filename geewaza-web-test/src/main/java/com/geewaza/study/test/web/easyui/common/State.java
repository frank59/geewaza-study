package com.geewaza.study.test.web.easyui.common;

import java.util.Date;


public enum State {
	Online(1), Wait_Online(0), Offline(2);
	private int value;
	private State(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public static State getWordState(Date startTime, Date endTime, Date now) {
		if (startTime != null && endTime != null) {
			if (now.compareTo(endTime) >= 0){
				return State.Offline;
			} else if (now.compareTo(startTime) < 0) {
				return State.Wait_Online;
			} else {
				return State.Online;
			}
		} else if (endTime != null) {
			if (now.compareTo(endTime) >= 0){
				return State.Offline;
			}
		} else if (startTime != null) {
			if (now.compareTo(startTime) < 0) {
				return State.Wait_Online;
			}
		}
		return State.Online;
	}
}
