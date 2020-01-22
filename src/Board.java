import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    Player player;
    Enemy enemy;
    Enemy[][] enemies = new Enemy[5][10];
    Bullet bullet;
    public Board(){
        setPreferredSize(new Dimension(1024,900));
        setBackground(Color.BLACK);
        Timer timer= new Timer(1000/60,this);
        timer.start();

    }
//    gives starting positions
    public void  setup(){
        player = new Player(this);
        for (int row = 0; row < 5; row++){
            for (int col = 0; col < 10; col++){
                enemies[row][col]= new Enemy(getWidth()/4 + col*50,row*50);
            }
        }
        bullet = new Bullet(player);

    }

    public void checkCollisions(){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        player.moveLeft();
        repaint();

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        player.paint(g);
        for (int row = 0; row < 5; row++){
            for (int col = 0; col < 10; col++){
                enemies[row][col].paint(g);
            }
        }
        bullet.paint(g);

    }


}
