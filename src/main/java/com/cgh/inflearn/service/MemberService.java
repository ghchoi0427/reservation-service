package com.cgh.inflearn.service;

import com.cgh.inflearn.domain.Member;

import java.util.UUID;

public interface MemberService {

    void signUp(Member member);

    boolean isMember(UUID memberId);

    Member findById(UUID memberId);

    Member findByName(String name);

    boolean userNameAlreadyExists(String name);
}
