// importing all the java libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

//creation of main class GamePanel

public class GamePanel extends JPanel implements ActionListener {
    //Stating all the variables that will be used in the code. These will not change.
    static final int SCREEN_WIDTH = 630;
    static final int SCREEN_HEIGHT = 560;
    static final int UNIT_SIZE = 35;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 100;
    final  int x[] = new int [GAME_UNITS];
    final int y[] = new int [GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'D';
    boolean running = false;
    boolean collision = false;
    MyKeyAdapter keyAdapter = new MyKeyAdapter();
    Timer timer;
    Random random;

    //Creating the GamePanel function



    public GamePanel( GameFrame gameFrame, int screenWidth, int screenHeight) {
        random = new Random();
        this.setSize(screenWidth,screenHeight);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.setFocusable(true);
        startGame();
    }



    //Creates the startGame function
    public void startGame(){
        //This class creates a new apple on the board and sets the game to running
        newApple();
        running = true;
        //This creates the timer that that game will run on E.g. How fast the game is
        timer = new Timer(DELAY,this);
        timer.start();
    }
    //Creates the paintComponent function
    public void paintComponent(Graphics g){
        //Runs the two methods that will draw all necessary lines on the panel
        super.paintComponent(g);
        draw(g);

    }
    //Create the draw function
    public void draw(Graphics g){
        //Draws the vertical and horizontal lines to make the illusion of a grid
        if(running) {
            for (int i = 0; i < (SCREEN_WIDTH/ UNIT_SIZE); i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_WIDTH);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }
            //Sets the size and color of the apple
            g.setColor(Color.RED);
            g.fillRect(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
            //Fills in colored boxes for the snake depending on the size
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    //Sets the "head" of the snake to a different color
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    //sets the rest of the snake to a color
                    g.setColor(new Color(45, 180, 0));
                    //If you eat more than 25 apples the snake will become rainbow
                    if (applesEaten>24){
                        g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    }
                    //Fills in the boxes
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            //This creates the score card at the  bottom of the screen
            g.setColor(new Color(107, 50, 168));
            g.setFont(new Font("Times New Roman",Font.BOLD,40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+applesEaten, (SCREEN_WIDTH-metrics.stringWidth("Score: "+applesEaten))/2,SCREEN_HEIGHT-50);
        }
        else{
            //If it's not running the drawn lines will not appear
            gameOver(g);
        }
    }
    //Creates newApple function
    public void newApple(){
        //Sets the apples X  and Y coordinate according to the given screen and unit size
        appleX = random.nextInt((SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
        //Ensures the apple does not spawn in any body parts
        for (int i = bodyParts;i>0;i--) {
            if ((appleX == x[i]) && (appleY == y[i])) {
                newApple();

            }
        }}

    //Create the move function
    public void move(){
        for(int i = bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch(direction){
            case 'W':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'S':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'A':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'D':
                x[0] = x[0] + UNIT_SIZE;
                break;


        }

    }
    public void checkApple(){
        if((x[0]== appleX)&& (y[0]==appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
        }

    }
    public void checkCollisions()
    {
        //checks if snake has into itself
        for(int i = bodyParts;i>0;i--){
            if((x[0]==x[i])&&(y[0]==y[i])){
                running = false;
            }

        }
        if(x[0]<0){
            running = false;
        }
        if(x[0]>SCREEN_WIDTH-UNIT_SIZE){
            running = false;
        }
        if(y[0]<0){
            running = false;
        }
        if(y[0]>SCREEN_HEIGHT-UNIT_SIZE){
            running = false;
        }
        if(!running){
            timer.stop();
        }
        for(int i = bodyParts;i>0;i--) {
            while((appleX == x[i]) && (appleY == y[i])) {
                newApple();
            }
        }

    }
    public void gameOver(Graphics g){
        //Game OVer text
        g.setColor(Color.RED);
        g.setFont(new Font("Serif",Font.BOLD,75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH-metrics1.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free",Font.BOLD,40));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Score: "+applesEaten, (SCREEN_WIDTH-metrics2.stringWidth("Score: "+applesEaten))/2,SCREEN_HEIGHT-50);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();

    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_A:
                    if(direction != 'D'){
                        direction = 'A';
                    }
                    break;
                case KeyEvent.VK_D:
                    if(direction != 'A'){
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_W:
                    if(direction != 'S'){
                        direction = 'W';
                    }
                    break;
                case KeyEvent.VK_S:
                    if(direction != 'W'){
                        direction = 'S';
                    }
                    break;

            }

        }
    }
}
