package com.cgh.inflearn.service;

import com.cgh.inflearn.domain.Member;
import com.cgh.inflearn.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void signUp(Member member) {
        memberRepository.save(member);
    }

    @Override
    public boolean isMember(UUID memberId) {
        return memberRepository.findById(memberId) != null;
    }

    @Override
    public Member findById(UUID memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public Member findByName(String name) {
        return memberRepository.findByName(name);
    }

    @Override
    public boolean userNameAlreadyExists(String name) {
        return memberRepository.nameExists(name);
    }


}
