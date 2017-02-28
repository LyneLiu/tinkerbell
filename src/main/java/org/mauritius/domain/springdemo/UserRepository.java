package org.mauritius.domain.springdemo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nn_liu on 2017/2/27.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByFirstName(String spring);

}
