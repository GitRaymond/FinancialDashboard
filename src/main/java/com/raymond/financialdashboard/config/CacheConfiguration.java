package com.raymond.financialdashboard.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.raymond.financialdashboard.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.TransactionIng.class.getName(), jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.TransactionIng.class.getName() + ".tags", jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.TransactionIng.class.getName() + ".splitTransactions", jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.Vendor.class.getName(), jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.Vendor.class.getName() + ".transactionIngs", jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.BankAccount.class.getName(), jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.BankAccount.class.getName() + ".transactionIngs", jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.ReportingCategory.class.getName(), jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.ReportingCategory.class.getName() + ".transactionIngs", jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.ReportingCategory.class.getName() + ".budgets", jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.Tag.class.getName(), jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.Tag.class.getName() + ".transactionIngs", jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.Budget.class.getName(), jcacheConfiguration);
            cm.createCache(com.raymond.financialdashboard.domain.SplitTransaction.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
