/**
 * name: Glavanits Marcel & Alexander Lampalzer
 * matnr.: i14075 & i14085
 * catnr.: 03 & 10
 * Created on 15.05.2017
 * file: AbsenceSummary
 * Class: 3CHIF
 */

public class AbsenceSummary {
    public String name; // Name of the Student. Has be called name because of easier converting to json.
    public int value; // Amount of seconds absent. Value because of converting.

    AbsenceSummary(String name, int value) {
        this.name = name;
        this.value = value;
    }

}
