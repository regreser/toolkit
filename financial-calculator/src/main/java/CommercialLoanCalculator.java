import org.jfree.data.category.DefaultCategoryDataset;

public class CommercialLoanCalculator {
    private double loanAmount;
    private double loanRateYearly;
    private double loanRateMonthly;
    private int installmentNumber;
    private double serviceChargeRateForRefund;

    public CommercialLoanCalculator() {
    }

    public CommercialLoanCalculator(double loanAmount, double loanRateYearly, int installmentNumber, double serviceChargeRateForRefund) {
        this.loanAmount = loanAmount;
        this.loanRateYearly = loanRateYearly;
        this.loanRateMonthly = loanRateYearly / 12;
        this.installmentNumber = installmentNumber;
        this.serviceChargeRateForRefund = serviceChargeRateForRefund;
    }

    public double calculateLoanRefundTotal(double loanAmount, double loanRateMonthly, int installmentNumber, double loanRefundTotal) {
        // 计算月供
        double monthlyInstallmentPayment = calculateMonthlyInstallmentPayment(loanAmount, loanRateMonthly, installmentNumber);
        // 计算本月偿还的本金
        double repayPrincipal = calculateMonthlyRepayPrincipal(monthlyInstallmentPayment, calculateMontlyInterest(loanAmount, loanRateMonthly));

        loanRefundTotal = loanRefundTotal + monthlyInstallmentPayment;

        installmentNumber -= 1;
        if (installmentNumber == 0)
            return loanRefundTotal;
        return calculateLoanRefundTotal(loanAmount - repayPrincipal, loanRateMonthly, installmentNumber, loanRefundTotal);
    }

    // 计算还款n月后提前还款的盈亏
    public double calculateMarginAfterNInstallment(DefaultCategoryDataset dataset, double investAmount, double investRateMonthly, double loanAmount, double loanRateMonthly, int installmentNumber, int repayInstallmentNumber, double serviceChargeRateForRefund, int finalInstallmentNumber, double payTotal, double incomeTotal) {
        // 计算月供
        double monthlyInstallmentPayment = calculateMonthlyInstallmentPayment(loanAmount, loanRateMonthly, installmentNumber - repayInstallmentNumber);
        // 计算本月偿还的利息
        double interestMontly = calculateMontlyInterest(loanAmount, loanRateMonthly);
        // 计算本月偿还的本金
        double repayPrincipal = calculateMonthlyRepayPrincipal(monthlyInstallmentPayment, interestMontly);

        payTotal += monthlyInstallmentPayment;

        // 计算投资收益
        double investIncomeMonthly = 0;
        if (investAmount > 0) {
            investIncomeMonthly = calculateInvestIncomeMonthly(investAmount, investRateMonthly);
        }
        incomeTotal += investIncomeMonthly;

        investAmount = investAmount + investIncomeMonthly - monthlyInstallmentPayment;
        loanAmount -= repayPrincipal;
        repayInstallmentNumber += 1;

        printPayAndIncome(repayInstallmentNumber, monthlyInstallmentPayment, investIncomeMonthly, investAmount);
        if (repayInstallmentNumber % 10 == 0) {
            dataset.addValue(investAmount - loanAmount, "solution1", String.valueOf(repayInstallmentNumber));
            dataset.addValue(0, "solution2", String.valueOf(repayInstallmentNumber));
        }
        // 偿还到"finalInstallmentNumber"指定的期数
        if (repayInstallmentNumber == finalInstallmentNumber) {
            System.out.println("总收入【"+ incomeTotal +"】，月供总支出【"+ payTotal +"】");
            return investAmount - loanAmount - loanAmount * serviceChargeRateForRefund;
        }
        return calculateMarginAfterNInstallment(dataset, investAmount, investRateMonthly, loanAmount, loanRateMonthly, installmentNumber, repayInstallmentNumber, serviceChargeRateForRefund, finalInstallmentNumber, payTotal, incomeTotal);
    }

    private void printPayAndIncome(int repayInstallmentNumber, double monthlyInstallmentPayment, double investIncomeMonthly, double investAmount) {
        System.out.println("第" + repayInstallmentNumber + "个月：剩余【" + investAmount + "】盈亏【" + (investIncomeMonthly - monthlyInstallmentPayment) + "】支出【" + monthlyInstallmentPayment + "】，收入【" + investIncomeMonthly + "】");
    }


    private double calculateInvestIncomeMonthly(double investAmount, double investRateMonthly) {
        return investAmount * investRateMonthly;
    }

    private double calculateMonthlyRepayPrincipal(double monthlyInstallmentPayment, double interestMontly) {
        return monthlyInstallmentPayment - interestMontly;
    }

    private double calculateMontlyInterest(double loanAmount, double loanRateMonthly) {
        return loanAmount * loanRateMonthly;
    }

    private double calculateMonthlyInstallmentPayment(double loanAmount, double loanRateMonthly, int installmentNumberLeft) {
        return loanAmount * loanRateMonthly * Math.pow(1 + loanRateMonthly, installmentNumberLeft) / (Math.pow(1 + loanRateMonthly, installmentNumberLeft) - 1);
    }

//    public double calculateInterestIncome(double principal, int installmentIndex, double totalInterestIncome) {
//        if (installmentIndex > installmentNumber) {
//            return totalInterestIncome;
//        }
//        double interestThisMonth = principal * interestRateMonthly;
//        double principalNextMonth = principal + interestThisMonth - installmentPayment;
//        totalInterestIncome += interestThisMonth;
//        return calculateInterestIncome(principalNextMonth, installmentIndex + 1, totalInterestIncome);
//    }
//
//    public double calculateMargin(double principal, int installmentIndex, double totalInterestIncome) {
//        return calculateInterestIncome(principal, installmentIndex, totalInterestIncome) - serviceCharge - loanAmount * loanRate * installmentNumber / 12;
//    }
}
