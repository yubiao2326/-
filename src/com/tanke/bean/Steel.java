package com.tanke.bean;

import java.io.IOException;

import com.tanke.business.AttactedAble;
import com.tanke.business.BlockAble;
import com.tanke.business.BrokenAble;
import com.tanke.game.Constants;
import com.tanke.utils.DrawUtils;

/**
 * 铁的类
 * @author 22793
 *
 */
public class Steel extends Element implements BlockAble,AttactedAble,BrokenAble{
	
	//血量
	private int blood = 150;
	
	public int getBlood() {
		return blood;
	}
	
	public void setBlood(int blood) {
		this.blood = blood;
	}

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
