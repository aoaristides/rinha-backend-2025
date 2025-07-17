package br.com.makersweb.rinhabackend2025.application.summary.retrieve.get;

import br.com.makersweb.rinhabackend2025.domain.summary.PaymentSummaryGateway;
import br.com.makersweb.rinhabackend2025.infrastructure.summary.models.PaymentSummaryAggregation;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author anderson
 */
public class DefaultGetPaymentSummaryByRequestedAtUseCase extends GetPaymentSummaryByRequestedAtUseCase {

    private final PaymentSummaryGateway paymentSummaryGateway;

    public DefaultGetPaymentSummaryByRequestedAtUseCase(final PaymentSummaryGateway paymentSummaryGateway) {
        this.paymentSummaryGateway = Objects.requireNonNull(paymentSummaryGateway);
    }

    @Override
    public PaymentSummaryOutput execute(final PaymentSummaryCommand aCommand) {
        final var from = aCommand.from();
        final var to = aCommand.to();

        final var paymentSummaries = this.paymentSummaryGateway.findSummariesPaymentsByRequestedAtBetween(from, to);

        final var byDefault = paymentSummaries.stream().filter(PaymentSummaryAggregation::processedAtDefault).findFirst().orElseGet(() -> new PaymentSummaryAggregation(false, BigDecimal.ZERO, 0L));
        final var byFallback = paymentSummaries.stream().filter(p -> !p.processedAtDefault()).findFirst().orElseGet(() -> new PaymentSummaryAggregation(false, BigDecimal.ZERO, 0L));

        return PaymentSummaryOutput.with(
                Totals.with(byDefault.processedAtDefault(), byDefault.totalAmount().intValue(), byDefault.totalCount().intValue()),
                Totals.with(byFallback.processedAtDefault(), byFallback.totalAmount().intValue(), byFallback.totalCount().intValue())
        );
    }
}
