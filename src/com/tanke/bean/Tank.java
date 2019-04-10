package com.tanke.bean;

import java.io.IOException;
import java.util.Collections;

import com.tanke.business.BlockAble;
import com.tanke.game.Constants;
import com.tanke.game.MyTanKWar;
import com.tanke.utils.CollsionUtils;
import com.tanke.utils.DrawUtils;

public class Tank extends Element {

	// 坦克方向
	private Direction direction = Direction.UP; // 默认朝上
	// 速度
	private int speed;

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	// 发射子弹的时间戳
	private long lastShootTime;
	
	//坦克将要撞上的方向
	private Direction badDirection;

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
		for (Element e : MyTanKWar.list) {
			if (checkHit(e) && badDirection == direction) {
				System.out.println("撞墙了");
				return;
			}
		}

		// 判断当前坦克的方向是否改变
		if (this.direction != direction) {
			this.direction = direction;
			// 不执行移动操作
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
		// 判断坦克是否出界
		checkOut();
	}

	/**
	 * 判断坦克出界的方法
	 */
	public void checkOut() {
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;

		if (x > Constants.WINDOWS_WIDTH - Constants.ELEMENT_WIDTH)
			x = Constants.WINDOWS_WIDTH - Constants.ELEMENT_WIDTH;
		if (y > Constants.WINDOWS_HEIGHT - Constants.ELEMENT_HEIGHT)
			y = Constants.WINDOWS_HEIGHT - Constants.ELEMENT_HEIGHT;
	}

	/**
	 * 画坦克
	 */
	public void draw() {
		
		//是坦克自行移动
		move(direction);
		
		try {
			// 根据移动方向不同，画不同的坦克
			switch (direction) {
			case UP:
				// 向上,y轴减小
				DrawUtils.draw(Constants.IMAGE_TANK_UP, x, y);
				break;
			case DOWN:
				// 向下,y轴变大
				DrawUtils.draw(Constants.IMAGE_TANK_DOWN, x, y);
				break;
			case LEFT:
				// 向左，x轴减小
				DrawUtils.draw(Constants.IMAGE_TANK_LEFT, x, y);
				break;
			case RIGHT:
				// 向右，x轴变大
				DrawUtils.draw(Constants.IMAGE_TANK_RIGHT, x, y);
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发射子弹的方法
	 * 
	 * @return
	 */
	public Bullet shoot() {
		// 获取当前时间
		long current = System.currentTimeMillis();
		if (current - lastShootTime > 200) {
			// 发射成功，重新记录上一次的时间戳
			lastShootTime = current;
			// 创建子弹对象
			Bullet bullet = new Bullet(this);
			return bullet;
		}
		return null;
	}

	/**
	 * 获取坦克方向的方法
	 * 
	 * @return
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * 检测坦克是否碰撞的方法
	 * 
	 * @param e
	 * @return
	 */
	public boolean checkHit(Element e) {

		//如果不是能阻挡坦克的就返回false
		/*if (e instanceof Tank || e instanceof Grass || e instanceof Bullet) {
			return false;
		}*/
		if(!(e instanceof BlockAble)) {
			return false;
		}

		// 能跟坦克产生碰撞的元素
		int x1 = e.getX();
		int y1 = e.getY();
		int w1 = Constants.ELEMENT_WIDTH;
		int h1 = Constants.ELEMENT_HEIGHT;
		// 坦克的元素
		int x2 = x;
		int y2 = y;
		int w2 = Constants.ELEMENT_WIDTH;
		int h2 = Constants.ELEMENT_HEIGHT;

		// 检测坦克碰撞
		switch (direction) {
		case UP:
			y2 -= speed;
			break;
		case DOWN:
			y2 += speed;
			break;
		case LEFT:
			x2 -= speed;
			break;
		case RIGHT:
			x2 += speed;
			break;
		}

		// 判断
		boolean isHit = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);
		if (isHit) {
			
			badDirection = direction;
			
			switch (direction) {
			case UP:
				y = y1 + h1;
				break;
			case DOWN:
				y = y1 - h2;
				break;
			case LEFT:
				x = x1 + w1;
				break;
			case RIGHT:
				x = x1 - w2;
				break;
			}
		}

		return isHit;
	}

}
