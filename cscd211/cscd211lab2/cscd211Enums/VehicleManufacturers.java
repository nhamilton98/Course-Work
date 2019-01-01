package cscd211Enums;

public enum VehicleManufacturers
{
	CHRYSLER,
	FORD,
	HONDA,
	MERCEDES,
	NISSAN,
	TOYOTA,
	VOLKSWAGEN;
	
	@Override
	public String toString() { return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase(); }
}
