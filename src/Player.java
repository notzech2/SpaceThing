import java.awt.*;

public class Player {

    int x, y;
    final int WIDTH = 20, Height = 40;
    double speed, dx = 4, dy;
    public Player(Board board){

        x = board.getWidth() / 2;
        y = board.getHeight() -  (Height + 50);
    }

    public void paint (Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x,y,WIDTH,Height);
    }


    public void moveRight(){
        x+=dx;
    }
    public void moveLeft(){
        x-=dx;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWIDTH() {
        return WIDTH;
    }
}
