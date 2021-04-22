package za.co.lottery.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import za.co.lottery.model.Powerball;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileProcessing {
    private JSONObject returnJsonAsList() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("c:\\\\projects\\\\powerball.json")) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
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
