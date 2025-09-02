package az.ingress.service.implementation;

import az.ingress.config.annotation.LogClass;
import az.ingress.dao.enitty.PaymentEntity;
import az.ingress.dao.enitty.PaymentEntity.Fields;
import az.ingress.dao.repository.PaymentRepository;
import az.ingress.exception.NotFoundException;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.criteria.PaymentCriteria;
import az.ingress.model.dto.CustomPage;
import az.ingress.model.dto.PaymentRequest;
import az.ingress.model.dto.PaymentResponse;
import az.ingress.model.enums.PaymentStatus;
import az.ingress.service.absrtaction.PaymentService;
import az.ingress.service.specification.PaymentSpecification;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static az.ingress.model.enums.ExceptionConstants.PAYMENT_NOT_FOUND;

@Service
@LogClass
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ModelMapper modelMapper;
    private final PaymentRepository paymentRepository;

    @Override
    public void createPayment(PaymentRequest request) {
        PaymentEntity payment = modelMapper.map(request, PaymentEntity.class);
        paymentRepository.save(payment);
    }

    @Override
    public void updatePayment(Long id, PaymentRequest request) {
        PaymentEntity payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PAYMENT_NOT_FOUND.getCode(), PAYMENT_NOT_FOUND.getMessage()));
        modelMapper.map(request, payment);
        paymentRepository.save(payment);
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        PaymentEntity payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PAYMENT_NOT_FOUND.getCode(), PAYMENT_NOT_FOUND.getMessage()));
        return modelMapper.map(payment, PaymentResponse.class);
    }

    @Override
    public CustomPage<PaymentResponse> searchPayment(PaymentCriteria paymentCriteria, PageCriteria pageCriteria) {

        var pageRequest = PageRequest.of(pageCriteria.getPage(), pageCriteria.getCount(), Sort.by(Fields.id));
        var paymentSpecification = new PaymentSpecification(paymentCriteria);
        var payments = paymentRepository.findAll(paymentSpecification, pageRequest);
        return new CustomPage<>(
                payments.stream().map(
                        payment ->
                                modelMapper.map(payment, PaymentResponse.class)).toList(),
                payments.getTotalPages(),
                payments.hasNext()
        );
    }

    @Override
    @Transactional
    public void deletePaymentById(Long id) {
        PaymentEntity payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PAYMENT_NOT_FOUND.getCode(), PAYMENT_NOT_FOUND.getMessage()));
        payment.setStatus(PaymentStatus.DELETED);
    }
}
