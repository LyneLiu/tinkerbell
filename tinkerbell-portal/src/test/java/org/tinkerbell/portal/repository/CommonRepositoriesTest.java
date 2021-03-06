package org.tinkerbell.portal.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tinkerbell.TinkerbellApplication;
import org.tinkerbell.common.TinkerbellCommonApplication;
import org.tinkerbell.entity.po.springdemo.User;
import org.tinkerbell.entity.po.tinkerbell.Owner;
import org.tinkerbell.repository.springdemo.UserRepository;
import org.tinkerbell.repository.tinkerbell.OwnerRepository;

/**
 * @Commit 注解提交测试; @Rollback 注解回滚测试。
 * http://stackoverflow.com/questions/29640989/spring4-junit4-test-transactionconfiguration-vs-transactional-use-only-one-o
 * <p>
 * Created by nn_liu on 2017/2/27.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TinkerbellCommonApplication.class, TinkerbellApplication.class})
@EnableAutoConfiguration
public class CommonRepositoriesTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    public void testUserRepositories() {

        User user = userRepository.findByFirstName("spring");

        User user1 = new User();
        user1.setFirstName("lyne");
        user1.setLastName("liu");
        user1.setPassword("123456");

        //userRepository.save(user1);
        Assert.assertEquals(10, userRepository.count());
    }

    @Test
    public void testOwnerRepositories() {

        Owner owner = new Owner();
        owner.setOwnerId(0); // protection
        owner.setOwnerName("lyne");
        owner.setOwnerAge("20");
        owner.setOwnerAddress("shanghai");

        /* 可以通过程序逻辑判断数据的唯一性 */
        if (ownerRepository.findByOwnerName("lyne") == null) {
            ownerRepository.save(owner);
        }

        Assert.assertEquals(2, ownerRepository.findAll().size());
        Assert.assertEquals(1, ownerRepository.findByOwnerAddress("shanghai").size());
    }


}
