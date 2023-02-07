package ru.belov.keycloakadmin.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.temporal.ChronoUnit
import java.util.UUID

@SpringBootTest
class KeycloakServiceTest {

	@Autowired
	private lateinit var keycloakService: KeycloakService

	@Test
	fun createUser() {
		keycloakService.createUser(UUID.randomUUID().toString(), "123")
		Thread.sleep(70_000)
		keycloakService.createUser(UUID.randomUUID().toString(), "123")
	}

}
