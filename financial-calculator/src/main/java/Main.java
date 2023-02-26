public class Main {
    public static void main(String[] args) {
        CommercialLoanCalculator commercialLoanCalculator = new CommercialLoanCalculator();
        double margin = commercialLoanCalculator.calculateMarginAfterNInstallment(400000, 0.03 / 12, 400000, 0.0375 / 12, 360, 0, 0.0, 360, 0, 0);
        System.out.println(margin);

        double totalRefund = commercialLoanCalculator.calculateLoanRefundTotal(500000, 0.0611 / 12, 24, 0);
        System.out.println(totalRefund);
    }
}
