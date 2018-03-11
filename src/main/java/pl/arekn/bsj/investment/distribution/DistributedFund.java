package pl.arekn.bsj.investment.distribution;

import lombok.Value;
import pl.arekn.bsj.investment.fund.Fund;

@Value
public class DistributedFund {
    private final Fund fund;
    private final Long amount;
}
