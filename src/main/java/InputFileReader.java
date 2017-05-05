import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by UltraKnecht on 04.05.2017.
 */
public class InputFileReader {


    public void readInsertAbwesenheit(String filename, Database db) {
        //read lines
        List<String> lines = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String numLinesStr = reader.readLine();
            if (numLinesStr == null) throw new Exception("invalid file format");
            lines = new ArrayList<>();
            int numLines = Integer.parseInt(numLinesStr);
            for (int i = 0; i < numLines; i++) {

                lines.add(reader.readLine());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        //select and insert relevant data

        for (String line : lines) {
            List<String> data = Arrays.asList(line.split("[ \\t ] "));
            List<String> name = Arrays.asList(data.get(0).split(" "));
            //db.insertAbsence(name.get(0), name.get(1),);
        }
    }
}
