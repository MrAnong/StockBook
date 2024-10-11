package StockBook.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	  @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

//	@Bean
//  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//          EntityManagerFactoryBuilder builder,
//          DataSource dataSource) {
//      return builder
//              .dataSource(dataSource)
//              .packages("mystores.models")
//              .persistenceUnit("default")
//              .build();
//  }

	  
  @Bean
  public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
      http.authorizeHttpRequests(auth -> auth
                              .requestMatchers("/stockbook-console/**",
                                      "/stockbook/**")
                              
                              .permitAll()
                              .requestMatchers("/stockbook/user/login").permitAll()
                              .anyRequest().authenticated()
//								 .and()
//					               .formLogin()
//					               .loginPage("/login")
//					               .loginProcessingUrl("/login")
//					               .usernameParameter("email")
//					               .passwordParameter("password")
//					               .defaultSuccessUrl("/home", true)
//					               .failureUrl("/login?error")
//					               .permitAll()
//								   .and()
//								   .logout()
//								   .logoutUrl("/logout")
//								   .logoutSuccessUrl("logout?success")
//								   .and()
//								   .httpBasic();
              )
              .cors().disable().csrf().disable().headers().frameOptions().disable();
      	
      return http.build();
  }

}
