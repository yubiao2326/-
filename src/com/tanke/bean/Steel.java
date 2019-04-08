package com.tanke.bean;

import java.io.IOException;

import com.tanke.game.Constants;
import com.tanke.utils.DrawUtils;

/**
 * 铁的类
 * @author 22793
 *
 */
public class Steel extends Element {

	public Steel(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void draw() {
		try {
			DrawUtils.draw(Constants.IMAGE_STEEL, x, y);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
