package com.demo;

public class Student {
   private  String sno;
   private  String name;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSno() {
	return sno;
}
public void setSno(String sno) {
	this.sno = sno;
}
 public Student(String sno,String name) {
	 this.name=name;
	 this.sno=sno;
}     
}
