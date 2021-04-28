package za.co.lottery.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    private static Optional<Integer> average(int... powerballs) {
        if (powerballs.length == 0) {
            return Optional.empty();
        }
        int sum = 0;

        for (int powerball : powerballs)
            sum += powerball;
        return Optional.of((int) sum / powerballs.length);
    }

    private static Optional<Integer> sum(int... powerballs) {
        int sum = 0;

        for (int powerball : powerballs)
            sum += powerball;
        return Optional.of(sum);
    }

    @Override
    @Transactional
    public List<Powerball> getAllPowerballs() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Optional<Powerball> getPowerballByDrawDate(LocalDateTime drawDate) {
        return repository.findByDrawDate(drawDate);
    }

    @Override
    @Transactional
    public Powerball saveNewPowerball(PowerballDTO powerballDTO) {
        return repository.save(convertDTOToPowerball(powerballDTO));
    }

    private Powerball convertDTOToPowerball(PowerballDTO dto) {
        Powerball powerball = new Powerball();
        powerball.setDrawDate(dto.getDrawDate().toString());
        powerball.setDrawNumber(dto.getDrawNumber());
        powerball.setBall1(dto.getBall1());
        powerball.setBall2(dto.getBall2());
        powerball.setBall3(dto.getBall3());
        powerball.setBall4(dto.getBall4());
        powerball.setBall5(dto.getBall5());
        powerball.setPowerball(dto.getPowerball());

        Optional<Integer> sum = sum(dto.getBall1(), dto.getBall2(), dto.getBall3(), dto.getBall4(), dto.getBall5());
        Optional<Integer> average = average(dto.getBall1(), dto.getBall2(), dto.getBall3(), dto.getBall4(), dto.getBall5());
        if (average.isPresent())
            powerball.setAverage(average.get());

        if (sum.isPresent())
            powerball.setSum(sum.get());

        return powerball;
    }
}
