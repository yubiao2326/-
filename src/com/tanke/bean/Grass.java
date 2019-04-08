package com.tanke.bean;

import java.io.IOException;

import com.tanke.game.Constants;
import com.tanke.utils.DrawUtils;

public class Grass extends Element {
	
	public Grass(int x,int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw() {
		try {
			DrawUtils.draw(Constants.IMAGE_GRASS, x, y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getLevel() {
		return 2;
	}
	
	

}
