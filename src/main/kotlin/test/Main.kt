package test

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger(::main.javaClass)

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        log.error("invalid input data (arguments is empty)")
    }

    val users = args.asSequence()
        .map { it.toUserWithEmails() }

    log.info("merge users:")
    UserMerge.merge(users).forEach {
        log.info(it.toString())
    }
}