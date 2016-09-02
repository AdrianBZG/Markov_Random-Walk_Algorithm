/**
 * @author Adrián Rodríguez Bazaga 
 * @version 1.0.0
 * @date 25 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programación de Aplicaciones Interactivas
 * @title Assignment 9 - Random Walk
 */

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MovementDelayInputWindow extends AskForInputWindow {
	
	private RandomWalkPanel randomWalk;    // The panel where the walk is drawn

	/**
	 * Creates the frame to set the delay
	 * @param title
	 * @param walk Panel to modify it's delay
	 */
	public MovementDelayInputWindow(String title, RandomWalkPanel randomWalk) {	
		super(title);

		setRandomWalk(randomWalk);
		initializeListeners();
	}

	/**
	 * Getter for the random walk panel
	 * @return The random walk panel as a RandomWalkPanel
	 */
	public RandomWalkPanel getRandomWalk() {
		return randomWalk;
	}

	/**
	 * Setter for the random walk
	 * @param randomWalk
	 */
	public void setRandomWalk(RandomWalkPanel randomWalk) {
		this.randomWalk = randomWalk;
	}
	
	/**
	 * This method stars the timer again
	 */
	public void resetTimer() {
	  RandomWalkView.setTimerState(true);
    RandomWalkView.runTimers();
	}
	
	/**
	 * Initialize the listener for the confirmation button
	 */
	private void initializeListeners() {
	  final String WRONG_FORMAT_ERROR_TEXT = "Wrong format (Needs to be an integer)";
	  getConfirmationButton().addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	      Integer newDelay;
	      try {
	        newDelay = Integer.parseInt(getText());
	        getRandomWalk().setMovementDelay((int)newDelay);
	        resetTimer();
	        dispose();
	      }
	      catch (Exception exc) {
	        JOptionPane.showMessageDialog(null, WRONG_FORMAT_ERROR_TEXT);
	      }     
	    }
	  });
	}
}
