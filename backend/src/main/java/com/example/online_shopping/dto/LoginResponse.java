package com.example.online_shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String memberId;
    private String memberName;
    private String role;
}
