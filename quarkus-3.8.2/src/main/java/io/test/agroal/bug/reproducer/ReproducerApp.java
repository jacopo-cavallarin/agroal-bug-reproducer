package io.test.agroal.bug.reproducer;

import io.quarkus.hibernate.orm.PersistenceUnit;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.test.agroal.bug.reproducer.default_pu.SomeEntity;
import io.test.agroal.bug.reproducer.other_pu.SomeOtherEntity;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.random.RandomGenerator;

@QuarkusMain
public class ReproducerApp implements QuarkusApplication {
    private static final RandomGenerator RANDOM_GENERATOR = RandomGenerator.getDefault();

    private final EntityManager entityManager;
    private final EntityManager otherEntityManager;

    @Inject
    public ReproducerApp(EntityManager entityManager, @PersistenceUnit("other") EntityManager otherEntityManager) {
        this.entityManager = entityManager;
        this.otherEntityManager = otherEntityManager;
    }

    @Override
    @Transactional
    public int run(String... args) {
        var someEntity = new SomeEntity().setNumber(RANDOM_GENERATOR.nextLong());
        var someOtherEntity = new SomeOtherEntity().setNumber(RANDOM_GENERATOR.nextLong());

        entityManager.persist(someEntity);
        otherEntityManager.persist(someOtherEntity);

        return 0;
    }
}
