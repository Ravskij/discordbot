import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class WeatherModule {
    public static int WeatherModule() {
        try {
            // URI объект с ссылкой на страницу
            URI weatherURI = new URI("https://api.openweathermap.org/data/2.5/" +
                    "weather?lat=53.893009&lon=27.567444&appid=c2640b58f3661dd81f1895959152fdfa");
            // InputStream JSON запроса
            BufferedReader weatherJSON = new BufferedReader(new InputStreamReader(weatherURI.toURL().openStream()));
            // Разделяю объект в котором хранится JSON по ":"
            String[] weatherQ = weatherJSON.readLine().split(":");
            // Преобразую Кельвины в градусы Цельсия
            // Температура указана в 11 строке, делю ее на подстроку с числом и преобразую к int
            int tempCelsius = Integer.parseInt(weatherQ[11].substring(0, 3)) - 273;
            weatherJSON.close();
            return tempCelsius;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return -273;
    }
}
