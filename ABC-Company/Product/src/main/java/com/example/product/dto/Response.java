package com.example.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Response {

    private String massage;
    private Object data;
}
