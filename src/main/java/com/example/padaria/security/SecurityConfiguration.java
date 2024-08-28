package com.example.padaria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;


    public static final List<String> WHITELIST = Arrays.asList(
            "/actuator/*",
            "/configuration/security",
            "/configuration/ui",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-resources/configuration/security",
            "/swagger-resources/configuration/ui",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-ui/index.html",
            "/v3/api-docs/**",
            "/webjars/**",
            "/webjars/swagger-ui/**",
            "/error"
    );

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity

                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // n guarda estado
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITELIST.toArray(String[]::new)).permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/registerUser").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth/registerFuncionario").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/lista/clientes").permitAll()


                   //

                                .requestMatchers(HttpMethod.GET, "/api/categoria/listar").hasAnyRole("USER", "ROOT")
                                .requestMatchers(HttpMethod.POST, "/api/categoria/cadastrar").hasAnyRole( "ROOT")
                                .requestMatchers(HttpMethod.DELETE, "/api/categoria/deletar/{idCategoria}").hasAnyRole( "ROOT")
                                .requestMatchers(HttpMethod.PUT, "/api/categoria/atualizar/{idCategoria}").hasAnyRole( "ROOT")

                                .requestMatchers(HttpMethod.POST, "/api/produtos/cadastrar").hasAnyRole( "ROOT")
                                .requestMatchers(HttpMethod.DELETE, "/api/produtos/deletar/{idProduto}").hasAnyRole( "ROOT")
                                .requestMatchers(HttpMethod.PUT, "/api/produtos/atualizar/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/produtos/estoque").hasAnyRole( "ROOT")
                                .requestMatchers(HttpMethod.GET, "/api/produtos/estoqueBaixo").hasAnyRole( "ROOT")

                        .requestMatchers(HttpMethod.GET, "/api/vendas/listar").hasAnyRole("USER", "ROOT")
                        .requestMatchers(HttpMethod.GET, "/api/vendas/total-vendas").hasAnyRole("USER", "ROOT")
                        .requestMatchers(HttpMethod.GET, "/api/vendas/total-pedidos").hasAnyRole("USER", "ROOT")
                        .requestMatchers(HttpMethod.POST, "/api/vendas").hasAnyRole("USER", "ROOT")
                        .requestMatchers(HttpMethod.POST, "/api/vendas/registrarVendas").hasAnyRole("USER", "ROOT")
                                .requestMatchers(HttpMethod.GET, "/api/vendas/mes/{mes}").hasAnyRole( "ROOT")

                        .requestMatchers(HttpMethod.GET, "/api/produtos/listar").permitAll()

                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); // vai pegar a instancia de authenticationmanager
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}


//                .cors(conf -> conf.configurationSource(request -> {
//var cors = new CorsConfiguration();
//                    cors.setAllowedOrigins(List.of("http://localhost:5173/"));
//        cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE"));
//        cors.setAllowedHeaders(List.of("*"));
//        return cors;
//                }))