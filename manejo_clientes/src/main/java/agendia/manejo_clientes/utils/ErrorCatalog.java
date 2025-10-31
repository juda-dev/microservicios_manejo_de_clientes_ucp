package agendia.manejo_clientes.utils;

public enum ErrorCatalog {

    CUSTOMER_NOT_FOUND("ERR_CUSTOMER_001", "Customer not found"),
    INVALID_CUSTOMER("ERR_CUSTOMER_002", "Invalid customer parameters"),
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
