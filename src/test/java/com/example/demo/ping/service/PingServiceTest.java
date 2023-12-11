package com.example.demo.ping.service;

import com.example.demo.general.model.Ping;
import com.example.demo.general.repository.PingRepository;
import com.example.demo.general.service.PingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PingServiceTest {
    @Mock
    private PingRepository pingRepository;
    @InjectMocks
    private PingService pingService;

    private final Long ID = 1L;

    @Test
    void addNewPingFromNow() {
        Ping ping = new Ping(
                ID,
                new Time(System.currentTimeMillis())
        );

        when(pingRepository.save(any())).thenReturn(ping);
        assertEquals(ping, pingService.addNewPingFromNow());
        verify(pingRepository, times(1)).save(any());
    }

    @Test
    void getAllPings() {
        Ping ping1 = new Ping(
                ID,
                new Time(System.currentTimeMillis())
        );

        Ping ping2 = new Ping(
                ID + 1,
                new Time(System.currentTimeMillis())
        );

        when(pingRepository.findAll()).thenReturn(List.of(ping1, ping2));
        List<Ping> result = pingService.getAllPings();
        assertEquals(List.of(ping1, ping2), result);
        verify(pingRepository, times(1)).findAll();
    }
}
