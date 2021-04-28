package za.co.lottery.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.lottery.dto.PowerballDTO;
import za.co.lottery.model.Powerball;
import za.co.lottery.repository.PowerballRepository;
import za.co.lottery.service.FileProcessing;
import za.co.lottery.service.PowerballlService;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/powerball")
@RequiredArgsConstructor
public class PowerballController {
    private final PowerballlService service;
    private final FileProcessing fileProcessing;
    private final PowerballRepository repo;

    @GetMapping("/ping")
    public String greeting() {
        return "pong";
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(
                    service.getAllPowerballs(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @GetMapping("/{draw-date}")
    public ResponseEntity<?> findbyDate(@PathVariable LocalDateTime localDateTime) {
        try {
            Optional<Powerball> optPowerball = service.getPowerballByDrawDate(localDateTime);
            if (optPowerball.isPresent()) {
                return new ResponseEntity<>(
                        optPowerball.get(),
                        HttpStatus.OK);
            } else {
                return resourceNotFoundResponse(localDateTime);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePowerball(@RequestBody PowerballDTO dto) {
        try {
            return new ResponseEntity<>(
                    service.saveNewPowerball(dto),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @PostMapping("/persist")
    public ResponseEntity<?> persistFile() {
        fileProcessing.returnJsonAsList().forEach(repo::saveAndFlush);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<String> errorResponse() {
        return new ResponseEntity<>("Something went wrong :(", HttpStatus.CREATED);
    }

    private ResponseEntity<String> resourceNotFoundResponse(LocalDateTime localDateTime) {
        return new ResponseEntity<>("No powerball found with id: " + localDateTime, HttpStatus.NOT_FOUND);
    }
}
