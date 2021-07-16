package com.sharp.ing.domain;

import org.springframework.stereotype.Component;

@Component("NoteDTO")
public class NoteDTO {

	private int note_id; 
	private int note_no;
	private String code04_vl; 
	private int note_amount;
	private String write_date;
	
	public int getNote_id() {
		return note_id;
	}
	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}
	public int getNote_no() {
		return note_no;
	}
	public void setNote_no(int note_no) {
		this.note_no = note_no;
	}
	public String getCode04_vl() {
		return code04_vl;
	}
	public void setCode04_vl(String code04_vl) {
		this.code04_vl = code04_vl;
	}
	public int getNote_amount() {
		return note_amount;
	}
	public void setNote_amount(int note_amount) {
		this.note_amount = note_amount;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	
}
