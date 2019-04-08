package com.tanke.bean;

/**
 * 公共的父类
 * @author 22793
 *
 */
public abstract class Element {
	//坐标
	protected int x;
	protected int y;
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * 画的方法
	 */
	public abstract void draw();
	
	/**
	 * 等级
	 * @return
	 */
	public int getLevel() {
		return 1;
	}
}
