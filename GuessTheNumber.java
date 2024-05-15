import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    private static final int MAX_ATTEMPTS = 10;
    private static final int MAX_ROUNDS = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int round = 1;

        while (round <= MAX_ROUNDS) {
            int randomNumber = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessed = false;

            System.out.println("Round " + round + ": Guess the number between 1 and 100");

            while (attempts < MAX_ATTEMPTS && !guessed) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    guessed = true;
                } else if (userGuess < randomNumber) {
                    System.out.println("The number is higher than your guess. Try again.");
                } else {
                    System.out.println("The number is lower than your guess. Try again.");
                }
            }

            if (!guessed) {
                System.out.println("You've used all attempts! The correct number was " + randomNumber);
            }

            int roundScore = Math.max(0, MAX_ATTEMPTS - attempts + 1);
            totalScore += roundScore;
            System.out.println("Round " + round + " score: " + roundScore);
            System.out.println("Total score after round " + round + ": " + totalScore);
            round++;
        }

        System.out.println("Game Over! Your total score is: " + totalScore);
        scanner.close();
    }
}
