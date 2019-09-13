package com.nataliia;

import java.time.LocalDate;


public class ValidationUtil {

    public static void validateFromToDates(DateWrapper from, DateWrapper to) {
        if (!(from.getLocalDate().isBefore(to.getLocalDate()))) {
            throw new DateNotValidException("Validation failed");
        }
    }

    public static void validateFromToDates(LocalDate from, LocalDate to) {
        validateFromToDates(inclusive(from), exclusive(to));
    }

    public static void validateFromToDates(DateWrapper from, LocalDate to) {
        validateFromToDates(from, exclusive(to));
    }

    public static void validateFromToDates(LocalDate from, DateWrapper to) {
        validateFromToDates(inclusive(from), to);
    }

    public static DateWrapper inclusive(LocalDate date) {
        return DateWrapper.inclusive(date);
    }

    public static DateWrapper inclusive(DateWrapper dateWrapper) {
        return dateWrapper.inclusive();
    }

    public static DateWrapper exclusive(LocalDate date) {
        return DateWrapper.exclusive(date);
    }

    public static DateWrapper exclusive(DateWrapper dateWrapper) {
        return dateWrapper.exclusive();
    }

    public static DateWrapper canBePast(LocalDate date) {
        return DateWrapper.canBePast(date);
    }

    public static DateWrapper canBePast(DateWrapper dateWrapper) {
        return dateWrapper.canBePast();
    }

    public static DateWrapper mustBeFuture(LocalDate date) {
        return DateWrapper.mustBeFuture(date);
    }

    public static DateWrapper mustBeFuture(DateWrapper dateWrapper) {
        return dateWrapper.mustBeFuture();
    }

}
