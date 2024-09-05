/* ****** автор АНАТОЛИЙ ГОЛОВНЕВ ******

*/

public class Paddle{

	public static final int WIDTH = 10;
	private final int delta;
	private final int defaultHeight;
	private int height;

	private static final int topWall = 0;
	private final int locationX;
	private int locationY;
	private PingPong frame;

		public Paddle(PingPong f, int w, int h){
			frame = f;
			defaultHeight=h/6;
			height=defaultHeight;
			delta=defaultHeight/2;
			locationX = w-WIDTH;
			locationY = (h-height)/2;
		}

	public int getX(){return locationX;}
	public int getY(){return locationY;}
	public int getHeight(){return height;}
	public void setHeight(int k){height=defaultHeight+defaultHeight*k/2;}

	public void moveUp(){if(locationY > topWall) locationY -= delta;}
	public void moveDown(){if(locationY + height < frame.getHeight() - topWall) locationY += delta;}

}//class
