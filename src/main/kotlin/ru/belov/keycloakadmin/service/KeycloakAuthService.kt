package ru.belov.keycloakadmin.service

import org.keycloak.OAuth2Constants.*
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.representations.AccessTokenResponse
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForEntity
import ru.belov.keycloakadmin.configuration.KeycloakAdminConfiguration.Companion.REALM
import ru.belov.keycloakadmin.configuration.KeycloakAdminConfiguration.Companion.URL
import java.util.NoSuchElementException

@Service
class KeycloakAuthService {

    private val clientId = "test-client"
    private val clientSecret = "NrTkrXeu3V5BLBArOrLUtmkIEQJwxISc"
    private val refreshUrl = "$URL/realms/$REALM/protocol/openid-connect/token"


    // not supported out of the box
    fun refreshAccessToken(refreshToken: String): AccessTokenResponse {
        val restTemplate = RestTemplate()

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED

        val map = LinkedMultiValueMap<String, String>()
        map["refresh_token"] = refreshToken
        map["client_id"] = clientId
        map["grant_type"] = REFRESH_TOKEN
        map["client_secret"] = clientSecret

        val request = HttpEntity<MultiValueMap<String, String>>(map, headers)

        return restTemplate
            .postForEntity<AccessTokenResponse>(refreshUrl, request, AccessTokenResponse::class)
            .body ?: throw NoSuchElementException("body not found")
    }


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
        .clientId(clientId)
        .clientSecret(clientSecret)


}