package academy.devdojo.springboot.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Log4j2
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest()
                        .authenticated()
                )
                .formLogin()
                .and()
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder
                = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Password encoded: {}", encoder.encode("Test"));

        UserDetails admin = User.builder()
                .username("Ugo")
                .password(encoder.encode("SenhaForte"))
                .roles("USER", "ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("Rick")
                .password(encoder.encode("SenhaFraca"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
