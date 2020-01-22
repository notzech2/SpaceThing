import java.awt.*;

public class Bullet {
    int x,y, diameter = 10;
    int speed, dx,dy;
    public Bullet(Player player){
        x = player.getX() + player.getWIDTH()/2-diameter/2;
        y = player.getY() - diameter;
    }
    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(x,y,diameter,diameter);
    }

}
