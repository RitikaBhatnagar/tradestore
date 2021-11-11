package com.example.tradestore;

import com.example.tradestore.exception.InvalidTradeException;
import com.example.tradestore.model.Trade;
import com.example.tradestore.rest.TradeStoreController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TradestoreApplicationTests {

    @Autowired
    TradeStoreController tradeStoreController;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldCreateTradeInStoreSuccessfully() throws InvalidTradeException {
        Trade t1 = trade("T1",1, LocalDate.now());
        ResponseEntity response = tradeStoreController.updateTradeStore(t1);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body(t1),response);
    }

    @Test
    void shouldValidateOlderVersionTrade() throws InvalidTradeException {

        Trade t1 = trade("T2",2, LocalDate.now());
        tradeStoreController.updateTradeStore(t1);
        // older version = 1
        Trade t2 = trade("T2",1, LocalDate.now());

        InvalidTradeException thrown = assertThrows(
                InvalidTradeException.class,
                () -> tradeStoreController.updateTradeStore(t2)
        );

        assertTrue(thrown.getMessage().contains("Invalid trade received for Id T2 as the version is old"));
    }

    @Test
    void shouldUpdateTradeWhenVersionSame() throws InvalidTradeException {

        Trade t1 = trade("T2",1, LocalDate.now());
        tradeStoreController.updateTradeStore(t1);
        // same version = 1 for trade T2 with updated values
        Trade t2 = trade("T2",1, LocalDate.now().plusDays(3));
        ResponseEntity<Trade> response = tradeStoreController.updateTradeStore(t2);
        // verify if the updated t2 object is returned
        assertEquals(t2, response.getBody());
        List<Trade> trades = tradeStoreController.getTrades();
        // verify that only 1 record is present for this trade
        assertEquals(trades.size(), 1);

    }

    @Test
    void shouldRejectTradeIfMaturityDateOlder() throws InvalidTradeException {

        // trade with maturity date is less than today date
        Trade t1 = trade("T3",1, LocalDate.now().minusDays(10));
        ResponseEntity response = tradeStoreController.updateTradeStore(t1);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

    private Trade trade(String tradeId, int version, LocalDate  maturityDate){
        Trade trade = new Trade();
        trade.setTradeId(tradeId);
        trade.setVersion(version);
        trade.setMaturityDate(maturityDate);
        trade.setBookId("BookId1");
        trade.setCounterPartyId("counterParty1");
        trade.setExpiry("N");
        return trade;
    }
}
 