package fr.eni.projetspring.configuration.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity


public class ProjetSpringSecurityConfig {

    protected final Log logger = LogFactory.getLog(getClass());
    private final String SELECT_USER = "select pseudo, mot_de_passe, 1 from utilisateurs where pseudo =?";
    private final String SELECT_ROLES = "select u.pseudo, r.role from UTILISATEURS  u inner join ROLES r on r.IS_ADMIN = u.administrateur where pseudo = ?";

    /**
     * Définition de l'encodeur de mot de passe
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(SELECT_USER);
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(SELECT_ROLES);

        return jdbcUserDetailsManager;

    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth
                    .requestMatchers(HttpMethod.GET, "/.well-known/*").permitAll()
                    .requestMatchers(HttpMethod.GET, "/liste").permitAll()
                    .requestMatchers(HttpMethod.GET, "/profil").authenticated()
                    .requestMatchers(HttpMethod.GET, "/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/login").permitAll()

                    .requestMatchers("/*").permitAll()
                    .requestMatchers("/css/*").permitAll()
                    .requestMatchers("/images/*").permitAll()
                    .anyRequest().denyAll();


        }
        );
        http.formLogin(form -> {
            form.loginPage("/login");
            form.defaultSuccessUrl("/");
        });

        http.logout(logout -> {
            logout
                    .logoutRequestMatcher(request ->
                            request.getMethod().equals("GET")&&request.getRequestURI().endsWith("/logout"))
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/")
                    .permitAll();
           });

        return http.build();

    }
}
