package com.nataliia;

import java.time.LocalDate;

public class DateWrapper {
    private LocalDate localDate;
    private boolean canBePast = false;
    private boolean isInclusive = false;

    private DateWrapper(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        if (isInclusive) {
            return localDate;
        } else {
            return localDate.minusDays(1);
        }
    }

    public static DateWrapper inclusive(LocalDate localDate) {
        DateWrapper dateWrapper = new DateWrapper(localDate);
        dateWrapper.isInclusive = true;
        validate(dateWrapper);
        return dateWrapper;
    }

    public DateWrapper inclusive() {
        isInclusive = true;
        validate(this);
        return this;
    }

    public static DateWrapper exclusive(LocalDate localDate) {
        DateWrapper dateWrapper = new DateWrapper(localDate);
        dateWrapper.isInclusive = false;
        validate(dateWrapper);
        return dateWrapper;
    }

    public DateWrapper exclusive() {
        isInclusive = false;
        validate(this);
        return this;
    }

    public static DateWrapper canBePast(LocalDate localDate) {
        DateWrapper dateWrapper = new DateWrapper(localDate);
        dateWrapper.canBePast = true;
        validate(dateWrapper);
        return dateWrapper;
    }

    public DateWrapper canBePast() {
        canBePast = true;
        validate(this);
        return this;
    }

    public static DateWrapper mustBeFuture(LocalDate localDate) {
        DateWrapper dateWrapper = new DateWrapper(localDate);
        dateWrapper.isInclusive = false;
        dateWrapper.canBePast = false;
        validate(dateWrapper);
        return dateWrapper;
    }

    public DateWrapper mustBeFuture() {
        isInclusive = false;
        canBePast = false;
        validate(this);
        return this;
    }

    private static void validate(DateWrapper dateWrapper) {
        if (!dateWrapper.canBePast) {
            if (dateWrapper.isInclusive) {
                LocalDate yesterday = LocalDate.now().minusDays(1);
                if (!dateWrapper.localDate.isAfter(yesterday)) {
                    throw new DateNotValidException("Date should be in the future(inclusive)");
                }
            } else {
                LocalDate today = LocalDate.now();
                if (!dateWrapper.localDate.isAfter(today)) {
                    throw new DateNotValidException("Date should be in the future");
                }
            }
        }
    }
}
