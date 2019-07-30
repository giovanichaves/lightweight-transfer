package com.revolut.challenge

import com.revolut.challenge.transfer.TransferController
import io.javalin.Javalin

class App(private val port: Int) {

    fun init(): Javalin {
        val app = Javalin.create { config ->
            config.defaultContentType = "application/json"
        }.start(port)

        TransferController(app)

        return app
    }
}

fun main(args: Array<String>) {
    App(7000).init()
}