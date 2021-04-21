package za.co.lottery.service;

import za.co.lottery.model.Powerball;


public interface ProbabilityCalculator {

    /**
     * Receives six numbers and matches the provided numbers
     * against winning numbers from the history and calculates the probability from there
     *
     * @param combination
     * @return
     */
    Probability calculateProbability(Powerball combination);

    /**
     * Receives six numbers and matches the provided numbers
     * against winning numbers from the history and calculates the probability from there
     *
     * @param ball1
     * @param ball2
     * @param ball3
     * @param ball4
     * @param ball5
     * @param powerball
     * @return
     */
    Probability calculateProbability(int ball1, int ball2, int ball3, int ball4, int ball5, int powerball);

    /**
     * Scans the DB for all draws and returns a total number of occurrences a single number has been drawn
     * @param ball
     * @return
     */
    int totalNumberOfOccurrences(int ball);
}
