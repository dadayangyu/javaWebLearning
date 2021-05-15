package com.demo;

public class St {
   private String sno;
   private String name;
   private String sex;
   private int age;
   private String Type;
public String getSno() {
	return sno;
}
public void setSno(String sno) {
	this.sno = sno;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getType() {
	return Type;
}
public void setType(String type) {
	Type = type;
}
public St(String sno,String name,String sex,int age,String Type) {
	this.sno=sno;
	this.name=name;
	this.sex=sex;
	this.age=age;
	this.Type=Type;
}
}
