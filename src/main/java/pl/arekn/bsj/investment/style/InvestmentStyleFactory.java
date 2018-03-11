package pl.arekn.bsj.investment.style;

import com.google.common.collect.ImmutableMap;
import pl.arekn.bsj.investment.fund.FundType;

public class InvestmentStyleFactory {

    public InvestmentStyle getSafeInvestmentStyle() {
        return new InvestmentStyle(ImmutableMap.of(
                FundType.POLISH, 20,
                FundType.FOREIGN, 75,
                FundType.MONETARY, 5));
    }

    public InvestmentStyle getBalancedInvestmentStyle() {
        return new InvestmentStyle(ImmutableMap.of(
                FundType.POLISH, 30,
                FundType.FOREIGN, 60,
                FundType.MONETARY, 10));
    }

    public InvestmentStyle getAggressiveInvestmentStyle() {
        return new InvestmentStyle(ImmutableMap.of(
                FundType.POLISH, 40,
                FundType.FOREIGN, 20,
                FundType.MONETARY, 40));
    }
}
