package org.tinkerbell.portal.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.tinkerbell.TinkerbellApplication;
import org.tinkerbell.common.TinkerbellCommonApplication;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void exampleTest() {
        String body = this.restTemplate.getForObject("/login", String.class);
        assertThat(body).isEqualTo("login");
    }

}
