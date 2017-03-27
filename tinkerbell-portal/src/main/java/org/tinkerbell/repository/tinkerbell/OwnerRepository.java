package org.tinkerbell.repository.tinkerbell;

import org.tinkerbell.entity.po.tinkerbell.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by nn_liu on 2017/2/27.
 */
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    Owner findByOwnerName(String lyne);

    /*Note：from 后跟实体类名，注意大小写*/
    @Query("select a from Owner a where a.ownerAddress like %:address% ")
    List<Owner> findByOwnerAddress(@Param("address") String address);

    Owner findByOwnerId(Integer id);
}
