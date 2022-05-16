package dataAccessor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class FileAccessor implements DataAccessor {
    @Override
    public void appendElecteeList(List<String> electees) {
        try {
            FileOutputStream electeeFileOS = new FileOutputStream("electee.txt",true);
            OutputStreamWriter electeeOSW = new OutputStreamWriter(electeeFileOS, "UTF-8");
            for (String electee: electees) {
                electeeOSW.write("\n");
                electeeOSW.write(electee);
            }
            electeeOSW.flush();
            electeeOSW.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
