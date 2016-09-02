/**
 * @author Adrián Rodríguez Bazaga 
 * @version 1.0.0
 * @date 25 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programación de Aplicaciones Interactivas
 * @title Assignment 9 - Random Walk
 */

package gui;

import java.util.ArrayList;

import logic.ButtonEnum;

/**
 * This class is used to store every control button in the program as a Keyboard,
 * this way we have them encapsulated
 */
public class Keyboard extends ArrayList<ControlButton> {
  /**
   * Default constructor that delegates to it's parent
   */
  public Keyboard() {
    super();
  }
  
  /**
   * Getter for the specified button type, this method searches for the button in
   * the keyboard and returns it if it finds it, in another case returns null
   * @param buttonType
   * @return The button as a ControlButton, or null if not found
   */
  public ControlButton getButton(ButtonEnum buttonType) {
    for (ControlButton button : this) {    // Iterator
      if(button.getButtonType() == buttonType) {
        return button;
      }
    }
    return null;
  }
  
  /**
   * This method disables the focus of all buttons, fixing the dual key-action listeners
   * usage
   */
  public void disableFocus() {
    for (ControlButton button : this) {    // Iterator
      button.setFocusable(false);
    }
  }
}
