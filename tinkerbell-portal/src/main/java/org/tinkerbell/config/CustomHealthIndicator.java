package org.tinkerbell.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.tinkerbell.common.HealthResultEnum;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 定制 /health
 * Created by nn_liu on 2017/4/1.
 */

@Component
public class CustomHealthIndicator implements HealthIndicator {

    private static final Logger logger = LoggerFactory.getLogger(CustomHealthIndicator.class);

    @Override
    public Health health() {
        int errorCode = checkHandler();
        if (errorCode != 0) {

            return Health.down().withDetail("Error info:", buildMsg(errorCode)).build();
        }

        return Health.up().build();
    }

    private int checkHandler() {

        InetAddress inet;
        int resultCode = 0;

        try {
            /*custom ping health check*/
            // 1、回路测试，验证本机的TCP/IP协议簇是否正常
            inet = InetAddress.getByAddress(new byte[]{127, 0, 0, 1});
            logger.info("Sending Ping Request to " + inet);
            if (!inet.isReachable(500)) {
                resultCode = -1;
            }

            // 2、测试DNS服务器是否正常
            inet = InetAddress.getByAddress(new byte[]{(byte)192, (byte)168, 102, 20});
            logger.info("Sending Ping Request to " + inet);
            if (!inet.isReachable(500)) {
                resultCode = -2;
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultCode;
    }

    private String buildMsg(int code) {
        String healthMsg = null;
        switch (code) {
            case 0:
                healthMsg = HealthResultEnum.HEALTHY.getCodeDesc();
                break;
            case -1:
                healthMsg = HealthResultEnum.LOCAL_ERROR.getCodeDesc();
                break;
            case -2:
                healthMsg = HealthResultEnum.DNS_ERROR.getCodeDesc();
                break;
            default:
                break;

        }
        return healthMsg;
    }
}
