package spaceInvadersGame;

import org.lwjgl.opengl.GL11;

public class SpaceInvader{

	/*Vector2f alienSizeHalved = new Vector2f(20.0f, 10.0f);
	Vector2f alienPos = new Vector2f(100f, 400f);
	Vector2f alienVel = new Vector2f(0.0f, 0.0f);
	Vector2f alienAcc = new Vector2f(0.0f, 0.0f);
	 */
	int alienWidth = 20, alienHeight = 10;


	int moveDown = 30;
	float x, y, yPosition = 0.0f;
	float moveVelocity = 5;

	boolean right = true;
	long lastTime = 1000;
	int max = 500;
	float borderLeft = 100, borderRight = 700;

	public SpaceInvader(int x, int y){

		this.x = x;
		this.y = y;

		//drawAlien();

	}

	private long getTime() {
		return System.nanoTime() / 1000000;
	}

	public void moveAliens(){

		if (getTime() - lastTime > max){
			lastTime = getTime();
			if(right){
				this.x += 15;
				if (this.x >= 700){
					this.y -= moveDown;
					right = false;
				}
			}else{	
				this.x -= 15;
				if (this.x <= 100){
					this.y -= 10;
					right = true;
				}
			}
		}

		if (this.x < borderLeft) this.x = borderLeft;
		if (this.x > borderRight) this.x = borderRight;
		if (this.y < 200) this.y = 200;
		if (this.y > 600) this.y = 600;
	}

	public float getX(){
		return this.x;
	}

	public float getY(){
		return this.y + yPosition;
	}

	public float height(){
		return (alienWidth*2);
	}

	public float width(){
		return (alienHeight*2);
	}

	public void drawAlien(){


		/*GL11.glBegin(GL11.GL_QUADS);	
		GL11.glColor3f(0.0f, 1.0f, 0.0f);
		GL11.glVertex2d((alienPos.x + alienSizeHalved.x) + x, (alienPos.y - alienSizeHalved.y) + y);
		GL11.glVertex2d((alienPos.x + alienSizeHalved.x) + x, (alienPos.y + alienSizeHalved.y) + y);
		GL11.glVertex2d((alienPos.x - alienSizeHalved.x) + x, (alienPos.y + alienSizeHalved.y) + y);
		GL11.glVertex2d((alienPos.x - alienSizeHalved.x) + x, (alienPos.y - alienSizeHalved.y) + y);
		GL11.glEnd();
		 */
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(0.0f, 1.0f, 0.0f);
		GL11.glVertex2f((x + alienWidth), (y - alienHeight));
		GL11.glVertex2f((x + alienWidth), (y + alienHeight));
		GL11.glVertex2f((x - alienWidth), (y + alienHeight));
		GL11.glVertex2f((x - alienWidth), (y - alienHeight));
		GL11.glEnd();
	}

}

