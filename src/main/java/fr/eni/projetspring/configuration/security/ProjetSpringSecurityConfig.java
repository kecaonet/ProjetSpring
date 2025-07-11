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
    private final String SELECT_USER = "select pseudo, mot_de_passe, is_desactive from utilisateurs where pseudo =?";
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
                    //évite le bug de connexion sur Google
                    .requestMatchers(HttpMethod.GET, "/.well-known/*").permitAll()
                    //Permettre aux visiteurs d'accéder à la page d'accueil (liste des ventes)
                    .requestMatchers(HttpMethod.GET, "/liste").permitAll()
                    //Permettre aux connectés d'accéder à la page profil
                    .requestMatchers(HttpMethod.GET, "/profil").authenticated()
                    //permettre à tous d'accéder à la page login
                    .requestMatchers(HttpMethod.GET, "/login").permitAll()
                    // permettre à tous de soumettre un formulaire de connexion
                    .requestMatchers(HttpMethod.POST, "/login").permitAll()
                    // permettre à tous d'accéder à la page de connexion
                    .requestMatchers(HttpMethod.POST, "/login/inscription").permitAll()
                    //permettre à tous de soumettre un formulaire d'inscription
                    .requestMatchers(HttpMethod.POST, "/login/inscription").permitAll()
                    // permettre à tous d'accéder à la page Mot de passe oublié
                    .requestMatchers(HttpMethod.GET, "/login/MdpOublie").permitAll()
                    //permettre à tous de soumettre un formulaire de mot de passe oublié
                    .requestMatchers(HttpMethod.POST, "/login/MdpOublie").permitAll()
                    // permettre aux membres et admin d'accéder à la page detailVentes (membres désactivés ne peuvent pas enchérir)
                    .requestMatchers(HttpMethod.GET, "/details").authenticated()
                    //permettre aux membres et admin d'accéder à la page nouvelleVente (membres désactivés ne peuvent pas enchérir
                    .requestMatchers(HttpMethod.GET, "/nouvelle_vente").hasRole("MEMBRE")
                    //permettre à tous de soumettre un formulaire de nouvelle vente
                    .requestMatchers(HttpMethod.POST, "/nouvelle-vente").hasRole("MEMBRE")
                    //permettre à tous de soumettre un formulaire de nouvelle enchere
                    .requestMatchers(HttpMethod.POST, "/nouvelle_enchere").hasRole("MEMBRE")
                    //permettre à tous de soumettre un formulaire de nouvelle enchere
                    .requestMatchers(HttpMethod.POST, "/view_admin").hasRole("ADMIN")

                    .requestMatchers("/*").permitAll()
                    .requestMatchers("/css/*").permitAll()
                    .requestMatchers("/js/*").permitAll()
                    .requestMatchers("/images/*").permitAll()
                    .anyRequest().denyAll();


        }
        );
        http.formLogin(form -> {
            form.loginPage("/login");
            //ajout pour utilisation utilisateurEnSession
            form.defaultSuccessUrl("/session").permitAll();
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
