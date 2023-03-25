import java.awt.event.*;

import javax.swing.*;

public class SpAction implements ActionListener {

   JFrame frame;
   JButton human;
   boolean IgnoreAL = true;
    public SpAction(JFrame frame, JButton human) {


        this.frame = frame;
        this.human = human;

    }


    public void actionPerformed(ActionEvent e) {
        if (IgnoreAL == true) {
            human.setFocusable(false);
            human.setVisible(false);
            ((GameFrame) frame).singlePlayer();
            boolean IgnoreAl = false;
            //human.setFocusable(false);
        }
        else{
            return;
        }




    }

}