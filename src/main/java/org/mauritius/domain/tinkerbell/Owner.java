package org.mauritius.domain.tinkerbell;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by nn_liu on 2017/2/27.
 */

@Entity
@Table(name = "owner_table")
@Data
@EqualsAndHashCode(exclude = {"ownerId"})
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ownerId;

    @Column(name = "OwnerName")
    private String ownerName;

    @Column(name = "OwnerAge")
    private String ownerAge;

    @Column(name = "OwnerAddress")
    private String ownerAddress;

    @Column(name = "DataChange_LastTime")
    private Timestamp dataChange_LastTime;

}
