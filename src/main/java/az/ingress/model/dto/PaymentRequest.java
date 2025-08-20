package az.ingress.model.dto;

import az.ingress.model.enums.Currency;
import az.ingress.model.enums.PaymentStatus;
import az.ingress.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequest {

    private BigDecimal amount;

    private Currency currency;

    private PaymentStatus status;

    private PaymentType paymentType;
}
