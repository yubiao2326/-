package com.tanke.game;

public class MainApp {
	public static void main(String[] args) {
		String title = Constants.TITLE;
		int width = Constants.WINDOWS_WIDTH;
		int height = Constants.WINDOWS_HEIGHT;
		int fps = Constants.FPS;
		
		MyTanKWar myTanKWar = new MyTanKWar(title, width, height, fps);
		myTanKWar.start();
		
	}
}
 