import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxAttempts = 7; // Set maximum attempts per round
        int totalRounds = 0;
        int totalScore = 0;
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; // Generate random number between 1 and 100
            int attemptsLeft = maxAttempts;
            boolean guessedCorrectly = false;

            System.out.println("\nRound " + (totalRounds + 1));
            System.out.println("I'm thinking of a number between 1 and 100. You have " + maxAttempts + " attempts to guess it.");

            while (attemptsLeft > 0 && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == numberToGuess) {
                    guessedCorrectly = true;
                    totalScore += attemptsLeft; // Increase score based on remaining attempts
                    System.out.println("Congratulations! You've guessed the correct number.");
                    System.out.println("You guessed it in " + (maxAttempts - attemptsLeft + 1) + " attempts.");
                } else if (userGuess < numberToGuess) {
                    attemptsLeft--;
                    System.out.println("Too low! Try again. Attempts left: " + attemptsLeft);
                } else {
                    attemptsLeft--;
                    System.out.println("Too high! Try again. Attempts left: " + attemptsLeft);
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all attempts. The correct number was: " + numberToGuess);
            }

            totalRounds++;

            System.out.print("\nDo you want to play another round? (yes/no): ");
            String userResponse = scanner.next();
            playAgain = userResponse.equalsIgnoreCase("yes");
        }

        System.out.println("\nGame Over! You played " + totalRounds + " round(s) and your total score is: " + totalScore);
        scanner.close();
    }
}
