package com.repository;

import com.domain.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {
//    @Modifying
//    @Query("UPDATE Coin c set c.name = :name c.rate = :rate updateTime = :updateTime where name = :name")
//    void udpateCoins(String name, Float rate, String update);

    Coin findOneByName(String name);
}
