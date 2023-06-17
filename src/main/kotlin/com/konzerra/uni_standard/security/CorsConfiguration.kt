package com.konzerra.uni_standard.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfiguration {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOrigins("https://journal.kstu.kg")
                    .allowedMethods(GET, POST, PUT, DELETE, OPTIONS)
                    .allowedHeaders("*")
                    .allowedOriginPatterns("*")
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
