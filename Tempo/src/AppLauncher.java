import javax.swing.*;

public class AppLauncher {
    static void main() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WeatherAppGui().setVisible(true);

                //System.out.println(WeatherApp.getLocationData("Brusque"));
//                System.out.println(WeatherApp.getCurrentTime());
            }
        });
    }
}
