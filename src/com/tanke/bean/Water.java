package com.tanke.bean;

import java.io.IOException;

import com.tanke.game.Constants;
import com.tanke.utils.DrawUtils;

/**
 * 水的类
 * @author 22793
 *
 */
public class Water extends Element {
	
	public Water(int x,int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 画的功能
	 */
	@Override
	public void draw() {
		try {
			DrawUtils.draw(Constants.IMAGE_WATER, x, y);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
