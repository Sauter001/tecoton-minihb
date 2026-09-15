package com.sauter001.demo.spike.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "store")
public class Store extends BaseEntity {

    private String name;

    protected Store() {
    }

    public Store(String name) {
        this.name = name;
    }

}
