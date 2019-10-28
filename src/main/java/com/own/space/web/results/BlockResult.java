package com.own.space.web.results;

import com.own.space.domain.AbstractBaseBlock;
import org.springframework.http.ResponseEntity;

public class BlockResult {
    public static <T extends AbstractBaseBlock> ResponseEntity<RequestResult> build(T newBlock) {
        return null;
    }

    public static <T extends AbstractBaseBlock> ResponseEntity<RequestResult> buildCreateResult(T newBlock) {
        return null;
    }

    public static <T extends AbstractBaseBlock> ResponseEntity<RequestResult> buildUpdateResult(T newBlock) {
        return null;
    }

    public static ResponseEntity<RequestResult> buildDeleteResult(boolean deleted) {
        return null;
    }
}
