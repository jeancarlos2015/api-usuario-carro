package api.appusuario.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsuarioServiceSecurityImp userService;
	@Autowired
	private JwtServiceImp jwtServiceImp;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtServiceImp, userService);
	}
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication()
        // .passwordEncoder(this.passwordEncoder)
        // .withUser("fulano")
        // .password(passwordEncoder.encode("123"))
        // .roles("USER");

        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/pedido/**")
            .hasAnyRole("USER", "ADMIN")
            .antMatchers("/api/carro/**")
            .hasRole("ADMIN")
            .antMatchers(HttpMethod.POST, "/api/usuario/**")
            .permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}
