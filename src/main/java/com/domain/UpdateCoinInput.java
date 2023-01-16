package com.domain;

import lombok.Data;

@Data
public class UpdateCoinInput {
    String originalName;
    String newName;
    Float rate;
}
