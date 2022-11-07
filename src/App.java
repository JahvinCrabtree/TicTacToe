import java.util.Scanner;

public class App {
    public static void printLines(String[][] game) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.printf("%s ", game[i][j]);
            }

            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    public static String[][] populateGameData() {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        String[][] game = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game[i][j] = data.substring(3 * i + j, 3 * i + j + 1);
            }
        }

        return game;
    }

    public static boolean hasThreeOf(String[][] game, String sChar) {
        return hasThreeColumnsOf(game, sChar) || hasThreeDiagonalsOf(game, sChar) || hasThreeRowsOf(game, sChar);
    }

    public static boolean hasThreeRowsOf(String[][] game, String sChar) {
        for (int i = 0; i < 3; i++) {
            if (game[i][0].equals(sChar) && game[i][1].equals(sChar) && game[i][2].equals(sChar)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasThreeColumnsOf(String[][] game, String sChar) {
        for (int j = 0; j < 3; j++) {
            if (game[0][j].equals(sChar) && game[1][j].equals(sChar) && game[2][j].equals(sChar)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasThreeDiagonalsOf(String[][] game, String sChar) {
        if (game[0][0].equals(sChar) && game[1][1].equals(sChar) && game[2][2].equals(sChar)) {
            return true;
        }

        if (game[0][2].equals(sChar) && game[1][1].equals(sChar) && game[2][0].equals(sChar)) {
            return true;
        }

        return false;
    }

    public static boolean hasEmptyCells(String[][] game) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game[i][j].equals("_") || game[i][j].equals(" ")) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isImpossibleState(String[][] game) {
        int x = 0; // how many X are in the fields?
        int o = 0; // how many O are in the fields?

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game[i][j].equals("X")) {
                    x++;
                } else if (game[i][j].equals("O")) {
                    o++;
                }
            }
        }

        return x > o + 1 || o > x + 1;
    }

    public static String evaluateGame(String[][] game) {
            boolean threeX = hasThreeOf(game, "X");
            boolean threeO = hasThreeOf(game, "O");
            if (isImpossibleState(game)) {
                return "Impossible";
            } else if (threeX && !threeO) {
                return "X wins";
            } else if (!threeX && threeO) {
                return "O wins";
            } else if (!threeX && !threeO) {
                if (hasEmptyCells(game)) {
                    return "Game not finished";
                } else {
                    return "Draw";
                }
            } else if (threeX && threeO) {
                return "Impossible";
            } else {
                return "Unknown";
            }
            
        }

    public static void main(String[] args) {
            String[][] game = populateGameData();
            String choice = "X";
            String result;
            while (true) {
                printLines(game);
                result = evaluateGame(game);
                if (result.equals("Impossible") || result.equals("X wins") || result.equals("O wins") ||
                        result.equals("Draw")) {
                    System.out.println(result);
                    break;
                }
                System.out.println("Game not finished");
                break;
            }
            
    } 
}   