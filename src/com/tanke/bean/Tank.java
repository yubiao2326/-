package com.tanke.bean;

import java.io.IOException;

import com.tanke.game.Constants;
import com.tanke.utils.DrawUtils;

public class Tank extends Element{

	//坦克方向
	private Direction direction = Direction.UP; // 默认朝上
	//速度
	private int speed = 32;

	/**
	 * 坐标的构造方法
	 * 
	 * @param x
	 * @param y
	 */
	public Tank(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/**
	 * 坦克的移动
	 */
	public void move(Direction direction) {
		//判断当前坦克的方向是否改变
		if(this.direction != direction) {
			this.direction = direction;
			//不执行移动操作
			return;
		}
		
		this.direction = direction;
		switch (direction) {
		case UP:
			y -= speed;
			break;
		case DOWN:
			y += speed;
			break;
		case LEFT:
			x -= speed;
			break;
		case RIGHT:
			x += speed;
			break;
		}
		//判断坦克是否出界
		checkOut();
	}
	
	/**
	 * 判断坦克出界的方法
	 */
	public void checkOut() {
		if(x < 0) 
			x=0;
		if(y < 0) 
			y=0;
		
		if(x > Constants.WINDOWS_WIDTH - Constants.ELEMENT_WIDTH)
			x = Constants.WINDOWS_WIDTH - Constants.ELEMENT_WIDTH;
		if(y > Constants.WINDOWS_HEIGHT - Constants.ELEMENT_HEIGHT)
			y = Constants.WINDOWS_HEIGHT - Constants.ELEMENT_HEIGHT;
	}

	/**
	 * 画坦克
	 */
	public void draw() {
		try {
			//根据移动方向不同，画不同的坦克
			switch (direction) {
			case UP:
				//向上,y轴减小
				DrawUtils.draw(Constants.IMAGE_TANK_UP, x, y);
				break;
			case DOWN:
				//向下,y轴变大
				DrawUtils.draw(Constants.IMAGE_TANK_DOWN, x, y);
				break;
			case LEFT:
				//向左，x轴减小
				DrawUtils.draw(Constants.IMAGE_TANK_LEFT, x, y);
				break;
			case RIGHT:
				//向右，x轴变大
				DrawUtils.draw(Constants.IMAGE_TANK_RIGHT, x, y);
				break;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发射子弹的方法
	 * @return
	 */
	public Bullet shoot() {
		//创建子弹对象
		Bullet bullet = new Bullet(this);
		return bullet;
	}
	
	/**
	 * 获取坦克方向的方法
	 * @return
	 */
	public Direction getDirection() {
		return direction;
	}
	
}
