package ru.belov.keycloakadmin.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

@SpringBootTest
class KeycloakServiceTest {

	@Autowired
	private lateinit var keycloakService: KeycloakService

	@Test
	fun createUser() {
		val userName = UUID.randomUUID().toString()
		keycloakService.createUser(userName, "123")
	}

}
