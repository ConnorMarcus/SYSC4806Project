package sysc4806.project;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/register", "resources/**", "/css/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .formLogin((formLogin) -> formLogin.loginPage("/login").permitAll());
        return http.build();
    }

}
