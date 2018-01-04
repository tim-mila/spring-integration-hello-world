package com.alimmit.hellointegration

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.PollableChannel
import org.springframework.messaging.support.GenericMessage


@SpringBootApplication
class HelloIntegrationApplication
private val LOG : Log = LogFactory.getLog(HelloIntegrationApplication::class.java)

fun main(args: Array<String>) {
    val ctx = SpringApplication(HelloIntegrationApplication::class.java).run(*args)
    val inputChannel = ctx.getBean("inputChannel", MessageChannel::class.java)
    LOG.info("Resolve input channel " + inputChannel.javaClass.canonicalName);
    val outputChannel = ctx.getBean("outputChannel", PollableChannel::class.java)
    LOG.info("Resolve output channel " + outputChannel.javaClass.canonicalName);
    inputChannel.send(GenericMessage<String>("World"))
    LOG.info("Send message");
    LOG.info(outputChannel.receive().payload)
    ctx.close()
}
