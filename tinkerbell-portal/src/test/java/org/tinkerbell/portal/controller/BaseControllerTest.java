package org.tinkerbell.portal.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.tinkerbell.TinkerbellApplication;
import org.tinkerbell.common.TinkerbellCommonApplication;
import org.tinkerbell.security.util.TokenUtil;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 启动随机端口（WebEnvironment.RANDOM_PORT）
 * Created by nn_liu on 2017/3/23.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TinkerbellCommonApplication.class, TinkerbellApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TokenUtil tokenUtil;

    @Value("${local.server.port}")
    protected int port;

    @Test
    public void exampleTest() {
        String body = this.restTemplate.getForObject("/login", String.class);
        assertThat(body).isEqualTo("login");
    }

    @Test
    public void testRestTemplateWithAuth1() {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        request.set("username", "test_test");
        request.set("password", "test_test");
        Map<String, Object> token = this.restTemplate.withBasicAuth("test_test", "test_test").postForObject("/login", request, Map.class);
        assertNotNull("Wrong response: " + token, token.get("access_token"));
    }

    @Test
    public void testRestTemplateWithAuth2() {
        HttpHeaders headers = new HttpHeaders();
        String token = getToken();
        headers.add(HttpHeaders.COOKIE, "AUTH-TOKEN=" + token);
        HttpEntity entity = new HttpEntity(null, headers);
        ResponseEntity<Map> responseEntity = this.restTemplate.exchange("http://localhost:" + port
                + "/responseEntiry", HttpMethod.GET, entity, Map.class);
        assertEquals("Hello World", responseEntity.getBody().get("content"));
    }

    private String getToken() {
        return tokenUtil.generateJsonWerbToken("test_test");
    }

}
