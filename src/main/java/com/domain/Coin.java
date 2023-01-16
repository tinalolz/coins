package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "updateTime")
    private String updateTime;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "rate")
    private Float rate;
}
