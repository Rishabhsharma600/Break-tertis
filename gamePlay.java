package demogame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;

public class gamePlay extends JPanel implements ActionListener,KeyListener {
    private boolean play=false;
    private int totalBrick=21;
    private Timer timer;
    private int delay=8;
    private int ballPosX=120;
    private int ballPosY=350;
    private int ballXdir=-1;
    private int ballYdir=-2;

    private int playerX=350;

    private walls w1;
    private int score=0;

    public gamePlay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer=new Timer(delay,this);
        timer.start();
        w1=new walls(3,7);

    }
    public void paint(Graphics g)
    //black color
    {
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);
//       borderColor
        g.setColor(Color.yellow);
        g.fillRect(0,0,692,3);
        g.fillRect(0,3,3,592);
        g.fillRect(691,3,3,592);
//        paddel
        g.setColor(Color.green);
        g.fillRect(playerX,550,100,8);
//        bricks
        w1.draw((Graphics2D )g);
//        ball
        g.setColor(Color.red);
        g.fillOval(ballPosX,ballPosY,20,20);
//        score
        g.setColor(Color.green);
        g.setFont(new Font("serif",Font.BOLD,30));;
        g.drawString("\nScore :"+score,550,30);

//        gameover
        if(ballPosY>=570)
        {
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.green);

            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("gameover!! Score"+score,200,300);

            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("press enter to start ",230,350);
        }

//      won game
        if(totalBrick<=0)
        {
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.green);

            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("You Won"+score,200,300);

            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("press enter to start ",230,350);
        }

    }



    private void moveLeft(){
        play=true;
        playerX-=20;
    }
    private void moveRight(){
        playerX+=20;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            if (playerX<=0)
                playerX=0;
            else
            moveLeft();
        }
        if (e.getKeyCode()==KeyEvent.VK_RIGHT)
        { if(playerX>=600)
            playerX=600;
            else
            moveRight();
        }
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(!play)
            {
                score=0;
                totalBrick=21;
                ballPosX=120;
                ballPosY=350;
                ballXdir=-1;
                ballYdir=-2;
                playerX=320;
                w1=new walls(3,7);
            }
        }
        repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (play){
            if(ballPosX<=0)
            {
                ballXdir=-ballXdir;
            }
            if(ballPosX>=670)
            {
                ballXdir=-ballXdir;
            }
            if (ballPosY<=0)
            {
                ballYdir=-ballYdir;
            }
            Rectangle ballRect= new Rectangle(ballPosX,ballPosY,20,20);
            Rectangle paddleRect=new Rectangle(playerX,550,100,8);
            if (ballRect.intersects(paddleRect)){
                ballYdir=-ballYdir;
            }

            A:for(int i=0;i<w1.map.length;i++)
            {
                for (int j=0;j<w1.map[0].length;j++)
                {
                    if(w1.map[i][j]>0)
                    {
                        int width=w1.brickWidth;
                        int height=w1.brickHeight;
                        int brickXpos=80+j*width;
                        int brickYpos=50+i*height;
                        Rectangle brickRect=new Rectangle(brickXpos,brickYpos,width,height);

                        if(ballRect.intersects(brickRect))
                        {
                            w1.setBrick(0,i,j);
                            totalBrick--;
                            score+=10;
                            if(ballPosX+19<=brickXpos||ballPosX+1>=brickXpos+width)
                            {
                                ballXdir=-ballXdir;
                            }
                            else {
                                ballYdir=-ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }






            ballPosX+=ballXdir;
            ballPosY+=ballYdir;
        }
        repaint();
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }


}
