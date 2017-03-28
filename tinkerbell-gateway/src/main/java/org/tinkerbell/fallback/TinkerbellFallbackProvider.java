package org.tinkerbell.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.tinkerbell.domain.ResultInfo;
import org.tinkerbell.util.ReturnInfoEnum;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by nn_liu on 2017/3/28.
 */

@Component
public class TinkerbellFallbackProvider implements ZuulFallbackProvider {

    @Override
    public String getRoute() {
        return "tinkerbell";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {

        return new ClientHttpResponse() {

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                ResultInfo resultInfo = new ResultInfo<>(ReturnInfoEnum.ERROR_SERVICE.getState(),
                        ReturnInfoEnum.ERROR_SERVICE.getStateInfo() + ";服务名为：" + TinkerbellFallbackProvider.this.getRoute());
                return new ByteArrayInputStream(resultInfo.toString().getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }

        };
    }
}
