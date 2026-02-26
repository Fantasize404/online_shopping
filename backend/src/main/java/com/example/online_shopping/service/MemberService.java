package com.example.online_shopping.service;

import com.example.online_shopping.entity.Member;
import com.example.online_shopping.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member register(Member member) {
        if (memberRepository.findByAccount(member.getAccount()).isPresent()) {
            throw new RuntimeException("Account is exists");
        }
        member.setId("M" + UUID.randomUUID().toString().substring(0, 8));
        if (member.getRole() == null || member.getRole().isEmpty()) {
            member.setRole("USER");
        }
        memberRepository.addMemberSp(
                member.getId(),
                member.getAccount(),
                member.getPassword(),
                member.getRole(),
                member.getName()
        );

        return member;
    }

    public Member login(String account, String password) {
        Optional<Member> member = memberRepository.findByAccount(account);
        if (member.isPresent() && member.get().getPassword().equals(password)) {
            return member.get();
        }
        throw new RuntimeException("Account OR Password is ERROR");
    }
}
