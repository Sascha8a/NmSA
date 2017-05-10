
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;

public class View {
    public View() {}

    public void startWS() {
        port(80);
        staticFileLocation("/public/dist");
        init();
    }
    public void showView() {}
    public void startWebbrowser() {
        String url = "localhost";

        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public void log(String message) {
        System.out.println(message);
    }
}
