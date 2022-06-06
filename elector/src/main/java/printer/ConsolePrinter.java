package printer;

import java.util.List;

public class ConsolePrinter implements Printer {
    @Override
    public void printElectorResult(List<String> electors) {
        System.out.println(electors);
    }
}
