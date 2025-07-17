package br.com.makersweb.rinhabackend2025.application.summary.retrieve.get;


/**
 * @author anderson
 * @param defaults
 * @param fallback
 */
public record PaymentSummaryOutput(
        Totals defaults,
        Totals fallback
) {

    public static PaymentSummaryOutput with(final Totals defaults, final Totals fallback) {
        return new PaymentSummaryOutput(defaults, fallback);
    }

}
