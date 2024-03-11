package io.test.agroal.bug.reproducer.other_pu;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class SomeOtherEntity {
    @Id
    @UuidGenerator
    UUID id;

    Long number;

    public SomeOtherEntity setNumber(Long number) {
        this.number = number;
        return this;
    }
}
