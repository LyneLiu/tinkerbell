package org.mauritius.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mauritius.repository.security.AuthPermissionRepoTest;
import org.mauritius.repository.security.AuthRoleRepoTest;
import org.mauritius.repository.security.AuthUserRepoTest;

/**
 * Created by nn_liu on 2017/3/8.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({CommonRepositoriesTest.class, AuthUserRepoTest.class,
        AuthRoleRepoTest.class, AuthPermissionRepoTest.class})
public class AllRepositoryTest {
}
