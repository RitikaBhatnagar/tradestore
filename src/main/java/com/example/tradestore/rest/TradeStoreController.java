package com.example.tradestore.rest;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tradestore.exception.InvalidTradeException;
import com.example.tradestore.model.Trade;
import com.example.tradestore.service.TradeStoreService;

@RestController
@RequestMapping("/api/v1")
public class TradeStoreController {

    @Autowired
    TradeStoreService tradeStoreService;

    @GetMapping(value = "/health")
    public String getHealth() {
        return String.format("Trade application is up");
    }

    @GetMapping(value = "/trade", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Trade> getTrades(){
        return tradeStoreService.getTrades();
    }

    @PostMapping(value = "/trade", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trade> updateTradeStore(@Valid @RequestBody Trade trade) throws InvalidTradeException {

        Trade response = null;
        if(!tradeStoreService.isValid(trade)) {
            throw new InvalidTradeException(trade.getTradeId());
        }else if(trade.getMaturityDate().isBefore(LocalDate.now())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            response = tradeStoreService.updateStore(trade);
        }

        return ResponseEntity.status(HttpStatus.OK).body(trade);
    }


}

 