package pl.arekn.bsj.investment;

import com.google.common.collect.ImmutableSet;
import org.testng.annotations.Test;
import pl.arekn.bsj.investment.distribution.DistributedFund;
import pl.arekn.bsj.investment.distribution.DistributedPercentFund;
import pl.arekn.bsj.investment.distribution.Distribution;
import pl.arekn.bsj.investment.fund.Fund;
import pl.arekn.bsj.investment.style.InvestmentStyle;
import pl.arekn.bsj.investment.style.InvestmentStyleFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.arekn.bsj.TestData.*;

public class InvestmentDistributorTest {
    private InvestmentStyleFactory factory = new InvestmentStyleFactory();


    private InvestmentDistributor cut = new InvestmentDistributor();

    @Test
    public void calculateShouldSpreadInvestmentSafelyWithNoReminder() throws Exception {
        // given
        ImmutableSet<Fund> funds = getFundsScenario1();
        Long amount = 10000L;
        InvestmentStyle safeInvestmentStyle = factory.getSafeInvestmentStyle();
        Investment.InvestmentBuilder builder = Investment.builder();
        Investment investment = builder.amount(amount).funds(funds).investmentStyle(safeInvestmentStyle).build();

        // when
        Distribution distribution = cut.distribute(investment);

        // then
        assertThat(distribution.getRemainder()).isEqualTo(0L);
        assertThat(distribution.getDistributedFunds()).containsExactlyInAnyOrder(getExpectedDistribution1());

    }

    @Test
    public void calculateShouldSpreadInvestmentSafelyAndReturnReminder_testScenario1() throws Exception {
        // given
        ImmutableSet<Fund> funds = getFundsScenario1();
        Long amount = 10001L;
        InvestmentStyle safeInvestmentStyle = factory.getSafeInvestmentStyle();
        Investment.InvestmentBuilder builder = Investment.builder();
        Investment investment = builder.amount(amount).funds(funds).investmentStyle(safeInvestmentStyle).build();

        // when
        Distribution distribution = cut.distribute(investment);

        // then
        assertThat(distribution.getRemainder()).isEqualTo(1L);
        assertThat(distribution.getDistributedFunds()).containsExactlyInAnyOrder(getExpectedDistribution1());

    }

    @Test
    public void calculateShouldSpreadInvestmentSafelyAndReturnReminder_testScenario2() throws Exception {
        // given
        ImmutableSet<Fund> funds = getFundsScenario2();
        Long amount = 10000L;
        InvestmentStyle safeInvestmentStyle = factory.getSafeInvestmentStyle();
        Investment.InvestmentBuilder builder = Investment.builder();
        Investment investment = builder.amount(amount).funds(funds).investmentStyle(safeInvestmentStyle).build();

        // when
        Distribution distribution = cut.distribute(investment);

        // then
        assertThat(distribution.getRemainder()).isEqualTo(2L);
        assertThat(distribution.getDistributedFunds()).containsExactlyInAnyOrder(getExpectedDistribution2());

    }

    private ImmutableSet<Fund> getFundsScenario1() {
        return ImmutableSet.of(polishFund1, polishFund2, foreignFund1, foreignFund2, foreignFund3, monetaryFund1);
    }

    private ImmutableSet<Fund> getFundsScenario2() {
        return ImmutableSet.of(polishFund1, polishFund2, polishFund3, foreignFund1, foreignFund2, monetaryFund1);
    }


    private DistributedPercentFund[] getExpectedDistribution1() {
        DistributedPercentFund df1 = new DistributedPercentFund(new DistributedFund(polishFund1, 1000L), 10D);
        DistributedPercentFund df2 = new DistributedPercentFund(new DistributedFund(polishFund2, 1000L), 10D);
        DistributedPercentFund df3 = new DistributedPercentFund(new DistributedFund(foreignFund1, 2500L), 25D);
        DistributedPercentFund df4 = new DistributedPercentFund(new DistributedFund(foreignFund2, 2500L), 25D);
        DistributedPercentFund df5 = new DistributedPercentFund(new DistributedFund(foreignFund3, 2500L), 25D);
        DistributedPercentFund df6 = new DistributedPercentFund(new DistributedFund(monetaryFund1, 500L), 5D);
        return new DistributedPercentFund[]{df1, df2, df3, df4, df5, df6};
    }

    private DistributedPercentFund[] getExpectedDistribution2() {
        DistributedPercentFund df1 = new DistributedPercentFund(new DistributedFund(polishFund1, 666L), 6.66D);
        DistributedPercentFund df2 = new DistributedPercentFund(new DistributedFund(polishFund2, 666L), 6.66D);
        DistributedPercentFund df3 = new DistributedPercentFund(new DistributedFund(polishFund3, 666L), 6.66D);
        DistributedPercentFund df4 = new DistributedPercentFund(new DistributedFund(foreignFund1, 3750L), 37.51D);
        DistributedPercentFund df5 = new DistributedPercentFund(new DistributedFund(foreignFund2, 3750L), 37.51D);
        DistributedPercentFund df6 = new DistributedPercentFund(new DistributedFund(monetaryFund1, 500L), 5D);
        return new DistributedPercentFund[]{df1, df2, df3, df4, df5, df6};
    }
}