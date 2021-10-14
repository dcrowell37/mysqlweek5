package app;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.CatDao;
import entities.Cat;

public class Menu {
	
	private CatDao catDao = new CatDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList("Display Cats",
			"Display Cat Info",
			"Create Cat",
			"Delete Cat",
			"Update Cat");

	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				if (selection.equals("1")) {
					displayCats();
				} else if (selection.equals("2")) {
					displayCatInfo();
				} else if (selection.equals("3")) {
					createCat();
				} else if (selection.equals("4")) { 
					deleteCat();
				} else if (selection.equals("5")) { 
					updateCat();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			System.out.println("Press enter to continue....");
			scanner.nextLine();
			
		} while (!selection.equals("-1"));
	}
	

	private void displayCats() throws SQLException {
		
		List<Cat> cats = catDao.getCats();
		for (Cat cat : cats) {
			System.out.println(cat.getCatId() + ": Name -- " + cat.getName());
		}
		
	}
	
	private void displayCatInfo() throws SQLException {
		System.out.println("Enter Cat ID: ");
		int id = Integer.parseInt(scanner.nextLine());
		Cat cat = catDao.getCatInfoById(id);
		System.out.println(cat.getCatId() + ": Color -- " + cat.getColor() + ", Name -- " + cat.getName() + ", Type of hat this cat would wear -- " + cat.getTypeOfHat());
	}
	
	public void createCat() throws SQLException {
		System.out.println("Enter name of cat: ");
		String catName = scanner.nextLine();
		System.out.println("Enter color of cat: ");
		String color = scanner.nextLine();
		System.out.println("What type of hat would this cat wear: ");
		String typeOfHat = scanner.nextLine();
		catDao.createCat(catName, color, typeOfHat);
	}
	
	private void deleteCat() throws SQLException {
		System.out.println("Enter Cat id to delete: ");
		int id = Integer.parseInt(scanner.nextLine());
		catDao.deleteCatById(id);
		
	}
	
	private void updateCat() throws SQLException {
		
		System.out.println("Enter Cat id to update: ");
		int id = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter updated name: ");
		String catName = scanner.nextLine();
		System.out.println("Enter updated color: ");
		String color = scanner.nextLine();
		System.out.println("Enter updated hat: ");
		String typeOfHat = scanner.nextLine();
		catDao.updateCatById(catName, color, typeOfHat, id);
		
	}
		

	private void printMenu() {
		System.out.println("Select an Option\n-----------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	

}
