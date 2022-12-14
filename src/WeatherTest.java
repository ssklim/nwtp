import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class WeatherTest {
    public static Map<String, String> getWeatherList(String baseDate, String baseTime) {
        Map<String, String> weatherMap = new HashMap<>();

        try {
            URL url = new URL(
                    "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=noKGlGvx8edr2izYWGao0UgmuXHYR8Uk8nOIVJx7ce0L8q%2FKd2do1ffRr2%2F9gcOj%2BmaRpLkXt68ueY91%2BVAEWw%3D%3D&numOfRows=1000&pageNo=1&dataType=JSON&base_date="+baseDate+"&base_time="+baseTime+"&nx=62&ny=124");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));

            String responseJson = br.readLine();
            Gson gson = new Gson();
            WeatherDto dto = gson.fromJson(responseJson, WeatherDto.class);
            List<Item> result = dto.getResponse().getBody().getItems().getItem();

            for (int i = 0; i < result.size(); i++) {
                weatherMap.put(result.get(i).getCategory(), result.get(i).getObsrValue());
            }
            return weatherMap;

        } catch (

        Exception e) {
            System.out.println("조회오류");
        }
        return null;
    }
}