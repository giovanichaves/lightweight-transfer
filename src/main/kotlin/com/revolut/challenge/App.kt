package com.revolut.challenge

import com.revolut.challenge.transfer.TransferController
import io.javalin.Javalin
import io.javalin.plugin.openapi.OpenApiOptions
import io.javalin.plugin.openapi.OpenApiPlugin
import io.javalin.plugin.openapi.ui.SwaggerOptions
import io.swagger.v3.oas.models.info.Info


class App(private val port: Int) {

    fun init(): Javalin {
        val app = Javalin.create {
            it.enableWebjars()
            it.showJavalinBanner = false
            it.defaultContentType = "application/json"
            it.registerPlugin(OpenApiPlugin(getOpenApiOptions()))
        }.start(port)

        TransferController(app)

        return app
    }

    private fun getOpenApiOptions(): OpenApiOptions {
        val applicationInfo = Info()
            .version("1.0")
            .description("Revolut coding challenge")

        return OpenApiOptions(applicationInfo).path("/swagger-docs")
            .swagger(SwaggerOptions("/swagger").title("My Swagger Documentation"))
    }
}

fun main(args: Array<String>) {
    App(7000).init()
}