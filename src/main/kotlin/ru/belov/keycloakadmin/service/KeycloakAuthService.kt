package ru.belov.keycloakadmin.service

import org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS
import org.keycloak.OAuth2Constants.PASSWORD
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.representations.AccessTokenResponse
import org.springframework.stereotype.Service
import ru.belov.keycloakadmin.configuration.KeycloakAdminConfiguration.Companion.REALM
import ru.belov.keycloakadmin.configuration.KeycloakAdminConfiguration.Companion.URL

@Service
class KeycloakAuthService {

    fun issueAccessToken(userName: String, password: String): AccessTokenResponse =
        buildKeycloakWithPasswordCredentials(userName, password)
            .tokenManager()
            .accessToken

    private fun buildKeycloakWithPasswordCredentials(userName: String, password: String) =
        buildKeycloakWithClientCredentials()
            .grantType(PASSWORD)
            .username(userName)
            .password(password)
            .build()

    private fun buildKeycloakWithClientCredentials() = KeycloakBuilder
        .builder()
        .serverUrl(URL)
        .realm(REALM)
        .grantType(CLIENT_CREDENTIALS)
        .clientId("test-client")
        .clientSecret("NrTkrXeu3V5BLBArOrLUtmkIEQJwxISc")

}