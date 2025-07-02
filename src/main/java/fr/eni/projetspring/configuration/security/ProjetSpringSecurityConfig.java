package fr.eni.projetspring.configuration.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity

public class ProjetSpringSecurityConfig {

    protected final Log logger = LogFactory.getLog(getClass());
    private final String SELECT_USER = "select pseudo, mot_de_passe from utilisateurs where pseudo =    ?";
    //private final String SELECT_ROLES = "select m.email, r.role from UTILISATEURS  u inner join ROLES r on r.IS_ADMIN = u.admin where pseudo = ?";

    @Bean
    UserDetailsService userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(SELECT_USER);
        //jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(SELECT_ROLES);

        return jdbcUserDetailsManager;

    }
}
