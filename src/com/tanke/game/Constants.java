package com.tanke.game;

/**
 * 常量类，配置信息
 * @author 22793
 *
 */
public class Constants {
	
	public static final String TITLE = "坦克大战";//标题
	public static final int WINDOWS_WIDTH = 64 * 17;//屏幕的宽
	public static final int WINDOWS_HEIGHT = 64 * 13;//屏幕的高
	public static final int FPS = 60;//屏幕刷新频率
	
	public static final int ELEMENT_WIDTH = 64;//游戏中每个元素的宽
	public static final int ELEMENT_HEIGHT = 64;//游戏中每个元素的高
	
	/**
	 * 坦克图片资源
	 */
	public static final String IMAGE_TANK_UP = "res/img/tank_u.gif";
	public static final String IMAGE_TANK_DOWN = "res/img/tank_d.gif";
	public static final String IMAGE_TANK_LEFT = "res/img/tank_l.gif";
	public static final String IMAGE_TANK_RIGHT = "res/img/tank_r.gif";
	
	/**
	 * 子弹图片资源
	 */
	public static final String IMAGE_BULLET_UP = "res/img/bullet_u.gif";
	public static final String IMAGE_BULLET_DOWN = "res/img/bullet_d.gif";
	public static final String IMAGE_BULLET_LEFT = "res/img/bullet_l.gif";
	public static final String IMAGE_BULLET_RIGHT = "res/img/bullet_r.gif";
	
	/**
	 * 墙的图片资源
	 */
	public static final String IMAGE_WALL  = "res/img/wall.gif";
	
	/**
	 * 水的图片资源
	 */
	public static final String IMAGE_WATER  = "res/img/water.gif";
	
	/**
	 * 铁的图片资源
	 */
	public static final String IMAGE_STEEL= "res/img/steel.gif";
	
	/**
	 * 草的图片资源
	 */
	public static final String IMAGE_GRASS= "res/img/grass.gif";
	
	/**
	 * 爆炸物的图片资源
	 */
	public static final String[] IMAGE_BLAST_ARRAY = {
			"res/img/blast_1.gif",
			"res/img/blast_2.gif",
			"res/img/blast_3.gif",
			"res/img/blast_4.gif",
			"res/img/blast_5.gif",
			"res/img/blast_6.gif",
			"res/img/blast_7.gif",
			"res/img/blast_8.gif"
	};
	
	/**
	 * 开始游戏的音乐资源
	 */
	public static final String MUSIC_START = "res/snd/start.wav";
	
	/**
	 * 射击的音乐资源
	 */
	public static final String MUSIC_FIRE = "res/snd/fire.wav";
	
	/**
	 * 击中目标的音乐资源
	 */
	public static final String MUSIC_HIT = "res/snd/hit.wav";
	
	/**
	 * 爆炸的音乐资源
	 */
	public static final String MUSIC_BLAST = "res/snd/blast.wav";
	
}
