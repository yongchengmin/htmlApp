package com.yc.test.qiyeweixin.pojo;

import java.util.List;

public class CheckIn {

	private int opencheckindatatype;//打卡类型。1：上下班打卡；2：外出打卡；3：全部打卡
	private long starttime;
	private long endtime;
	private List<String> useridlist;
	
	public CheckIn(){} ;
	
	public CheckIn(int opencheckindatatype, long starttime, long endtime, List<String> useridlist) {
		super();
		this.opencheckindatatype = opencheckindatatype;
		this.starttime = starttime;
		this.endtime = endtime;
		this.useridlist = useridlist;
	}
	public int getOpencheckindatatype() {
		return opencheckindatatype;
	}
	public void setOpencheckindatatype(int opencheckindatatype) {
		this.opencheckindatatype = opencheckindatatype;
	}
	public long getStarttime() {
		return starttime;
	}
	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}
	public long getEndtime() {
		return endtime;
	}
	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}
	public List<String> getUseridlist() {
		return useridlist;
	}
	public void setUseridlist(List<String> useridlist) {
		this.useridlist = useridlist;
	}

	@Override
	public String toString() {
		return "CheckIn [opencheckindatatype=" + opencheckindatatype + ", starttime=" + starttime + ", endtime="
				+ endtime + ", useridlist=" + useridlist + "]";
	}
	
	
	
}
