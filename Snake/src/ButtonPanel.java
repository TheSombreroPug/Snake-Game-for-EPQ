import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ButtonPanel extends JPanel {
JFrame frame;
 ButtonPanel(JFrame frame) {
  setVisible(true);
  setPreferredSize(new Dimension(630,560));
  setBackground(Color.BLACK);
  setLayout(new GridLayout(5,0,50,50));
  //Adding Space for the text
  JLabel Clear = new JLabel();
  add(Clear);
//ADDING HUMAN BUTTON
  JButton Human = new JButton("For Humans");
  Human.setBackground(new Color(107, 50, 168));
  Human.setBounds(400,400,100,100);
  Human.setLayout(new GridLayout(2,0));
  add(Human);
  //Human.addActionListener(new SpListener(frame,Human));
  Human.addActionListener(new SpAction(frame,Human));

  //ADDING HAMILTIONIAN CYCLE BUTTON
  JButton Cycle = new JButton("Hamiltonian Cycle");
  Cycle.setBackground(Color.RED);
  Cycle.setBounds(250,250,100,100);
  Cycle.setLayout(new GridLayout(1,0));
  add(Cycle);
  Cycle.addActionListener(new HcAction(frame,Cycle));


  //PAthFinder Button
  JButton Path = new JButton("PathFinder");
  Path.setBackground(Color.GREEN);
  Path.setBounds(250,250,100,100);
  Path.setLayout(new GridLayout(1,0));
  add(Path);
  Path.addActionListener(new PfAction(frame,Path));

 }


 public void paintComponent(Graphics g) {
  super.paintComponent(g);
  draw(g);
 }

 public void draw(Graphics g) {
  //Drawing the title for snake
  g.setColor(Color.WHITE);
  g.setFont(new Font("Times New Roman", Font.BOLD, 75));
  FontMetrics metrics = getFontMetrics(g.getFont());
  g.drawString("SNAKE", (630 - metrics.stringWidth("SNAKE"))/2,115 );
 }


}