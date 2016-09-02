/**
 * @author Adrián Rodríguez Bazaga 
 * @version 1.0.0
 * @date 25 April 2016
 * @email alu0100826456@ull.edu.es / arodriba@ull.edu.es
 * @subject Programación de Aplicaciones Interactivas
 * @title Assignment 9 - Random Walk
 */

package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class AskForInputWindow extends JFrame{

  private JTextField textArea;
  private JButton confirmationButton;

  public final static int WINDOW_WIDTH = 600;
  public final static int WINDOW_HEIGHT = 100;
  public final static int NUMBER_OF_ROWS = 2;
  public final static String CONFIRMATION_BUTTON_TEXT = "OK";

  /**
   * Creates a dialog window with a textarea and a confirmation button
   * @param title
   */
  public AskForInputWindow(String title) {
    initializeSettings(title);
    initializeElements();
  }
  
  private void initializeSettings(String title) {
    setTitle(title);
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 
    setLayout(new GridLayout(NUMBER_OF_ROWS, 1));
    setTextArea(new JTextField());
    setConfirmationButton(new JButton (CONFIRMATION_BUTTON_TEXT));
  }
  
  private void initializeElements() {
    add(getTextArea());
    add(getConfirmationButton());
  }

  public JTextField getTextArea() {
    return textArea;
  }

  public void setTextArea(JTextField textArea) {
    this.textArea = textArea;
  }

  public JButton getConfirmationButton() {
    return confirmationButton;
  }

  public void setConfirmationButton(JButton aceptar) {
    this.confirmationButton = aceptar;
  }

  public String getText() {
    return textArea.getText();
  }
}
