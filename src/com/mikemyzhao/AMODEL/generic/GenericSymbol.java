package com.mikemyzhao.AMODEL.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author mikemyzhao
 *类型通配符一般是使用？代替具体的类型参数，例如List<?>在逻辑上是List<String>，List<Integer>等所有List<具体类型实参>的父类。
 */
public class GenericSymbol {
	public static void main(String[] args){
		List<String> name = new ArrayList<String>();
		List<Integer> age = new ArrayList<Integer>();
		List<Number> number = new ArrayList<Number>();
		
		name.add("mike");
		age.add(26);
		number.add(314);
		
		getData(name);
		getData(age);
		getData(number);
		
		//getUperNumber(name);//报错，因为getUpNumber()参数已经限定了参数泛型的上限为Number，String不在这个范围，报错
		getUperNumber(age);
		getUperNumber(number);
	}
	public static void getData(List<?> data){
		System.out.println("data:"+data.get(0));
	}
	
	public static void getUperNumber(List<? extends Number> data){
		System.out.println("dataextends:" + data.get(0));
	}
}
