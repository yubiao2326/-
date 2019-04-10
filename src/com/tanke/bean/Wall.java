package com.tanke.bean;

import java.io.IOException;

import com.tanke.business.AttactedAble;
import com.tanke.business.BlockAble;
import com.tanke.business.BrokenAble;
import com.tanke.game.Constants;
import com.tanke.utils.DrawUtils;

/**
 * 墙的类
 * @author 22793
 *
 */
public class Wall extends Element implements BlockAble,AttactedAble,BrokenAble{

	//血量
	private int blood = 50;
	
	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}


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

	@Override
	public Blast Attacted() {
		Blast blast = new Blast(this);
		return blast;
	}

	@Override
	public Blast broken() {
		Blast blast = new Blast(this,true);
		return blast;
	}
}
