/**
 * @author Adrián Rodríguez Bazaga 
 * @version 1.0.0
 * @date 25 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programación de Aplicaciones Interactivas
 * @title Assignment 9 - Random Walk
 */

package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import javax.swing.JPanel;
import logic.RandomWalk;

public class RandomWalkPanel  extends JPanel{
  final int DEFAULT_MOVEMENT_DELAY = 5;                 // Default movement delay for the random walk
  private int panelDensity;			                        // Number of available cells to draw
  private int movementDelay = DEFAULT_MOVEMENT_DELAY;   // Movement delay of the random walk
  private int rows;				                              // Number of rows
  private int columns;				                          // Number of columns
  private RandomWalk walk;		                          // The random walk
  private Color walkLineColor;			                    // Color for the random walk line

  public RandomWalkPanel(int density) {
    setDensity(density);
  }
  
  /**
   * Calculates one step of the simulation and draws it
   */
  public void calculateNextStep() {
    getRandomWalk().calculateNextPosition();
    repaint();
  }
  
  /**
   * Calculates every step until the end of the simulation and then draws the walk
   */
  public void finishWalk(){
    while (getRandomWalk().calculateNextPosition());
    repaint();
  }
  
  /**
   * Resets the simulation
   */
  public void resetWalk() {
    final int AVAILABLE_CELLS_SCALE_FACTOR = 2;     // We use this to scale the total cells into Rows and Columns
    setRandomWalk(new RandomWalk(new Point(getNumberOfColumns() / AVAILABLE_CELLS_SCALE_FACTOR, getNumberOfRows() / AVAILABLE_CELLS_SCALE_FACTOR), getNumberOfRows(), getNumberOfColumns()));
    repaint();
  }

  /**
   * Getter for the density of the walk panel
   * @return The density as an int
   */
  public int getDensity() {
    return panelDensity;
  }

  /**
   * Setter for the density of the panel
   * @param panelDensity
   */
  public void setDensity(int panelDensity) {
    final int AVAILABLE_CELLS_SCALE_FACTOR = 2;     // We use this to scale the total cells into Rows and Columns
    this.panelDensity = panelDensity;
    int availableLines = (int)Math.sqrt(panelDensity);    // Calculating the number of available lines with the given density to split it into Rows and Columns
    setRows(availableLines);
    setCols(availableLines);
    setRandomWalk(new RandomWalk(new Point(getNumberOfColumns() / AVAILABLE_CELLS_SCALE_FACTOR, getNumberOfRows() / AVAILABLE_CELLS_SCALE_FACTOR), getNumberOfRows(), getNumberOfColumns()));
    repaint();
  }

  /**
   * Getter for the movement delay
   * @return The movement delay as an int
   */
  public int getMovementDelay() {
    return movementDelay;
  }

  /**
   * Setter for the movement delay
   * @param movementDelay
   */
  public void setMovementDelay(int movementDelay) {
    this.movementDelay = movementDelay;
  }

  /**
   * Getter for the walk
   * @return The random walk as a RandomWalk
   */
  public RandomWalk getRandomWalk() {
    return walk;
  }

  /**
   * Setter for the random walk
   * @param walk
   */
  public void setRandomWalk(RandomWalk walk) {
    this.walk = walk;
  }

  /**
   * Getter for the random walk line color
   * @return The line color as a Color
   */
  public Color getWalkLineColor() {
    return walkLineColor;
  }

  /**
   * Setter for the walk line color
   * @param walkLineColor
   */
  public void setWalkLineColor(Color walkLineColor) {
    this.walkLineColor = walkLineColor;
    repaint();
  }

  /**
   * Getter for the number of rows
   * @return The number of rows as an int
   */
  public int getNumberOfRows() {
    return rows;
  }

  /**
   * Setter for the number of rows
   * @param rows
   */
  public void setRows(int rows) {
    this.rows = rows;
  }

  /**
   * Getter for the number of columns
   * @return The number of columns as an int
   */
  public int getNumberOfColumns() {
    return columns;
  }

  /**
   * Setter for the number of columns
   * @param columns
   */
  public void setCols(int columns) {
    this.columns = columns;
  }

  /**
   * Method used to paint the table (grid) and the walk
   */
  protected void paintComponent(Graphics g) {	
    super.paintComponent(g);	
    drawTable(g);	

    g.setColor(getWalkLineColor());
    drawWalk(g);
  }
  
  /**
   * This method draws the walk
   * @param g
   */
  protected void drawWalk(Graphics g) {
    double rowSpace = (double)getHeight() / (double)getNumberOfRows();     // Space between rows
    double colSpace = (double)getWidth() / (double)getNumberOfColumns();   // Space between columns

    // Create the polygons to save the points
    Polygon graphicWalk1 = new Polygon();
    Polygon graphicWalk2 = new Polygon();
    Polygon graphicWalk3 = new Polygon();
    Polygon graphicWalk4 = new Polygon();
    Polygon graphicWalk5 = new Polygon();

    // Save the points of the visited points into the polygons
    for (int i = 0; i < getRandomWalk().getVisitedPoints().size(); i++) {
      graphicWalk1.addPoint((int)(getRandomWalk().getVisitedPoints().get(i).getX() * colSpace), (int)(getRandomWalk().getVisitedPoints().get(i).getY() * rowSpace));
      graphicWalk2.addPoint((int)(getRandomWalk().getVisitedPoints().get(i).getX() * colSpace - 1.0), (int)(getRandomWalk().getVisitedPoints().get(i).getY() * rowSpace));
      graphicWalk3.addPoint((int)(getRandomWalk().getVisitedPoints().get(i).getX() * colSpace + 1.0), (int)(getRandomWalk().getVisitedPoints().get(i).getY() * rowSpace));
      graphicWalk4.addPoint((int)(getRandomWalk().getVisitedPoints().get(i).getX() * colSpace), (int)(getRandomWalk().getVisitedPoints().get(i).getY() * rowSpace + 1.0));
      graphicWalk5.addPoint((int)(getRandomWalk().getVisitedPoints().get(i).getX() * colSpace), (int)(getRandomWalk().getVisitedPoints().get(i).getY() * rowSpace - 1.0));
    }
    
    // And finally draw the poly lines
    g.drawPolyline(graphicWalk1.xpoints, graphicWalk1.ypoints, graphicWalk1.npoints);
    g.drawPolyline(graphicWalk2.xpoints, graphicWalk2.ypoints, graphicWalk2.npoints);
    g.drawPolyline(graphicWalk3.xpoints, graphicWalk3.ypoints, graphicWalk3.npoints);
    g.drawPolyline(graphicWalk4.xpoints, graphicWalk4.ypoints, graphicWalk2.npoints);
    g.drawPolyline(graphicWalk5.xpoints, graphicWalk5.ypoints, graphicWalk3.npoints);
  }
  
  /**
   * This method draws the table (the grid where we'll draw the walk)
   * @param g
   */
  protected void drawTable(Graphics g) {
    final Color TABLE_DEFAULT_COLOR = Color.GRAY;
    double rowSpace = (double)getHeight() / (double)getNumberOfRows();       // Space between rows
    double colSpace = (double)getWidth() / (double)getNumberOfColumns();     // Space between columns

    g.setColor(TABLE_DEFAULT_COLOR);

    // Draw the columns lines
    for (int i = 1; i < getNumberOfColumns(); i++) {
      g.drawLine(0, (int)(i * rowSpace), getWidth(), (int)(i * rowSpace));
    }
    
    // And draw the rows lines
    for (int i = 1; i < getNumberOfRows(); i++) {
      g.drawLine((int)(i * colSpace), 0, (int)(i * colSpace), getHeight());
    }
  }
}
