package az.ingress.controller;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.criteria.PaymentCriteria;
import az.ingress.model.dto.CustomPage;
import az.ingress.model.dto.PaymentRequest;
import az.ingress.model.dto.PaymentResponse;
import az.ingress.service.absrtaction.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createPayment(@RequestBody PaymentRequest request) {
        paymentService.createPayment(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updatePayment(@PathVariable Long id,
                              @RequestBody PaymentRequest request) {
        paymentService.updatePayment(id, request);
    }

    @GetMapping("/{id}")
    public PaymentResponse getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping("/search")
    public CustomPage<PaymentResponse> searchPayments(PaymentCriteria paymentCriteria,
                                                      PageCriteria pageCriteria) {

        return paymentService.searchPayment(paymentCriteria, pageCriteria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePaymentById(id);
        return ResponseEntity.noContent().build();
    }
}
