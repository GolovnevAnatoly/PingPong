/* ****** автор АНАТОЛИЙ ГОЛОВНЕВ ******
панель где двищутся мяч и доска.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PingPong extends JPanel implements KeyListener{
	private Ball ball;
	private Paddle pad;
	private int velosity=0;
	private int height =600, width=800;
	

	public PingPong(){
		setBackground(Color.WHITE);
		addKeyListener(this);
		setFocusable(true);
		setPreferredSize(new Dimension(width,height));
		pad = new Paddle(this, width, height);
		ball = new Ball(this, width, height);
		setVisible(true);
		ball.start();
	}

	public void paint(Graphics g){
		g.clearRect(0,0,getWidth(),getHeight());
		g.setColor(Color.BLUE);
		g.fillOval(ball.getX(),ball.getY(),ball.SIZE,ball.SIZE);
		g.setColor(Color.red);
		g.fillRect(pad.getX(),pad.getY(),Paddle.WIDTH,pad.getHeight());
	}
	
	public boolean ballHitsPaddle(){
		return ball.getX() + Ball.SIZE >= pad.getX() &&  ball.getY() >= pad.getY() && ball.getY() <= pad.getY() + pad.getHeight();
		}

	public void keyPressed(KeyEvent e){
		int keyCode = e.getKeyCode();
		if(keyCode == e.VK_UP) pad.moveUp();
		else if(keyCode == e.VK_DOWN) pad.moveDown();
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}

	public void changeSize(int k){pad.setHeight(k);}
	public void speed(int k){velosity=Math.abs(k); ball.setVelosity(k);}
	
	public int mult(int dir){
		/*мяч с нормальной скоростью увеличивает вертикальную составляющую скорости если ударяется в дальнюю по ходу движения половину доски.
		  мяч с нормальной скоростью не меняет вертикальную составляющую скорости если ударяется в ближнюю по ходу движения половину доски.
		  если мячь уже ускоренный, то после удара скорость мяча становится нормальной (т.е. не повышенной).
		*/
		int sign = (dir<0)?-1:1;
		if(sign*dir!=velosity) return sign*velosity;
		int pos = (ball.getY()+Ball.SIZE/2) - ( pad.getY()+pad.getHeight()/2);//положительно если удар в нижнюю половину
		if(pos*dir>0) return dir+2*sign;
	return dir;
	}
	
	public void start(){ball.start();} 

}//class