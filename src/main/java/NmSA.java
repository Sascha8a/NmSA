
public class NmSA {
    public static void main(String[] args) {
        Controller c = new Controller();
        API a = new API(c);

        Database db = new DatabaseFactory().getInstance();

    }
}
