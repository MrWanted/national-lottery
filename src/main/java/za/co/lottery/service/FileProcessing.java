package za.co.lottery.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import za.co.lottery.model.Powerball;
import za.co.lottery.repository.PowerballRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Data
public class FileProcessing {

    private final PowerballRepository repository;

    private  JSONObject returnJsonAsList() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("./powerball-results-2015-2021.json")) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            List<Object> data = (List<Object>) jsonObject.get("data");
            final Gson gson = new GsonBuilder().create();
            final ObjectMapper objectMapper = new ObjectMapper();
            data.stream().map(rec -> {
                try {
                    return objectMapper.readValue(gson.toJson(rec), Powerball.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return null;
                }
            }).filter(Objects::nonNull).forEach(repository::saveAndFlush);
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
//        returnJsonAsList();
    }

    public Powerball getPowerball(JSONObject jsonObject) {
        Powerball powerball = new Powerball();
        powerball.setDrawDate((String) jsonObject.get("drawDate"));
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
