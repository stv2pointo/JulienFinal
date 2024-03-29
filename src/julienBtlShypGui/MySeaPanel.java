package julienBtlShypGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import main.btlshyp.message.AttackResponseMessage;
import main.btlshyp.model.Coordinate;
import main.btlshyp.model.Ship;

public class MySeaPanel extends JPanel {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  public static int GRID_SIZE = 5;
  private JButton[][] btlButtons;
  private BtlView owner;
  

  
  public MySeaPanel(BtlView owner) {
 
    this.owner = owner;
    this.btlButtons = new JButton[GRID_SIZE][GRID_SIZE];
    this.setLayout(new GridLayout(GRID_SIZE,GRID_SIZE));
    fillButtons(this);
  }
  
  public void resetGame() {
    for(int i=0;i<GRID_SIZE;i++) {
      for(int j=0;j<GRID_SIZE;j++) {
        btlButtons[i][j].setBackground(Color.blue);
        btlButtons[i][j].setForeground(Color.white);
      }
    }
  }
  
  public void fillButtons(MySeaPanel panel) {
    int size = 150;
    for(int i=0;i<GRID_SIZE;i++) {
      for(int j=0;j<GRID_SIZE;j++) {
        JButton temp = new JButton("(" +Integer.toString(i) + ", " + Integer.toString(j) + ")");//BtlButton temp =  new BtlButton( i, j, 160, 50);
        temp.setMinimumSize(new Dimension(size,size));
        temp.setMaximumSize(new Dimension(size,size));
        temp.setPreferredSize(new Dimension(size,size));
        temp.setBackground(Color.blue);
        temp.setForeground(Color.white);
        temp.setFont(new Font("Arial", Font.PLAIN, 25));
        temp.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            setCoordinate(e);
          }
        });
        panel.add(temp);
        btlButtons[i][j] = temp;
      }
    }
  }

public void setCoordinate(ActionEvent e) {
  JButton btn = (JButton)e.getSource();
  btn.setBackground(Color.WHITE);
  owner.attemptSetShip(e);
}
  
  public void displayShip(Ship ship) {
    if(ship != null && ship.isValid()) {
      for(Coordinate c : ship.getShipCoordinates()) {
        btlButtons[c.x][c.y].setBackground(Color.GRAY);
      }
    }
  }
  
  public void lockMySea() {
    for(int i=0;i<GRID_SIZE;i++) {
      for(int j=0;j<GRID_SIZE;j++) {
        btlButtons[i][j].setEnabled(false);
      }
    }
  }
  public void displayOpponentAttack(AttackResponseMessage message) {
    int x = message.getCoordinate().x;
    int y = message.getCoordinate().y;
    if(message.getHitOrMiss() == AttackResponseMessage.HitOrMiss.HIT) {
      btlButtons[x][y].setBackground(Color.red);
      java.awt.Toolkit.getDefaultToolkit().beep();
    }
    else {
      btlButtons[x][y].setBackground(Color.white);
    }
  }
  
}
