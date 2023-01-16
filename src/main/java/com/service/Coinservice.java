package com.service;

import com.domain.Coin;
import com.domain.UpdateCoinInput;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface Coinservice {

    String getOriginalData();

    List<Coin> getFormatData();

    List<Coin> getCoins();

    Coin createCoin(String name, Float rate);

    Coin updateCoin(UpdateCoinInput updateCoinInput);

    void deleteCoin(String name);
}
