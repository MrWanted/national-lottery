package za.co.lottery.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PowerballDTO {
    private int drawNumber;
    private LocalDateTime drawDate;
    private int ball1;
    private int ball2;
    private int ball3;
    private int ball4;
    private int ball5;
    private int powerball;
    // Used as input to the probability calculations
    private int sum, average;
}
