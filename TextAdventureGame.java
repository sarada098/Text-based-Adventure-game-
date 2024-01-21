import java.util.Scanner;

public class TextAdventureGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user details
        System.out.println("Welcome to the Text Adventure Game!");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        // Check age
        if (age < 18) {
            System.out.println("Sorry, " + name + "! You are young. Go study and don't play games.");
            System.exit(0);
        }

        // Introduction by the Queen
        System.out.println("\nWelcome, brave " + name + "! I am the Queen, and I shall be your storyteller.");
        System.out.println("In a land far, far away, a hero must rise to face the challenges that await.");

        // Start the game
        System.out.println("\nAnd so, the adventure begins...");

        // Game loop
        int level = 1;
        int playerHealth = 200;
        int score = 0;
        boolean hasLegendarySword = false;

        while (level <= 3 && playerHealth > 0) {
            int enemyHealth;

            // Initialize enemy based on level
            if (level == 1) {
                System.out.println("\n--- Level " + level + " - Zombie ---");
                enemyHealth = 100;
            } else if (level == 2) {
                System.out.println("\n--- Level " + level + " - Dragon ---");
                enemyHealth = 200;
            } else {
                System.out.println("\n--- Level " + level + " - Thanos ---");
                enemyHealth = 500;
            }

            // Display player information
            System.out.println("Player Health: " + playerHealth);
            System.out.println("Enemy Health: " + enemyHealth);

            // Check for bonus riddle
            if (level == 2) {
                System.out.println("You encounter a mysterious figure who offers a riddle:");
                System.out.println("I speak without a mouth and hear without ears. I have no body, but I come alive with the wind. What am I?");
                String answer = scanner.next().toLowerCase();

                if (answer.equals("an echo")) {
                    System.out.println("Correct! You are awarded the 'Excalibur' legendary sword!");
                    hasLegendarySword = true;
                } else {
                    System.out.println("Incorrect! The mysterious figure disappears.");
                }
            }

            // Choose weapon
            System.out.println("Choose your weapon:");
            System.out.println("1. Sword");
            System.out.println("2. Bow");
            System.out.println("3. Magic Staff");

            int weaponChoice = scanner.nextInt();
            int damage = 0;

            // Check for legendary sword bonus
            if (hasLegendarySword && (level == 1 || level == 2)) {
                System.out.println("You use the 'Excalibur' Legendary Sword!");
                damage = (level == 1) ? 100 : 200;  // 1 shot for zombie, 2 shots for dragon
            } else {
                // Regular damage
                switch (weaponChoice) {
                    case 1:
                        damage = 20;
                        break;
                    case 2:
                        damage = 15;
                        break;
                    case 3:
                        damage = 25;
                        break;
                    default:
                        System.out.println("Invalid choice. You missed the attack!");
                        continue;  // Skip the rest of the loop and start a new iteration
                }
            }

            // Enemy turn
            int enemyDamage = (int) (Math.random() * 20) + 10;
            playerHealth -= enemyDamage;

            // Villain's scary dialogue
            System.out.println(getVillainDialogue(level));

            // Battle outcome
            if (playerHealth > 0) {
                // Check if enemy defeated
                if (enemyHealth - damage <= 0) {
                    System.out.println("You defeated the enemy!");
                    score += enemyHealth;  // Update score based on enemy health
                    level++;
                } else {
                    System.out.println("You dealt " + damage + " damage to the enemy!");
                    System.out.println("The enemy dealt " + enemyDamage + " damage to you!");
                }
            } else {
                System.out.println("Game over! You couldn't defeat the enemy.");
            }
        }

        // Game completion
        if (playerHealth > 0) {
            System.out.println("\nCongratulations, " + name + "! You completed the adventure!");
            System.out.println("As a token of my admiration, I, the Queen, offer you my hand in marriage.");
            System.out.println("Your final score is: " + score);
        }
    }

    private static String getVillainDialogue(int level) {
        switch (level) {
            case 1:
                return "The Zombie groans: 'You think you can escape the clutches of the undead?'";
            case 2:
                return "The Dragon roars: 'Your feeble attempts amuse me, little mortal.'";
            case 3:
                return "Thanos sneers: 'You believe you can challenge the might of the Mad Titan?'";
            default:
                return "";
        }
    }
}
