package pl.arekn.bsj.investment.style;

import com.google.common.collect.ImmutableMap;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.arekn.bsj.investment.fund.FundType;

import static org.assertj.core.api.Assertions.assertThat;

public class InvestmentStyleTest {

    private static final FundType TEST_FUND_TYPE = FundType.POLISH;
    private static final int TEST_PERCENT_VALUE = 13;
    private InvestmentStyle cut;

    @BeforeMethod
    public void setUp() throws Exception {
        ImmutableMap<FundType, Integer> percents = ImmutableMap.of(TEST_FUND_TYPE, TEST_PERCENT_VALUE);
        cut = new InvestmentStyle(percents);
    }

    @Test
    public void getInvestmentPercentShouldReturnExpectedPercentValue() throws Exception {
        // given

        // when
        int investmentPercent = cut.getInvestmentPercent(TEST_FUND_TYPE);

        // then
        assertThat(investmentPercent).isEqualTo(TEST_PERCENT_VALUE);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getInvestmentPercentShouldThrowExceptionWhenPercentIsNotProvided() throws Exception {
        // given
        FundType notProvidedFundType = FundType.FOREIGN;

        // when
        cut.getInvestmentPercent(notProvidedFundType);

        // then
    }
}