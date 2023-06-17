package com.konzerra.uni_standard.security



import com.konzerra.uni_standard.ApiPath
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
class SecurityConfig(
    private val userDetailsService: UserDetailsService,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtTokenFilter: JwtTokenFilter,
)
{

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val auth = DaoAuthenticationProvider()
        auth.setUserDetailsService(userDetailsService)
        auth.setPasswordEncoder(passwordEncoder())
        return auth
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager? {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    @Throws(java.lang.Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {

        http.csrf { csrf -> csrf.disable() }

        // Set session management to stateless

        http.sessionManagement { sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

        // Set unauthorized requests exception handler
        http.exceptionHandling { exceptionHandling ->
            exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint)
        }


        // Set permissions on endpoints
        http.authorizeHttpRequests {auth ->
            auth.requestMatchers(
                "${ApiPath.publicPath}/**",
                "/swagger-ui/**","/v3/api-docs/**","/"
            ).permitAll()
                .anyRequest().authenticated()
        }


        // Add JWT and Payment filter
        http.addFilterBefore(
            jwtTokenFilter,
            UsernamePasswordAuthenticationFilter::class.java
        )
        return http.build()
    }

}