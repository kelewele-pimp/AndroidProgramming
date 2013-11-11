package com.example.anothertodolistactivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToDoItem {
	
	private String task; 
	private Date date_created;
	
	public ToDoItem(String task, Date date_created){
		this.task = task; 
		this.date_created = date_created; 
	}
	
	public ToDoItem(String task){
		this(task, new Date(System.currentTimeMillis()));
	}

	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	} 
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String dateString = sdf.format(date_created); 
		return "(" + dateString + ") f" + task; 
	}
}
