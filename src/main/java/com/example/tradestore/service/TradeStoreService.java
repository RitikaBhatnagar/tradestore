package com.example.tradestore.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.tradestore.dao.TradeStoreRepository;
import com.example.tradestore.exception.InvalidTradeException;
import com.example.tradestore.model.Trade;

@Service
public class TradeStoreService {

    @Autowired
    private TradeStoreRepository tradeStoreRepository;

    public List<Trade> getTrades() {
        return tradeStoreRepository.findAll();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED )
    public Trade updateStore(Trade trade) throws InvalidTradeException {

        // check if the trade is duplicate - same version
        Trade recordFromDB = tradeStoreRepository.findByTradeIdAndVersion(trade.getTradeId(), trade.getVersion());
        if(Objects.nonNull(recordFromDB)){
            recordFromDB.setMaturityDate(trade.getMaturityDate());
            recordFromDB.setCounterPartyId(trade.getCounterPartyId());
            recordFromDB.setBookId(trade.getBookId());
            recordFromDB.setExpiry(trade.getExpiry());
            recordFromDB.setCreatedDate(LocalDate.now());
            return tradeStoreRepository.save(recordFromDB);
        } else{
            trade.setCreatedDate(LocalDate.now());
            return tradeStoreRepository.save(trade);
        }

    }

    public boolean isValid(Trade trade) {
        List<Trade> existingTrade = tradeStoreRepository.findByTradeId(trade.getTradeId());
        if(!existingTrade.isEmpty()){
            return !existingTrade.stream().anyMatch(t -> t.getVersion() > trade.getVersion());
        }

        return true;
    }
}