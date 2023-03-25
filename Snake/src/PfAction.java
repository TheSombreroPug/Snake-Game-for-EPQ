import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PfAction implements ActionListener {

    JFrame frame;
    JButton Path;
    boolean IgnoreAL = true;
    public PfAction(JFrame frame, JButton Path) {


        this.frame = frame;
        this.Path = Path;

    }


    public void actionPerformed(ActionEvent e) {
        if (IgnoreAL == true) {
            Path.setFocusable(false);
            Path.setVisible(false);
            ((GameFrame) frame).PathFinder();
            boolean IgnoreAl = false;
            //human.setFocusable(false);
        }
        else{
            return;
        }




    }

}
