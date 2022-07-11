package com.cgh.inflearn.repository;

import com.cgh.inflearn.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final Map<UUID, Member> map = new HashMap<>();

    @Override
    public void save(Member member) {
        map.put(member.getId(), member);
        System.out.println(map.size());
    }

    @Override
    public Member findById(UUID uuid) {
        return map.get(uuid);
    }

    @Override
    public Optional<Member> findByName(String name) {
        return map.values().stream().filter(member -> Objects.equals(member.getName(), name)).findFirst();
    }

    @Override
    public boolean nameExists(String name) {
        return map.values().stream().anyMatch(e -> Objects.equals(e.getName(), name));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(map.values());
    }
}
