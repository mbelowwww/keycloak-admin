package ru.belov.keycloakadmin.service

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.keycloak.representations.AccessTokenResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class KeycloakAuthServiceTest {

    @Autowired
    private lateinit var keycloakAuthService: KeycloakAuthService

    @Autowired
    private lateinit var keycloakService: KeycloakService

    @Test
    fun issueAccessToken() {
        val userName = UUID.randomUUID().toString()
        val password = UUID.randomUUID().toString()

        val userId = keycloakService.createUser(userName, password)
        val tokenResponse = keycloakAuthService.issueAccessToken(userName, password)

        assertToken(tokenResponse, userId)
    }

    @Test
    fun refreshToken() {
        val userName = UUID.randomUUID().toString()
        val password = UUID.randomUUID().toString()

        val userId = keycloakService.createUser(userName, password)
        val firstTokenResponse = keycloakAuthService.issueAccessToken(userName, password)
        val secondTokenResponse = keycloakAuthService.refreshAccessToken(firstTokenResponse.refreshToken)

        assertToken(secondTokenResponse, userId)
    }

    fun assertToken(tokenResponse: AccessTokenResponse, userId: String) {
        val chunks = tokenResponse.token.split(".")
        val decoder = Base64.getUrlDecoder()
        val payload = String(decoder.decode(chunks[1]))

        assertTrue(payload.contains("\"sub\":\"$userId\""))

    }

}