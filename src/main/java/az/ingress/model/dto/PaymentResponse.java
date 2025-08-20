package az.ingress.model.dto;

import az.ingress.model.enums.Currency;
import az.ingress.model.enums.PaymentStatus;
import az.ingress.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

    private Long id;

    private BigDecimal amount;

    private Currency currency;

    private PaymentStatus status;

    private PaymentType paymentType;

    private LocalDateTime createdAt;
}
