package za.co.lottery.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import za.co.lottery.model.Powerball;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@Component
public class FileProcessing {

    public List<Powerball> returnJsonAsList() {

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("./powerball-results-2015-2021.json")) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            List<Object> data = (List<Object>) jsonObject.get("data");
            final Gson gson = new GsonBuilder().create();
            final ObjectMapper objectMapper = new ObjectMapper();
            return data.stream().map(rec -> {
                try {
                    return objectMapper.readValue(gson.toJson(rec), Powerball.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
