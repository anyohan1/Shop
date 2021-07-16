package com.sharp.ing.domain;

import org.springframework.stereotype.Component;

@Component("shopping_noteDTO")
public class Shopping_noteDTO {

	//노트 번호
	public int note_no;
	//노트 아이디
	public int note_id;
	//구매 수량
	private int note_amount;
	// 대분류 코드
	private int code01;
	// 중분류 코드
	private int code02;
	// 소분류 코드
	private int code03;
	// 세분류 코드
	private int code04;
	
	
	public int getNote_no() {
		return note_no;
	}
	public void setNote_no(int note_no) {
		this.note_no = note_no;
	}
	public int getNote_id() {
		return note_id;
	}
	public void setNote_id(int note_id) {
		this.note_id = note_id;
	}
	public int getNote_amount() {
		return note_amount;
	}
	public void setNote_amount(int note_amount) {
		this.note_amount = note_amount;
	}
	public int getCode01() {
		return code01;
	}
	public void setCode01(int code01) {
		this.code01 = code01;
	}
	public int getCode02() {
		return code02;
	}
	public void setCode02(int code02) {
		this.code02 = code02;
	}
	public int getCode03() {
		return code03;
	}
	public void setCode03(int code03) {
		this.code03 = code03;
	}
	public int getCode04() {
		return code04;
	}
	public void setCode04(int code04) {
		this.code04 = code04;
	}
	
}
