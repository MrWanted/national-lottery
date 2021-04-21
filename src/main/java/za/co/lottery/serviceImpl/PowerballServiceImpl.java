package za.co.lottery.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.lottery.dto.PowerballDTO;
import za.co.lottery.model.Powerball;
import za.co.lottery.repository.PowerballRepository;
import za.co.lottery.service.PowerballlService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PowerballServiceImpl implements PowerballlService {
    private final PowerballRepository repository;

    @Override
    public List<Powerball> getAllPowerballs() {
        return repository.findAll();
    }

    @Override
    public Optional<Powerball> getKanbanByDrawDate(LocalDateTime drawDate) {
        return repository.findByDrawdate(drawDate);
    }

    @Override
    public Powerball saveNewPowerball(PowerballDTO powerballDTO) {
        return repository.save(convertDTOToPowerball(powerballDTO));
    }

    private Powerball convertDTOToPowerball(PowerballDTO powerballDTO){
        Powerball powerball = new Powerball();
        powerball.setBall1(powerball.getBall1());

        return powerball;
    }
}
