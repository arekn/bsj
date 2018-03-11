package pl.arekn.bsj.investment.distribution;

import lombok.Value;

import java.util.Set;

@Value
public class Distribution {
    private Set<DistributedPercentFund> distributedFunds;
    private Long remainder;
}
