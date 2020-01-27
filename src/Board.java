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
        for (int i = bullets.size()-1; i>=0; i--) {
            for (int j = 0; j > enemies.length; j++)
                for (int k= 0; k < enemies[0].length; k++){
                    if(enemies[j][k] != null){
                    if (bullets.get(i).getBounds().intersects(enemies[j][k].getBounds())){
                        bullets.get(i).isRemove();
                        enemies[j][k] = null;
                        break;
                    }
                    }
                    if (bullets.get(i).isRemove()){
                        bullets.remove(bullets.get(i));
                    }
                }
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        checkCollisions();

        long currentTime = System.currentTimeMillis();

        if(game.spacePressed == true && currentTime - bulletDelay >= 250 ){
            bullets.add(new Bullet(player));
            bulletDelay = System.currentTimeMillis();
        }


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
                if(enemies[row][col] != null){
                    enemies[row][col].paint(g);
                }

            }
        }
        for (Bullet bullet: bullets){
        bullet.paint(g);
        }

    }


}
