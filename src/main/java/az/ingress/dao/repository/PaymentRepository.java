package az.ingress.dao.repository;

import az.ingress.dao.enitty.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Long>, JpaSpecificationExecutor<PaymentEntity> {
}
