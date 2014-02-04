package spaceInvadersGame;

import org.lwjgl.opengl.GL11;

public class Bunkers {
	
	float x, y, bunkerWidth = 50, bunkerHeight = 15;
	
	public Bunkers(int x, int y){
		 this.x = x;
		 this.y = y;
		
	}
	
	public void drawBunkers(){
		

		
		
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glColor3f(1.0f, 0.5f, 0.0f);
		GL11.glVertex2f((x + bunkerWidth), (y - bunkerHeight));
		GL11.glVertex2f((x + bunkerWidth), (y + bunkerHeight));
		GL11.glVertex2f((x - bunkerWidth), (y + bunkerHeight));
		GL11.glVertex2f((x - bunkerWidth), (y - bunkerHeight));
		GL11.glEnd();
		
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f((x + (bunkerWidth/2)), (y - (bunkerHeight*2)));
		GL11.glVertex2f((x + (bunkerWidth/2)), (y + (bunkerHeight*2)));
		GL11.glVertex2f((x - (bunkerWidth/2)), (y + (bunkerHeight*2)));
		GL11.glVertex2f((x - (bunkerWidth/2)), (y - (bunkerHeight*2)));
		GL11.glEnd();
		
	}

}
