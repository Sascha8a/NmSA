/**
 * Created by Sascha on 15/05/2017.
 */
public class AbsenceSummary {
    public String name; // Name of the Student. Has be called name because of easier converting to json.
    public int value; // Amount of seconds absent. Value because of converting.

    public AbsenceSummary(String name, int value) {
        this.name = name;
        this.value = value;
    }

}
