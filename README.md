# Safari-Adventure-The-Looping-Expedition

1. Game Initialization

When the program starts, it:
Prints a welcome message and the game's objective to the user.

2. Main Game Loop (5 Days)

The game runs for 5 days:
Each day consists of:
Choosing an area to explore (Jungle, River, Desert, or Mountains).
3 random events (unless danger forces an early end).
Calculating daily points and adding them to totalPoints.

3. Event System

Each day, the player experiences 3 events (unless interrupted by danger). Possible events:

A. Special Bird Sighting (10% Chance)

A special bird appears (does not count as an event).

B. Resource Discovery (Event Type 1)

The player finds a resource (e.g., "edible berries" in the Jungle).
Points are added based on the resource's value.

C. Animal Encounter (Event Type 2)

Friendly (70% chance): Just a description (e.g., "You see a friendly elephant").
Dangerous (30% chance): The player must choose:
Fight (20% success chance) → Gain 25-60 points or lose 20-50 points.
Run → Lose 5-15 points but escape.
Hide → Skip event safely.

D. Weather Hazard (Event Type 3)

A random weather event (e.g., "sandstorm") reduces points by 5-20.

4. Danger Handling

If a dangerous animal appears, the player must react:
1. Fight (20% chance to win +25-60 points, 80% chance to lose -20-50)
2. Run (Lose 5-15 points but escape)
3. Hide (Skip event, no penalty)
Choosing Fight is high-risk, high-reward.
Run guarantees a small penalty.
Hide avoids risk but skips the event.
If the player fights and loses or runs away, the day ends early (dangerEncountered = true).

5. Scoring & Winning Conditions

After each day, points are added to totalPoints.
At the end of 5 days, the game checks:
≥100 points → Win ("You survived!").
<100 points → Lose ("You didn't collect enough resources").
≥150 points → Bonus praise ("Master explorer!").
