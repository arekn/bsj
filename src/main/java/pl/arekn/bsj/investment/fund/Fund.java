package pl.arekn.bsj.investment.fund;

import lombok.Value;

@Value
public class Fund {
    private final Long id;
    private final FundType type;
    private final String name;
}
