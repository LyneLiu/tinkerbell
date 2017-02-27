package org.mauritius;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mauritius.domain.springdemo.UserRepository;
import org.mauritius.domain.tinkerbell.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by nn_liu on 2017/2/27.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class RepositoriesTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    public void testUserRepositories() {
        Assert.assertEquals(2, userRepository.findAll().size());
    }

    @Test
    public void testOwnerRepositories() {
        Assert.assertEquals(2, ownerRepository.findAll().size());
    }

}
