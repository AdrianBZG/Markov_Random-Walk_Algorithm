/**
 * @author Adrián Rodríguez Bazaga 
 * @version 1.0.0
 * @date 25 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programación de Aplicaciones Interactivas
 * @title Assignment 9 - Random Walk
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.ButtonEnum;

public class RandomWalkView extends JFrame {
  public static final String START_BUTTON_TEXT = "Start";
  public static final String STOP_BUTTON_TEXT = "Stop";
  public static final String FINISH_BUTTON_TEXT = "Finish";
  public static final String RANDOM_COLOR_BUTTON_TEXT = "Random Color";
  public static final String SELECT_COLOR_BUTTON_TEXT = "Select Color";
  public static final String CHANGE_DELAY_BUTTON_TEXT = "Change Delay";
  public static final String CHANGE_DENSITY_BUTTON_TEXT = "Change Density";
  public static final String RESET_BUTTON_TEXT = "Reset";
  public static final int DEFAULT_DENSITY = 10000;                                        // Default density value for the ramdom walk
  public static final Color DEFAULT_WALK_COLOR = Color.RED;                               // Default color for the walk
  public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;                       // Default background color for the walk frame
  private JPanel buttonsPanel = new JPanel();							                                // Panel that contains the Keyboard
  private static Keyboard keyboard = new Keyboard();                                      // The keyboard, that will have all the buttons
  private static RandomWalkPanel randomWalkPanel = new RandomWalkPanel(DEFAULT_DENSITY);	// Panel where the walk'll be drawn
  private static Timer randomMovementTimer = new Timer();                                 // Timer to draw the next movement
  private static boolean timerIsRunning;                                                  // Status of the timer. True = Running, False = Paused

  /**
   * This is the view default constructor
   */
  public RandomWalkView() {
    initializeButtons();     // Initialize the buttons (Keyboard + Buttons)
    initializeListeners();   // Initialize the listeners of every button in the Keyboard
    initializePanels();      // Initialize the panels of the view
    initializeView();        // And finally initialize the view and it's settings
  }
  
  private void initializeButtons() {
    // First, we initialize the keyboard before we add the control buttons
    setKeyboard(new Keyboard());    
    
    getKeyboard().add(new ControlButton(START_BUTTON_TEXT, ButtonEnum.STARTSTOP));              // The Start/Stop button
    getKeyboard().add(new ControlButton(FINISH_BUTTON_TEXT, ButtonEnum.FINISH));                // The Finish button
    getKeyboard().add(new ControlButton(RANDOM_COLOR_BUTTON_TEXT, ButtonEnum.RANDOMCOLOR));     // The Random Color button
    getKeyboard().add(new ControlButton(SELECT_COLOR_BUTTON_TEXT, ButtonEnum.SELECTCOLOR));     // The Select Color button
    getKeyboard().add(new ControlButton(CHANGE_DELAY_BUTTON_TEXT, ButtonEnum.CHANGEDELAY));     // The Change Delay button
    getKeyboard().add(new ControlButton(CHANGE_DENSITY_BUTTON_TEXT, ButtonEnum.CHANGEDENSITY)); // The Change Density button
    getKeyboard().add(new ControlButton(RESET_BUTTON_TEXT, ButtonEnum.RESETWALK));              // The Reset button
  }
  
  private void initializeListeners() {
    final String DELAY_WINDOW_TEXT = "Type the movement delay (In milliseconds; Positive Integer)";
    final String SELECT_COLOR_WINDOW_TEXT = "Pick a color for the Random Walk";
    final String DENSITY_WINDOW_TEXT = "Type the desired available number of cells for the walk";
    
    // Initialize every button inner anonymous listener (ActionListener) from the Keyboard
    getKeyboard().getButton(ButtonEnum.STARTSTOP).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (getTimerState()) {
          getRandomMovementTimer().cancel();
          getKeyboard().getButton(ButtonEnum.STARTSTOP).setText(START_BUTTON_TEXT);
          setTimerState(false);
        }
        else {
          runTimers();
          getKeyboard().getButton(ButtonEnum.STARTSTOP).setText(STOP_BUTTON_TEXT);
          setTimerState(true);
        } 
      }      
    });
    
    getKeyboard().getButton(ButtonEnum.FINISH).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        getRandomMovementTimer().cancel();
        getRandomWalkPanel().finishWalk();
        getKeyboard().getButton(ButtonEnum.STARTSTOP).setText(START_BUTTON_TEXT);
      }      
    });
    
    getKeyboard().getButton(ButtonEnum.RANDOMCOLOR).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        getRandomWalkPanel().setWalkLineColor(createRandomColor());
      }      
    });
    
    getKeyboard().getButton(ButtonEnum.CHANGEDELAY).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        getRandomMovementTimer().cancel();
        MovementDelayInputWindow delayWindow = new MovementDelayInputWindow(DELAY_WINDOW_TEXT, getRandomWalkPanel());
        delayWindow.setLocationRelativeTo(null); 
        delayWindow.setVisible(true);
      }      
    });
    
    getKeyboard().getButton(ButtonEnum.SELECTCOLOR).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Color color = JColorChooser.showDialog(getView(), SELECT_COLOR_WINDOW_TEXT, Color.RED);
        if (color != null) {
          getRandomWalkPanel().setWalkLineColor(color);
        }
      }      
    });
    
    getKeyboard().getButton(ButtonEnum.CHANGEDENSITY).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        DensityInputWindow densityWindow = new DensityInputWindow(DENSITY_WINDOW_TEXT, getRandomWalkPanel());
        densityWindow.setLocationRelativeTo(null); 
        densityWindow.setVisible(true);
        getRandomMovementTimer().cancel();
        getKeyboard().getButton(ButtonEnum.STARTSTOP).setText(START_BUTTON_TEXT);
      }     
    });
    
    getKeyboard().getButton(ButtonEnum.RESETWALK).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        getRandomWalkPanel().resetWalk();
        getRandomMovementTimer().cancel();
        getKeyboard().getButton(ButtonEnum.STARTSTOP).setText(START_BUTTON_TEXT);
      }     
    });
  }
  
  private void initializePanels() {
    getButtonsPanel().setLayout(new GridLayout(1, 0));  // Set the buttons panel (lower panel) layout settings

    for(ControlButton button : getKeyboard()) {
      getButtonsPanel().add(button);  // Add every button from the Keyboard to the buttons panel
    }
  }
  
  private void initializeView() {
    add(getRandomWalkPanel(), BorderLayout.CENTER);   // Add the random walk panel to the frame (centered)
    add(getButtonsPanel(), BorderLayout.SOUTH);       // Add the buttons panel to the frame (lowered)
    initializeSettings();                             // And initialize the frame settings
  }
  
  private void initializeSettings() {
    final String WINDOW_TITLE = "Random Walk";
    final int WINDOW_WIDTH = 500;
    final int WINDOW_HEIGHT = 500;
    
    getRandomWalkPanel().setBackground(DEFAULT_BACKGROUND_COLOR);   // Set the random walk background color
    getRandomWalkPanel().setWalkLineColor(DEFAULT_WALK_COLOR);              // Set the random walk line color
    setTitle(WINDOW_TITLE);                                         // Set the frame title
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);                           // Set the frame dimensions
    setLocationRelativeTo(null);                                    // Center the frame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(false);
  }
  
  public void showView() {
    setVisible(true);           // Show the view
  }
  
  public static void runTimers() {
    getRandomMovementTimer().cancel();    // Cancel the timer if it's was running
    
    // Create the task that the timer will execute
    TimerTask randomMovementTask = new TimerTask()
    {
      /**
       * Method that the timer will call
       */
      public void run() 
      {
        getRandomWalkPanel().calculateNextStep();
      }
    };

    // And finally update the timer with the new values and set it's state as "Running"
    setRandomMovementTimer(new Timer());
    getRandomMovementTimer().scheduleAtFixedRate(randomMovementTask, 0, getRandomWalkPanel().getMovementDelay());
    setTimerState(true);
    setScheduleButtons();     // Update the Start/Stop button text
  }

  /**
   * This method updates the Start/Stop button text
   */
  private static void setScheduleButtons() {
    if (getTimerState()) {
      getKeyboard().getButton(ButtonEnum.STARTSTOP).setText(STOP_BUTTON_TEXT);
    }
    else {
      getKeyboard().getButton(ButtonEnum.STARTSTOP).setText(START_BUTTON_TEXT);
    }
  }

  /**
   * This method creates a random color using the RGB color space
   * @return The generated random color as a Color
   */
  private Color createRandomColor() {
    final int MAX_RED_VALUE = 255;
    final int MAX_GREEN_VALUE = 255;
    final int MAX_BLUE_VALUE = 255;
    final Random randomGenerator = new Random();    
    int r = randomGenerator.nextInt(MAX_RED_VALUE);
    int g = randomGenerator.nextInt(MAX_GREEN_VALUE);
    int b = randomGenerator.nextInt(MAX_BLUE_VALUE);

    return new Color(r, g, b);
  }
  
  /**
   * Setter for the keyboard
   * @param newKeyboard
   */
  private void setKeyboard(Keyboard newKeyboard) {
    keyboard = newKeyboard;
  }
  
  /**
   * Getter for the Keyboard
   * @return The keyboard as a Keyboard
   */
  public static Keyboard getKeyboard() {
    return keyboard;
  }

  /**
   * Getter for the buttons panel
   * @return The buttons panel as a JPanel
   */
  public JPanel getButtonsPanel() {
    return buttonsPanel;
  }

  /**
   * Setter for the buttons panel
   * @param buttonsPanel
   */
  public void setButtonsPanel(JPanel buttonsPanel) {
    this.buttonsPanel = buttonsPanel;
  }

  /**
   * Getter for the timer status
   * @return The timer status as a boolean. True = Running, False = Paused.
   */
  public static boolean getTimerState() {
    return timerIsRunning;
  }

  /**
   * Setter for the timer status
   * @param newTimerState
   */
  public static void setTimerState(boolean newTimerState) {
    timerIsRunning = newTimerState;
  }
  
  /**
   * Getter for the random movement timer
   * @return The random movement timer as a Timer
   */
  public static Timer getRandomMovementTimer() {
    return randomMovementTimer;
  }
  
  /**
   * Setter for the random movement timer
   * @param newTimer
   */
  public static void setRandomMovementTimer(Timer newTimer) {
    randomMovementTimer = newTimer;
  }

  /**
   * Setter for the random walk panel
   * @param newRandomWalkPanel
   */
  public static void setRandomWalkPanel(RandomWalkPanel newRandomWalkPanel) {
    randomWalkPanel = newRandomWalkPanel;
  }

  /**
   * Getter for the random walk panel
   * @return The random walk panel as a RandomWalkPanel
   */
  public static RandomWalkPanel getRandomWalkPanel() {
    return randomWalkPanel;
  }

  /**
   * Getter for the view
   * @return This view, as a JFrame
   */
  public RandomWalkView getView() {
    return this;
  }
}
