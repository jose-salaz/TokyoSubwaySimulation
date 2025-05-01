# Tokyo Subway Simulation

This is my final project for CS 4632: Modeling and Simulation at Kennesaw State University. The project simulates a single Tokyo subway station to analyze passenger flows and train operations, focusing on metrics like average wait time, queue length, and rejections.

## Project Structure
- `src/main/java/TokyoSubway/`: Contains the Java source files for the simulation (e.g., `SubwaySimulation.java`, `Station.java`).
- `src/test/java/TokyoSubway/`: Contains test files (e.g., `SubwaySimulationTest.java`).
- `FinalReport.tex`: LaTeX source file for the project report.

## How to Run
1. Open the project in IntelliJ IDEA.
2. Run `SubwaySimulationTest.java` to execute the simulation for Baseline, Optimistic, and Pessimistic scenarios.
3. Check the console output for results.

## Results
- Baseline: Wait Time: 6.85 min, Max Queue: 28.00 pax, Rejections: 0.00
- Optimistic: Wait Time: 1.47 min, Max Queue: 3.00 pax, Rejections: 0.00
- Pessimistic: Wait Time: 19.01 min, Max Queue: 107.00 pax, Rejections: 0.00

**Note**: I’m working on adding a station capacity limit to handle rejections more realistically.
