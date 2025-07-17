package br.com.makersweb.rinhabackend2025.application.summary.retrieve.get;

import java.time.LocalDateTime;

/**
 * @author anderson
 * @param from
 * @param to
 */
public record PaymentSummaryCommand(
        LocalDateTime from,
        LocalDateTime to
) {

    public static PaymentSummaryCommand with(final LocalDateTime from, final LocalDateTime to) {
        return new PaymentSummaryCommand(from, to);
    }

}
