package com.sauter001.demo.spike.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "member")
public class Member extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store; // 매니저인 경우만 채운다. nullable

    protected Member() {
    }

    public Member(String name, Role role) {
        this(name, role, null);
    }

    public Member(String name, Role role, Store store) {
        this.name = name;
        this.role = role;
        this.store = store;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public Store getStore() {
        return store;
    }
}
