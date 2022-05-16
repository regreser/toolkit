package inputResolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ConsoleInputResolver implements InputResolver {

    @Override
    public List<String> readCandidator(String candidatorFilePath) {
        List<String> candidators = new ArrayList<>();
        try {
            File candidatorFile = new File(candidatorFilePath);
            Scanner candidatorFileScanner = new Scanner(candidatorFile);
            while (candidatorFileScanner.hasNextLine()) {
                String lineStr = candidatorFileScanner.nextLine();
                candidators.add(lineStr);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return candidators;
    }

    @Override
    public Map<String, Boolean> readElectee(String electeeFilePath) {
        Map<String, Boolean> electeeMap = new HashMap<>();
        try {
            File electeeFile = new File(electeeFilePath);
            Scanner electeeFileScanner = new Scanner(electeeFile);
            while (electeeFileScanner.hasNextLine()) {
                String lineStr = electeeFileScanner.nextLine();
                electeeMap.put(lineStr, true);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return electeeMap;
    }
}
