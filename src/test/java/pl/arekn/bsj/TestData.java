package pl.arekn.bsj;

import pl.arekn.bsj.investment.fund.Fund;
import pl.arekn.bsj.investment.fund.FundType;

public class TestData {

    public final static Fund polishFund1 = new Fund(11L, FundType.POLISH, "Fund PL 1");
    public final static Fund polishFund2 = new Fund(12L, FundType.POLISH, "Fund PL 2");
    public final static Fund polishFund3 = new Fund(13L, FundType.POLISH, "Fund PL 3");
    public final static Fund foreignFund1 = new Fund(21L, FundType.FOREIGN, "Fund FR 1");
    public final static Fund foreignFund2 = new Fund(22L, FundType.FOREIGN, "Fund FR 2");
    public final static Fund foreignFund3 = new Fund(23L, FundType.FOREIGN, "Fund FR 3");
    public final static Fund monetaryFund1 = new Fund(31L, FundType.MONETARY, "Fund MO 1");
}