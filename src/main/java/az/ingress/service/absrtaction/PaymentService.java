package az.ingress.service.absrtaction;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.criteria.PaymentCriteria;
import az.ingress.model.dto.CustomPage;
import az.ingress.model.dto.PaymentRequest;
import az.ingress.model.dto.PaymentResponse;

public interface PaymentService {

    void createPayment(PaymentRequest request);

    void updatePayment(Long id,PaymentRequest request);

    PaymentResponse getPaymentById(Long id);

    CustomPage<PaymentResponse> searchPayment(PaymentCriteria paymentCriteria, PageCriteria pageCriteria);

    void deletePaymentById(Long id);
}
