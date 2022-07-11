package com.cgh.inflearn.repository;

import com.cgh.inflearn.domain.Member;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository {

    void save(Member member);

    Member findById(UUID uuid);

    Optional<Member> findByName(String name);

    boolean nameExists(String name);

    List<Member> findAll();

}
