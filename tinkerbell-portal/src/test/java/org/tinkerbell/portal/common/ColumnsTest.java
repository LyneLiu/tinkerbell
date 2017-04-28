package org.tinkerbell.portal.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tinkerbell.TinkerbellApplication;
import org.tinkerbell.common.DBManager;
import org.tinkerbell.common.EntityHandler;
import org.tinkerbell.common.EntityManager;
import org.tinkerbell.common.TinkerbellCommonApplication;

import java.util.List;


/**
 * Created by nn_liu on 2017/4/28.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TinkerbellCommonApplication.class, TinkerbellApplication.class})
public class ColumnsTest {

    @Autowired
    private EntityHandler entityHandler;

    private static final String PATH = "F:\\lyne\\coding_repo\\tinkerbell\\tinkerbell-portal\\src\\main\\java\\org\\tinkerbell\\entity\\po\\tinkerbell";

    private static final String COMMOM_PATH = "F:\\lyne\\coding_repo\\tinkerbell\\tinkerbell-portal\\src\\main\\java\\";

    @Test
    public void testColumns() throws ClassNotFoundException {
        entityHandler.getEntitiesFromPath(PATH,COMMOM_PATH);
        List<EntityManager> entityManagers = entityHandler.getEntitiesFromPos();
        List<DBManager> dbManagers = entityHandler.getDBEntities(entityManagers);
        List<String> poList = EntityHandler.process(entityManagers, dbManagers);
        if (poList != null) {
            System.out.println(">>>>>>>>>>po size:"+poList.size());
            for (int i = 0; i < poList.size(); i++) {
                if (i != poList.size() - 1) {
                    System.out.println(poList.get(i) + ",");
                } else {
                    System.out.println(poList.get(i));
                }

            }
        }
    }
}
