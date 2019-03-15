package test

import org.slf4j.LoggerFactory
import java.io.File
import java.time.Duration
import java.time.ZonedDateTime

private val log = LoggerFactory.getLogger(::main.javaClass)

fun main(args: Array<String>) {
    if (args.size < 2) {
        log.error("invalid input data")
        log.info(usage())
        return
    }

    when (args.first()) {
        "-f" -> File(args[1]).useLines { lines ->
            processData(lines)
        }
        "-d" -> {
            val records = args.drop(1).asSequence()
            processData(records)
        }
        else -> log.info(usage())
    }
}

private fun processData(records: Sequence<String>) {
    log.info("begin..")
    val startTime = ZonedDateTime.now()

    val users = records.asSequence()
        .map { it.toUserWithEmails() }

    log.info("start to merge users")
    UserMerge.merge(users).forEach {
        log.info(it.toUserDataFormat())
    }

    val finishTime = ZonedDateTime.now()
    val diff = Duration.between(startTime, finishTime).toMillis()
    log.info("merge finished. time (in millis): $diff")
}

private fun usage(): String = """
    supported below usage:
    -f <fileName>
        there <fileName> is path to file with input data
    -d '<dataLine1>' '<dataLine2>' ..
        there <dataLineN> is a user data

    Each line in file and dataLine1 must by in next format:
    <userName> -> <email1>, <email2>, .. etc, there `userName` is user name and `emailN` is a list of user emails
""".trimIndent()
