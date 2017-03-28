package org.tinkerbell.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tinkerbell.security.TinkerbellSecurityApplication;
import org.tinkerbell.common.TinkerbellCommonApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TinkerbellCommonApplication.class, TinkerbellSecurityApplication.class})
public class TinkerbellSecurityApplicationTests {

	@Test
	public void contextLoads() {
	}

}
