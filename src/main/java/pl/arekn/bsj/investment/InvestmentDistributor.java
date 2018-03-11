package pl.arekn.bsj.investment;

import com.google.common.collect.ImmutableSet;
import pl.arekn.bsj.investment.distribution.DistributedFund;
import pl.arekn.bsj.investment.distribution.DistributedPercentFund;
import pl.arekn.bsj.investment.distribution.Distribution;
import pl.arekn.bsj.investment.fund.Fund;
import pl.arekn.bsj.investment.fund.FundType;
import pl.arekn.bsj.investment.style.InvestmentStyle;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class InvestmentDistributor {

    private static final int ROUND_PRECISION = 100;
    private static final double ROUNDING_PERCENT = 100D;
    private static final long PERCENT_DENOMINATOR = 100L;

    public Distribution distribute(Investment investment) {
        Set<DistributedFund> distributedFunds = initializeDistributedFunds(investment);
        Long investmentAmount = investment.getAmount();
        InvestmentStyle investmentStyle = investment.getInvestmentStyle();

        for (FundType fundType : FundType.values()) {
            Set<Fund> sameTypeFunds = extractFundsByType(investment, fundType);
            Integer fundTypePercent = investmentStyle.getInvestmentPercent(fundType);
            long amountPerFundType = calculateAmountPerType(investmentAmount, fundTypePercent);
            long amountPerSingleFund = amountPerFundType / sameTypeFunds.size();

            Set<DistributedFund> distributedFundSet = sameTypeFunds
                    .stream()
                    .map(fund -> new DistributedFund(fund, amountPerSingleFund))
                    .collect(Collectors.toSet());

            distributedFunds.addAll(distributedFundSet);
        }

        long totalAmountDistributed = sumTotalAmountDistributed(distributedFunds);
        long reminder = investmentAmount - totalAmountDistributed;

        Set<DistributedPercentFund> distributedPercentFunds = distributedFunds.stream().map(df -> {
            double roundOff = calculateRoundedPercent(totalAmountDistributed, df);
            return new DistributedPercentFund(df, roundOff);
        }).collect(Collectors.toSet());

        return new Distribution(ImmutableSet.copyOf(distributedPercentFunds), reminder);
    }

    private double calculateRoundedPercent(long totalAmountDistributed, DistributedFund df) {
        double per = df.getAmount() * ROUNDING_PERCENT / totalAmountDistributed;
        return (double) Math.round(per * ROUND_PRECISION) / ROUND_PRECISION;
    }

    private long sumTotalAmountDistributed(Set<DistributedFund> distributedFunds) {
        return distributedFunds.stream().mapToLong(DistributedFund::getAmount).sum();
    }

    private long calculateAmountPerType(Long investmentAmount, Integer percent) {
        return investmentAmount * percent / PERCENT_DENOMINATOR;
    }

    private Set<Fund> extractFundsByType(Investment investment, FundType fundType) {
        return investment.getFunds().stream().filter(fund -> fundType.equals(fund.getType())).collect(Collectors.toSet());
    }

    private Set<DistributedFund> initializeDistributedFunds(Investment investment) {
        int size = investment.getFunds().size();
        return new HashSet<>(size);
    }
}
