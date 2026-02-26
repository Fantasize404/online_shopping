package com.example.online_shopping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Member")
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MemberID")
    private String id;
    @Column(name = "Account")
    private String account;
    @Column(name = "Password")
    private String password;
    @Column(name = "Role")
    private String role;
    @Column(name = "MemberName")
    private String name;
}
