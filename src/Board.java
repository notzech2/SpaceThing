import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener {

    Player player;
    Enemy enemy;
    Enemy[][] enemies = new Enemy[5][10];
    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    long timeDelay;
    long bulletDelay;
    long currentTime;
    Game game;
    public Board(Game game){
        this.game = game;
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
        timeDelay = System.currentTimeMillis();

    }

    public void checkCollisions(){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(game.spacePressed == true && currentTime - bulletDelay >= 250 ){
            bullets.add(new Bullet(player));
            bulletDelay = System.currentTimeMillis();
        }
         currentTime = System.currentTimeMillis();

        for (Bullet bullet: bullets){
            bullet.move();

        }
        for (int i = 1; i < bullets.size(); i--){
            if (bullets.get(i).getY() < 0 ){
                bullets.remove(bullets.get(i));
            }
            else
                bullets.get(i).move();
        }

        if (currentTime - timeDelay>= 1000) {
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 10; col++) {
                    enemies[row][col].move();
                }
            }
            timeDelay = System.currentTimeMillis();

            if (game.leftPressed() && player.getX() >0){
                player.moveLeft();
            }
            if (game.rightPressed() && player.getY() < getWidth()){
                player.moveRight();
            }
        }


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
        for (Bullet bullet: bullets){
        bullet.paint(g);
        }

    }


}
