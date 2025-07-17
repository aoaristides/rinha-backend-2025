package br.com.makersweb.rinhabackend2025.domain.summary;

import br.com.makersweb.rinhabackend2025.infrastructure.summary.models.PaymentSummaryAggregation;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author anderson
 */
public interface PaymentSummaryGateway {

    List<PaymentSummaryAggregation>  findSummariesPaymentsByRequestedAtBetween(LocalDateTime from, LocalDateTime to);

}
