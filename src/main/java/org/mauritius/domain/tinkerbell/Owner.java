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

    @Column(name = "ownername")
    private String ownerName;

    @Column(name = "ownerage")
    private String ownerAge;

    @Column(name = "owneraddress")
    private String ownerAddress;

    @Column(name = "datachange_lasttime")
    private Timestamp dataChange_LastTime;

}
