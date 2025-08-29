package az.ingress.service.specification;

import az.ingress.dao.enitty.PaymentEntity;
import az.ingress.model.criteria.PaymentCriteria;
import az.ingress.model.enums.PaymentStatus;
import az.ingress.util.PredicateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static az.ingress.dao.enitty.PaymentEntity.Fields;

@AllArgsConstructor
@Data
public class PaymentSpecification implements Specification<PaymentEntity> {

    private PaymentCriteria paymentCriteria;


    @Override
    public Predicate toPredicate(Root<PaymentEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        var predicates = PredicateUtil.builder()
                .addNullSafety(paymentCriteria.getAmountFrom(),
                        amountFrom -> cb.greaterThanOrEqualTo(root.get(Fields.amount), amountFrom))
                .addNullSafety(paymentCriteria.getAmountTo(),
                        amountTo -> cb.lessThanOrEqualTo(root.get(Fields.amount), amountTo))
                .addNullSafety(paymentCriteria.getCurrency(),
                        currency -> cb.equal(root.get(Fields.currency), currency))
                .addNullSafety(paymentCriteria.getDateFrom(),
                        dateFrom -> cb.greaterThanOrEqualTo(root.get(Fields.createdAt), dateFrom))
                .addNullSafety(paymentCriteria.getDateTo(),
                        dateTo -> cb.lessThanOrEqualTo(root.get(Fields.createdAt), dateTo))
                .addNullSafety(paymentCriteria.getPaymentType(),
                        paymentType -> cb.equal(root.get(Fields.paymentType), paymentType))
                .add(PaymentStatus.DELETED,
                        status -> cb.notEqual(root.get(Fields.status), status))
                .build();
        return cb.and(predicates);
    }
}
