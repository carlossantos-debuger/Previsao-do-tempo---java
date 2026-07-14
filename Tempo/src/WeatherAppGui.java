import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherAppGui extends JFrame {

    private JSONObject weatherData;
    private JLabel backgroundGif;

    public WeatherAppGui() {
        super("Clima");

        setSize(450, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        addGuiComponents();
    }

    private void addGuiComponents() {
        backgroundGif = new JLabel();

        backgroundGif.setBounds(0, 0, 450, 650);
        add(backgroundGif);

        JTextField searchTextField = new JTextField();

        searchTextField.setBounds(15,15,351,45);

        searchTextField.setFont(new Font("Dialog", Font.PLAIN,24));

        add(searchTextField);


        // imagem do tempo
        JLabel weatherConditionImage = new JLabel(loadImage("src/assets/cloudy.png"));
        weatherConditionImage.setBounds(0,125,450,217);
        add(weatherConditionImage);

        //texto temperatura
        JLabel temperatureText = new JLabel("10 C");
        temperatureText.setBounds(0,350,450,54);
        temperatureText.setFont(new Font("Dialog", Font.BOLD, 48));
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);


        //condicao do tempo
        JLabel watherConditionDesc = new JLabel("Nublado");
        watherConditionDesc.setBounds(0,405,450,36);
        watherConditionDesc.setFont(new Font("Dialog",Font.PLAIN,32));
        watherConditionDesc.setHorizontalAlignment(SwingConstants.CENTER);
        add(watherConditionDesc);

        //humidade
        JLabel humidityText = new JLabel("<html><b>Umidade</b> 100%</html>");
        humidityText.setBounds(90,500,85,55);
        humidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(humidityText);

        //humidade image
        JLabel humidityImage = new JLabel(loadImage("src/assets/humidity.png"));
        humidityImage.setBounds(15,500,74,66);
        add(humidityImage);

        //Vento
        JLabel windspeedImage = new JLabel(loadImage("src/assets/windspeed.png"));
        windspeedImage.setBounds(220,500,74,66);
        add(windspeedImage);

        JLabel windspeedText = new JLabel("<html><b>Vento</b> 15km</html>");
        windspeedText.setBounds(310,500,85,55);
        windspeedText.setFont(new Font("Dialog",Font.PLAIN,16));
        add(windspeedText);

        //botão de pesquisa
        JButton searchButton = new JButton(loadImage("src/assets/search.png"));

        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375,13,47,45);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = searchTextField.getText();

                if(userInput.replaceAll("\\s","").length()<=0){
                    return;
                }

                weatherData = WeatherApp.getWeatherData(userInput);

                String weatherCondition = (String) weatherData.get("weather_condition");
                switch (weatherCondition){
                    case "Limpo":
                        weatherConditionImage.setIcon(loadImage("src/assets/clear.png"));
                        //backgroundGif.setIcon(new ImageIcon("src/assets/clear.gif"));
                        break;
                    case "Nublado":
                        weatherConditionImage.setIcon(loadImage("src/assets/cloudy.png"));
                        //backgroundGif.setIcon(new ImageIcon("src/assets/cloudy.gif"));
                        break;
                    case "Chuva":
                        weatherConditionImage.setIcon(loadImage("src/assets/rain.png"));
                        //backgroundGif.setIcon(new ImageIcon("src/assets/rain-gif.gif"));
                        break;
                    case "Snow":
                        weatherConditionImage.setIcon(loadImage("src/assets/snow.png"));
                        //backgroundGif.setIcon(new ImageIcon("src/assets/snow.gif"));

                        break;
                }

                double temperature = (double) weatherData.get("temperature");
                temperatureText.setText(temperature + " C");

                watherConditionDesc.setText(weatherCondition);

                long humidity = (long) weatherData.get("humidity");
                humidityText.setText("<html><b>Umidade</b> "+ humidity + "%</html>");

                double windspeed = (double) weatherData.get("windspeed");
                windspeedText.setText("<html><b>Vento</b> "+ windspeed + "km/h</html>");

            }
        });
        add(searchButton);

        searchTextField.setText("Brusque");

        // simula o clique do botão
        searchButton.doClick();


        getContentPane().setComponentZOrder(backgroundGif, getContentPane().getComponentCount() - 1);

    }

    private ImageIcon loadImage (String resourcePath){
        try{
            BufferedImage image = ImageIO.read(new File(resourcePath));

            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Recurso não encontrado");
        return null;
    }

}