package com.tanke.bean;

import java.io.IOException;

import com.tanke.game.Constants;
import com.tanke.game.MyTanKWar;
import com.tanke.utils.DrawUtils;

/**
 * 爆炸物的类
 * @author 22793
 *
 */
public class Blast extends Element{
	
	//决定是大爆炸还是小爆炸的值
	private boolean isBigBoom = false;
	//大小爆炸的限制值
	private int limit = 0;
	
	public Blast(Element e) {
		//调用本类的构造
		this(e,false);
	}

	public Blast(Element e, boolean isBigBoom) {
		
		this.isBigBoom = isBigBoom;
		
		int blastWidth = 0;
		int blastHeight = 0;
		//获取爆炸物的宽高
		try {
			int[] arr = DrawUtils.getSize(Constants.IMAGE_BLAST_ARRAY[0]);
			blastWidth = arr[0];
			blastHeight = arr[1];
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//被击打元素属性值
		int eX = e.getX();
		int eY = e.getY();
		int eWidth = Constants.ELEMENT_WIDTH;
		int eHeight = Constants.ELEMENT_HEIGHT;
		//计算爆炸物的横纵坐标
		x = (int) (eX - (blastWidth - eWidth) / 2.0f + 0.5f);
		y = (int) (eY - (blastHeight - eHeight) / 2.0f + 0.5f);
	}

	@Override
	public void draw() {
		
		//判断是否为大爆炸或小爆炸
		if(isBigBoom == true) {
			limit = 0;
		}else {
			limit = 4;
		}
		
		try {
			for(int i=0; i < Constants.IMAGE_BLAST_ARRAY.length - limit; i++) {
				DrawUtils.draw(Constants.IMAGE_BLAST_ARRAY[i], x, y);
			}
			//销毁爆炸物
			MyTanKWar.list.remove(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
