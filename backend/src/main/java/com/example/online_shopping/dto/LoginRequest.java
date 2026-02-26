package com.example.online_shopping.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String account;
    private String password;
}
