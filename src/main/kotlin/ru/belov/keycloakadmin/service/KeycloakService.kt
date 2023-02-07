package ru.belov.keycloakadmin.service

import org.keycloak.admin.client.CreatedResponseUtil
import org.keycloak.admin.client.resource.UserResource
import org.keycloak.admin.client.resource.UsersResource
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.stereotype.Service


@Service
class KeycloakService(
    private val usersResource: UsersResource
) {

    fun createUser(userName: String, password: String) {
        val user = UserRepresentation()
        user.isEnabled = true
        user.username = userName
        user.email = "tom+tester1@tdlabs.local"

        val response = usersResource.create(user)
        val userId: String = CreatedResponseUtil.getCreatedId(response)

        val passwordCred = CredentialRepresentation()
        passwordCred.isTemporary = false
        passwordCred.type = CredentialRepresentation.PASSWORD
        passwordCred.value = password

        val userResource: UserResource = usersResource.get(userId)
        userResource.resetPassword(passwordCred)
    }

}
