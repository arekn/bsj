package pl.arekn.bsj.investment.distribution;

import lombok.Value;

@Value
public class DistributedPercentFund {
    private final DistributedFund distributedFund;
    private final Double percent;
}
