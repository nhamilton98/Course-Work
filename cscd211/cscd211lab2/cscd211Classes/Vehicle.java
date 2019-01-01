package cscd211Classes;

import cscd211Enums.VehicleManufacturers;

public class Vehicle implements Comparable<Vehicle>
{
	private Integer cylinders;
	private double liters;
	private VehicleManufacturers manufacturer;
	private String model;
	
	public Vehicle(final VehicleManufacturers manufacturer, final String model, final Integer cylinders, final double liters)
	{
		if (model == null || model.equals(""))
			throw new IllegalArgumentException("Invalid model.");
		if (liters < 1)
			throw new IllegalArgumentException("Invalid liter amount.");
		if (cylinders == null || cylinders < 1)
			throw new IllegalArgumentException("Invalid cylinder count.");
		
		this.cylinders = cylinders;
		this.liters = liters;
		this.manufacturer = manufacturer;
		this.model = model;
	}
	
	public Vehicle(final VehicleManufacturers manufacturer, final String model, final double liters, final Integer cylinders)
	{
		if (model == null || model.equals(""))
			throw new IllegalArgumentException("Invalid model.");
		if (liters < 1)
			throw new IllegalArgumentException("Invalid liter amount.");
		if (cylinders == null || cylinders < 1)
			throw new IllegalArgumentException("Invalid cylinder count.");
		
		this.cylinders = cylinders;
		this.liters = liters;
		this.manufacturer = manufacturer;
		this.model = model;
	}
	
	public VehicleManufacturers getManufacturer() { return this.manufacturer; }
	
	public String getModel() { return this.model; }
	
	@Override
	public int compareTo(Vehicle anotherVehicle)
	{
		if (anotherVehicle == null)
			throw new IllegalArgumentException("Cannot compare to null.");
		
		if (this.manufacturer.compareTo(anotherVehicle.manufacturer) > 1)
			return 1;
		else if (this.manufacturer.compareTo(anotherVehicle.manufacturer) < 1)
			return -1;
		else
		{
			if (this.model.compareTo(anotherVehicle.model) > 0)
				return 1;
			else if (this.model.compareTo(anotherVehicle.model) < 0)
				return -1;
			else
			{
				if (this.cylinders.compareTo(anotherVehicle.cylinders) > 0)
					return 1;
				else if (this.cylinders.compareTo(anotherVehicle.cylinders) < 0)
					return -1;
				else
				{
					if (Double.valueOf(this.liters).compareTo(Double.valueOf(anotherVehicle.liters)) > 0)
						return 1;
					else if (Double.valueOf(this.liters).compareTo(Double.valueOf(anotherVehicle.liters)) < 0)
						return -1;
					else
						return 0;
				}
			}
		}
	}
	
	@Override
	public String toString() { return "Your " + this.manufacturer.toString() + " " + this.model + " is a " + this.cylinders + " banger with a " + this.liters + " engine"; }
}
