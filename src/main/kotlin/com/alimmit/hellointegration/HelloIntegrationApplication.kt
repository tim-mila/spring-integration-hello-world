package com.alimmit.hellointegration

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ImportResource
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.PollableChannel
import org.springframework.messaging.support.GenericMessage


@SpringBootApplication
@ImportResource("/META-INF/integration.xml")
class HelloIntegrationApplication

private val LOG : Log = LogFactory.getLog(HelloIntegrationApplication::class.java)

fun main(args: Array<String>) {
    val ctx = SpringApplication(HelloIntegrationApplication::class.java).run(*args)
    val inputChannel = ctx.getBean("inputChannel", MessageChannel::class.java)
    val outputChannel = ctx.getBean("outputChannel", PollableChannel::class.java)
    inputChannel.send(GenericMessage<String>("World"))
    LOG.info(outputChannel.receive().payload)
    ctx.close()

}
