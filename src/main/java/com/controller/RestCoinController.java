package com.controller;

import com.domain.Coin;
import com.domain.UpdateCoinInput;
import com.service.Coinservice;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class RestCoinController {
    @Autowired
    Coinservice coinservice;

    @GetMapping("/original-coin")
    public String coins() {
        RestTemplate restTemplate = new RestTemplate();
        String message = restTemplate.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", String.class, "1");
        return message;
    }

    @GetMapping("/formatted-coin")
    public List<Coin> formatteCoins() {
               return coinservice.getFormatData();
    }

    @PostMapping("/create-coin")
    public Coin createCoin(@RequestParam String name, @RequestParam Float rate) {
        return coinservice.createCoin(name, rate);
    }

    @PostMapping("/update-coin")
    public Coin updateCoin(@RequestBody UpdateCoinInput updateCoinInput) {
        return coinservice.updateCoin(updateCoinInput);
    }

    @PostMapping("/delete-coin")
    public void deleteCoin(@RequestBody String name) {
        coinservice.deleteCoin(name);
    }
}
