public class InterestCalculator {
    private double loanAmount;
    private double loanRate;
    private double interestRateMonthly;
    private int installmentNumber;
    private double installmentPayment;
    private double serviceCharge;

    public InterestCalculator(double loanAmount, double loanRate, double interestRateMonthly, int installmentNumber, double serviceCharge) {
        this.loanAmount = loanAmount;
        this.loanRate = loanRate;
        this.interestRateMonthly = interestRateMonthly;
        this.installmentNumber = installmentNumber;
        this.serviceCharge = serviceCharge;
        this.installmentPayment = loanAmount / installmentNumber;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public double calculateInterestIncome(double principal, int installmentIndex, double totalInterestIncome) {
        if (installmentIndex > this.installmentNumber) {
            return totalInterestIncome;
        }
        double interestThisMonth = principal * interestRateMonthly;
        double principalNextMonth = principal + interestThisMonth - this.installmentPayment;
        totalInterestIncome += interestThisMonth;
        return calculateInterestIncome(principalNextMonth, installmentIndex + 1, totalInterestIncome);
    }

    public double calculateLoanResult(double principal, int installmentIndex, double totalInterestIncome) {
        return calculateInterestIncome(principal, installmentIndex, totalInterestIncome) - this.serviceCharge - this.loanAmount * this.loanRate * this.installmentNumber / 12;
    }
}
