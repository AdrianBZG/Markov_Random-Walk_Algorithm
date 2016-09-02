/**
 * @author Adrián Rodríguez Bazaga 
 * @version 1.0.0
 * @date 25 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programación de Aplicaciones Interactivas
 * @title Assignment 9 - Random Walk
 */

package testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import gui.RandomWalkView;

public class RandomWalkLogicTesting {
  
  @Before
  public void setup() {
    // We don't need to setup the view because we'll access it in a static way
  }
  
  /**
   * This test verifies that the Random Walk is calculating the
   * next position properly
   */
  @Test
  public void shouldCalculateNextPositionProperly() {
    assertTrue(RandomWalkView.getRandomWalkPanel().getRandomWalk().calculateNextPosition());
  }
  
  /**
   * This test verifies that when the walk starts it hasn't reached the bounds of the window
   */
  @Test
  public void shouldNotReachBoundsOnStart() {
    Point currentPosition = RandomWalkView.getRandomWalkPanel().getRandomWalk().getCurrentPoint();
    assertFalse(RandomWalkView.getRandomWalkPanel().getRandomWalk().boundsReached(currentPosition));
  }
  
  /**
   * This test verifies that when the walk finishes it has reached the bounds
   */
  @Test
  public void shouldReachBoundsOnFinish() {
    RandomWalkView.getRandomWalkPanel().finishWalk();
    Point currentPosition = RandomWalkView.getRandomWalkPanel().getRandomWalk().getCurrentPoint();
    assertTrue(RandomWalkView.getRandomWalkPanel().getRandomWalk().boundsReached(currentPosition));
  }
}
