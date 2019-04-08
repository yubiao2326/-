package com.tanke.bean;

import java.io.IOException;

import com.tanke.game.Constants;
import com.tanke.utils.DrawUtils;

public class Bullet extends Element {

	Direction direction;

	public Bullet(Tank tank) {

		// 获取坦克的方向，就是子弹发射的方向
		direction = tank.getDirection();

		// 坦克的横纵坐标
		int tankX = tank.getX();
		int tankY = tank.getY();
		// 坦克的宽高
		int tankWidt = Constants.ELEMENT_WIDTH;
		int tankHeight = Constants.ELEMENT_HEIGHT;
		// 子弹的宽高
		int bulletWidt = 0;
		int bulletHeight = 0;
		try {
			// 获取图片大小
			int[] arr = DrawUtils.getSize(Constants.IMAGE_BULLET_UP);
			bulletWidt = arr[0];
			bulletHeight = arr[1];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 计算子弹坐标
		switch (direction) {
		case UP:
			x = (int) (tankX + (tankWidt - bulletWidt) / 2.0f + 0.5f);
			y = (int) (tankY - bulletHeight / 2.0f + 0.5f);
			break;
		case DOWN:
			x = (int) (tankX + (tankWidt - bulletWidt) / 2.0f + 0.5f);
			y = (int) (tankY + tankHeight - bulletHeight / 2.0f + 0.5f);
			break;
		case LEFT:
			x = (int) (tankX - bulletHeight / 2.0f + 0.5f);
			y = (int) (tankY + (tankHeight - bulletHeight) / 2.0f + 0.5f);
			break;
		case RIGHT:
			x = (int) (tankX + tankHeight - bulletHeight / 2.0f + 0.5f);
			y = (int) (tankY + (tankHeight - bulletHeight) / 2.0f + 0.5f);
			break;
		}

	}

	@Override
	public void draw() {
		try {
			switch (direction) {
			case UP:
				DrawUtils.draw(Constants.IMAGE_BULLET_UP, x, y);
				break;
			case DOWN:
				DrawUtils.draw(Constants.IMAGE_BULLET_DOWN, x, y);
				break;
			case LEFT:
				DrawUtils.draw(Constants.IMAGE_BULLET_LEFT, x, y);
				break;
			case RIGHT:
				DrawUtils.draw(Constants.IMAGE_BULLET_RIGHT, x, y);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
