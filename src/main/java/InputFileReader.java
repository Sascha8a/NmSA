import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by UltraKnecht on 04.05.2017.
 */
public class InputFileReader {


    public void readInsertAbwesenheit(String filename, Database db) {
        //read lines
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/resources/inputFiles/" + filename));
            String numLinesStr = reader.readLine();
            if (numLinesStr == null) throw new Exception("invalid file format");
            int numLines = Integer.parseInt(numLinesStr);
            for (int i = 0; i < numLines; i++) {
                String line = reader.readLine();
                List<String> data = Arrays.asList(line.split("[ \\t ] "));
                List<String> name = Arrays.asList(data.get(0).split(" "));
                db.insertAbsence(name.get(0), name.get(1), data.get(8), data.get(4), data.get(5), Integer.parseInt(data.get(7)));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
