package ru.belov.keycloakadmin.configuration

import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.admin.client.resource.RealmResource
import org.keycloak.admin.client.resource.UserResource
import org.keycloak.admin.client.resource.UsersResource
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class KeycloakAdminConfiguration {

    @Bean
    fun keycloakAdmin(): Keycloak = KeycloakBuilder
        .builder()
        .serverUrl(URL)
        .realm(REALM)
        .grantType(OAuth2Constants.PASSWORD)
        .clientId("admin-cli")
        .username("admin")
        .password("admin")
        .build()

    @Bean
    fun realmResource(keycloak: Keycloak): RealmResource = keycloak.realm(REALM)

    @Bean
    fun userResource(realmResource: RealmResource): UsersResource = realmResource.users()

    companion object {
        const val REALM = "test-realm"
        const val URL = "http://localhost:8000/auth"
    }

}
