import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
public class Camera implements KeyListener{
    public double xPos, yPos, xDir, yDir, xPlane, yPlane;
    public boolean left, right, forward, back,pause;
    public final double MOVE_SPEED = .08;
    public final double NITRO = .5;
    public final double ROTATION_SPEED = .045;
    public int mapWidth, mapHeight, width, height;
    public ArrayList<Texture> textures;
    public int[][] map;
    //public int mapWidth, mapHeight, width, height;
    public Camera(double x, double y, double xd, double yd, double xp, double yp) {
        xPos = x;
        yPos = y;
        xDir = xd;
        yDir = yd;
        xPlane = xp;
        yPlane = yp;
    }

    public Camera(int[][] m, int mapW, int mapH, ArrayList<Texture> tex, int w, int h) {
        map = m;
        mapWidth = mapW;
        mapHeight = mapH;
        textures = tex;
        width = w;
        height = h;
    }

    public void keyPressed(KeyEvent key) {
        if((key.getKeyCode() == KeyEvent.VK_LEFT))
            left = true;
        if((key.getKeyCode() == KeyEvent.VK_A))
            left = true;
        if((key.getKeyCode() == KeyEvent.VK_RIGHT))
            right = true;
        if((key.getKeyCode() == KeyEvent.VK_D))
            right = true;
        if((key.getKeyCode() == KeyEvent.VK_UP))
            forward = true;
        if((key.getKeyCode() == KeyEvent.VK_W))
            forward = true;
        if((key.getKeyCode() == KeyEvent.VK_DOWN))
            back = true;
        if((key.getKeyCode() == KeyEvent.VK_S))
            back = true;
        if((key.getKeyCode() == KeyEvent.VK_SPACE))
            pause = true;
    }

    public void keyReleased(KeyEvent key) {
        if((key.getKeyCode() == KeyEvent.VK_LEFT))
            left = false;
        if((key.getKeyCode() == KeyEvent.VK_A))
            left = false;
        if((key.getKeyCode() == KeyEvent.VK_RIGHT))
            right = false;
        if((key.getKeyCode() == KeyEvent.VK_D))
            right = false;
        if((key.getKeyCode() == KeyEvent.VK_UP))
            forward = false;
        if((key.getKeyCode() == KeyEvent.VK_W))
            forward = false;
        if((key.getKeyCode() == KeyEvent.VK_DOWN))
            back = false;
        if((key.getKeyCode() == KeyEvent.VK_S))
            back = false;
        if((key.getKeyCode() == KeyEvent.VK_SPACE))
            pause = false;
    }

    public void update(int[][] map) {
        if(forward) {
            if(map[(int)(xPos + xDir * MOVE_SPEED)][(int)yPos] == 0) {
                xPos+=xDir*MOVE_SPEED;
            }
            if(map[(int)xPos][(int)(yPos + yDir * MOVE_SPEED)] ==0)
                yPos+=yDir*MOVE_SPEED;
        }
        if(back) {
            if(map[(int)(xPos - xDir * MOVE_SPEED)][(int)yPos] == 0)
                xPos-=xDir*MOVE_SPEED;
            if(map[(int)xPos][(int)(yPos - yDir * MOVE_SPEED)]==0)
                yPos-=yDir*MOVE_SPEED;
        }
        if(right) {
            double oldxDir=xDir;
            xDir=xDir*Math.cos(-ROTATION_SPEED) - yDir*Math.sin(-ROTATION_SPEED);
            yDir=oldxDir*Math.sin(-ROTATION_SPEED) + yDir*Math.cos(-ROTATION_SPEED);
            double oldxPlane = xPlane;
            xPlane=xPlane*Math.cos(-ROTATION_SPEED) - yPlane*Math.sin(-ROTATION_SPEED);
            yPlane=oldxPlane*Math.sin(-ROTATION_SPEED) + yPlane*Math.cos(-ROTATION_SPEED);
        }
        if(left) {
            double oldxDir=xDir;
            xDir=xDir*Math.cos(ROTATION_SPEED) - yDir*Math.sin(ROTATION_SPEED);
            yDir=oldxDir*Math.sin(ROTATION_SPEED) + yDir*Math.cos(ROTATION_SPEED);
            double oldxPlane = xPlane;
            xPlane=xPlane*Math.cos(ROTATION_SPEED) - yPlane*Math.sin(ROTATION_SPEED);
            yPlane=oldxPlane*Math.sin(ROTATION_SPEED) + yPlane*Math.cos(ROTATION_SPEED);
        }
        if(pause) {
            if(map[(int)(xPos*height + xDir)][(int)yPos]<=1)
            {
                if(map[(int)(xPos + xDir * MOVE_SPEED)][(int)yPos] == 0) {
                    xPos=0;
                }
                if(map[(int)xPos][(int)(yPos + yDir * MOVE_SPEED)] ==0)
                    yPos=0;
            }
            if(map[(int)(yPos*height + yDir)][(int)yPos]<=1)
            {
                if(map[(int)(xPos - xDir * MOVE_SPEED)][(int)yPos] == 0)
                    xPos=0;
                if(map[(int)xPos][(int)(yPos - yDir * MOVE_SPEED)]==0)
                    yPos=0;
            }
        }
    }

    public void keyTyped(KeyEvent arg0) 
    {
        // TODO Auto-generated method stub

    }
}