package com.own.space.web.results;

import org.springframework.util.Assert;

import java.util.HashMap;

public class RequestResult extends HashMap<String,Object> {

    private static final long serialVersionUID = 99999L;

    private static final String RETURN_MESSAGE = "message";
    private static final String ERROR_CODE = "errorCode";

    public static RequestResult empty() {
        return new RequestResult();
    }

    public static RequestResult withMessage(String message) {
        Assert.hasText(message, "Parameter `message` must not be empty");

        RequestResult apiResult = new RequestResult();
        apiResult.put("message", message);
        return apiResult;
    }

    public static RequestResult withError(String message, String errorCode) {
        Assert.hasText(message, "Parameter `message` must not be empty");
        Assert.hasText(errorCode, "Parameter `errorReferenceCode` must not be blank");

        RequestResult apiResult = new RequestResult();
        apiResult.put(RETURN_MESSAGE, message);
        apiResult.put(ERROR_CODE, errorCode);
        return apiResult;
    }

    public RequestResult add(String key, Object value) {
        Assert.hasText(key, "Parameter `key` must not be blank");
        Assert.notNull(value, "Parameter `value` must not be null");

        this.put(key, value);
        return this;
    }


}
