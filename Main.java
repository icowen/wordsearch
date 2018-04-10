package wordsearch;

/**
 * Small word search solver.
 * @author Flip van Rijn
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        /*
         * Uses a wordsearch generator as input.
         * char[][] grid is how you can enter your own wordsearch Board.
         * String[] words holds the words that are searched for.
         * This is where you can change which board is being solved.
         */
         //WordsearchGenerator test = new WordsearchGenerator();
         //char[][] grid = test.getBoard();
         //String[] words = test.getWords();


        /* Uses a .txt file as input for wordsearch.
         * File contains lines of the board written on separate lines,
         * the word "end" to indicate the next lines are the words, and
         * the words entered on separate lines.
         */
         //String file = "C:\\Users\\ian_c\\java\\wordsearch\\wordsearch\\toTheSun.txt";
         ImportWordsearch test = new ImportWordsearch();
         char[][] grid = test.getLetters();
         String[] words = test.getWords();

        //Solves wordsearch
        Solver solver = new Solver(grid, words);
        solver.solve();

        //Creates solved wordsearch graphic
        Board picture = new Board(grid, solver.solutions);
        picture.showBoard();

        //Prints wordsearch, words, and locations of solutions in console.
        System.out.println(solver);
    }

}
