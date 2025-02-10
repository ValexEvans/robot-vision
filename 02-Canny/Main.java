public class Main {
    public static void main(String[] args) {
        // Define constants
        boolean ON = true;
        boolean OFF = false;
        double HI = 30; // Adjust this value to your desired threshold
        double LO = 20; // Adjust this value to your desired threshold

        // Initialize 'mags' and 'peaks' arrays
        double[][] mags = {
            {5, 0, 0, 0, 0, 0},
            {9, 13, 3, 23, 3, 7},
            {9, 13, 23, 23, 23, 7},
            {9, 13, 35, 23, 13, 7},
            {9, 15, 23, 23, 23, 7},
            {0, 0, 0, 0, 0, 0}
        };

        boolean[][] peaks = {
            {false, false, false, false, true, false},
            {false, true, false, true, false, false},
            {false, true, false, false, false, false},
            {false, true, true, true, true, false},
            {false, false, false, false, true, false},
            {false, false, false, false, false, false}
        };

        boolean[][] finalArray = new boolean[peaks.length][peaks[0].length];
        boolean moretodo = true;

        while (moretodo) {
            moretodo = false; // Reset moretodo to false at the beginning of each iteration

            // Loop through each element of 'peaks'
            for (int i = 0; i < peaks.length; i++) {
                for (int j = 0; j < peaks[i].length; j++) {
                    if (peaks[i][j] == ON) {
                        if (mags[i][j] > HI) {
                            peaks[i][j] = OFF;
                            finalArray[i][j] = ON;
                        } else if (mags[i][j] < LO) {
                            peaks[i][j] = OFF;
                            finalArray[i][j] = OFF;
                        }
                    }
                }
            }

            // Check for neighboring updates
            for (int i = 0; i < peaks.length; i++) {
                for (int j = 0; j < peaks[i].length; j++) {
                    if (peaks[i][j] == ON) {
                        for (int p = -1; p <= 1; p++) {
                            for (int q = -1; q <= 1; q++) {
                                if (i + p >= 0 && i + p < peaks.length && j + q >= 0 && j + q < peaks[i].length &&
                                        peaks[i + p][j + q] == ON) {
                                    peaks[i][j] = OFF;
                                    finalArray[i][j] = ON;
                                    moretodo = true; // Set moretodo to true if any updates were made
                                }
                            }
                        }
                    }
                }
            }
        }

        // Print the 'finalArray'
        for (int i = 0; i < finalArray.length; i++) {
            for (int j = 0; j < finalArray[i].length; j++) {
                System.out.print(finalArray[i][j] ? "1 " : "0 ");
            }
            System.out.println(); // Newline after each row
        }
    }
}
