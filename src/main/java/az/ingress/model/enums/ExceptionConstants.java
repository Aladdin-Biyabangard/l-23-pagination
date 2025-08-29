package az.ingress.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionConstants {
    PAYMENT_NOT_FOUND("Payment_NOT_FOUND", "Payment with id: %s not found"),
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION", "Unexpected exception occurred");
    private String code;
    private String message;
}
