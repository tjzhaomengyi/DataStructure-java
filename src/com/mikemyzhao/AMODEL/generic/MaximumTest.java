package com.mikemyzhao.generic;
/*
 *   可能有的时候，你会想限制那些被允许传递到一个类型参数的类型种类范围。例如一个操作数字的方法可能只希望接受Number或者Number子类的实例，这就是有界类型参数的目的。
     要声明一个有界的类型参数，首先列出类型参数的名称，后跟extends关键字，最后紧跟它的上界。
	实例：下面例子延时了“extends”如何使用在一般意义上的意思“extends”类或者”implements”接口。该例子中的泛型方法可以返回是哪个可比较对象的最大值
 * 
 */

public class MaximumTest {
	//比较三个值并返回最大值
		public static <T extends Comparable<T>> T maximum(T x,T y,T z){
			T max = x;//假设x是初始最大值
			if( y.compareTo(max) > 0 ){
				max = y; //y更大
			}
			if( z.compareTo(max) > 0){
				max = z; //现在z更大
			}
			return max;//返回最大值
		}
	
		public static void main( String args[] ){
			System.out.printf("%d, %d和%d中最大的数为 %d\n\n",3,4,5,maximum(3,4,5));
			System.out.printf("%f, %f和%f中最大的数为 %f\n\n",3.1,4.2,5.3,maximum(3.1,4.2,5.3));
			System.out.printf("%s, %s和%s中最大的数为 %s\n\n","pear","apple","banana",maximum("pear","apple","banana"));
		}
}
