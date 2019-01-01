package cscd211Methods;

import java.util.Scanner;

import cscd211Classes.Vehicle;
import cscd211Enums.VehicleManufacturers;

public class Lab2Methods
{
	public static int menu(final Scanner kin)
	{
		if (kin == null)
			throw new IllegalArgumentException("Invalid keyboard Scanner.");
		
		int choice;
		do
		{
			System.out.println("VEHICLE MENU");
			System.out.println("-----------------------------------");
			System.out.println("1.) PRINT ARRAY TO SCREEN");
			System.out.println("2.) SORT BY METHOD");
			System.out.println("3.) SORT BY MANUFACTURER COMPARATOR");
			System.out.println("4.) SORT BY MODEL COMPARATOR");
			System.out.println("5.) PRINT ARRAY TO FILE");
			System.out.println("\n6.) QUIT");
			System.out.println("-----------------------------------");
			System.out.println("CHOICE:");
			choice = Integer.parseInt(kin.nextLine());
			
			if (choice < 1 || choice > 6)
				System.out.println("Invalid choice.");
		} while (choice < 1 || choice > 6);
		
		return choice;
	}
	
	public static Vehicle[] fillArray(final Scanner fin, final int total)
	{
		if (fin == null)
			throw new IllegalArgumentException("Invalid file Scanner.");
		if (total < 1)
			throw new IllegalArgumentException("Invalid vehicle total.");
		
		Vehicle[] vehicles = new Vehicle[total];
		int cylinders, index = 0;
		VehicleManufacturers manufacturer;
		String model;
		double liters;
		while (fin.hasNext())
		{
			manufacturer = Lab2Methods.convertStringToVehicleManufacturers(fin.nextLine());
			model = fin.nextLine();
			cylinders = Integer.parseInt(fin.nextLine());
			liters = Double.parseDouble(fin.nextLine());
			
			vehicles[index] = new Vehicle(manufacturer, model, cylinders, liters);
			index++;
		}
		
		return vehicles;
	}
	
	public static VehicleManufacturers convertStringToVehicleManufacturers(final String manufacturer)
	{
		if (manufacturer == null || manufacturer.equals(""))
			throw new IllegalArgumentException("Invalid manufacturer.");
		
		return VehicleManufacturers.valueOf(manufacturer.toUpperCase());
	}
}
