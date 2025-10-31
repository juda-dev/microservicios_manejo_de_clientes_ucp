package agendia.manejo_clientes.utils;

public enum ErrorCatalog {

    VERIFICATION_CODE_NOT_FOUND("ERR_VERIFICATION_CODE_001"
            , "Verification code not found for this client"),
    VERIFICATION_CODE_EXPIRED("ERR_VERIFICATION_CODE_002"
            , "Verification code expired"),
    INCORRECT_VERIFICATION_CODE("ERR_VERIFICATION_CODE_003"
            , "Incorrect verification code"),
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
