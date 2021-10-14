package entities;

public class Cat {
	
	private int catId;
	private String name;
	private String color;
	private String typeOfHat;
	
	public Cat(int catId, String name, String color, String typeOfHat) {
		this.setCatId(catId);
		this.setName(name);
		this.setColor(color);
		this.setTypeOfHat(typeOfHat);
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTypeOfHat() {
		return typeOfHat;
	}

	public void setTypeOfHat(String typeOfHat) {
		this.typeOfHat = typeOfHat;
	}

}
