package com.example.yhproject.creditserver.config;

import com.example.yhproject.creditserver.constant.CreditProperties;
import com.example.yhproject.creditserver.entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = CreditProperties.VERSION_PROPERTY_NAME, havingValue = CreditProperties.VERSION_1)
public class InitialDataConfigV1 {

    private final EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void init() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        for (int i = 0; i < 1000; i++) {
            entityManager.persist(new User("User" + (i + 1)));
        }

        entityManager.getTransaction().commit();
    }

}
