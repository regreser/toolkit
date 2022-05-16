package inputResolver;

import java.util.List;
import java.util.Map;

public interface InputResolver {
    public List<String> readCandidator(String candidatorFilePath);
    public Map<String, Boolean> readElectee(String electeeFilePath);
}
