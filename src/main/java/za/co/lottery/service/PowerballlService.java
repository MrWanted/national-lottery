package za.co.lottery.service;

import za.co.lottery.dto.PowerballDTO;
import za.co.lottery.model.Powerball;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PowerballlService {
    List<Powerball> getAllPowerballs();
    Optional<Powerball> getPowerballByDrawDate(LocalDateTime drawDate);
    Powerball saveNewPowerball(PowerballDTO powerballDTO);
    Powerball persistFile(Iterable<Powerball> powerball);
}
