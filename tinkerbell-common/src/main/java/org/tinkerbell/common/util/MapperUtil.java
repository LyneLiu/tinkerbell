package org.tinkerbell.common.util;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by nn_liu on 2017/3/13.
 */
public class MapperUtil {

    public static <FROM, TO> List<TO> mapList(List<FROM> fromList, final Class<TO> toClass) {
        return Lists.transform(fromList, new Function<FROM, TO>() {
            @Override
            public TO apply(FROM from) {
                return null;
            }
        });
    }

}
