package com.konzerra.uni_standard.security

import com.konzerra.uni_standard.config.security_config.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfiguration(
    val securityProperties: SecurityProperties
) {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOriginPatterns(*securityProperties.allowedOrigins.toTypedArray())
                    .allowedMethods(GET, POST, PUT, DELETE, OPTIONS)
                    .allowedHeaders("*")
                    .allowCredentials(true)
            }
        }
    }

    companion object {
        private const val GET = "GET"
        private const val POST = "POST"
        private const val PUT = "PUT"
        private const val DELETE = "DELETE"
        private const val OPTIONS = "OPTIONS"
    }
}
