
public class GameOfLife {

	static final int GRIDSIZE = 20;
	static int pattern;
	static int boundCond;
	static boolean invalidFile = true;
	static boolean[][] gameField = new boolean[GRIDSIZE][GRIDSIZE];
	static boolean[][] nextGeneration = new boolean[GRIDSIZE][GRIDSIZE];

	static void printField(int pattern) {

		if (invalidFile) {
			for (int i = 0; i < nextGeneration.length; i++) {
				for (int j = 0; j < nextGeneration[0].length; j++) {
					if (nextGeneration[i][j]) { /* true */
						Out.print("* ");
					} else { /* false */
						Out.print("  ");
					}
				}
				Out.println();
			}
			Out.println("----------------------------------------");
			Out.println();
		}
	}

	static void setInitialState(int pattern) {

		switch (pattern) {
		case 1: { // Block pattern
			gameField[10][9] = true;
			gameField[10][10] = true;
			gameField[11][9] = true;
			gameField[11][10] = true;
			break;
		}
		case 2: { // Boat pattern
			gameField[10][10] = true;
			gameField[10][11] = true;
			gameField[11][10] = true;
			gameField[11][12] = true;
			gameField[12][11] = true;
			break;
		}
		case 3: { // Blinker pattern
			gameField[10][9] = true;
			gameField[10][10] = true;
			gameField[10][11] = true;
			break;
		}
		case 4: { // Toad pattern
			gameField[10][10] = true;
			gameField[10][11] = true;
			gameField[10][12] = true;
			gameField[11][9] = true;
			gameField[11][10] = true;
			gameField[11][11] = true;
			break;
		}
		case 5: { // Glider pattern
			gameField[9][10] = true;
			gameField[10][11] = true;
			gameField[11][9] = true;
			gameField[11][10] = true;
			gameField[11][11] = true;
			break;
		}
		case 6: { // Spaceship pattern
			gameField[9][8] = true;
			gameField[9][11] = true;
			gameField[10][12] = true;
			gameField[11][8] = true;
			gameField[11][12] = true;
			gameField[12][9] = true;
			gameField[12][10] = true;
			gameField[12][11] = true;
			gameField[12][12] = true;
			break;
		}
		case 7: { // Read from file
			In.open("pattern_EX05.txt");
			int[][] gameFieldFile = new int[GRIDSIZE][GRIDSIZE];
			int gridSizeFile = In.readInt();
			int patternStart = 3;

			if (gridSizeFile > 0 && gridSizeFile <= 15) {
				while (In.done()) {
					for (int i = patternStart; i < (patternStart + gridSizeFile); i++) {
						for (int j = patternStart; j < (patternStart + gridSizeFile); j++) {
							gameFieldFile[i][j] = In.readInt();

							if (gameFieldFile[i][j] == 1) {
								gameField[i][j] = true;
							} else if (gameFieldFile[i][j] == 0) {
								gameField[i][j] = false;
							} else {
								Out.print("Error! Invalid Numbers in File!");
								invalidFile = false;
								return;
							}
						}
					}
					In.readInt();
				}
			} else {
				Out.println("Error! Invalid Number for Rows and Columns in File!");
			}
			In.close();
			break;
		}
		}

		// Print initial state of patterns
		for (int i = 0; i < gameField.length; i++) {
			for (int j = 0; j < gameField[0].length; j++) {
				if (gameField[i][j]) { /* true */
					Out.print("* ");
				} else { /* false */
					Out.print("  ");
				}
			}
			Out.println();
		}
		Out.println("----------------------------------------");
		Out.println();
	}

	static void performNextStep(int boundCond) {

		int aliveNeighbours = 0;

		// Torus model
		if (boundCond == 1) {
			for (int i = 0; i < gameField.length; i++) { // rows
				for (int j = 0; j < gameField.length; j++) { // columns

					// Check if neighbourly cells are alive
					if (gameField[(i - 1 + gameField.length) % gameField.length][(j - 1 + gameField.length)
							% gameField.length]) {
						aliveNeighbours++;
					}
					if (gameField[(i - 1 + gameField.length) % gameField.length][j]) {
						aliveNeighbours++;
					}
					if (gameField[(i - 1 + gameField.length) % gameField.length][(j + 1) % gameField.length]) {
						aliveNeighbours++;
					}
					if (gameField[i][(j - 1 + gameField.length) % gameField.length]) {
						aliveNeighbours++;
					}
					if (gameField[i][(j + 1) % gameField.length]) {
						aliveNeighbours++;
					}
					if (gameField[(i + 1) % gameField.length][(j - 1 + gameField.length) % gameField.length]) {
						aliveNeighbours++;
					}
					if (gameField[(i + 1) % gameField.length][j]) {
						aliveNeighbours++;
					}
					if (gameField[(i + 1) % gameField.length][(j + 1) % gameField.length]) {
						aliveNeighbours++;
					}

					// Rule 1: alive cell dies if less than 2 or more than 3 alive neighbours
					if (gameField[i][j] && (aliveNeighbours < 2) || gameField[i][j] && (aliveNeighbours > 3)) {
						nextGeneration[i][j] = false;
						// Rule 2: dead cell comes to life if 3 alive neighbours
					} else if ((gameField[i][j] == false) && (aliveNeighbours == 3)) {
						nextGeneration[i][j] = true;
						// Rule 3: cells stay the same if rules 1/2 don't apply
					} else {
						nextGeneration[i][j] = gameField[i][j];
					}
					aliveNeighbours = 0;
				}
			}

			// Box model
		} else if (boundCond == 2) {
			for (int i = 0; i < gameField.length; i++) { // rows
				for (int j = 0; j < gameField.length; j++) { // columns

					// Check if neighbourly cells are alive
					if (i != 0 && j != 0 && gameField[i - 1][j - 1]) {
						aliveNeighbours++;
					}
					if (i != 0 && gameField[i - 1][j]) {
						aliveNeighbours++;
					}
					if (i != 0 && j != 19 && gameField[i - 1][j + 1]) {
						aliveNeighbours++;
					}
					if (j != 0 && gameField[i][j - 1]) {
						aliveNeighbours++;
					}
					if (j < 19 && gameField[i][j + 1]) {
						aliveNeighbours++;
					}
					if (i != 19 && j != 0 && gameField[i + 1][j - 1]) {
						aliveNeighbours++;
					}
					if (i != 19 && gameField[i + 1][j]) {
						aliveNeighbours++;
					}
					if (i != 19 && j != 19 && gameField[i + 1][j + 1]) {
						aliveNeighbours++;
					}

					// Rule 1: alive cell dies if less than 2 or more than 3 alive neighbours
					if (gameField[i][j] && (aliveNeighbours < 2) || gameField[i][j] && (aliveNeighbours > 3)) {
						nextGeneration[i][j] = false;
						// Rule 2: dead cell comes to life if 3 alive neighbours
					} else if ((gameField[i][j] == false) && (aliveNeighbours == 3)) {
						nextGeneration[i][j] = true;
					} else {
						nextGeneration[i][j] = gameField[i][j];
					}
					aliveNeighbours = 0;
				}
			}
		}
	}

	public static void main(String[] args) {
		// print header
		Out.println("Selection of Pattern:");
		Out.println("1. Block pattern");
		Out.println("2. Boat pattern");
		Out.println("3. Blinker pattern");
		Out.println("4. Toad pattern");
		Out.println("5. Glider pattern");
		Out.println("6. Spaceship pattern");
		Out.println("7. Read from file");
		Out.print("=> ");

		int pattern = In.readInt();
		Out.println();

		while (pattern < 1 || pattern > 7) {
			Out.println("Error! Please enter valid Pattern Number: ");
			Out.print("=> ");
			pattern = In.readInt();
			Out.println();
		}

		Out.println();
		Out.println("Selection of Boundary Conditions:");
		Out.println("1. Torus model");
		Out.println("2. Box model");
		Out.print("=> ");

		int boundCond = In.readInt();
		Out.println();

		while (boundCond < 1 || boundCond > 2) {
			Out.println("Error! Please enter valid Number for Boundary Condition: ");
			Out.print("=> ");
			boundCond = In.readInt();
			Out.println();
		}
		Out.println();

		setInitialState(pattern);

		while (In.done()) {
			In.read();
			In.read();

			// Next generation
			performNextStep(boundCond);

			// generate new field
			for (int i = 0; i < gameField.length; i++) {
				gameField[i] = nextGeneration[i].clone();
			}
			printField(pattern);
		}
	}
}
