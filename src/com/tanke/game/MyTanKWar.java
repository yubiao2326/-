package com.tanke.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.input.Keyboard;

import com.tanke.bean.Bullet;
import com.tanke.bean.Direction;
import com.tanke.bean.Element;
import com.tanke.bean.Grass;
import com.tanke.bean.Steel;
import com.tanke.bean.Tank;
import com.tanke.bean.Wall;
import com.tanke.bean.Water;
import com.tanke.utils.DrawUtils;
import com.tanke.utils.SoundUtils;
import com.tanke.utils.Window;

public class MyTanKWar extends Window {

	private Tank tank;
	private Wall wall;

	// 墙的集合
	// private ArrayList<Wall> list = new ArrayList<>();
	public static CopyOnWriteArrayList<Element> list = new CopyOnWriteArrayList<>();

	public MyTanKWar(String title, int width, int height, int fps) {
		super(title, width, height, fps);
	}

	/**
	 * 窗口创建时调用，只调用一次
	 */
	@Override
	protected void onCreate() {

		// 开始游戏播放的音乐
		try {
			SoundUtils.play(Constants.MUSIC_START);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 坦克对象
		int width = Constants.WINDOWS_WIDTH / Constants.ELEMENT_WIDTH / 2 * Constants.ELEMENT_WIDTH;
		int height = Constants.WINDOWS_HEIGHT - Constants.ELEMENT_HEIGHT;
		tank = new Tank(width, height);
		list.add(tank);

		// 墙的对象
		for (int i = 1; i < Constants.WINDOWS_WIDTH / Constants.ELEMENT_WIDTH - 1; i++) {
			Wall wall = new Wall(i * Constants.ELEMENT_WIDTH, Constants.ELEMENT_HEIGHT);
			list.add(wall);
		}

		// 水的对象
		for (int i = 5; i < Constants.WINDOWS_WIDTH / Constants.ELEMENT_WIDTH - 4; i++) {
			Water water = new Water(i * Constants.ELEMENT_WIDTH, 4 * Constants.ELEMENT_HEIGHT);
			list.add(water);
		}

		// 铁的对象
		for (int i = 3; i < Constants.WINDOWS_WIDTH / Constants.ELEMENT_WIDTH - 8; i++) {
			Steel steel = new Steel(i * Constants.ELEMENT_WIDTH, 6 * Constants.ELEMENT_HEIGHT);
			list.add(steel);
		}

		// 草的对象
		for (int i = 5; i < Constants.WINDOWS_WIDTH / Constants.ELEMENT_WIDTH - 5; i++) {
			Grass grass = new Grass(i * Constants.ELEMENT_WIDTH, 7 * Constants.ELEMENT_HEIGHT);
			list.add(grass);
		}

		// 对集合进行排序
		Collections.sort(list, new Comparator<Element>() {
			@Override
			public int compare(Element o1, Element o2) {
				return o1.getLevel() - o2.getLevel();
			}
		});
	}

	/**
	 * 鼠标事件（key：0时为左键，1为右键）
	 */
	@Override
	protected void onMouseEvent(int key, int x, int y) {

	}

	/**
	 * 键盘事件（）
	 */
	@Override
	protected void onKeyEvent(int key) {
		// 坦克移动功能
		if (key == Keyboard.KEY_W) {
			tank.setSpeed(4);
			tank.move(Direction.UP);
		} else if (key == Keyboard.KEY_S) {
			tank.setSpeed(4);
			tank.move(Direction.DOWN);
		} else if (key == Keyboard.KEY_A) {
			tank.setSpeed(4);
			tank.move(Direction.LEFT);
		} else if (key == Keyboard.KEY_D) {
			tank.setSpeed(4);
			tank.move(Direction.RIGHT);
		} else if (key == Keyboard.KEY_SPACE) {
			// 发射子弹
			Bullet bullet = tank.shoot();
			if (bullet != null) {
				list.add(bullet);
			}
		}

	}

	/**
	 * 屏幕刷新时调用（不停刷新就不停调用）
	 */
	@Override
	protected void onDisplayUpdate() {
		// 画坦克
		// tank.draw();
		// 画墙
		for (Element e : list) {

			// 判断得到的是不是子弹
			if (e instanceof Bullet) {
				if (((Bullet) e).isDestroy()) {
					// 销毁
					list.remove(e);
				}
			}

			e.draw();
		}

	}

	@Override
	protected void onKeyRelease(int key) {
		if (key == Keyboard.KEY_W) {
			tank.setSpeed(0);
		} else if (key == Keyboard.KEY_S) {
			tank.setSpeed(0);
		} else if (key == Keyboard.KEY_A) {
			tank.setSpeed(0);
		} else if (key == Keyboard.KEY_D) {
			tank.setSpeed(0);
		}
	}

}
