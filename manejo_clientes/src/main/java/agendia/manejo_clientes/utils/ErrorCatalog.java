package agendia.manejo_clientes.utils;

public enum ErrorCatalog {

    CLIENT_NOT_FOUND("ERR_CLIENT_001", "Client not found"),
    INVALID_CLIENT("ERR_CLIENT_002", "Invalid client parameters"),
    GENERIC_ERROR("ERR_GEN_001", "An unexpected error");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
