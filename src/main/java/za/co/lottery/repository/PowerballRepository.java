package za.co.lottery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.lottery.model.Powerball;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PowerballRepository extends JpaRepository<Powerball, Long> {
    Optional<Powerball> findByDrawDate(LocalDateTime drawDate);
}
