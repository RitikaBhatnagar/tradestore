package com.example.tradestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tradestore.model.Trade;

public interface TradeStoreRepository extends JpaRepository<Trade, Long> {

	List<Trade> findByTradeId(String tradeId);

	Trade findByTradeIdAndVersion(String tradeId, Integer version);

	 
}
