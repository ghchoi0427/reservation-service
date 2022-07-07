package com.cgh.inflearn.repository;

import com.cgh.inflearn.domain.Member;
import com.cgh.inflearn.domain.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MemberRepository {

    void save(Member member);

    Member findById(UUID uuid);

    Member findByName(String name);

    boolean nameExists(String name);

}
