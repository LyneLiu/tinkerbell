package org.tinkerbell.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 定制/health
 * Created by nn_liu on 2017/4/1.
 */

@Component
public class CustomHealth implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = checkHandler();
        if (errorCode != 0){

            return Health.down().withDetail("Error info:",buildMsg(errorCode)).build();
        }

        return Health.up().build();
    }

    private int checkHandler(){
        // TODO: perform some specific health check
        return 0;
    }

    private String buildMsg(int code){
        // TODO: build error code message
        return null;
    }
}
