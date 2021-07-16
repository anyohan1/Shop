package com.sharp.ing.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("purchaseDTO")
public class PurchaseDTO {

	public int code04;
	
	public String code04_vl;
	
	public int daily_average;
	
	public int total_qt;
	
	public Date purchase_date;
	
	public String userId;
	
	public int list_id;

	public int getCode04() {
		return code04;
	}

	public void setCode04(int code04) {
		this.code04 = code04;
	}

	public String getCode04_vl() {
		return code04_vl;
	}

	public void setCode04_vl(String code04_vl) {
		this.code04_vl = code04_vl;
	}

	public int getDaily_average() {
		return daily_average;
	}

	public void setDaily_average(int daily_average) {
		this.daily_average = daily_average;
	}

	public int getTotal_qt() {
		return total_qt;
	}

	public void setTotal_qt(int total_qt) {
		this.total_qt = total_qt;
	}

	public Date getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getList_id() {
		return list_id;
	}

	public void setList_id(int list_id) {
		this.list_id = list_id;
	}

	

}
