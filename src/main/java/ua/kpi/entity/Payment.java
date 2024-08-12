package ua.kpi.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Payment(
    String id, String customerName, BigDecimal amount, PaymentStatus status,
    LocalDate date, Currency currency, String paymentMethod, String reference
) {
    public enum PaymentStatus {
        PENDING,
        APPROVED,
        REJECTED,
        FAILED
    }

    public enum Currency {
        USD,
        EUR,
        GBP,
        JPY,
        CAD
    }
}
