package za.co.lottery.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "powerball")
public class Powerball {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String drawNumber;
    private LocalDateTime drawDate;
    private String ball1;
    private String ball2;
    private String ball3;
    private String ball4;
    private String ball5;
    private String powerball;

}
