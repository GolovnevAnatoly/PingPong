/* ****** автор АНАТОЛИЙ ГОЛОВНЕВ ******
мяч движется сам, постоянно. Отдельный Thread нужен для параллельности с другими процессами, чтобы у других объектов тоже было время.
*/

import javax.swing.*;
import java.awt.Toolkit;

public class Ball extends Thread{
	public static final int SIZE = 10;
	private static final int topWall=0, leftWall=0;
	private final int bottomWall, rightWall;
	private PingPong frame;
	private int locationX=1, locationY=1;
	private int directionX = 1, directionY = 1;
	private Toolkit kit = Toolkit.getDefaultToolkit();

	public Ball(PingPong f, int w, int h){
		frame = f;
		bottomWall = h;
		rightWall = w;
	}

	public void move(){
		locationX += directionX;
		locationY += directionY;

		if(locationX <= leftWall){directionX = -directionX;}
		else if(locationY + SIZE >= bottomWall || locationY <= topWall){directionY = -directionY;}
		else if(locationX+ SIZE >= rightWall){locationX = leftWall + 1;kit.beep();}
		else if(frame.ballHitsPaddle()){directionX = -directionX; directionY=frame.mult(directionY);}
	}

	public int getX(){return locationX;}
	public int getY(){return locationY;}
	public void setVelosity(int k){
		directionX = (directionX>0)? k:-k;
		directionY = (directionY>0)? k:-k;
	}

	public void run(){
		while(true){
			move();
			frame.repaint ();
				try{sleep(15);} catch(InterruptedException e){}
		}//while
	}

}//class