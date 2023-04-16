package pl.mfurman.owasp_sample.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static pl.mfurman.owasp_sample.controllers.BasicController.API_HELLO;

@Configuration
@Profile("secured")
@EnableWebSecurity
@EnableMethodSecurity
public class SecuredConfiguration {

  @Bean
  public UserDetailsService users(final PasswordEncoder encoder) {
    final UserDetails user = User.builder()
      .username("user")
      .password(encoder.encode("user"))
      .roles("USER")
      .build();

    final UserDetails admin = User.builder()
      .username("admin")
      .password(encoder.encode("admin"))
      .roles("ADMIN")
      .build();

    return new InMemoryUserDetailsManager(user, admin);
  }

  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
    return http

      // Logowanie
      .formLogin()
      .defaultSuccessUrl(API_HELLO, true)
      .and()

      // Wylogowanie i usunięcie sesji
      .logout()
      .logoutSuccessUrl(API_HELLO)
      .invalidateHttpSession(true)
      .deleteCookies("JSESSIONID")
      .and()

      // Zabezpieczenie żądań
      .authorizeHttpRequests()
      .anyRequest().authenticated()
      .and()
      .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
