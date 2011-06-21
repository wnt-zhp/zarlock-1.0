package zzQuery;

public enum Operand {
	SMALLER ("Mniejszy"),
	GREATER ("Wi�kszy"),
	EQUAL ("R�wny"),
	SMALLER_OR_EQUAL ("Mniejszy r�wny"),
	GREATER_OR_EQUAL ("Wi�kszy r�wny"),
	NOT_EQUAL ("Nier�wny");
	
	String name;
	
	Operand(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
