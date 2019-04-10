package com.tanke.bean;

import java.io.IOException;

import com.tanke.business.AttactedAble;
import com.tanke.game.Constants;
import com.tanke.game.MyTanKWar;
import com.tanke.utils.CollsionUtils;
import com.tanke.utils.DrawUtils;
import com.tanke.utils.SoundUtils;

public class Bullet extends Element {

	// 决定播放大小爆炸音乐的值
	private boolean isBigBoomMusic = false;

	// 子弹的伤害量
	private int power = 25;

	// 子弹的速度
	private int speed = 6;

	Direction direction;

	public Bullet(Tank tank) {

		// 播放发射子弹的音乐
		try {
			SoundUtils.play(Constants.MUSIC_FIRE);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

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

		// 检测碰撞
		for (Element e : MyTanKWar.list) {
			if (checkAttact(e)) {
				return;
			}

		}

		try {
			switch (direction) {
			case UP:
				y -= speed;
				DrawUtils.draw(Constants.IMAGE_BULLET_UP, x, y);
				break;
			case DOWN:
				y += speed;
				DrawUtils.draw(Constants.IMAGE_BULLET_DOWN, x, y);
				break;
			case LEFT:
				x -= speed;
				DrawUtils.draw(Constants.IMAGE_BULLET_LEFT, x, y);
				break;
			case RIGHT:
				x += speed;
				DrawUtils.draw(Constants.IMAGE_BULLET_RIGHT, x, y);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断子弹是否要销毁
	 * 
	 * @return
	 */
	public boolean isDestroy() {
		if (x < 0 || y < 0 || x > Constants.WINDOWS_WIDTH || y > Constants.WINDOWS_HEIGHT) {
			return true;
		}
		return false;
	}

	/**
	 * 子弹碰撞的检测
	 * 
	 * @param e
	 * @return
	 */
	public boolean checkAttact(Element e) {

		/*
		 * if(e instanceof Tank || e instanceof Bullet || e instanceof Grass || e
		 * instanceof Water) { return false; }
		 */
		if (!(e instanceof AttactedAble)) {
			return false;
		}

		// 碰撞体
		int x1 = e.getX();
		int y1 = e.getY();
		int w1 = Constants.ELEMENT_WIDTH;
		int h1 = Constants.ELEMENT_HEIGHT;
		// 子弹体
		int x2 = x;
		int y2 = y;
		int w2 = 8;
		int h2 = 8;
		boolean isAttact = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);

		// 销毁
		if (isAttact) {

			MyTanKWar.list.remove(this);

			// 如果是墙
			if (e instanceof Wall) {

				// 创建爆炸物
				Blast blast = ((Wall) e).Attacted();

				int blood2 = ((Wall) e).getBlood() - power;
				((Wall) e).setBlood(blood2);

				if (((Wall) e).getBlood() <= 0) {
					// 产生大爆炸
					blast = ((Wall) e).broken();
					MyTanKWar.list.remove(e);
					isBigBoomMusic = true;
				}
				// 添加爆炸物
				MyTanKWar.list.add(blast);
			}
			// 如果是水
			if (e instanceof Steel) {

				// 创建爆炸物
				Blast blast = ((Steel) e).Attacted();

				int blood2 = ((Steel) e).getBlood() - power;
				((Steel) e).setBlood(blood2);

				if (((Steel) e).getBlood() <= 0) {
					// 产生大爆炸
					blast = ((Steel) e).broken();
					MyTanKWar.list.remove(e);
					isBigBoomMusic = true;
				}
				// 添加爆炸物
				MyTanKWar.list.add(blast);
			}
			try {
				if (isBigBoomMusic) {//大爆炸音乐
					SoundUtils.play(Constants.MUSIC_BLAST);
				}else {//小爆炸音乐
					SoundUtils.play(Constants.MUSIC_HIT);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

		return isAttact;
	}

}
