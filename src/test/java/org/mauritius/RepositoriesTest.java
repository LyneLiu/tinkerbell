package org.mauritius;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mauritius.domain.springdemo.User;
import org.mauritius.domain.springdemo.UserRepository;
import org.mauritius.domain.tinkerbell.Owner;
import org.mauritius.domain.tinkerbell.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @Commit 注解提交测试；@Rollback 注解回滚测试。
 * http://stackoverflow.com/questions/29640989/spring4-junit4-test-transactionconfiguration-vs-transactional-use-only-one-o
 *
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
        User user = userRepository.findByFirstName("spring");
        Assert.assertEquals(2, userRepository.count());
    }

    @Test
    @Commit
    public void testOwnerRepositories() {
        Owner owner = new Owner();
        owner.setOwnerName("lyne");
        owner.setOwnerAge("20");
        owner.setOwnerAddress("shanghai");

        ownerRepository.save(owner);

        Owner owner1 = ownerRepository.findByOwnerName("lyne");

        Assert.assertEquals(1, ownerRepository.findAll().size());
    }

}
