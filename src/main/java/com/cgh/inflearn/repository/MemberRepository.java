package com.cgh.inflearn.repository;

import com.cgh.inflearn.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface MemberRepository {

    void save(Member member);

    Member findById(UUID uuid);

    Member findByName(String name);

    boolean nameExists(String name);

}
