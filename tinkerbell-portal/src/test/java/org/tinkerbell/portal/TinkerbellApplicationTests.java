package org.tinkerbell.portal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tinkerbell.TinkerbellApplication;
import org.tinkerbell.TinkerbellCommonApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TinkerbellCommonApplication.class, TinkerbellApplication.class})
public class TinkerbellApplicationTests {

	@Test
	public void contextLoads() {
	}

}
