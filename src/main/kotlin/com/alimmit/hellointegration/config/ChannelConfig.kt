package com.alimmit.hellointegration.config

import com.alimmit.hellointegration.HelloIntegrationApplication
import com.alimmit.hellointegration.service.HelloService
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.context.annotation.Bean
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.channel.QueueChannel
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.PollableChannel
import org.springframework.stereotype.Component

@Component
class ChannelConfig(val helloService: HelloService) {

    private val LOG : Log = LogFactory.getLog(HelloIntegrationApplication::class.java)

    @Bean(name = arrayOf("inputChannel"))
    fun inputChannel() : MessageChannel {
        return DirectChannel()
    }

    @Bean(name = arrayOf("outputChannel"))
    fun outputChannel() : PollableChannel {
        return QueueChannel()
    }

    @ServiceActivator(inputChannel = "inputChannel", outputChannel = "outputChannel")
    fun activate(input : String) : String {
        LOG.info("Receive input " + input)
        return helloService.sayHello(input)
    }
}