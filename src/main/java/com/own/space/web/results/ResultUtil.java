package com.own.space.web.results;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

public class ResultUtil {

    private ResultUtil(){

    }
    public static ResponseEntity<RequestResult> created() {
        return ResponseEntity.status(201).build();
    }

    public static ResponseEntity<RequestResult> ok() {
        return ResponseEntity.ok().build();
    }

    public static ResponseEntity<RequestResult> ok(String message) {
        return ok(RequestResult.withMessage(message));
    }

    public static ResponseEntity<RequestResult> ok(@NonNull RequestResult result) {
        return ResponseEntity.ok(result);
    }

    public static ResponseEntity<RequestResult> error(String message) {
        return ResponseEntity.badRequest().body(RequestResult.withMessage(message));
    }

    public static ResponseEntity<RequestResult> serverError(String message, String errorCode) {
        return ResponseEntity.status(500).body(RequestResult.withError(message, errorCode));
    }

    public static ResponseEntity<RequestResult> notFound() {
        return ResponseEntity.notFound().build();
    }

    public static ResponseEntity<RequestResult> unauthenticated() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public static ResponseEntity<RequestResult> forbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
