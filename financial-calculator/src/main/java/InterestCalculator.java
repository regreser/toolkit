public class InterestCalculator {
    private double loanAmount; // 贷款金额
    private double loanRate;  // 贷款年利率
    private double interestRateMonthly;  // 月收益率
    private int installmentNumber;  // 分期期数
    private double installmentPayment;  // 月供
    private double serviceCharge;  // 服务费

    public InterestCalculator(double loanAmount, double loanRate, double interestRateMonthly, int installmentNumber, double serviceCharge) {
        this.loanAmount = loanAmount;
        this.loanRate = loanRate;
        this.interestRateMonthly = interestRateMonthly;
        this.installmentNumber = installmentNumber;
        this.serviceCharge = serviceCharge;
        this.installmentPayment = loanAmount / installmentNumber + loanAmount * loanRate / 12;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public double calculateInterestIncome(double principal, int installmentIndex, double totalInterestIncome) {
        if (installmentIndex > installmentNumber) {
            return totalInterestIncome;
        }
        System.out.println("第[" + installmentIndex + "]期：");
        System.out.println("本金：" + principal);
        System.out.println("月供：" + installmentPayment);
        double interestThisMonth = principal * interestRateMonthly;
        System.out.println("利息收入：" + interestThisMonth);
        double principalNextMonth = principal + interestThisMonth - installmentPayment;
        totalInterestIncome += interestThisMonth;
        return calculateInterestIncome(principalNextMonth, installmentIndex + 1, totalInterestIncome);
    }

    public double calculateMargin(double principal, int installmentIndex, double totalInterestIncome) {
        return calculateInterestIncome(principal, installmentIndex, totalInterestIncome) - serviceCharge - loanAmount * loanRate * installmentNumber / 12;
    }
}
