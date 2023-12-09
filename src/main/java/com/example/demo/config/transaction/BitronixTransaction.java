package com.example.demo.config.transaction;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import org.springframework.context.annotation.Bean;

import javax.transaction.SystemException;

public class BitronixTransaction {
    @Bean
    public bitronix.tm.Configuration transactionManagerServices() {
        bitronix.tm.Configuration configuration = TransactionManagerServices.getConfiguration();
        configuration.setServerId("1");
        return configuration;
    }

    @Bean(name = "bitronixTransactionManager")
    public BitronixTransactionManager transactionManager(bitronix.tm.Configuration _c){
        var transactionManager = TransactionManagerServices.getTransactionManager();
        try {
            transactionManager.setTransactionTimeout(60);
        } catch (SystemException e) {
            throw new RuntimeException(e);
        }
        return transactionManager;

    }
}
