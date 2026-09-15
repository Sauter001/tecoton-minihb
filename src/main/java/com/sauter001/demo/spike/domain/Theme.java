package com.sauter001.demo.spike.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "theme")
public class Theme extends BaseEntity {

    private String name;
    private String description;

    protected Theme() {
    }

    public Theme(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
