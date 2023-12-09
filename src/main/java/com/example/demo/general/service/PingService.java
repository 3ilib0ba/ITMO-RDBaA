package com.example.demo.general.service;

import com.example.demo.general.model.Ping;
import com.example.demo.general.repository.PingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PingService {
    private PingRepository pingRepository;
    @Autowired
    public PingService(
            PingRepository pingRepository
    ) {
        this.pingRepository = pingRepository;
    }

    @Transactional
    public Ping addNewPingFromNow() {
        Ping pingCurrent = new Ping(
                null,
                new Time(System.currentTimeMillis())
        );
        return pingRepository.save(pingCurrent);
    }

    public List<Ping> getAllPings() {
        return pingRepository.findAll();
    }

}
