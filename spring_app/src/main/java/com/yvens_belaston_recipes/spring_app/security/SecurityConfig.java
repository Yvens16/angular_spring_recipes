package com.yvens_belaston_recipes.spring_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.yvens_belaston_recipes.spring_app.entity.UserEntity;
import com.yvens_belaston_recipes.spring_app.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return new UserDetailsService() {
      @Override
      public UserDetails loadUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).get();
        System.out.println("@@@@@ " + user.getRole());
        return user.asUserDetails();
      }
    };
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/home").hasAnyAuthority("USER", "ADMIN")
        .requestMatchers("/admin").hasAuthority("ADMIN")
        .requestMatchers("/other/**")
				.authenticated()
			)
			.formLogin((form) -> form
				// .loginPage("/login") // Décommenter pour utiliser la page de login personnalisée qui se trouve dans le dossier templates
        // Sinon on utilise la page de login par défaut de Spring Security
        .defaultSuccessUrl("/")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build(); // Très important de retourner le résultat de la méthode build() !
    // Sinon rien de tout ça ne fonctionne !
  }
}
