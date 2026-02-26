package com.example.online_shopping.repository;

import com.example.online_shopping.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByAccount(String account);

    @Procedure(procedureName = "sp_add_member")
    void addMemberSp(String p_member_id, String p_account, String p_password, String p_role, String p_member_name);
}
