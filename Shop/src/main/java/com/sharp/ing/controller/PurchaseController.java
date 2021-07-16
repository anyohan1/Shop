package com.sharp.ing.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharp.ing.service.PurchaseService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PurchaseController {
	
	private PurchaseService service;
		
	List<JSONObject> purchase;
	List<JSONObject> recentPurchase;
	
	@Autowired
	public PurchaseController(PurchaseService service) {
	this.service = service;
	}
	
	//구매 임박 목록
	@RequestMapping(value = "/purchase")
	public List<JSONObject> Purchase(Authentication authentication, Model model) throws Exception {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername(); 
		purchase = service.Purchase(userId);	
		model.addAttribute("UserPurchase", purchase);	
		return purchase;
	}
	
	//최근 쇼핑 목록
	@RequestMapping(value = "/recentpurchase")
	public List<JSONObject> RecentPurchase(Authentication authentication, Model model) throws Exception {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername(); 
		recentPurchase = service.RecentPurchase(userId);	
		model.addAttribute("UserPurchase", recentPurchase);	
		return recentPurchase;	
	}
	
}
