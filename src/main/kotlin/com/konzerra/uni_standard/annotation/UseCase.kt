package com.konzerra.uni_standard.annotation

import org.springframework.stereotype.Service

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Service
annotation class UseCase()
