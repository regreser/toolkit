package core;

import dataAccessor.DataAccessor;
import dataAccessor.FileAccessor;
import inputResolver.ConsoleInputResolver;
import inputResolver.InputResolver;
import printer.ConsolePrinter;
import printer.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Elector {
    public static void main(String[] args) {
        InputResolver inputResolver = new ConsoleInputResolver();
        DataAccessor dataAccessor = new FileAccessor();
        Printer printer = new ConsolePrinter();

        String candidatorFilePath = args[0];
        String electeeFilePath = args[1];
        int electeeNumberForThisTime = Integer.parseInt(args[2]);

        List<String> candidators = inputResolver.readCandidator(candidatorFilePath);
        Map<String, Boolean> electees = inputResolver.readElectee(electeeFilePath);
        List<String> electedList = elect(candidators, electeeNumberForThisTime, electees);
        dataAccessor.appendElecteeList(electedList);
        printer.printElectorResult(electedList);
    }

    private static List<String> elect(List<String> candidators, int electeeNumber, Map<String, Boolean> electees) {
        int count = 0;
        List<String> electeesForThisTime = new ArrayList<>();
        while (count < electeeNumber && candidators.size() > 0) {
            int index = (int) (Math.random() * candidators.size());
            String electeeForThisLoop = candidators.get(index);
            if (electees.containsKey(electeeForThisLoop)) {
                candidators.remove(index);
            } else {
                electeesForThisTime.add(electeeForThisLoop);
                electees.put(electeeForThisLoop, true);
                count++;
            }
        }
        return electeesForThisTime;
    }
}
