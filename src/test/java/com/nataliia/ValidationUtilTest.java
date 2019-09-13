package com.nataliia;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static com.nataliia.ValidationUtil.canBePast;
import static com.nataliia.ValidationUtil.exclusive;
import static com.nataliia.ValidationUtil.inclusive;
import static com.nataliia.ValidationUtil.mustBeFuture;
import static com.nataliia.ValidationUtil.validateFromToDates;

public class ValidationUtilTest {

    private LocalDate fromDateInPast = LocalDate.of(2015, Month.JANUARY, 5);
    private LocalDate fromDateInFuture = LocalDate.of(2025, Month.JANUARY, 5);


    private LocalDate toDateInPast = LocalDate.of(2015, Month.APRIL, 5);
    private LocalDate toDateInPastTheSameDate = LocalDate.of(2015, Month.JANUARY, 5);
    private LocalDate toDateInFuture = LocalDate.of(2025, Month.MARCH, 5);
    private LocalDate toDateInFutureTheSameDate = LocalDate.of(2025, Month.JANUARY, 5);
    private LocalDate toDateAsToDay = LocalDate.now();

    @Test(expected = DateNotValidException.class)
    public void validateToAndFromMixedUp() {
        validateFromToDates(toDateInPast, fromDateInPast);
    }

    @Test
    public void validateDatesInPast() {
        validateFromToDates(canBePast(fromDateInPast), inclusive(canBePast(toDateInPast)));
    }

    @Test
    public void validateDatesInPastTheSameDate() {
        validateFromToDates(canBePast(fromDateInPast), inclusive(canBePast(toDateInPastTheSameDate)));
    }

    @Test(expected = DateNotValidException.class)
    public void validateDatesInFutureTheSameDate() {
        validateFromToDates(mustBeFuture(fromDateInFuture), exclusive(mustBeFuture(toDateInFutureTheSameDate)));
    }

    @Test
    public void validateDatesInFuture() {
        validateFromToDates(mustBeFuture(fromDateInFuture), inclusive(mustBeFuture(toDateInFuture)));
    }

    @Test
    public void validateDatesFromPastToFuture() {
        validateFromToDates(canBePast(fromDateInPast), inclusive(mustBeFuture(toDateInFuture)));
    }

    @Test
    public void validateDatesFromPastToNowInclusive() {
        validateFromToDates(canBePast(fromDateInPast), inclusive(toDateAsToDay));
    }

    @Test(expected = DateNotValidException.class)
    public void validateDatesFromPastToNowExclusive() {
        validateFromToDates(canBePast(fromDateInPast), exclusive(toDateAsToDay));
    }
    
}