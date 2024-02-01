package com.konzerra.uni_standard.config.security_config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "security-config")
class SecurityProperties(
    @Value("\${security-config.allowed_origins}")
    val allowedOrigins: List<String>
) {
}