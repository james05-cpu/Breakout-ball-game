package com.Annonymous;
        
        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.*;
        /*game panel  where will draw graphics
         ....the game action will take place
         */
        public class Gameplay extends JPanel implements ActionListener {
            private static boolean play = false;
            int playerx = 310;
            int leftWall=600;
            int rightWall=10;
            int score = 0;
            int totalbricks = 21;
            int delay = 10;
            int ballposx = 120;
            int playery=550;
            int ballposy = 350;
            int playerlen=100;
            int ballxdir = -1;
            int ballydir = -2;
            Timer timer;
            MapGenerator map;
            public Gameplay() {
                map = new MapGenerator(3, 7);
                setFocusable(true);
                setFocusTraversalKeysEnabled(false);
                addKeyListener(new AL());
                timer = new Timer(delay, this);
                timer.start();
            }
            public void paint(Graphics g) {
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
            @Override
            public void actionPerformed(ActionEvent e) {
        
                timer.start();
               //play = true;
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
        
                public void moveRight () {
                    play = true;
                    playerx = playerx + 30;
                }
                public void moveLeft () {
                   play = true;
                    playerx = playerx - 20;
                }
        
            public class AL extends KeyAdapter {
        
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (!play) {
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
                        if (playerx >= leftWall) {
                            playerx = 600;
                        } else {
                            moveRight();
        
                        }
                    }
                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            if (playerx <= rightWall) {
                                playerx = 10;
                            } else {
                                moveLeft();
                            }
                        }
                }
            }
        }
