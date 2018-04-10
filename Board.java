package wordsearch;

import java.util.*;
import javax.swing.JFrame;
import java.awt.Rectangle;

/*
 * Class that displays solved wordsearch as a graphic.
 */
public class Board{
  static int windowWidth, windowHeigth;
  char[][] board;
  HashMap<String, Solver.Coord> solutions;

 /*
  * Constructor which sets the characters for the board, solutions, and window size.
  * @param char[][] Wordsearch board
  * @param HashMap<String, Solver.Coord> Word as key and location as value
  */
  public Board(char[][] letters, HashMap<String, Solver.Coord> solutions) {
    this.board = letters;
    this.solutions = solutions;
    windowWidth = board[0].length*40 + 200;
    windowHeigth = board.length*40 + 200;
  }

  /*
   * Method that displays wordsearch as a graphic.
   */
  public void showBoard() {
    BoardOutline BO = new BoardOutline(board, solutions);

    JFrame window = new JFrame();
    window.setSize(windowWidth, windowHeigth);
    window.setTitle("Solved Word Search");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setVisible(true);
    window.add(BO);
  }
}
