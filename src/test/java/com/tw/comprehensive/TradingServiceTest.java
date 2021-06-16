package com.tw.comprehensive;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.ArgumentMatchers.anyString;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    //Check if findTrade() of auditService returns same value as the one findById() returned of TradeRepository
    @Test
    public void should_return_same_value_when_findTrade_and_findById(){
        // given
        AuditService auditService = new AuditService();
        TradeRepository tradeRepository = mock(TradeRepository.class);
        Trade trade = new Trade("King","Queue");
        when(tradeRepository.findById(anyLong())).thenReturn(trade);
        // when
        TradingService tradingService = new TradingService(tradeRepository,auditService);
        // then
        assertEquals(trade,tradingService.findTrade(1L));
        assertEquals(trade,tradingService.findTrade(1L));
    }

    //Check if createTrade() of tradeRepository called when execute createTrade() of TradeService, and the trade id is same as the one returned by tradeRepository
    @Test
    public void should_return_same_trade_when_call_createTrade(){
        // given
        AuditService auditService = new AuditService();
        TradeRepository tradeRepository = mock(TradeRepository.class);
        Trade trade = new Trade("King","Queue");
        TradingService tradingService = new TradingService(tradeRepository,auditService);
        // when
        when(tradeRepository.createTrade(trade)).thenReturn(1L);
        // then
        assertEquals(1L,tradingService.createTrade(trade));
    }
}