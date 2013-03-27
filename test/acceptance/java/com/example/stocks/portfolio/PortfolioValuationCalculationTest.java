package com.example.stocks.portfolio;

import com.example.stocks.CopyResourcesToOutputFolder;
import org.concordion.api.ExpectedToFail;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.extension.Extension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
@ExpectedToFail
public class PortfolioValuationCalculationTest {

    @Extension public ConcordionExtension extension = new CopyResourcesToOutputFolder(this.getClass());

}
