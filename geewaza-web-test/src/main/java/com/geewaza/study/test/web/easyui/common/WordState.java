package com.geewaza.study.test.web.easyui.common;

import java.util.Date;


public enum WordState {
	Online(1), Wait_Online(0), Offline(2);
	private int value;
	private WordState(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public static WordState getWordState(Date startTime, Date endTime, Date now) {
		if (startTime != null && endTime != null) {
			if (now.compareTo(endTime) >= 0){
				return WordState.Offline;
			} else if (now.compareTo(startTime) < 0) {
				return WordState.Wait_Online;
			} else {
				return WordState.Online;
			}
		} else if (endTime != null) {
			if (now.compareTo(endTime) >= 0){
				return WordState.Offline;
			}
		} else if (startTime != null) {
			if (now.compareTo(startTime) < 0) {
				return WordState.Wait_Online;
			}
		}
		return WordState.Online;
	}

}
