package com.geewaza.study.test.web.easyui.pojo;

import java.util.Date;

import com.geewaza.study.test.web.easyui.common.WordState;

public class PromoteWord {
	private int id;
	private String word;
	private long queryCount;
	private int cateId;
	private int site;
	private Date startTime;
	private Date endTime;
	private int state;
	private String editor;
	private Date lastUpdate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public long getQueryCount() {
		return queryCount;
	}
	public void setQueryCount(long queryCount) {
		this.queryCount = queryCount;
	}
	public int getCateId() {
		return cateId;
	}
	public void setCateId(int cateId) {
		this.cateId = cateId;
	}
	public int getSite() {
		return site;
	}
	public void setSite(int site) {
		this.site = site;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public String toString() {
		return "{"
				+ "id=" + id
				+ ", word=" + word
				+ ", queryCount=" + queryCount
				+ ", cateId=" + cateId
				+ ", site=" + site
				+ ", startTime=" + startTime
				+ ", endTime=" + endTime
				+ ", state=" + state
				+ ", editor=" + editor
				+ ", lastUpdate=" + lastUpdate
				+ "}";
	}
	
	public static WordState getWordState(PromoteWord word, Date date) {
		if (word.getStartTime() != null && word.getEndTime() != null) {
			if (date.compareTo(word.getEndTime()) >= 0){
				return WordState.Offline;
			} else if (date.compareTo(word.getStartTime()) < 0) {
				return WordState.Wait_Online;
			} else {
				return WordState.Online;
			}
		} else if (word.getEndTime() != null) {
			if (date.compareTo(word.getEndTime()) >= 0){
				return WordState.Offline;
			}
		} else if (word.getStartTime() != null) {
			if (date.compareTo(word.getStartTime()) < 0) {
				return WordState.Wait_Online;
			}
		}
		return WordState.Online;
	}

}
