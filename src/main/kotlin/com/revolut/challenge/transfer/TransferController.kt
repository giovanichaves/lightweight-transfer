package com.revolut.challenge.transfer

import io.javalin.Javalin
import io.javalin.http.Context
import io.javalin.plugin.openapi.annotations.OpenApi
import io.javalin.plugin.openapi.annotations.OpenApiContent
import io.javalin.plugin.openapi.annotations.OpenApiRequestBody
import io.javalin.plugin.openapi.annotations.OpenApiResponse
import javax.servlet.http.HttpServletResponse.SC_OK


class TransferController(
    javalinApp: Javalin
) {

    init {
        javalinApp.post("/transfer", ::transfer)
    }

    @OpenApi(
        requestBody = OpenApiRequestBody(content = [OpenApiContent(from = TransferRequest::class)]),
        responses = [OpenApiResponse(status = "200")]
    )
    fun transfer(ctx: Context) {
        val transferRequest = ctx.body<TransferRequest>()
        ctx.status(SC_OK)
    }

}

data class TransferRequest(
    val from: String,
    val to: String,
    val amount: Int
)