package com.alimmit.hellointegration.service

import org.springframework.stereotype.Service

@Service
class HelloServiceImpl : HelloService {

    override fun sayHello(input: String): String  = "Hello " + input
}

