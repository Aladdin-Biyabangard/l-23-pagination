package az.ingress.model.criteria;

import az.ingress.model.enums.Currency;
import az.ingress.model.enums.PaymentStatus;
import az.ingress.model.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCriteria {

    private BigDecimal amountFrom;
    private BigDecimal amountTo;
    private Currency currency;
    private PaymentStatus status;
    private PaymentType paymentType;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
