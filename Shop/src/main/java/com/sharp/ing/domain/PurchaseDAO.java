package com.sharp.ing.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("purchaseDAO")
public interface PurchaseDAO {

	// total_qt 구할 때
	public List<PurchaseDTO> purchase(String userId) throws Exception;
	
	// 일평균 사용량 구할 때
	public List<PurchaseDTO> dailyAverage(String userId) throws Exception;

	// 최근구매목록 조회
	public List<PurchaseDTO> recentPurchase(String userId) throws Exception;

	
	
}

