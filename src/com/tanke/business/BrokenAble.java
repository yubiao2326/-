package com.tanke.business;

import com.tanke.bean.Blast;

/**
 * 能被击毁的接口
 * @author 22793
 *
 */
public interface BrokenAble {
	public abstract Blast broken();
}
