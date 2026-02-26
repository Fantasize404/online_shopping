package com.example.online_shopping.controller;

import com.example.online_shopping.dto.LoginRequest;
import com.example.online_shopping.dto.LoginResponse;
import com.example.online_shopping.entity.Member;
import com.example.online_shopping.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Member member) {
        try {
            member.setName(HtmlUtils.htmlEscape(member.getName()));
            Member savedMember = memberService.register(member);
            return ResponseEntity.ok(savedMember);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Member member = memberService.login(request.getAccount(), request.getPassword());
            LoginResponse response = new LoginResponse(member.getId(), member.getName(), member.getRole());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
