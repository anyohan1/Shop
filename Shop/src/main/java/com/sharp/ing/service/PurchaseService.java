package com.sharp.ing.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharp.ing.domain.PurchaseDAO;
import com.sharp.ing.domain.PurchaseDTO;
import com.sharp.ing.domain.Shopping_noteDTO;
import com.sharp.ing.domain.Shopping_note_headerDTO;


@Service("PurchaseService")
public class PurchaseService {

	private Shopping_noteDTO shopping_noteDTO;
	private PurchaseDAO purchaseDAO;

	@Autowired
	public PurchaseService(Shopping_noteDTO shopping_noteDTO, PurchaseDAO purchaseDAO) {
		this.shopping_noteDTO = shopping_noteDTO;
		this.purchaseDAO = purchaseDAO;
	}

	// 구매 임박 목록 조회
	public List<JSONObject> Purchase(String userId) throws Exception {
		List<PurchaseDTO> dailyAverage = purchaseDAO.dailyAverage(userId);
		List<PurchaseDTO> purchase = purchaseDAO.purchase(userId);

		List<JSONObject> resultList = new ArrayList<JSONObject>();
		for(int i=0;i<dailyAverage.size();i++) {

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(purchase.get(i).getPurchase_date());

			cal.add(Calendar.DATE, (int) Math.round(Double.valueOf(purchase.get(i).getTotal_qt())/dailyAverage.get(i).getDaily_average()));
			JSONObject updata = new JSONObject();
			SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
			String format_time1 = format1.format (System.currentTimeMillis());
			
			updata.put("code04", purchase.get(i).getCode04());
			updata.put("code04_vl", purchase.get(i).getCode04_vl());
			updata.put("purchase_date", df.format(purchase.get(i).getPurchase_date()));
			updata.put("repurchase_count", Math.round(Double.valueOf(purchase.get(i).getTotal_qt())/dailyAverage.get(i).getDaily_average()));
			updata.put("repurchase_date",df.format(cal.getTime()));
			
			resultList.add(updata);
		}
		return resultList;
	}

	// 최근 구매 목록 조회
	public List<JSONObject> RecentPurchase(String userId) throws Exception {
		List<PurchaseDTO> recentPurchase = purchaseDAO.recentPurchase(userId);

		List<JSONObject> recentList = new ArrayList<JSONObject>();

		for(int i=0;i<recentPurchase.size();i++) {
			JSONObject recentdata = new JSONObject();
			recentdata.put("code04", recentPurchase.get(i).getCode04());
			recentdata.put("code04_vl", recentPurchase.get(i).getCode04_vl());
			recentList.add(recentdata);
		}

		return recentList;
	}

}
