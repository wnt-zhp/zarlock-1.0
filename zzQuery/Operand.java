package zzQuery;

public enum Operand {
	SMALLER ("Mniejszy"),
	GREATER ("Wiêkszy"),
	EQUAL ("Równy"),
	SMALLER_OR_EQUAL ("Mniejszy równy"),
	GREATER_OR_EQUAL ("Wiêkszy równy"),
	NOT_EQUAL ("Nierówny");
	
	String name;
	
	Operand(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
