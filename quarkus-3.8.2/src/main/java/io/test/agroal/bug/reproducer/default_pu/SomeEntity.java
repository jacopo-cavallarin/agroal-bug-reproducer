package io.test.agroal.bug.reproducer.default_pu;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class SomeEntity {
    @Id
    @UuidGenerator
    UUID id;

    Long number;

    public SomeEntity setNumber(Long number) {
        this.number = number;
        return this;
    }
}
