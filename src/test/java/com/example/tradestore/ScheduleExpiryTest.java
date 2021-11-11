package com.example.tradestore;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.tradestore.schedule.ScheduleExpiry;

 

@SpringJUnitConfig(TradeStoreApplication.class)
@SpringBootTest
public class ScheduleExpiryTest {

    @SpyBean
    private ScheduleExpiry scheduleExpiry;

    @Test
    public void shouldCallScheduleAtleastOnce() {
        await()
                .atMost(Duration.ONE_MINUTE)
                .untilAsserted(() -> verify(scheduleExpiry,atLeast(1)).runScheduledExpiry());
    }


}