import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class SafariAdventure {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    
    public static void main(String[] args) {
        System.out.println("Welcome to Safari Adventure!");
        System.out.println("You are an explorer on a 5-day safari. Each day, choose an area to explore.");
        System.out.println("Collect resources, encounter wildlife, and survive to complete your adventure!");
        System.out.println("Goal: Survive all 5 days and collect at least 100 points.\n");
        
        // Game variables
        int totalPoints = 0;
        List<String> areas = Arrays.asList("Jungle", "River", "Desert", "Mountains");
        
        // Resource point values
        Map<String, Map<String, Integer>> resources = new HashMap<>();
        resources.put("Jungle", Map.of(
            "edible berries", 15,
            "medicinal herbs", 20,
            "rare orchid", 30
        ));
        resources.put("River", Map.of(
            "freshwater fish", 10,
            "clean water", 15,
            "river pearls", 25
        ));
        resources.put("Desert", Map.of(
            "cactus fruit", 10,
            "fossil", 20,
            "desert rose", 30
        ));
        resources.put("Mountains", Map.of(
            "minerals", 15,
            "alpine herbs", 20,
            "crystal", 35
        ));
        
        // Special continue event birds by area
        Map<String, String> areaBirds = Map.of(
            "Jungle", "scarlet macaw",
            "River", "kingfisher",
            "Desert", "roadrunner",
            "Mountains", "golden eagle"
        );
        
        // Dangerous animals by area
        Map<String, List<String>> dangers = new HashMap<>();
        dangers.put("Jungle", Arrays.asList("lion", "python", "poisonous frog"));
        dangers.put("River", Arrays.asList("crocodile", "hippopotamus", "electric eel"));
        dangers.put("Desert", Arrays.asList("rattlesnake", "scorpion", "coyote"));
        dangers.put("Mountains", Arrays.asList("snow leopard", "wolves", "bears"));

        // Friendly animals by area
        Map<String, List<String>> friendly = new HashMap<>();
        friendly.put("Jungle", Arrays.asList("elephant", "giant river otter", "capybara"));
        friendly.put("River", Arrays.asList("manatee", "turtle", "seals"));
        friendly.put("Desert", Arrays.asList("camel", "fennec fox", "sand cat"));
        friendly.put("Mountains", Arrays.asList("mountain goat", "himalayan marmots", "yak"));

        // Weather hazards by area
        Map<String, List<String>> weather = new HashMap<>();
        weather.put("Jungle", Arrays.asList("torrential rain", "heat wave", "mosquito swarm"));
        weather.put("River", Arrays.asList("flash flood", "fog", "strong currents"));
        weather.put("Desert", Arrays.asList("sandstorm", "extreme heat", "dust devil"));
        weather.put("Mountains", Arrays.asList("blizzard", "rock slide", "thin air"));
        
        // Main game loop
        for (int day = 1; day <= 5; day++) {
            System.out.println("\n-----------------------------------");
            System.out.println("Day " + day + ":");
            
            // Area selection
            String chosenArea;
            while (true) {
                System.out.print("Where would you like to explore? (" + String.join(", ", areas) + "): ");
                chosenArea = scanner.nextLine().trim();
                if (areas.contains(chosenArea)) {
                    break;
                }
                System.out.println("Invalid area. Please choose again.");
            }
            
            System.out.println("\nYou chose: " + chosenArea);
            System.out.println("Exploring " + chosenArea + "...\n");
            
            int dayPoints = 0;
            int eventsRemaining = 3;
            boolean dangerEncountered = false;
            
            // Daily events
            while (eventsRemaining > 0 && !dangerEncountered) {
                // Special bird sighting event (10% chance)
                if (random.nextDouble() < 0.10) {
                    String bird = areaBirds.get(chosenArea);
                    System.out.println(">> You spot a magnificent " + bird + "! <<");
                    System.out.println("You pause to admire its beauty before continuing your exploration...\n");
                    continue; // Doesn't count as an event
                }
                
                int eventType = random.nextInt(3) + 1; // 1-3
                
                if (eventType == 1) { // Find resource
                    List<String> areaResources = new ArrayList<>(resources.get(chosenArea).keySet());
                    String resource = areaResources.get(random.nextInt(areaResources.size()));
                    
                    int points = resources.get(chosenArea).get(resource);
                    dayPoints += points;
                    System.out.println("Event " + (4 - eventsRemaining) + ": You found " + resource + "! (+" + points + " points)");
                    eventsRemaining--;
                    
                } else if (eventType == 2) { // Encounter animal
                    if (random.nextDouble() < 0.3) { // 30% chance of dangerous animal
                        List<String> areaDangers = dangers.get(chosenArea);
                        String dangerousAnimal = areaDangers.get(random.nextInt(areaDangers.size()));
                        
                        System.out.println("\nEvent " + (4 - eventsRemaining) + ": A " + dangerousAnimal + " attacks! 😱");
                        System.out.println("Choose your action:");
                        System.out.println("1. Fight (20% chance to win +25-60 points, 80% chance to lose -20-50)");
                        System.out.println("2. Run (Lose 5-15 points but escape)");
                        System.out.println("3. Hide (Skip event, no penalty)");
                        
                        String action;
                        while (true) {
                            System.out.print("Enter your choice (1-3): ");
                            action = scanner.nextLine().trim();
                            if (action.equals("1") || action.equals("2") || action.equals("3")) {
                                break;
                            }
                            System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                        }

                        switch (action) {
                            case "1": // Fight
                                int fightOutcome = random.nextInt(100);
                                if (fightOutcome < 20) { // Win (20%)
                                    int bonusPoints = random.nextInt(36) + 25; // 25-60
                                    dayPoints += bonusPoints;
                                    System.out.println("You heroically defeated the " + dangerousAnimal + "! (+" + bonusPoints + " points)");
                                } else { // Lose (80%)
                                    int lostPoints = random.nextInt(31) + 20; // 20-50
                                    dayPoints -= lostPoints;
                                    System.out.println("The " + dangerousAnimal + " overpowered you! (-" + lostPoints + " points)");
                                }
                                dangerEncountered = true;
                                break;
                                
                            case "2": // Run
                                int lostPoints = random.nextInt(11) + 5; // 5-15
                                dayPoints -= lostPoints;
                                System.out.println("You escaped the " + dangerousAnimal + " but lost supplies. (-" + lostPoints + " points)");
                                dangerEncountered = true;
                                break;
                                
                            case "3": // Hide
                                System.out.println("You hid until the " + dangerousAnimal + " left.");
                                eventsRemaining--;
                                break;
                        }
                    } else { // Friendly animal
                        List<String> areaFriendly = friendly.get(chosenArea);
                        String animal = areaFriendly.get(random.nextInt(areaFriendly.size()));
                        System.out.println("Event " + (4 - eventsRemaining) + ": You see a friendly " + animal + ".");
                        eventsRemaining--;
                    }
                    
                } else if (eventType == 3) { // Weather hazard
                    List<String> areaWeather = weather.get(chosenArea);
                    String hazard = areaWeather.get(random.nextInt(areaWeather.size()));
                    System.out.println("Event " + (4 - eventsRemaining) + ": " + 
                        hazard.substring(0, 1).toUpperCase() + hazard.substring(1) + " strikes!");
                    
                    int lostPoints = random.nextInt(16) + 5; // 5-20
                    dayPoints -= lostPoints;
                    System.out.println("The " + hazard + " damages your supplies! (-" + lostPoints + " points)");
                    eventsRemaining--;
                }
            }
            
            totalPoints += dayPoints;
            System.out.println("\nDay Summary: " + 
                (dayPoints >= 0 ? "+" + dayPoints : dayPoints) + " points earned.");
            System.out.println("Total points: " + totalPoints);
        }
        
        // Final results
        System.out.println("\nSafari Complete!");
        System.out.println("You collected " + totalPoints + " points!");
        
        if (totalPoints >= 100) {
            System.out.println("Congratulations! You survived and completed the adventure!");
        } else {
            System.out.println("You didn't collect enough resources to survive the journey.");
        }
        
        if (totalPoints >= 150) {
            System.out.println("Outstanding! You're a master explorer!");
        } else if (totalPoints >= 100) {
            System.out.println("Well done! You made it through the safari.");
        } else {
            System.out.println("Better luck in the adventure. The wilderness is unforgiving.");
        }
    }
}
