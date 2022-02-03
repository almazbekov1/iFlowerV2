package i.flowers.config;

import i.flowers.config.jwt.JwtConfigurer;
import i.flowers.config.jwt.JwtTokenProvider;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ADMIN_ENDPOINT = "/api/admin/**";
    private static final String USER_ENDPOINT = "/api/users/**";
    private static final String LOGIN_ENDPOINT = "/api/auth/login";
    private static final String PUBLIC_ENDPOINT = "/api/public/**";
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();
        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
        http = http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
            response.sendError(401, ex.getMessage());
        }).and();
        ((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)((AuthorizedUrl)http.authorizeRequests().antMatchers(new String[]{"/api/auth/login"})).permitAll().antMatchers(new String[]{"/api/users/**"})).permitAll().antMatchers(new String[]{"/api/public/**"})).permitAll().antMatchers(new String[]{"/swagger-ui.html"})).permitAll().antMatchers(new String[]{"/swagger-ui/index.html"})).permitAll().antMatchers(new String[]{"/swagger-ui/**", "/v3/api-docs/**"})).permitAll().antMatchers(new String[]{"/api/admin/**"})).hasRole("ADMIN").anyRequest()).permitAll();
        http.apply(new JwtConfigurer(this.jwtTokenProvider));
    }

    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
