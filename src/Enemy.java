import java.awt.*;

public class Enemy {
    int x, y, diameter  = 30;
    int speed, dx, dy;
    int numEnemys = 50;

    public Enemy(){

        x = 50;
        y = 50;
    }
    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
    }
    public  void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillOval(x, y,diameter,diameter);
    }
    public int getNumEnemys(){
        return numEnemys;
    }

}
