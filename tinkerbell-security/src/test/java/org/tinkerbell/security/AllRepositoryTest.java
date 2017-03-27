package org.tinkerbell.security;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.tinkerbell.security.repository.AuthPermissionRepoTest;
import org.tinkerbell.security.repository.AuthRoleRepoTest;
import org.tinkerbell.security.repository.AuthUserRepoTest;

/**
 * Created by nn_liu on 2017/3/8.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({AuthUserRepoTest.class, AuthRoleRepoTest.class, AuthPermissionRepoTest.class})
public class AllRepositoryTest {
}
