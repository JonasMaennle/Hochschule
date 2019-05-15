package de.computernetze.praktikum2;

import java.io.Serializable;

public class TestObject implements Serializable{
	
	private static final long serialVersionUID = 8537611781593777133L;
	private String name;
	private int x;
	
	public TestObject(String name) {
		this.name = name;
		this.x = 10;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
}
