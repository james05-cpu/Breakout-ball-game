package com.Annonymous;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Gameplay extends JPanel implements Runnable ,KeyListener{
    static boolean play=false;
    static  int playerx = 310;
    static int leftWall=600;
    static int rightWall=10;
   int score = 0;
   int ns=-10;
    int totalbricks = 21;
    int ballposx = 120;
  static   int playery=550;
  int ballposy = 350;
    int playerlen=100;
   int ballxdir = -1;
   int ballydir = -2;
    Thread gameT=null;
     static int xvel=0;
     int sp=10;
    MapGenerator map;

    public Gameplay() {
        gameT=new Thread(this);
        gameT.start();
        map = new MapGenerator(3, 7);
        setFocusable(true);
      // play=true;
        addKeyListener(this);

    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592);
        //border
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 550, 30);
        //map
        map.draw((Graphics2D) g);
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        //paddle
        g.setColor(Color.green);
        g.fillRect(playerx, playery, playerlen, 8);
        //ball
        if (ballposy > 570) {
            play = false;
            ballxdir = 0;
            ballydir = 0;
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Score: " + score, 190, 300);
            //map
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to restart:", 230, 350);
            //map
        }
        if (totalbricks<=0){
            play = false;
            ballxdir = 0;
            ballydir = 0;
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Congratulations !!" , 200, 300);
            //map
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart:", 230, 350);
            //map
        }
        g.setColor(Color.yellow);
        g.fillOval(ballposx, ballposy, 20, 20);


        g.dispose();
    }

    public void action() {
       play = true;
        repaint();

        if (play) {
            if (new Rectangle(ballposx, ballposy, 20, 20).intersects(playerx, playery, playerlen, 8)) {
                ballydir = -ballydir;
            }
                    BCACB:for (int i = 0; i < map.map.length; i++) {
                        for (int j = 0; j < map.map[0].length; j++) {
                            if (map.map[i][j] > 0) {
                                int brickx = j * map.brickwidth + 80;
                                int bricky = i * map.brickheight + 50;
                                int brickwidth = map.brickwidth;
                                int brickheight = map.brickheight;
                                Rectangle rect = new Rectangle(brickx, bricky, brickwidth, brickheight);
                                Rectangle ballRect = new Rectangle(ballposx, ballposy, 20, 20);
                                Rectangle brickRect = rect;
                                if (ballRect.intersects(brickRect)) {
                                    map.setBrickValue(0, i, j);
                                    totalbricks--;
                                    score += 5;
                                    if (ballposx + 19 <= brickRect.x || ballposx + 1 >= brickRect.x + brickRect.width) {
                                        ballxdir = -ballxdir;
                                    } else {
                                        ballydir = -ballydir;
                                    }
                                    break BCACB;
                                }
                            }
                        }
                    }
            ballposx += ballxdir;
            ballposy += ballydir;
            if (ballposx <= 4) {
                ballxdir = -ballxdir;
            }
            if (ballposy <= 0) {
                ballydir = -ballydir;
            }
            if (ballposx >= 670) {
                ballxdir = -ballxdir;
            }
        }
            repaint();
    }


    @Override
    public void run() {
        long LastTime=System.nanoTime();
        double amountOfTicks=60.0;
        double ns=1000000000/amountOfTicks;
        double delta=0;

        while (true){
            long now=System.nanoTime();
            delta+=(now-LastTime)/ns;
            LastTime=now;
            if (delta>1){
                mymove();
                action();
                repaint();
                delta--;
            }
        }
    }

    public static void mymove(){
        checkC();
        playerx=playery+xvel;

    }
    public static void checkC(){
        if (playerx >leftWall) {
            playerx = 600;
        }
        else if (playerx < rightWall) {
            playerx = 10;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (play) {
                play = true;
                ballposy = 120;
                ballposx = 350;
                ballxdir = -1;
                ballydir = -2;
                playerx = 310;
                score = 0;
                totalbricks = 21;
                map = new MapGenerator(3, 7);
                repaint();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
           play = true;
            xvel=sp;
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                play = true;
                xvel=ns;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
xvel=0;
    }

    }
