package com.test.dto;

public class BoardDTO {

	private int num;
	private String id;
	private String name;
	private String subject;
	private String content;
	private String reg_day;
	private int hit;
	
	public BoardDTO() {
		// TODO Auto-generated constructor stub
		super();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_day() {
		return reg_day;
	}

	public void setReg_day(String reg_day) {
		this.reg_day = reg_day;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	
	
}
