package com.example.stocks.driver.pages;

import com.google.code.tempusfugit.temporal.ProbeFor;
import com.google.code.tempusfugit.temporal.SelfDescribingCondition;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ProbeMatcherCondition<T> implements SelfDescribingCondition {

    private final ProbeFor<T> probe;
    private final Matcher<T> matcher;

    public static <T> ProbeMatcherCondition probe(ProbeFor<T> probe, Matcher<T> matcher) {
        return new ProbeMatcherCondition(probe, matcher);
    }

    public ProbeMatcherCondition(ProbeFor<T> probe, Matcher<T> matcher) {
        this.probe = probe;
        this.matcher = matcher;
    }

    @Override
    public boolean isSatisfied() {
        return matcher.matches(probe.call());
    }

    @Override
    public void describeTo(Description description) {
        description.appendDescriptionOf(matcher).appendDescriptionOf(probe);
    }
}
