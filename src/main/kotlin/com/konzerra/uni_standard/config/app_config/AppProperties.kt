package com.konzerra.uni_standard.config.app_config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app-config")
class AppProperties(
    val key:String,
    val location: String,
    val duration: Int
) {
}