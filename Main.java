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
         * String[] words holds the words that are search for.
         * This is where you can change which board is being solved.
         */
        WordsearchGenerator test = new WordsearchGenerator();
        char[][] grid = test.getBoard();
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
