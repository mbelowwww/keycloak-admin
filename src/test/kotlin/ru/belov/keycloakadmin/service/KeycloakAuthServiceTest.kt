package ru.belov.keycloakadmin.service

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

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

        keycloakService.createUser(userName, password)

        val token = keycloakAuthService.issueAccessToken(userName, password)

        println(token)
    }

}