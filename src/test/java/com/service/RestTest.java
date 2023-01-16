package com.service;

import com.domain.Coin;
import com.repository.CoinRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestTest {
    @Autowired
    Coinservice coinservice;

    @Autowired
    CoinRepository coinRepository;

    @Test
    void getOriginalData() {
        System.out.println(coinservice.getOriginalData());
    }

    @Test
    void getFormatData() {
        System.out.println(coinservice.getFormatData());
    }



    @Test
    void findAll(){
        coinservice.createCoin("人民幣",4.5f);
        coinservice.createCoin("印尼盾",1.1f);
        coinservice.createCoin("日幣",2.2f);
        System.out.println(coinservice.getCoins());
    }

    @Test
    void saveOne() {
        Coin coin = coinservice.createCoin("人民幣",4.5f);
        assert(coin.getId() != null);
    }

    @Test
    void updateOne() {
        Coin coin = coinservice.createCoin("人民幣",4.5f);
        coin.setName("泰銖");
        coin.setRate(8.654f);
        coin = coinRepository.save(coin);
        assert(coin.getName() != "人民幣");
        System.out.println(coin);
    }

    @Test
    void deleteOne() {
        Coin coin = coinservice.createCoin("人民幣",4.5f);
        coinservice.deleteCoin(coin.getName());
        Coin deletedCoin = coinRepository.findOneByName(coin.getName());
        assert(deletedCoin == null);
    }
}