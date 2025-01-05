package org.example.evendrivenfromscartch.config;

@Configuration
@EnableAxon
public class AxonConfig {

    @Bean
    public EventStorageEngine eventStorageEngine(EntityManagerFactory entityManagerFactory) {
        return JpaEventStorageEngine.builder()
                                    .entityManagerProvider(() -> entityManagerFactory.createEntityManager())
                                    .build();
    }

    @Bean
    public EventBus eventBus() {
        return SpringEventBus.builder().build();
    }
}