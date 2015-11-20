package pl.lstypka.springSecurityDistributedSystem.config.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

@EnableRedisHttpSession
public class HttpSessionConfig {

        @Bean
        public JedisConnectionFactory connectionFactory() {
                return new JedisConnectionFactory();  
        }

        @Bean
        public HttpSessionStrategy httpSessionStrategy() {
                return new HeaderHttpSessionStrategy();
        }
}