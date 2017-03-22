package org.mauritius;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by nn_liu on 2017/3/22.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesConfigTest {

    @Value("${guest_name}")
    private String username;

    private String url;

    @Test
    public void testPropertiesConfig(){
        assertEquals("lyne",username);
    }

}
