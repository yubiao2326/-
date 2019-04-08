package com.tanke.bean;

import java.io.IOException;

import com.tanke.game.Constants;
import com.tanke.utils.DrawUtils;

/**
 * 墙的类
 * @author 22793
 *
 */
public class Wall extends Element{

	//坐标
	public Wall(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}


	/**
	 * 画墙
	 */
	public void draw() {
		try {
			DrawUtils.draw(Constants.IMAGE_WALL, x, y);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
