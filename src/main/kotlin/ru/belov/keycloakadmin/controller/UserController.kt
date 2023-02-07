package ru.belov.keycloakadmin.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.belov.keycloakadmin.dto.UserForm
import ru.belov.keycloakadmin.service.KeycloakService

@RestController
@RequestMapping("users")
class UserController(
    private val keycloakService: KeycloakService
) {

    @PostMapping
    fun create(@RequestBody form: UserForm): String =
        keycloakService.createUser(form.userName, form.password)

}