package julienBtlShypGui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.btlshyp.message.AttackResponseMessage;
import main.btlshyp.model.Ship;

public class CenterPanel extends JPanel {
 
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private MySeaPanel mySea;
  private JTextArea chatDisplayArea;
  private OpponentPanel opponentSea;

  public CenterPanel(BtlView owner) {
    this.mySea = new MySeaPanel(owner);
    this.opponentSea = new OpponentPanel(owner);
    this.chatDisplayArea = new JTextArea();
    chatDisplayArea.setFont(new Font("Arial", Font.PLAIN, 20));
    chatDisplayArea.setLineWrap(true);
    chatDisplayArea.setText("Welcome");

    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    // natural height, maximum width
    c.fill = GridBagConstraints.VERTICAL;
    c.ipadx = -2;
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 0.4;
    c.weighty = 1.0;
    add(mySea, c);

    c.fill = GridBagConstraints.VERTICAL;
    c.ipadx = 6;
    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 0.2;
    c.weighty = 1.0;
    add(chatDisplayArea, c);

    c.fill = GridBagConstraints.VERTICAL;
    c.ipadx = -2;
    c.gridx = 2;
    c.gridy = 0;
    c.weightx = 0.4;
    c.weighty = 1.0;
    add(opponentSea, c);
  }

  public void initGame() {
    mySea.fillButtons(mySea);
    opponentSea.fillButtons(opponentSea);
    chatDisplayArea.setText("");
  }
  
  public void resetGame() {
    mySea.resetGame();
    opponentSea.resetGame();
    chatDisplayArea.setText("");
  }
  
  public void displayChat(String user, String chat) {
    String from = (user == null) ? "" : user;
    if (chat != null && chat.length() > 0) {
      chatDisplayArea.append(from + ": " + chat + "\n");
    }
  }

  public void displayShip(Ship ship) {
    mySea.displayShip(ship);
  }

  public void displayOpponentAttack(AttackResponseMessage message) {
    mySea.displayOpponentAttack(message);
  }
  
  public void displayAttack(AttackResponseMessage message) {
    opponentSea.displayAttack(message);
  }
  public void notYourTurn() {
    opponentSea.lockOppSea();
    
  }
  
  public void yourTurn() {
    opponentSea.setEnabled(true);
    opponentSea.unlockOppSea();
  }
  
  public void lockMySea() {
    mySea.lockMySea();
  }
  
  public void lockOppSea() {
    opponentSea.lockOppSea();
  }
}
