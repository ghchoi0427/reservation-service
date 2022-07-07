package com.cgh.inflearn.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Member {

    private UUID id;
    private String name;
    private String password;

    public Member(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public Member(String name, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.password = password;
    }


}
