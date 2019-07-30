package com.revolut.challenge.transfer

import io.javalin.Javalin
import io.javalin.http.Context
import javax.servlet.http.HttpServletResponse.SC_OK


class TransferController(
    javalinApp: Javalin
) {

    init {
        javalinApp.post("/transfer", ::transfer)
    }

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