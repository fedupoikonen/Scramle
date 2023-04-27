import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        char[][][] cube = new char[6][3][3];

        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                cube[0][i][j] = 'O';
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                cube[1][i][j] = 'G';
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                cube[2][i][j] = 'R';
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                cube[3][i][j] = 'B';
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                cube[4][i][j] = 'W';
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                cube[5][i][j] = 'Y';
        }

      
        //put the scramble here
        List<String> scramble = new ArrayList<>(Arrays.asList("F2", "U", "B\'", "R", "U", "L\'" , "D2", "F\'", "R2", "U2", "D2"));

        for (String x : scramble) {
            char layerSelected = x.charAt(0);
            char directionSelected = ' ';
            if (x.length() > 1) {
                directionSelected = x.charAt(1);
            }

            switch (layerSelected) {
                case 'L':
                    if (directionSelected == '\'') {
                        cube = turnRight(cube, 0);
                        cube[0] = rotateCounterClockWise(cube[0]);

                    } else if (directionSelected == '2') {
                        cube = turnLeft(cube, 0);
                        cube = turnLeft(cube, 0);
                        cube[0] = rotateHalf(cube[0]);
                    } else {
                        cube = turnLeft(cube, 0);
                        cube[0] = rotateClockWise(cube[0]);

                    }
                    break;

                case 'R':
                    if (directionSelected == '\'') {
                        cube = turnLeft(cube, 2);
                        cube[2] = rotateCounterClockWise(cube[2]);

                    } else if (directionSelected == '2') {
                        cube = turnRight(cube, 2);
                        cube = turnRight(cube, 2);
                        cube[2] = rotateHalf(cube[2]);
                    } else {
                        cube = turnRight(cube, 2);
                        cube[2] = rotateClockWise(cube[2]);

                    }
                    break;

                case 'F':
                    if (directionSelected == '\'') {
                        cube = turnBack(cube, 2);
                        cube[1] = rotateCounterClockWise(cube[1]);

                    } else if (directionSelected == '2') {
                        cube = turnFace(cube, 2);
                        cube = turnFace(cube, 2);
                        cube[1] = rotateHalf(cube[1]);
                    } else {
                        cube = turnFace(cube, 2);
                        cube[1] = rotateClockWise(cube[1]);

                    }
                    break;

                case 'B':
                    if (directionSelected == '\'') {
                        cube = turnFace(cube, 0);
                        cube[3] = rotateCounterClockWise(cube[3]);

                    } else if (directionSelected == '2') {
                        cube = turnBack(cube, 0);
                        cube = turnBack(cube, 0);
                        cube[3] = rotateHalf(cube[3]);
                    } else {
                        cube = turnBack(cube, 0);
                        cube[3] = rotateClockWise(cube[3]);

                    }
                    break;

                case 'U':
                    if (directionSelected == '\'') {
                        cube = turnDown(cube, 0);
                        cube[4] = rotateCounterClockWise(cube[4]);

                    } else if (directionSelected == '2') {
                        cube = turnUp(cube, 0);
                        cube = turnUp(cube, 0);
                        cube[4] = rotateHalf(cube[4]);
                    } else {
                        cube = turnUp(cube, 0);
                        cube[4] = rotateClockWise(cube[4]);

                    }
                    break;

                case 'D':
                    if (directionSelected == '\'') {
                        cube = turnUp(cube, 2);
                        cube[5] = rotateCounterClockWise(cube[5]);

                    } else if (directionSelected == '2') {
                        cube = turnDown(cube, 2);
                        cube = turnDown(cube, 2);
                        cube[5] = rotateHalf(cube[5]);
                    } else {
                        cube = turnDown(cube, 2);
                        cube[5] = rotateClockWise(cube[5]);

                    }
                    break;

            }
        }

        print(cube);
    }

    // print method
    private static void print(char[][][] cube) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    System.out.print(cube[i][j][k] + " ");
                }
                System.out.println();

            }
            System.out.println("\n");
        }
        System.out.println("-----------------");
    }


    /*
     * For moves U (layer 0) and D' (layer 2)
     * Moves the outer edges of a turning layer
     * They way the methdon works is that it stores the first edge,
     * then moves next side's colors to that side, and at the end 
     * from memory the firsts.
     * Note that forth time we don't take from next side but from the first, 
     * because turning one layer affect four sides of the cube
     */
    private static char[][][] turnUp(char[][][] cube, int layer) {

        char[][][] temp = cube.clone();

        char[] first = Arrays.copyOf(cube[0][layer], cube[0][layer].length);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // cube[i][layer][j]
                temp[i][layer][j] = cube[i + 1][layer][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            temp[3][layer][i] = first[i];
        }

        return temp;
    }


    /*
     * For moves D (2) and U' (0)
     * Otherwise the same as turnUp but the opposite :0
     */
    private static char[][][] turnDown(char[][][] cube, int layer) {

        char[][][] temp = cube.clone();
        char[] first = Arrays.copyOf(cube[3][layer], cube[3][layer].length);

        for (int i = 3; i > 0; i--) {
            for (int j = 0; j < 3; j++) {
                temp[i][layer][j] = cube[i - 1][layer][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            temp[0][layer][i] = first[i];
        }

        return temp;
    }


    /*
     * For moves R (2) and L' (0)
     * This is and the next ones are more complicated than methods turning in horizontal direction (turnUp and turnDown)
     * because in the horizontals the layer numbers go nicely in order so we can iterate simply.
     * Also, the colors movin are not in the same row but the n:th element of a row. 
     * Other thing is that the numbering of tiles is mirrored e.g. in F and B sides so in some method
     * we need a counter number based on this table:
     * 
     * i    2-i
     * 
     * 0    2    
     * 1    1
     * 2    0
     * 
     */
    private static char[][][] turnRight(char[][][] cube, int layer) {

        char[][][] temp = cube.clone();

        char[] first = new char[3];
        System.arraycopy(cube[4][0], layer, first, 0, 1);
        System.arraycopy(cube[4][1], layer, first, 1, 1);
        System.arraycopy(cube[4][2], layer, first, 2, 1);

        int counterlayer = 0;
        if (layer == 0) {
            counterlayer = 2;
        } else if (layer == 2) {
            counterlayer = 0;
        } else {
            counterlayer = 1;
        }

        for (int i = 0; i < 3; i++) {
            temp[4][i][layer] = cube[1][i][layer];
        }

        for (int i = 0; i < 3; i++) {
            temp[1][i][layer] = cube[5][i][layer];
        }

        for (int i = 0; i < 3; i++) {
            temp[5][i][layer] = cube[3][2 - i][counterlayer];
        }

        for (int i = 0; i < 3; i++) {
            temp[3][i][counterlayer] = first[2-i];
        }

        return temp;
    }

    /*
     * For moves L (0)  and R' (2)
     */
    private static char[][][] turnLeft(char[][][] cube, int layer) {

        char[][][] temp = cube.clone();

        char[] first = new char[3];
        System.arraycopy(cube[4][0], layer, first, 0, 1);
        System.arraycopy(cube[4][1], layer, first, 1, 1);
        System.arraycopy(cube[4][2], layer, first, 2, 1);

        int counterlayer = 0;
        if (layer == 0) {
            counterlayer = 2;
        } else if (layer == 2) {
            counterlayer = 0;
        } else {
            counterlayer = 1;
        }

        for (int i = 0; i < 3; i++) {
            temp[4][i][layer] = cube[3][2 - i][counterlayer];
        }

        for (int i = 0; i < 3; i++) {
            cube[3][2 - i][counterlayer] = cube[5][i][layer];
        }

        for (int i = 0; i < 3; i++) {
            temp[5][i][layer] = cube[1][i][layer];
        }

        for (int i = 0; i < 3; i++) {
            temp[1][i][layer] = first[i];
        }

        return temp;
    }


    /*
     * For moves F (2) and U' (0)
     */
    private static char[][][] turnFace(char[][][] cube, int layer) {

        char[][][] temp = cube.clone();

        char[] first = new char[3];
        System.arraycopy(cube[4][layer], 0, first, 0, 1);
        System.arraycopy(cube[4][layer], 1, first, 1, 1);
        System.arraycopy(cube[4][layer], 2, first, 2, 1);

        int counterlayer = 0;
        if (layer == 0) {
            counterlayer = 2;
        } else if (layer == 2) {
            counterlayer = 0;
        } else {
            counterlayer = 1;
        }

        for (int i = 0; i < 3; i++) {
            temp[4][layer][i] = cube[0][2 - i][layer];
        }

        for (int i = 0; i < 3; i++) {
            temp[0][i][layer] = cube[5][counterlayer][i];
        }

        for (int i = 0; i < 3; i++) { // tuleeko i vai 2-i
            temp[5][counterlayer][i] = cube[2][2-i][counterlayer];
        }

        for (int i = 0; i < 3; i++) {
            temp[2][i][counterlayer] = first[i];
        }

        return temp;
    }

    /*
     * For moves B (0) and F' (2)
     */
    private static char[][][] turnBack(char[][][] cube, int layer) {

        char[][][] temp = cube.clone();

        char[] first = new char[3];
        System.arraycopy(cube[4][layer], 0, first, 0, 1);
        System.arraycopy(cube[4][layer], 1, first, 1, 1);
        System.arraycopy(cube[4][layer], 2, first, 2, 1);

        int counterlayer = 0;
        if (layer == 0) {
            counterlayer = 2;
        } else if (layer == 2) {
            counterlayer = 0;
        } else {
            counterlayer = 1;
        }

        for (int i = 0; i < 3; i++) {
            temp[4][layer][i] = cube[2][i][counterlayer];
        }

        for (int i = 0; i < 3; i++) {
            temp[2][i][counterlayer] = cube[5][counterlayer][2 - i];
        }

        for (int i = 0; i < 3; i++) {
            temp[5][counterlayer][i] = cube[0][i][layer];
        }

        for (int i = 0; i < 3; i++) {
            temp[0][i][layer] = first[2 - i];
        }

        return temp;
    }

    /*
     * The next methods move the tiles on the moving side itself.
     * The methods only need two dimensional array, part of the whole dimensionl, because they handle only one side
     */
    
    private static char[][] rotateClockWise(char[][] side) {

        int size = side.length;
        char[][] temp = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[i][j] = side[size - j - 1][i];
            }
        }
        return temp;
    }

    private static char[][] rotateCounterClockWise(char[][] side) {

        int size = side.length;
        char[][] temp = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[i][j] = side[j][size - i - 1];
            }
        }
        return temp;
    }

    private static char[][] rotateHalf(char[][] side) {

        int size = side.length;
        char[][] temp = new char[size][size];
        char[][] temp2 = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[i][j] = side[size - j - 1][i];
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp2[i][j] = temp[size - j - 1][i];
            }
        }

        return temp2;
    }

}
