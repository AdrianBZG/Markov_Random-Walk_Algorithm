/**
 * @author Adrián Rodríguez Bazaga 
 * @version 1.0.0
 * @date 25 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programación de Aplicaciones Interactivas
 * @title Assignment 9 - Random Walk
 */

package logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class RandomWalk {
  private Random randomNumberGenerator = new Random();			        // Random number generator
  private Point initialPoint;		                                    // Initial point of the walk
  private Point currentPoint;		                                    // Current point of the walk
  private int numberOfRows;				                                  // Number of the grid rows
  private int numberOfColumns;				                              // Number of the grid columns
  private ArrayList<Point> visitedPoints = new ArrayList<Point>();	// Array to store the walk already visited points

  /**
   *  
   * @param initialPoint The initial point
   * @param numberOfRows Number of rows
   * @param numberOfColumns Number of columns
   */
  public RandomWalk(Point initialPoint, int numberOfRows, int numberOfColumns) {
    setInitialPoint(initialPoint);
    setCurrentPoint(initialPoint);
    setNumberOfRows(numberOfRows);
    setNumberOfColumns(numberOfColumns);
    getVisitedPoints().add(initialPoint);
  }

  /**
   * Calculate a random direction (from the available ones)
   * @return
   */
  private DirectionEnum calculateNextStep() {
    final int NUMBER_OF_DIRECTIONS = 4;
    int chosenNumber = getRandomNumberGenerator().nextInt(NUMBER_OF_DIRECTIONS);

    switch (chosenNumber) {
      case 0: return DirectionEnum.UP;
      case 1: return DirectionEnum.DOWN;
      case 2: return DirectionEnum.RIGHT;
      case 3: return DirectionEnum.LEFT;
      default: return null;
    }
  }
  
  /**
   * Calculates the next position for the walk
   * @return true If the position was calculated successfully
   */
  public boolean calculateNextPosition() {
    DirectionEnum chosenDirection;
    Point newPosition = null;

    if (!boundsReached(getCurrentPoint())) {
      chosenDirection = calculateNextStep();

      switch(chosenDirection) {
        case UP: newPosition = new Point((int)getCurrentPoint().getX(), (int)(getCurrentPoint().getY() - 1));
                               break;
        case DOWN: newPosition = new Point((int)getCurrentPoint().getX(), (int)(getCurrentPoint().getY() + 1));
                                 break;
        case RIGHT: newPosition = new Point((int)(getCurrentPoint().getX() + 1), (int)(getCurrentPoint().getY()));
                                  break;
        case LEFT: newPosition = new Point((int)(getCurrentPoint().getX() - 1), (int)(getCurrentPoint().getY()));
                                 break;
      }
      
      getVisitedPoints().add(newPosition);
      setCurrentPoint(newPosition);
      return true;
    }
    return false;
  }
  
  /**
   * This method checks if a given Point belongs to the bounds of the window
   * @param position Position to analyze
   * @return true If it's a position from the bounds of the window
   */
  public boolean boundsReached(Point position) {
    if (position.getX() == 0 || position.getX() == getNumberOfColumns()) { // Left or right limits
      return true;
    }
    if (position.getY() == 0 || position.getY() == getNumberOfRows()) { // Lower or upper limits
      return true;
    }
    return false;
  }
  
  /**
   * This is the getter for the initial point
   * @return The initial point as a Point
   */
  public Point getInitialPoint() {
    return initialPoint;
  }

  /**
   * This is the setter for the initial point
   * @param startPoint
   */
  public void setInitialPoint(Point startPoint) {
    this.initialPoint = startPoint;
  }

  /**
   * This is the getter for the current point
   * @return The current point as a Point
   */
  public Point getCurrentPoint() {
    return currentPoint;
  }

  /**
   * This is the setter for the current point
   * @param currentPoint
   */
  public void setCurrentPoint(Point currentPoint) {
    this.currentPoint = currentPoint;
  }

  /**
   * This is the getter for the number of rows
   * @return The number of rows as an int
   */
  public int getNumberOfRows() {
    return numberOfRows;
  }

  /**
   * This is the setter for the number of rows
   * @param numberOfRows
   */
  public void setNumberOfRows(int numberOfRows) {
    this.numberOfRows = numberOfRows;
  }

  /**
   * This is the getter for the number of columns
   * @return The number of columns as an int
   */
  public int getNumberOfColumns() {
    return numberOfColumns;
  }

  /**
   * This is the setter for the number of columns
   * @param numberOfColumns
   */
  public void setNumberOfColumns(int numberOfColumns) {
    this.numberOfColumns = numberOfColumns;
  }

  /**
   * This is the getter for the Array of visited points
   * @return The visited points as an Array of Point
   */
  public ArrayList<Point> getVisitedPoints() {
    return visitedPoints;
  }

  /**
   * This is the getter for the random number generator
   * @return The random number generator as a Random
   */
  public Random getRandomNumberGenerator() {
    return randomNumberGenerator;
  }

  /**
   * This is the setter for the random number generator
   * @param randomNumberGenerator
   */
  public void setRandomNumberGenerator(Random randomNumberGenerator) {
    this.randomNumberGenerator = randomNumberGenerator;
  }

  /**
   * This is the setter for the walk visited points
   * @param visitedPoints
   */
  public void setVisitedPoints(ArrayList<Point> visitedPoints) {
    this.visitedPoints = visitedPoints;
  }
}