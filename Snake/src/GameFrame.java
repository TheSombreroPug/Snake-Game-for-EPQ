import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

    static final int SCREEN_WIDTH = 630;
    static final int SCREEN_HEIGHT = 560;
    private final JPanel PlayFrame;
    private GamePanel gamePanel;
    private  JPanel PlayFrame1;
    private GamePanel gamePanel1;
    private HamiltonianCycle hamiltonianCycle;
    private PathFinding pathFinder;
    private ButtonPanel ButtonPanel = new ButtonPanel(this);
    CardLayout PanelLayout = new CardLayout();
    CardLayout PanelLayout1 = new CardLayout();

    public GameFrame() {

        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.PlayFrame = new JPanel();
        this.PlayFrame.setLayout(PanelLayout);
        this.PlayFrame.add(ButtonPanel);
        this.setResizable(true);
        this.setContentPane(PlayFrame);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }


    public void singlePlayer() {
        gamePanel = new GamePanel(this,  SCREEN_WIDTH, SCREEN_HEIGHT);
        PlayFrame.add(gamePanel, "Game Panel");
        PanelLayout.next(PlayFrame);
        PlayFrame.remove(ButtonPanel);
        this.setTitle("Snake SinglePlayer");
        gamePanel.requestFocusInWindow();
    }
//HAMILTONIAN CYCLE
    public void HamiltonianCycle() {
        hamiltonianCycle = new HamiltonianCycle(this,  SCREEN_WIDTH, SCREEN_HEIGHT);
        PlayFrame.add(hamiltonianCycle, "Game Panel");
        PanelLayout.next(PlayFrame);
        PlayFrame.remove(ButtonPanel);
        this.setTitle("Snake Hamiltonian Cycle");
        hamiltonianCycle.requestFocusInWindow();
    }

    //PathFinding
    public void PathFinder() {
        pathFinder = new PathFinding(this,  SCREEN_WIDTH, SCREEN_HEIGHT);
        PlayFrame.add(pathFinder, "Game Panel");
        PanelLayout.next(PlayFrame);
        PlayFrame.remove(ButtonPanel);
        this.setTitle("Snake Path Finder");
        pathFinder.requestFocusInWindow();
    }


}