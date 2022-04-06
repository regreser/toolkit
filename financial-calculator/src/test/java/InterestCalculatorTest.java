import org.junit.jupiter.api.Test;

class InterestCalculatorTest {

    @Test
    void calculateAutomobileLoanResult() {
        InterestCalculator interestCalculator = new InterestCalculator(102000, 0, 0.03/12, 24, 3000);
        System.out.println("Income: " + interestCalculator.calculateLoanResult(interestCalculator.getLoanAmount() - interestCalculator.getServiceCharge(), 1, 0));
    }

    @Test
    void calculateTruckSpaceLoanResult() {
        InterestCalculator interestCalculator = new InterestCalculator(100000, 0.036, 0.03/12, 60, 0);
        System.out.println("Income: " + interestCalculator.calculateLoanResult(interestCalculator.getLoanAmount() - interestCalculator.getServiceCharge(), 1, 0));
    }
}