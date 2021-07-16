package com.sharp.ing.domain;

import org.springframework.stereotype.Component;

@Component("shopping_note_headerDTO")
public class Shopping_note_headerDTO {
	
	// 사용자 번호 
	private int id;
	// 노트 번호
	private int note_id;
	//유저 아이디
	private String userId;
	//작성일
	private String write_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNote_id() {
		return note_id;
	}
	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}

}
