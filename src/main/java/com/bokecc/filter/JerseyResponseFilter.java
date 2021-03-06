package com.bokecc.filter;

import com.bokecc.constant.Constant;
import com.bokecc.supports.RestResponse;
import com.bokecc.supports.CommonErrorCode;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;


/**
 * 拦截器 -> 用于修改HTTP状态码
 * 默认允许所有跨域支持
 **/

@Slf4j
public class JerseyResponseFilter implements ContainerResponseFilter {


    private static final String HTTP_METHOD = "OPTIONS";

    private static final String ORIGIN = "Origin";

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        cross(requestContext, responseContext);

        Object entity = responseContext.getEntity();

        if(entity instanceof RestResponse) {

            RestResponse response = (RestResponse) responseContext.getEntity();

            if (null != response.getCode() && !response.getCode().equals(CommonErrorCode.UNKNOWN_ERROR.getCode())) {

                responseContext.setStatus(Constant.ERROR_HTTP_CODE);
            }
        }
    }

    /**
     * 跨域
     * @param requestContext request
     * @param responseContext response

     */
    private void cross(ContainerRequestContext requestContext, ContainerResponseContext responseContext){

        if(HTTP_METHOD.equalsIgnoreCase(requestContext.getMethod())) {

            responseContext.setStatus(200);
        }


        String origin = requestContext.getHeaderString(ORIGIN);

        responseContext.getHeaders().add("Access-Control-Allow-Origin", origin);

        responseContext.getHeaders().add("Access-Control-Allow-Headers", "*");

        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");

        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");

        //CORS策略的缓存时间
        responseContext.getHeaders().add("Access-Control-Max-Age", "86400");
    }
}
