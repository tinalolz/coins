package com.service.impl;

import com.domain.Bpi;
import com.domain.Coin;
import com.domain.UpdateCoinInput;
import com.repository.CoinRepository;
import com.service.Coinservice;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class CoinServiceImpl implements Coinservice {

    @Autowired
    private CoinRepository coinRepository;

    @Override
    public String getOriginalData() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", String.class, "1");
    }

    @Override
    public List<Coin> getFormatData() {
        String message = getOriginalData();
        JSONObject json = new JSONObject(message);
        JSONObject times = json.getJSONObject("time");
        String time = times.getString("updatedISO");
        LocalDateTime updateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = updateTime.format(formatter);

        JSONObject bpi = json.getJSONObject("bpi");
        Iterator<String> keys = bpi.keys();

        List<Coin> coinList = new ArrayList<>();

        while(keys.hasNext()) {
            String key = keys.next();
            if (bpi.get(key) instanceof JSONObject) {
                Float rate = bpi.getJSONObject(key).getFloat("rate_float");
                Coin coin = new Coin();
                coin.setName(Bpi.valueOf(key).value);
                coin.setRate(rate);
                coin.setUpdateTime(formatDateTime);
                coinList.add(coin);
            }
        }
        return coinList;
    }

    @Override
    public List<Coin> getCoins() {
        return coinRepository.findAll();
    }

    @Override
    public Coin createCoin(String name, Float rate) {
        Coin coin = new Coin();
        coin.setName(name);
        coin.setRate(rate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        coin.setUpdateTime(LocalDateTime.now().format(formatter));
        return coinRepository.save(coin);
    }

    @Override
    public Coin updateCoin(UpdateCoinInput updateCoinInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Coin coin = coinRepository.findOneByName(updateCoinInput.getOriginalName());
        if(Objects.nonNull(updateCoinInput.getNewName())){
            coin.setName(updateCoinInput.getNewName());
        }

        if(Objects.nonNull(updateCoinInput.getRate())){
            coin.setRate(updateCoinInput.getRate());
        }

        coin.setUpdateTime(LocalDateTime.now().format(formatter));
        return coinRepository.save(coin);
    }

    @Override
    public void deleteCoin(String name) {
        Coin coin = coinRepository.findOneByName(name);
        coinRepository.deleteById(coin.getId());
    }

}
