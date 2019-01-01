

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class Skeleton extends Monster
{
	private static final int HITPOINTS = 100;
	private static final int ATTACK_SPEED = 3;
	private static final double CHANCE_TO_HIT = 0.8;
	private static final double CHANCE_TO_HEAL = 0.3;
	private static final int DAMAGE_MIN = 30;
	private static final int DAMAGE_MAX = 50;
	private static final int HEAL_MIN = 30;
	private static final int HEAL_MAX = 50;
	
    public Skeleton()
	{
		super("Sargath the Skeleton", HITPOINTS, ATTACK_SPEED, CHANCE_TO_HIT, CHANCE_TO_HEAL,
									  DAMAGE_MIN, DAMAGE_MAX, HEAL_MIN, HEAL_MAX);

    }//end constructor

	public void attack(DungeonCharacter opponent)
	{
		System.out.println(name + " slices his rusty blade at " +
							opponent.getName() + ":");
		super.attack(opponent);

	}//end override of attack


}//end class Skeleton