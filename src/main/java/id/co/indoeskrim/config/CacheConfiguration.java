package id.co.indoeskrim.config;

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
            cm.createCache(id.co.indoeskrim.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.Customer.class.getName(), jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.Customer.class.getName() + ".customerAddresses", jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.CustomerAddress.class.getName(), jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.MasterAddress.class.getName(), jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.MasterAddress.class.getName() + ".masterZipcodes", jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.MasterZipcode.class.getName(), jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.Test.class.getName(), jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.OrderMaster.class.getName(), jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.OrderMaster.class.getName() + ".orderItems", jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.OrderMaster.class.getName() + ".orderLoans", jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.OrderItem.class.getName(), jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.OrderLoan.class.getName(), jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.OrderShipping.class.getName(), jcacheConfiguration);
            cm.createCache(id.co.indoeskrim.domain.OrderPayment.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
