package s18cs350task3;

/**
 * A base class used for the movement of an Agent in three-dimensional space.
 * @author Nathan Hamilton
 * @version 1.0
 */
public class AgentMover implements I_Updateable
{
	private double x;
	private double y;
	private double z;
	private double speed;
	private double speedDeltaAcceleration;
	private double speedDeltaDeceleration;
	private double speedMax;
	private double azimuth;
	private double azimuthDelta;
	private double altitudeDelta;
	private double altitudeTarget;
	private double azimuthTarget;
	private double speedTarget;
	private boolean isAzimuthConverted;
	private boolean isClockwise;
	
	/**
	 * Constructs a new AgentMover that moves in three-dimensional space.
	 * @param x Position along x-axis
	 * @param y Position along y-axis
	 * @param z Agent altitude
	 * @param speed Current speed
	 * @param speedDeltaAcceleration Agent speed increase on position update
	 * @param speedDeltaDeceleration Agent speed decrease on position update
	 * @param speedMax Agent maximum speed
	 * @param azimuth Agent direction
	 * @param azimuthDelta Agent direction change on position update
	 * @param altitudeDelta Agent altitude change on position update
	 */
	public AgentMover(double x, double y, double z, double speed, double speedDeltaAcceleration,
			double speedDeltaDeceleration, double speedMax, double azimuth, double azimuthDelta,
			double altitudeDelta)
	{
		if (z < 0)
			throw new RuntimeException("Altitude must be non-negative.");
		if (speed < 0)
			throw new RuntimeException("Speed must be non-negative.");
		if (speedDeltaAcceleration < 0 || speedDeltaDeceleration < 0)
			throw new RuntimeException("Speed delta must be non-negative.");
		if (speedMax < 0)
			throw new RuntimeException("Max speed must be non-negative.");
		if (speed > speedMax)
			throw new RuntimeException("Current speed cannot exceed max speed.");
		if (azimuth < 0 || azimuth >= 360)
			throw new RuntimeException("Azimuth must be between 0 and 359 degrees (inclusive).");
		if (azimuthDelta < 0 || azimuthDelta >= 360)
			throw new RuntimeException("Azimuth delta must be between 0 and 359 degrees (inclusive).");
		if (altitudeDelta < 0)
			throw new RuntimeException("Altitude delta must be non-negative.");
		this.x = x;
		this.y = y;
		this.z = z;
		this.speed = speed;
		this.speedDeltaAcceleration = speedDeltaAcceleration;
		this.speedDeltaDeceleration = speedDeltaDeceleration;
		this.speedMax = speedMax;
		this.azimuth = azimuth;
		this.azimuthDelta = azimuthDelta;
		this.altitudeDelta = altitudeDelta;
		this.altitudeTarget = z;
		this.azimuthTarget = azimuth;
		this.speedTarget = speed;
	}
	
	/**
	 * @return the value of altitude change on position update
	 */
	public double getAltitudeDelta() { return this.altitudeDelta; }
	
	/**
	 * @return the current direction
	 */
	public double getAzimuth() { return this.azimuth; }
	
	/**
	 * @return the value of directional change on position update
	 */
	public double getAzimuthDelta() { return this.azimuthDelta; }
	
	/**
	 * @return the value of the target direction
	 */
	public double getAzimuthTarget() { return this.azimuthTarget; }
	
	/**
	 * @return the current speed
	 */
	public double getSpeed() { return this.speed; }
	
	/**
	 * @return the value of speed increase on position update
	 */
	public double getSpeedDeltaAcceleration() { return this.speedDeltaAcceleration; }
	
	/**
	 * @return the value of speed decrease on position update
	 */
	public double getSpeedDeltaDeceleration() { return this.speedDeltaDeceleration; }
	
	/**
	 * @return the maximum speed
	 */
	public double getSpeedMax() { return this.speedMax; }
	
	/**
	 * @return the minimum speed
	 */
	public double getSpeedMin() { return 0.0; }
	
	/**
	 * @return the target speed
	 */
	public double getSpeedTarget() { return this.speedTarget; }
	
	/**
	 * @return the position along the x-axis
	 */
	public double getX() { return this.x; }
	
	/**
	 * @return the position along the y-axis
	 */
	public double getY() { return this.y; }
	
	/**
	 * @return the altitude
	 */
	public double getZ() { return this.z; }
	
	/**
	 * @return true if the current altitude is equal to the target altitude
	 */
	public boolean hasAttainedTargetAltitude() { return this.z == this.altitudeTarget; }
	
	/**
	 * @return true if the current direction is equal to the target direction
	 */
	public boolean hasAttainedTargetAzimuth() { return this.azimuth == this.azimuthTarget; }
	
	/**
	 * @return true if the current speed is equal to the target speed
	 */
	public boolean hasAttainedTargetSpeed() { return this.speed == this.speedTarget; }
	
	/**
	 * @return true if directional rotation is clockwise
	 */
	public boolean isAzimuthTargetClockwise() { return this.isClockwise; }
	
	/**
	 * @param x Position along the x-axis of the proximate point
	 * @param y Position along the y-axis of the proximate point
	 * @param z Altitude of the proximate point
	 * @param distance Test distance between the Agent position and the proximate point
	 * @return true if the distance between the Agent position and the proximate point is less than the test distance
	 */
	public boolean isProximate(double x, double y, double z, double distance)
	{
		if (z < 0)
			throw new RuntimeException("Proximate altitude must be non-negative.");
		if (distance < 0)
			throw new RuntimeException("Proximite distance must be non-negative.");
		
		double calcDistance = Math.sqrt(Math.pow(x - this.x, 2.0) + Math.pow(y - this.y, 2.0) + Math.pow(z - this.z, 2.0));
		if (calcDistance > distance)
			return false;
		return true;
	}
	
	/**
	 * @param altitude the target altitude
	 */
	public void setAltitudeTarget(double altitude)
	{
		if (altitude < 0)
			throw new RuntimeException("Altitude must be non-negative.");
		this.altitudeTarget = altitude;
	}
	
	/**
	 * @param azimuth The target direction
	 * @param isClockwise The direction in which the Agent rotates
	 */
	public void setAzimuthTarget(double azimuth, boolean isClockwise)
	{
		if (azimuth < 0 || azimuth >= 360)
			throw new RuntimeException("Azimuth must be between 0 and 359 degrees (inclusive).");
		this.azimuthTarget = azimuth;
		this.isClockwise = isClockwise;
		this.isAzimuthConverted = false;
	}
	
	/**
	 * @param speed The target speed
	 */
	public void setSpeedTarget(double speed)
	{
		if (speed < 0)
			throw new RuntimeException("Speed must be non-negative.");
		this.speedTarget = speed;
	}
	
	/**
	 * Updates the current position of the Agent in the order of direction, speed, altitude, position
	 */
	public void update_()
	{
		if (this.azimuth != this.azimuthTarget) // Update azimuth
		{
			if (this.isClockwise)
			{
				if (this.azimuth + this.azimuthDelta > 359)
				{
					this.azimuth = this.azimuthDelta - (359 - this.azimuth);
					this.isAzimuthConverted = true;
				}
				else
				{
					if (this.isAzimuthConverted && this.azimuth > this.azimuthTarget)
						this.azimuth = this.azimuthTarget;
					else
					{
						this.azimuth += this.azimuthDelta;
						if (this.isAzimuthConverted && this.azimuth > this.azimuthTarget)
							this.azimuth = this.azimuthTarget;
					}
				}
			}
			else if (!this.isClockwise)
			{
				if (this.azimuth - this.azimuthDelta < 0)
				{
					this.azimuth = 359 - (this.azimuthDelta - this.azimuth);
					this.isAzimuthConverted = true;
				}
				else
				{
					if (this.isAzimuthConverted && this.azimuth < this.azimuthTarget)
						this.azimuth = this.azimuthTarget;
					else
					{
						this.azimuth -= this.azimuthDelta;
						if (this.isAzimuthConverted && this.azimuth < this.azimuthTarget)
							this.azimuth = this.azimuthTarget;
					}
				}
			}
		} // End update azimuth
		
		if (this.speed != this.speedTarget) // Update speed
		{
			if (this.speedTarget > this.speed)
			{
				this.speed += this.speedDeltaAcceleration;
				if (this.speed > this.speedTarget)
					this.speed = this.speedTarget;
			}
			else
			{
				this.speed -= this.speedDeltaDeceleration;
				if (this.speed < this.speedTarget)
					this.speed = this.speedTarget;
			}
		} // End update speed
		
		if (this.z != this.altitudeTarget) // Update altitude
		{
			if (this.altitudeTarget > this.z)
			{
				this.z += this.altitudeDelta;
				if (this.z > this.altitudeTarget)
					this.z = this.altitudeTarget;
			}
			else
			{
				this.z -= this.altitudeDelta;
				if (this.z < this.altitudeTarget)
					this.z = this.altitudeTarget;
			}
		} // End update altitude
		
		// Update position
		double polarAzimuth = 90 - this.azimuth;
		this.x += Math.cos(Math.toRadians(polarAzimuth)) * this.speed;
		this.y += Math.sin(Math.toRadians(polarAzimuth)) * this.speed;
		// End update position
	}
	
	/**
	 * @return a text representation of the objects current movement and position, target movement and position, and change values
	 */
	public String toString()
	{
		return "Azimuth: " + this.azimuth + " --> " + this.azimuthTarget+
				"\nSpeed: " + this.speed + " --> " + this.speedTarget +
				"\nAltitude: " + this.z + " --> " + this.altitudeTarget +
				"\nPosition: " + this.x + ", " + this.y + ", " + this.z +
				"\nAzimuth Delta: " + this.azimuthDelta +
				"\nSpeed Delta: " + this.speedDeltaAcceleration +
				"\nAltitude Delta: " + this.altitudeDelta;
	}
}
