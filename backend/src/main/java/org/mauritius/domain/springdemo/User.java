package org.mauritius.domain.springdemo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Created by nn_liu on 2017/2/27.
 */

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(exclude = { "id" })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "password")
    private String password;

}
