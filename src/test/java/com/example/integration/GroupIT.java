package com.example.integration;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.GroupsApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(classes = GroupsApplication.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class GroupIT {
    private static final String GROUPS_ENDPOINT = "/groups";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createNewGroup_withAdmin_passes() {
        String json = "{"
            + "\"name\":\"foo\","
            + "\"members\":["
            + "  {"
            + "    \"userId\":1,"
            + "    \"role\":\"ADMIN\""
            + "  }"
            + "]"
            + "}";

        ResponseEntity<Object> response = restTemplate.postForEntity(GROUPS_ENDPOINT, makeRequest(json), Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createNewGroup_thenGet_passes() {
        String json = "{"
            + "\"name\":\"foo\","
            + "\"members\":["
            + "  {"
            + "    \"userId\":1,"
            + "    \"role\":\"ADMIN\""
            + "  }"
            + "]"
            + "}";

        ResponseEntity<Object> r1 = restTemplate.postForEntity(GROUPS_ENDPOINT, makeRequest(json), Object.class);
        assertThat(r1.getStatusCode()).isEqualTo(HttpStatus.OK);
        // TODO: extract groupId from response
        String groupId = "1";

        ResponseEntity<Object> r2 = restTemplate.getForEntity(GROUPS_ENDPOINT + "/" + groupId, Object.class);
        assertThat(r2.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(r2.getBody()).isNotNull();
    }

    @Test
    void createNewGroup_withoutMembers_fails() {
        String json = "{"
            + "\"name\":\"foo\""
            + "}";

        ResponseEntity<Object> response = restTemplate.postForEntity(GROUPS_ENDPOINT, makeRequest(json), Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private static HttpEntity<String> makeRequest(String json) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(json, headers);
    }

}
