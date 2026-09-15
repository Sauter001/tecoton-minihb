package com.sauter001.demo.spike.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;

/**
 * 시퀀스를 하나로 통일하기 위한 장치다.
 *
 * <p>IDENTITY로 매핑하면 TABLE_PER_CLASS에서 자식 테이블마다 auto increment가 독립적으로 돌아
 * ID가 겹친다. 엔티티마다 시퀀스가 따로 생기는 것도 같은 이유로 막아야 해서
 * {@code @SequenceGenerator}를 여기 한 번만 선언하고 전 엔티티가 상속받는다.
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spike_gen")
    @SequenceGenerator(name = "spike_gen", sequenceName = "spike_seq", allocationSize = 50)
    private Long id;

    public Long getId() {
        return id;
    }
}
