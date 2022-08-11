package academy.devdojo.springboot.config;

import academy.devdojo.springboot.service.DevDojoUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
@RequiredArgsConstructor
public class SecurityConfig {

    private final DevDojoUserDetailsService devDojoUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers("/animes/admin/**").hasRole("ADMIN")
                        .antMatchers("/animes/**").hasRole("USER")
                        .anyRequest()
                        .authenticated()
                )
                .formLogin()
                .and()
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        PasswordEncoder encoder
                = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Password encoded: {}", encoder.encode("Senha"));

        UserDetails admin = User.builder()
                .username("Ugo2")
                .password(encoder.encode("SenhaForte"))
                .roles("USER", "ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("Rick2")
                .password(encoder.encode("SenhaFraca"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        PasswordEncoder encoder
                = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(devDojoUserDetailsService);
        authProvider.setPasswordEncoder(encoder);

        return authProvider;
    }
}
