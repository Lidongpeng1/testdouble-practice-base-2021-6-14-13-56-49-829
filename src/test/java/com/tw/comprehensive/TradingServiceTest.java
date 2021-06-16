package com.tw.comprehensive;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class TradingServiceTest {


    @Test
    public void should_call_logNewTrade_when_use_createTrade(){
        // given
        AuditService auditService = new AuditService();
        AuditService spyAuditService = Mockito.spy(auditService);

        TradingService tradingService = new TradingService(new TradeRepository(),spyAuditService);

        // when
        Trade trade = new Trade("King","Queue");
        tradingService.createTrade(trade);

        // then
        verify(spyAuditService).logNewTrade(trade);

    }
}