package com.example.tradestore.schedule;

import com.example.tradestore.dao.TradeStoreRepository;
import com.example.tradestore.model.Trade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleExpiry {

    @Autowired
    TradeStoreRepository tradeStoreRepository;

    private static final Logger LOG = LoggerFactory.getLogger(ScheduleExpiry.class);

    @Scheduled(cron="${tradestore.expiry.schedule}")
    public void runScheduledExpiry(){
        LOG.info("schedule job started");
        LocalDate today = LocalDate.now();
        List<Trade> expiredTrades = tradeStoreRepository.findAll().stream()
                                                .filter(record -> record.getMaturityDate().isBefore(today))
                                                .map(trade -> {
                                                    trade.setExpiry("Y");
                                                    return trade;
                                                })
                                                .collect(Collectors.toList());
        if(!expiredTrades.isEmpty()){
            tradeStoreRepository.saveAll(expiredTrades);
            List<String> expiredTradeIds = expiredTrades.stream().map(t -> t.getTradeId()).collect(Collectors.toList());
            LOG.info("Trades expiry updated for trade Ids {} ", expiredTradeIds);
        }

        LOG.info("schedule job ended");
    }
}