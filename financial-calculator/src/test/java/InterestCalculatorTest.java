import org.junit.jupiter.api.Test;

class InterestCalculatorTest {

    @Test
    void calculateAutomobileLoanResult() {
        InterestCalculator interestCalculator = new InterestCalculator(102000, 0, 0.027/12, 24, 3000);
        System.out.println("Income: " + interestCalculator.calculateMargin(interestCalculator.getLoanAmount() - interestCalculator.getServiceCharge(), 1, 0));
    }

    @Test
    void calculateTruckSpaceLoanResult() {
        InterestCalculator interestCalculator = new InterestCalculator(100000, 0.036, 0.058/12, 60, 0);
        System.out.println("Income: " + interestCalculator.calculateMargin(interestCalculator.getLoanAmount() - interestCalculator.getServiceCharge(), 1, 0));
    }
}