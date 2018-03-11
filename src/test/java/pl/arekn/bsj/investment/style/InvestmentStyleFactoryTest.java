package pl.arekn.bsj.investment.style;

import org.testng.annotations.Test;
import pl.arekn.bsj.investment.fund.FundType;

import static org.assertj.core.api.Assertions.assertThat;

public class InvestmentStyleFactoryTest {

    private InvestmentStyleFactory cut = new InvestmentStyleFactory();

    @Test
    public void getSafeInvestmentStyleShouldReturnExpectedInvestmentStyle() throws Exception {
        // given

        // when
        InvestmentStyle safeInvestmentStyle = cut.getSafeInvestmentStyle();

        // then
        assertThat(safeInvestmentStyle.getInvestmentPercent(FundType.POLISH)).isEqualTo(20);
        assertThat(safeInvestmentStyle.getInvestmentPercent(FundType.FOREIGN)).isEqualTo(75);
        assertThat(safeInvestmentStyle.getInvestmentPercent(FundType.MONETARY)).isEqualTo(5);
    }

    @Test
    public void getAggressiveInvestmentStyleShouldReturnExpectedInvestmentStyle() throws Exception {
        // given

        // when
        InvestmentStyle aggressiveInvestmentStyle = cut.getAggressiveInvestmentStyle();

        // then
        assertThat(aggressiveInvestmentStyle.getInvestmentPercent(FundType.POLISH)).isEqualTo(40);
        assertThat(aggressiveInvestmentStyle.getInvestmentPercent(FundType.FOREIGN)).isEqualTo(20);
        assertThat(aggressiveInvestmentStyle.getInvestmentPercent(FundType.MONETARY)).isEqualTo(40);
    }

    @Test
    public void getBalancedInvestmentStyleShouldReturnExpectedInvestmentStyle() throws Exception {
        // given

        // when
        InvestmentStyle balancedInvestmentStyle = cut.getBalancedInvestmentStyle();

        // then
        assertThat(balancedInvestmentStyle.getInvestmentPercent(FundType.POLISH)).isEqualTo(30);
        assertThat(balancedInvestmentStyle.getInvestmentPercent(FundType.FOREIGN)).isEqualTo(60);
        assertThat(balancedInvestmentStyle.getInvestmentPercent(FundType.MONETARY)).isEqualTo(10);
    }
}