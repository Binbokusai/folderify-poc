package com.prithvi.try2;

public class Date {
	String year;
	String month;
	String day;
	String time;
	
	public Date(String date) {
		this.time=date.split(" ")[1];
		String part[]=date.split(" ")[0].split(":");
		this.year=part[0];
		this.month=part[1];
		this.day=part[2];
	}
	public String getYear() {
		return year;
	}
	public String getMonth() {
		return month;
	}
	public String getDay() {
		return day;
	}
	public String getTime() {
		return time;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
