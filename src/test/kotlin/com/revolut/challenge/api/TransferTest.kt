package com.revolut.challenge.api

import com.revolut.challenge.App
import com.revolut.challenge.transfer.TransferRequest
import io.javalin.Javalin
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.apache.http.HttpStatus
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransferTest {

    private lateinit var app: Javalin

    @BeforeAll
    fun setUp() {
        app = App(7000).init()
        RestAssured.baseURI = "http://localhost:${app.port()}"
    }

    @AfterAll
    fun tearDown() {
        app.stop()
    }

    @Test
    fun `transfer funds between existing accounts`() {
        val origin = "DE123"
        val dest = "DE456"

        val payload = TransferRequest(origin, dest, 1000)

        given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(payload)
            .`when`()
            .post("/transfer")
            .then()
            .statusCode(HttpStatus.SC_OK)
    }
}