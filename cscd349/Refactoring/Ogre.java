

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class Ogre extends Monster
{
	private static final int HITPOINTS = 200;
	private static final int ATTACK_SPEED = 2;
	private static final double CHANCE_TO_HIT = 0.6;
	private static final double CHANCE_TO_HEAL = 0.1;
	private static final int DAMAGE_MIN = 30;
	private static final int DAMAGE_MAX = 50;
	private static final int HEAL_MIN = 30;
	private static final int HEAL_MAX = 50;
			
    public Ogre()
	{
		super("Oscar the Ogre", HITPOINTS, ATTACK_SPEED, CHANCE_TO_HIT, CHANCE_TO_HEAL,
								DAMAGE_MIN, DAMAGE_MAX, HEAL_MIN, HEAL_MAX);


    }//end constructor

	public void attack(DungeonCharacter opponent)
	{
		System.out.println(name + " slowly swings a club toward's " +
							opponent.getName() + ":");
		super.attack(opponent);

	}//end override of attack


}//end Monster class