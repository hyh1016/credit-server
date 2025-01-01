package com.example.yhproject.creditserver.config;

import com.example.yhproject.creditserver.constant.CreditProperties;
import com.example.yhproject.creditserver.entity.User;
import com.example.yhproject.creditserver.entity.UserCredit;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = CreditProperties.VERSION_PROPERTY_NAME, havingValue = CreditProperties.VERSION_2)
public class InitialDataConfigV2 {

    private final EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void init() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        for (int i = 0; i < 1000; i++) {
            User user = new User("User" + (i + 1));
            entityManager.persist(user);
            entityManager.persist(new UserCredit(user, CreditProperties.TOTAL_CREDIT));
        }

        entityManager.getTransaction().commit();
    }

}
