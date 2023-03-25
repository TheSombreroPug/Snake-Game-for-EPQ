import java.awt.event.*;

import javax.swing.*;

public class HcAction implements ActionListener{
    JFrame frame;
    JButton Cycle;
    boolean IgnoreAL = true;
    public HcAction(JFrame frame, JButton Cycle) {


        this.frame = frame;
        this.Cycle = Cycle;

    }


    public void actionPerformed(ActionEvent e) {
        if (IgnoreAL == true) {
            Cycle.setFocusable(false);
            Cycle.setVisible(false);
            ((GameFrame) frame).HamiltonianCycle();
            boolean IgnoreAl = false;
            //human.setFocusable(false);
        }
        else{
            return;
        }




    }

}

