package com.cgh.inflearn.repository;

import com.cgh.inflearn.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    public Member findByName(String name) {
        return map.values().stream().filter(member -> member.getName().equals(name)).findFirst().get();
    }

    @Override
    public boolean nameExists(String name) {
        return map.values().stream().anyMatch(e -> e.getName().equals(name));
    }
}
