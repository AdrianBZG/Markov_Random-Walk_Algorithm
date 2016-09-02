/**
 * @author Adrián Rodríguez Bazaga 
 * @version 1.0.0
 * @date 25 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programación de Aplicaciones Interactivas
 * @title Assignment 9 - Random Walk
 */

package gui;

import java.awt.Dimension;
import javax.swing.JButton;

import logic.ButtonEnum;


public class ControlButton extends JButton {
  final int BUTTON_SIZE_X = 80;         // The button width in pixels
  final int BUTTON_SIZE_Y = 40;         // The button height in pixels
  private ButtonEnum buttonType;        // The button type (Start, Finish...)
  
  /**
   * This constructor creates a Control Button with the specified text and the
   * specified type, also you can specify a mnemonic to it
   * @param buttonText
   * @param buttonType
   * @param buttonMnemonic
   */
  public ControlButton(String buttonText, ButtonEnum buttonType, int buttonMnemonic) {
    super(buttonText);                    // Construct the parent class (JButton)
    setName(buttonText);                  // Set the button name, so we can refer it at anytime
    setPreferredSize(new Dimension(BUTTON_SIZE_X, BUTTON_SIZE_Y));  // Set the button size
    setMnemonic(buttonMnemonic);          // Set the button mnemonic
    setButtonType(buttonType);            // Set the button type
  }
  
  /**
   * This constructor creates a Control Button with the specified text and the
   * specified type.
   * @param buttonText
   * @param buttonType
   */
  public ControlButton(String buttonText, ButtonEnum buttonType) {
    super(buttonText);                    // Construct the parent class (JButton)
    setName(buttonText);                  // Set the button name, so we can refer it at anytime
    setPreferredSize(new Dimension(BUTTON_SIZE_X, BUTTON_SIZE_Y));  // Set the button size
    setButtonType(buttonType);            // Set the button type
  }

  /**
   * Getter for the type of the button
   * @return The type of the button as ButtonEnum
   */
  public ButtonEnum getButtonType() {
    return buttonType;
  }

  /**
   * Setter for the button type
   * @param buttonType
   */
  public void setButtonType(ButtonEnum buttonType) {
    this.buttonType = buttonType;
  }
}
