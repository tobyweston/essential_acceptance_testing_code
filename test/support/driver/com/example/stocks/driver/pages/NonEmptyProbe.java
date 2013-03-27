package com.example.stocks.driver.pages;

import com.google.code.tempusfugit.temporal.ProbeFor;
import com.google.code.tempusfugit.temporal.SelfDescribingCondition;
import org.hamcrest.Description;

public class NonEmptyProbe implements SelfDescribingCondition {

    private final ProbeFor<String> probe;

    public static NonEmptyProbe nonEmpty(ProbeFor<String> probe) {
        return new NonEmptyProbe(probe);
    }

    private NonEmptyProbe(ProbeFor<String> probe) {
        this.probe = probe;
    }

    @Override
    public boolean isSatisfied() {
        return !probe.call().isEmpty();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a non-empty ").appendDescriptionOf(probe);
    }
}
