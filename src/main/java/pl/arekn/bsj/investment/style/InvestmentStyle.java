package pl.arekn.bsj.investment.style;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import pl.arekn.bsj.investment.fund.FundType;

import java.util.Map;
import java.util.Optional;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class InvestmentStyle {
    private final Map<FundType, Integer> investmentStyle;

    public int getInvestmentPercent(FundType fundType) {
        return Optional
                .ofNullable(investmentStyle.get(fundType))
                .orElseThrow(() -> new IllegalArgumentException("Percent value is not set"));
    }
}

