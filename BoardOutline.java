package wordsearch;

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.JComponent;
import java.util.*;

/*
 * Class that creates wordsearch as a Graphics2D.
 */
public class BoardOutline extends JComponent {
  char[][] words;
  HashMap<String, Solver.Coord> solutions;
  int x = 100;
  int y = 100;

 /*
  * Constructor that sets the letters on the board
  * and the words with their locations.
  * @param char[][] letters for the Board
  * @param HashMap<String, Solver.Coord> Words on the board and their locations.
  */
  public BoardOutline(char[][] words, HashMap<String, Solver.Coord> solutions) {
    this.words = words;
    this.solutions = solutions;
  }

  /*
   * Method that positions characters and rectangles on a Graphics2D.
   * @param Graphics Window that displays the Board
   */
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;

    //Set font type
    g2.setColor(new Color(0,0,0));
    g2.setFont(new Font("serif", Font.BOLD, 20));

    //Places each character 40 pixels(?) apart
    for(char[] line : words) {
      x = 100;
      for(char c : line) {
        String letter = Character.toString(c);
        g2.drawString(letter.toUpperCase() , x, y);
        x = x + 40;
      }
      y = y + 40;
    }

    //Draws a rectangle around each word in solution HashMap
    for(String key : solutions.keySet()) {
      int startX = solutions.get(key).getStartX()*40 + 90;
      int startY = solutions.get(key).getStartY()*40 + 80;
      int endX = solutions.get(key).getEndX()*40 + 90;
      int endY = solutions.get(key).getEndY()*40 + 80;
      int width = Math.abs(endX - startX + 30);
      int height = Math.abs(endY - startY + 30);

      if(startY == endY && startX > endX) {           //Word Going Left

          g2.drawRect(endX, endY, width + 70, 30);

      } else if(startX == endX && startY > endY) {    //Word Going Up

          g2.drawRect(endX, endY, 30, height + 70);

      } else if (startX == endX || startY == endY) {  //Word Going Right or Down

          g2.drawRect(startX, startY, width, height);

      } else {
        /*
         * Polygons are used for diagonal words because so you
         * don't have to rotate rectangles.
         */
          int[] x = new int[4];     //Array of x-coordinates
          int[] y = new int[4];     //Array of y-coordinates corresponding to x

          if(startY < endY) {
            if(startX < endX) {                       //Right-Down diagonal
              x[0] = startX - 10;
              y[0] = startY;
              x[1] = startX + 30;
              y[1] = startY;
              x[2] = endX + 50;
              y[2] = endY + 30;
              x[3] = endX + 10;
              y[3] = endY + 30;
            } else {                                 //Left-Down diagonal
              x[0] = startX;
              y[0] = startY;
              x[1] = startX + 40;
              y[1] = startY;
              x[2] = endX + 30;
              y[2] = endY + 30;
              x[3] = endX - 10;
              y[3] = endY + 30;
            }
          } else {
              if(startX < endX){                      //Right-Up diagonal
                x[0] = endX;
                y[0] = endY;
                x[1] = endX + 40;
                y[1] = endY;
                x[2] = startX + 30;
                y[2] = startY + 30;
                x[3] = startX - 10;
                y[3] = startY + 30;
              } else {                                //Left-Up diagonal
                x[0] = endX - 10;
                y[0] = endY;
                x[1] = endX + 30;
                y[1] = endY;
                x[2] = startX + 50;
                y[2] = startY + 30;
                x[3] = startX + 10;
                y[3] = startY + 30;
                }
              }
        g2.drawPolygon(x, y, 4);
      }
    }
  }
}
