package com.book.zuoshen.InterviewGuide.chpt9.math;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/28 14:53
 * @Description:判断一个点是否在矩形内部
 */
public class PointInMatrix {
    //纯纯的长方形
    public boolean isInside(double x1, double y1, double x4, double y4, double x, double y){
        if(x <= x1){
            return false;
        }
        if(x >= x4){
            return false;
        }
        if(y >= y1){
            return false;
        }
        if(y <= y4){
            return false;
        }
        return true;
    }

    //矩形
    public boolean isInsideNotParrlem(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4,
                                      double x, double y){
        if(y1 == y2) {
            return isInside(x1, y1, x4, y4, x, y);
        }
        double l = Math.abs(y4 - y3);
        double k = Math.abs(x4 - x3);
        double s = Math.sqrt(k * k + l * l);
        double sin = l / s;
        double cos = k / s;
        double x1R = cos * x1 + sin * y1;
        double y1R = -x1 * sin + y1 * cos;
        double x4R = cos * x4 + sin * y4;
        double y4R = -x4 * sin + y4 * cos;
        double xR = cos * x + sin * y;
        double yR = -x * sin + y * cos;
        return isInside(x1R, y1R, x4R, y4R, xR, yR);

    }
}
