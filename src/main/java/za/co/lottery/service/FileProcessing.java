package za.co.lottery.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import za.co.lottery.model.Powerball;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class FileProcessing {

    private final ObjectMapper objectMapper;

    private static JSONObject returnJsonAsList() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("c:\\\\projects\\\\powerball.json")) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            List<Object> data = (List<Object>) jsonObject.get("data");

            data.forEach(System.out::println);
            return jsonObject;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public static void main(String[] args) {
        returnJsonAsList();
    }

    public Powerball getPowerball(JSONObject jsonObject) {
        Powerball powerball = new Powerball();
        powerball.setDrawDate((LocalDateTime) jsonObject.get("drawDate"));
        powerball.setDrawNumber(Integer.parseInt((String) jsonObject.get("drawNumber")));
        powerball.setBall1(Integer.parseInt((String) jsonObject.get("ball1")));
        powerball.setBall2(Integer.parseInt((String) jsonObject.get("ball2")));
        powerball.setBall3(Integer.parseInt((String) jsonObject.get("ball3")));
        powerball.setBall4(Integer.parseInt((String) jsonObject.get("ball4")));
        powerball.setBall5(Integer.parseInt((String) jsonObject.get("ball5")));
        powerball.setPowerball(Integer.parseInt((String) jsonObject.get("powerball")));

        return powerball;
    }
}
