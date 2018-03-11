package pl.arekn.bsj.investment;

import lombok.Builder;
import lombok.Value;
import pl.arekn.bsj.investment.fund.Fund;
import pl.arekn.bsj.investment.style.InvestmentStyle;

import java.util.Set;

@Value
@Builder
public class Investment {
    private final Long amount;
    private final Set<Fund> funds;
    private final InvestmentStyle investmentStyle;
}
