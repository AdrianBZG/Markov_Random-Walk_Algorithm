/**
 * @author Adrián Rodríguez Bazaga 
 * @version 1.0.0
 * @date 25 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programación de Aplicaciones Interactivas
 * @title Assignment 9 - Random Walk
 */

package testing;

import static org.assertj.swing.core.MouseClickInfo.leftButton;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gui.ControlButton;
import gui.RandomWalkView;
import logic.ButtonEnum;

public class RandomWalkPanelTesting {
  final ButtonEnum STARTSTOP_BUTTON = ButtonEnum.STARTSTOP;              // Start/Stop button identifier
  final ButtonEnum FINISH_BUTTON = ButtonEnum.FINISH;                    // Finish button identifier
  final ButtonEnum RANDOM_COLOR_BUTTON = ButtonEnum.RANDOMCOLOR;         // Random Color button identifier
  final ButtonEnum SELECT_COLOR_BUTTON = ButtonEnum.SELECTCOLOR;         // Select Color button identifier
  final ButtonEnum CHANGE_DELAY_BUTTON = ButtonEnum.CHANGEDELAY;         // Change Delay button identifier
  final ButtonEnum CHANGE_DENSITY_BUTTON = ButtonEnum.CHANGEDENSITY;     // Change Density button identifier
  final ButtonEnum RESET_WALK_BUTTON = ButtonEnum.RESETWALK;             // Reset Walk button identifier
  final int TIMES_TO_CLICK_A_BUTTON = 10;                                // Times to click a button, used for testing
  private FrameFixture window;                                           // The window that AssertJ Swing will use
  private RandomWalkView view;                                           // The random walk view
  
  @Before
  public void setup() {
    view = new RandomWalkView();
    view.showView();
    window = new FrameFixture(view);                                     // Start the FrameFixture for AssertJ Swing testing
  }
  
  /**
   * This test verifies that the button exists in the Keyboard
   */
  @Test
  public void testStartStopButtonExistsInKeyboard() { 
    assertThat(RandomWalkView.getKeyboard().getButton(STARTSTOP_BUTTON), instanceOf(ControlButton.class));
  }
  
  /**
   * This test verifies that the button exists in the Keyboard
   */
  @Test
  public void testFinishButtonExistsInKeyboard() { 
    assertThat(RandomWalkView.getKeyboard().getButton(FINISH_BUTTON), instanceOf(ControlButton.class));
  }
  
  /**
   * This test verifies that the button exists in the Keyboard
   */
  @Test
  public void testRandomColorButtonExistsInKeyboard() { 
    assertThat(RandomWalkView.getKeyboard().getButton(RANDOM_COLOR_BUTTON), instanceOf(ControlButton.class));
  }
  
  /**
   * This test verifies that the button exists in the Keyboard
   */
  @Test
  public void testSelectColorButtonExistsInKeyboard() { 
    assertThat(RandomWalkView.getKeyboard().getButton(SELECT_COLOR_BUTTON), instanceOf(ControlButton.class));
  }
  
  /**
   * This test verifies that the button exists in the Keyboard
   */
  @Test
  public void testChangeDelayButtonExistsInKeyboard() { 
    assertThat(RandomWalkView.getKeyboard().getButton(CHANGE_DELAY_BUTTON), instanceOf(ControlButton.class));
  }
  
  /**
   * This test verifies that the button exists in the Keyboard
   */
  @Test
  public void testChangeDensityButtonExistsInKeyboard() { 
    assertThat(RandomWalkView.getKeyboard().getButton(CHANGE_DENSITY_BUTTON), instanceOf(ControlButton.class));
  }
  
  /**
   * This test verifies that the button exists in the Keyboard
   */
  @Test
  public void testResetWalkButtonExistsInKeyboard() { 
    assertThat(RandomWalkView.getKeyboard().getButton(RESET_WALK_BUTTON), instanceOf(ControlButton.class));
  }
  
  /**
   * This test verifies that all buttons are clickable, this implicitily verifies that:
   * - The buttons exist
   * - The buttons are working
   */
  @Test
  public void testButtonsAreClickable() {
    final String START_BUTTON_TEXT = "Start";
    final String FINISH_BUTTON_TEXT = "Finish";
    final String RANDOM_COLOR_BUTTON_TEXT = "Random Color";
    final String SELECT_COLOR_BUTTON_TEXT = "Select Color";
    final String CHANGE_DELAY_BUTTON_TEXT = "Change Delay";
    final String CHANGE_DENSITY_BUTTON_TEXT = "Change Density";
    final String RESET_BUTTON_TEXT = "Reset";
    
    window.button(START_BUTTON_TEXT).click(leftButton().times(1));
    window.button(FINISH_BUTTON_TEXT).click(leftButton().times(TIMES_TO_CLICK_A_BUTTON));
    window.button(RANDOM_COLOR_BUTTON_TEXT).click(leftButton().times(TIMES_TO_CLICK_A_BUTTON));
    window.button(SELECT_COLOR_BUTTON_TEXT).click(leftButton().times(TIMES_TO_CLICK_A_BUTTON));
    window.button(CHANGE_DELAY_BUTTON_TEXT).click(leftButton().times(TIMES_TO_CLICK_A_BUTTON));
    window.button(CHANGE_DENSITY_BUTTON_TEXT).click(leftButton().times(TIMES_TO_CLICK_A_BUTTON));
    window.button(RESET_BUTTON_TEXT).click(leftButton().times(TIMES_TO_CLICK_A_BUTTON));
  }
  
  /**
   * We clean the window element (the FrameFixture), it's a good practice
   */
  @After
  public void clear() {
    window.cleanUp();
  }
}
