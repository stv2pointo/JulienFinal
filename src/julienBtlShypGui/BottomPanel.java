package julienBtlShypGui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class BottomPanel extends JPanel {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private BtlView owner;
  private static JButton btnSend;
  JEditorPane inputPane;
  JScrollPane scrlPane;

  public BottomPanel(BtlView btlView) {
    this.owner = btlView;
    Dimension dim = getPreferredSize();
    dim.width = 400;
    dim.height = 100;
    setPreferredSize(dim);

    setLayout(new BorderLayout());

    btnSend = new JButton("Send");
    inputPane = new JEditorPane();

    btnSend.setFont(new Font("Arial", Font.PLAIN, 20));
    btnSend.setPreferredSize(new Dimension(70, 40));

    inputPane.setPreferredSize(new Dimension(70, 70));
    inputPane.setFont(new Font("Arial", Font.PLAIN, 20));
    inputPane.setBackground(Color.lightGray);

    // to add CTRL + Enter functionality to chat input
    CtrlEnterAction ceAction = new CtrlEnterAction();
    inputPane.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, ActionEvent.CTRL_MASK), "doEnterAction");
    inputPane.getActionMap().put("doEnterAction", ceAction);

    btnSend.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String chat = inputPane.getText();
        if(chat != null && chat.length()>0) {
     
          owner.sendChat(e);
        }
      }
    });

    JPanel bs1 = new JPanel();
    bs1.setPreferredSize(new Dimension(300, 100));
    btnSend.setPreferredSize(new Dimension(80, 40));
    add(bs1, BorderLayout.WEST);
    add(btnSend, BorderLayout.EAST);
    scrlPane = new JScrollPane(inputPane);
    add(scrlPane, BorderLayout.CENTER);
  } 
  
  public void resetGame() {
    inputPane.setText("");
    inputPane.requestFocusInWindow();
  }
  
  public void resetChat() {
    inputPane.setText("");
    inputPane.requestFocusInWindow();
  }
  static class CtrlEnterAction extends AbstractAction {

    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(ActionEvent e) {
      btnSend.doClick();
    }
  }

}