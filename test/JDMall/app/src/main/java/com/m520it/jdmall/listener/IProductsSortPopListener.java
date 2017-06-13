package com.m520it.jdmall.listener;

/**
 * @author 杨飞
 * @time 2016/9/6  15:13
 * @desc ${TODD}
 */
public interface IProductsSortPopListener {
    public static final int ALL_SORT=1;
    public static final int NEW_SORT=2;
    public static final int COMMENT_SORT=3;

    public void onSortChaneged(int sort);
}
