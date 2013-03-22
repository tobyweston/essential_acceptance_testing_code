package com.example.stocks.portfolio;

import org.concordion.api.Resource;
import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;

public class JQuery implements ConcordionExtension {

    @Override
    public void addTo(ConcordionExtender concordionExtender) {
        concordionExtender.withLinkedJavaScript("/jquery-1.9.1.js", new Resource("/jquery-1.9.1.js"));
        concordionExtender.withLinkedJavaScript("/specifications.js", new Resource("/specifications.js"));
        concordionExtender.withResource("/info.jpg", new Resource("/info.jpg"));
    }
}