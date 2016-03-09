package com.restapicep.util;

public class ApiResponse {

    private final Status status;
    private final Object data;
    private final ApiError error;

    public ApiResponse(Status status, Object data) {
        this(status, data, null);
    }

    public ApiResponse(Status status, Object data, ApiError error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public Status getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public ApiError getError() {
        return error;
    }

    public static enum Status {
        SUCESSO,
        ERRO
    }

    public static final class ApiError {

        private final String mensagem;

        public ApiError(String mensagem) {

            this.mensagem = mensagem;
        }


        public String getMensagem() {
            return mensagem;
        }
    }
}
