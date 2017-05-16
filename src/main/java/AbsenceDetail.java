import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sascha on 15/05/2017.
 */
public class AbsenceDetail {
    public String name; // Name of the Student. Has be called name because of easier converting to json.
    public int value; // Amount of seconds absent. Value because of converting.
    public Date date;

    public AbsenceDetail(String name, int value, Date date) {
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public String getDate() {
        return new SimpleDateFormat("yyyy-mm-dd").format(this.date);
    }
}
