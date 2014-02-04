package spaceInvadersGame;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game {

	//this is the main class, it will create the window and call the other classes to for objects

	float moveHorizontal, moveVertical;
	boolean closeWindow, fullScreen = true;
	Player newPlayer = new Player();
	Shot newShot = new Shot();
	//SpaceInvader alien = new SpaceInvader();
	long lastFramePerSec, lastDeltaSeconds;
	int framesPerSec, borderLeft = 100, borderRight = 700, borderTop = 550, borderBottom = 200, width = 800, height = 600;


	//ArrayList<SpaceInvader> alienShips = new ArrayList<SpaceInvader>();
	SpaceInvader[][] alienShips = new SpaceInvader[3][10];
	Bunkers [] bunkers = new Bunkers[3];


	public void startGame(){

		createEntities();

		try{

			Display.setTitle("Space Invaders");
			Display.setDisplayMode(new DisplayMode(width , height));
			//Display.setFullscreen(fullScreen);
			Display.create();

		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		view();
		initiateTimer();
		initiateGL();

		while(!closeWindow && !Display.isCloseRequested()){

			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

			float deltaSec = getDelta();
			updateFramesPerSec();

			spawnEntities();
			userInput();
			Update(deltaSec);



			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}



	private void createEntities() {

		int x = borderLeft;
		int y = borderTop;
		int bx = 200;
		int by = borderBottom;

		for(int i = 0; i < alienShips.length; i++){
			for(int j = 0; j < alienShips[i].length; j++){

				alienShips[i][j] = new SpaceInvader(x, y);

				x += 50;
			}
			x = borderLeft;
			y -= 60;
		}
		
		for (int i = 0; i < bunkers.length; i++){
			
			bunkers[i] = new Bunkers(bx, by);
			
			bx += 200;
		}
		/*for(int i = 0; i < 3; i++){
			for(int j = 0; j < 10; j ++){


				SpaceInvader alienX = new SpaceInvader(100 + (j * 50) , (400) + i *30);
				alienShips.add(alienX);

			}*/
		
	}



	private void spawnEntities() {
		for (int j = 0; j < alienShips.length; j++){
			for(int i = 0; i <alienShips[j].length; i++){
				if (alienShips != null){
					alienShips[j][i].drawAlien();

				}
			}
			
			for (int i = 0; i < bunkers.length; i++){
				if(bunkers != null){
					
					bunkers[i].drawBunkers();
				}
			}

		/*for(int i = 0; i < 3; i++){
			for(int j = 0; j < 10; j ++){
				if(alienShips != null){
					alienShips.get(i).drawAlien();
					alienShips.get(j).drawAlien();
				}
			}*/
		}

		newPlayer.drawCannon();

	}
	private void Update(float deltaSec) {
		newPlayer.move();
		newPlayer.update(deltaSec);

		for (int i =0; i < alienShips.length; i++ ){
			for(int j = 0; j < alienShips[i].length; j ++){
				if(alienShips != null){

					alienShips[i][j].moveAliens();
				}
			}

		/*for(int i = 0; i < alienShips.size(); i++){
			if(alienShips != null){

				alienShips.get(i).moveAliens();
			}*/
		}
	}

	private void initiateTimer() {

		getDelta();
		lastFramePerSec = getTime();
	}

	private long getTime() {
		return System.nanoTime() / 1000000;
		
	}

	private float getDelta() {
		long currentTime = getTime();
		float delta = (float) (currentTime - lastDeltaSeconds) /1000.0f;
		lastDeltaSeconds = currentTime;
		return delta;

	}

	public void updateFramesPerSec(){

		if(getTime() - lastFramePerSec > 1000){
			Display.setTitle("FPS " + framesPerSec );
			framesPerSec = 0;
			lastFramePerSec += 1000;			
		}

		framesPerSec++;
	}

	public void initiateGL(){

		Display.setVSyncEnabled(true);
	}

	public void view(){

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0,width, 0, height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

	}

	private void userInput() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				//closes window when user presses escape key
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					closeWindow = true;
				}
			}
		}
		//Moves ship left or right
		/*if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))         newPlayer.shipVel.x = -150.0f;
		else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))   newPlayer.shipVel.x = 150.0f;
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) fired.add(new Shot((int) newPlayer.shipPos.x), newPlayer.shipPos.y + 20);*/


	}

	public static void main(String[] argv) {
		Game newGame = new Game();
		newGame.startGame();
	}


}
