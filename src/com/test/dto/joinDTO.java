package com.test.dto;

public class joinDTO {

	private String id;
	private String name;
	private String passwd1;

	private String birth;
	private Integer tel1, tel2, tel3;
	private String addr;
	private String gender;

	
	public joinDTO(String id, String name, String passwd1) {
		super();
		this.id = id;
		this.name = name;
		this.passwd1 = passwd1;
	}


	@Override
	public String toString() {
		return "joinDTO [id=" + id + ", name=" + name + ", passwd1=" + passwd1 + "]";
	}


	public joinDTO() {

	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public Integer getTel2() {
		return tel2;
	}

	public void setTel2(Integer tel2) {
		this.tel2 = tel2;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getPasswd1() {
		return passwd1;
	}

	public void setPasswd1(String passwd1) {
		this.passwd1 = passwd1;
	}

	public Integer getTel1() {
		return tel1;
	}

	public void setTe1(Integer tel1) {
		this.tel1 = tel1;
	}

	public Integer getTel3() {
		return tel3;
	}

	public void setTel3(Integer tel3) {
		this.tel3 = tel3;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	


}
