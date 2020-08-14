package sudoko;

import java.util.*;

public class Sudoko {

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static int counter = 0;

    public static void main(String[] args) {
//worlds hardest
        int[][] card = {
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 6, 0, 0, 0, 0, 0},
            {0, 7, 0, 0, 9, 0, 2, 0, 0},
            {0, 5, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 4, 5, 7, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 3, 0},
            {0, 0, 1, 0, 0, 0, 0, 6, 8},
            {0, 0, 8, 5, 0, 0, 0, 1, 0},
            {0, 9, 0, 0, 0, 0, 4, 0, 0}};

        //int[][] card = new int[9][9];
//        card[0][0] = 7;
//        int[][] card
//                = {{0, 0, 4, 0, 5, 0, 1, 0, 0},
//                   {0, 5, 0, 9, 0, 8, 0, 7, 0},
//                   {8, 0, 0, 0, 0, 0, 0, 0, 5},
//                   {0, 3, 0, 5, 0, 1, 0, 4, 0},
//                   {1, 0, 0, 0, 0, 0, 0, 0, 2},
//                   {0, 6, 0, 8, 0, 9, 0, 3, 0},
//                   {2, 0, 0, 0, 0, 0, 0, 0, 9},
//                   {0, 8, 0, 2, 0, 4, 0, 5, 0},
//                   {0, 0, 5, 0, 7, 0, 6, 0, 0}};
//         int[][] card
//                = {{5, 3, 0, 4, 0, 0, 0, 0, 0},
//                   {6, 9, 0, 8, 3, 0, 0, 0, 0},
//                   {0, 1, 8, 0, 0, 0, 0, 0, 0},                 
//                   {1, 6, 9, 0, 8, 0, 0, 0, 7},
//                   {0, 2, 0, 0, 7, 0, 0, 6, 0},
//                   {7, 0, 0, 0, 5, 0, 1, 8, 9},
//                   {0, 0, 0, 0, 0, 0, 9, 5, 0},
//                   {0, 0, 0, 0, 4, 8, 0, 3, 1},
//                   {0, 0, 0, 0, 0, 0, 0, 7, 8}};
//                int[][] card
//                = {{1, 0, 0, 0, 0, 7, 0, 9, 0},
//                   {0, 3, 0, 0, 2, 0, 0, 0, 8},
//                   {0, 0, 9, 6, 0, 0, 5, 0, 0},                 
//                   {0, 0, 5, 3, 0, 0, 9, 0, 0},
//                   {0, 1, 0, 0, 8, 0, 0, 0, 2},
//                   {6, 0, 0, 0, 0, 4, 0, 0, 0},
//                   {3, 0, 0, 0, 0, 0, 0, 1, 0},
//                   {0, 4, 0, 0, 0, 0, 0, 0, 7},
//                   {0, 0, 7, 0, 0, 0, 3, 0, 0}};
        print(card);
        solve(card);//gets the for sures
        //if (checkComplete(card) == false) {//if not cpmplete after the for sures, uses backtracking to guess and check for the rest of them
        solveGuess(card);
        //}
        System.out.println("\n");
        print(card);
        System.out.println("\nIt took " + counter + " tries");

    }
    
    public static void checkValid(int[][] card){
        for (int i = 0; i < card.length; i++){
            for (int j = 0; j < card[i].length; j++){
                
            }
        }
    }

    public static void print(int[][] card) {
        for (int i = 0; i < card.length; i++) {
            if (i == 3 || i == 6) {
                System.out.println("---------------------");
            }
            for (int j = 0; j < card[i].length; j++) {
                System.out.print(card[i][j] + " ");
                if (j == 2 || j == 5) {
                    System.out.print("| ");
                }
            }
            System.out.println("");

        }
    }

    public static void solve(int[][] card) {
        int changes = 0;

        for (int i = 0; i < card.length; i++) {
            for (int j = 0; j < card[i].length; j++) {
                if (card[i][j] == 0) {
                    ArrayList<Integer> possible = new ArrayList<Integer>();
                    for (int a = 1; a <= 9; a++) {
                        possible.add(a);
                    }
                    //check box
                    int box = getBox(i, j);
                    checkBox(possible, box, card);
                    //check Vertical
                    for (int a = 0; a < card.length; a++) {
                        for (int b = 0; b < possible.size(); b++) {
                            if (card[a][j] == possible.get(b)) {
                                possible.remove(b);
                            }
                        }
                    }
                    //check Horizontal
                    for (int a = 0; a < card.length; a++) {
                        for (int b = 0; b < possible.size(); b++) {
                            if (card[i][a] == possible.get(b)) {
                                possible.remove(b);
                            }
                        }
                    }
                    //if there is only one possible
                    if (possible.size() == 1) {
                        card[i][j] = possible.get(0);
                        System.out.println(ANSI_BLUE + possible.get(0) + ANSI_RESET);
                        changes++;
                        counter++;
                    }
                }
            }
        }
        if (changes == 0) {
            return;
        }
        //if(checkComplete(card) == false) {
        solve(card);
        //}
    }

    public static boolean solveGuess(int[][] card) {

        for (int i = 0; i < card.length; i++) {
            for (int j = 0; j < card[i].length; j++) {
                if (card[i][j] == 0) {
                    for (int p = 1; p <= 9; p++) {
                        int box = getBox(i, j);
                        if (checkHorizontal(p, i, card) && checkVertical(p, j, card) && checkBox(p, box, card)) {

                            card[i][j] = p;
                            counter++;
                            //System.out.println(counter);
                            System.out.println(ANSI_RED + p + ANSI_RESET);
                            //solve(card);
                            solveGuess(card);
                            if (checkComplete(card)) {
                                return true;//when it is completely done
                            }
                        }
                    }
                    card[i][j] = 0;//resets the one that is false
                    return false;//goes back to the next possible option of where the mistake was made
                }
            }
        }
        return true;
    }

    public static boolean checkHorizontal(int num, int i, int[][] card) {
        for (int a = 0; a < card.length; a++) {
            if (card[i][a] == num) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkVertical(int num, int j, int[][] card) {
        for (int a = 0; a < card.length; a++) {
            if (card[a][j] == num) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkBox(int num, int box, int[][] card) {
        if (box == 1) {
            for (int c = 0; c <= 2; c++) {
                for (int d = 0; d <= 2; d++) {
                    if (card[c][d] == num) {
                        return false;
                    }
                }
            }
        } else if (box == 2) {
            for (int c = 0; c <= 2; c++) {
                for (int d = 3; d <= 5; d++) {
                    if (card[c][d] == num) {
                        return false;
                    }
                }
            }
        } else if (box == 3) {
            for (int c = 0; c <= 2; c++) {
                for (int d = 6; d <= 8; d++) {
                    if (card[c][d] == num) {
                        return false;
                    }
                }
            }
        } else if (box == 4) {
            for (int c = 3; c <= 5; c++) {
                for (int d = 0; d <= 2; d++) {
                    if (card[c][d] == num) {
                        return false;
                    }
                }
            }
        } else if (box == 5) {
            for (int c = 3; c <= 5; c++) {
                for (int d = 3; d <= 5; d++) {
                    if (card[c][d] == num) {
                        return false;
                    }
                }
            }
        } else if (box == 6) {
            for (int c = 3; c <= 5; c++) {
                for (int d = 6; d <= 8; d++) {
                    if (card[c][d] == num) {
                        return false;
                    }
                }
            }
        } else if (box == 7) {
            for (int c = 6; c <= 8; c++) {
                for (int d = 0; d <= 2; d++) {
                    if (card[c][d] == num) {
                        return false;
                    }
                }
            }
        } else if (box == 8) {
            for (int c = 6; c <= 8; c++) {
                for (int d = 3; d <= 5; d++) {
                    if (card[c][d] == num) {
                        return false;
                    }
                }
            }
        } else {
            for (int c = 6; c <= 8; c++) {
                for (int d = 6; d <= 8; d++) {
                    if (card[c][d] == num) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean checkComplete(int[][] card) {
        for (int i = 0; i < card.length; i++) {
            for (int j = 0; j < card[i].length; j++) {
                if (card[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int getBox(int i, int j) {
        if ((i >= 0 && i <= 2) && (j >= 0 && j <= 2)) {
            return 1;
        } else if ((i >= 0 && i <= 2) && (j >= 3 && j <= 5)) {
            return 2;
        } else if ((i >= 0 && i <= 2) && (j >= 6 && j <= 8)) {
            return 3;
        } else if ((i >= 3 && i <= 5) && (j >= 0 && j <= 2)) {
            return 4;
        } else if ((i >= 3 && i <= 5) && (j >= 3 && j <= 5)) {
            return 5;
        } else if ((i >= 3 && i <= 5) && (j >= 6 && j <= 8)) {
            return 6;
        } else if ((i >= 6 && i <= 8) && (j >= 0 && j <= 2)) {
            return 7;
        } else if ((i >= 6 && i <= 8) && (j >= 3 && j <= 5)) {
            return 8;
        } else {
            return 9;
        }
    }

    public static void checkBox(ArrayList<Integer> possible, int box, int[][] card) {
        if (box == 1) {
            for (int c = 0; c <= 2; c++) {
                for (int d = 0; d <= 2; d++) {
                    for (int b = 0; b < possible.size(); b++) {
                        if (card[c][d] == possible.get(b)) {
                            possible.remove(b);
                        }
                    }
                }
            }
        } else if (box == 2) {
            for (int c = 0; c <= 2; c++) {
                for (int d = 3; d <= 5; d++) {
                    for (int b = 0; b < possible.size(); b++) {
                        if (card[c][d] == possible.get(b)) {
                            possible.remove(b);
                        }
                    }
                }
            }
        } else if (box == 3) {
            for (int c = 0; c <= 2; c++) {
                for (int d = 6; d <= 8; d++) {
                    for (int b = 0; b < possible.size(); b++) {
                        if (card[c][d] == possible.get(b)) {
                            possible.remove(b);
                        }
                    }
                }
            }
        } else if (box == 4) {
            for (int c = 3; c <= 5; c++) {
                for (int d = 0; d <= 2; d++) {
                    for (int b = 0; b < possible.size(); b++) {
                        if (card[c][d] == possible.get(b)) {
                            possible.remove(b);
                        }
                    }
                }
            }
        } else if (box == 5) {
            for (int c = 3; c <= 5; c++) {
                for (int d = 3; d <= 5; d++) {
                    for (int b = 0; b < possible.size(); b++) {
                        if (card[c][d] == possible.get(b)) {
                            possible.remove(b);
                        }
                    }
                }
            }
        } else if (box == 6) {
            for (int c = 3; c <= 5; c++) {
                for (int d = 6; d <= 8; d++) {
                    for (int b = 0; b < possible.size(); b++) {
                        if (card[c][d] == possible.get(b)) {
                            possible.remove(b);
                        }
                    }
                }
            }
        } else if (box == 7) {
            for (int c = 6; c <= 8; c++) {
                for (int d = 0; d <= 2; d++) {
                    for (int b = 0; b < possible.size(); b++) {
                        if (card[c][d] == possible.get(b)) {
                            possible.remove(b);
                        }
                    }
                }
            }
        } else if (box == 8) {
            for (int c = 6; c <= 8; c++) {
                for (int d = 3; d <= 5; d++) {
                    for (int b = 0; b < possible.size(); b++) {
                        if (card[c][d] == possible.get(b)) {
                            possible.remove(b);
                        }
                    }
                }
            }
        } else {
            for (int c = 6; c <= 8; c++) {
                for (int d = 6; d <= 8; d++) {
                    for (int b = 0; b < possible.size(); b++) {
                        if (card[c][d] == possible.get(b)) {
                            possible.remove(b);
                        }
                    }
                }
            }
        }
    }
}
