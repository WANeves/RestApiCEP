package com.restapicep.error;

public class RecordNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(String description) {
        super(description);
    }
}
