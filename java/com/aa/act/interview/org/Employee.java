package com.aa.act.interview.org;

import java.util.Random;

public class Employee {

	private int identifier;
	private Name name;
	
	public Employee(Name name) { //Change: randomly creates a 6-digit identifier
		if(name == null)
			throw new IllegalArgumentException("name cannot be null");
		this.identifier = new Random().nextInt(900000) + 100000;
		this.name = name;
	}
	
	public int getIdentifier() {
		return identifier;
	}
	
	public Name getName() {
		return name;
	}

	@Override
	public String toString() {
		return name.toString() + ": " + identifier;
	}
}
