package main;

import character.*;
import guitar.*;
import solo.*;

public class StrategyPattern
{
	public static void main(String[] args)
	{
		// Initial Behaviors
		GuitarFlyingV g1 = new GuitarFlyingV();
		SoloFire s1 = new SoloFire();
		CharacterAngusYoung c1 = new CharacterAngusYoung();
		c1.setGuitar(g1);
		c1.setSolo(s1);
		c1.play();
		c1.perform();
		
		GuitarSG g2 = new GuitarSG();
		SoloJump s2 = new SoloJump();
		CharacterJimiHendrix c2 = new CharacterJimiHendrix();
		c2.setGuitar(g2);
		c2.setSolo(s2);
		c2.play();
		c2.perform();
		
		GuitarTelecaster g3 = new GuitarTelecaster();
		SoloSmash s3 = new SoloSmash();
		CharacterSlash c3 = new CharacterSlash();
		c3.setGuitar(g3);
		c3.setSolo(s3);
		c3.play();
		c3.perform();
		
		// Changing Behaviors
		System.out.println();
		
		c1.setGuitar(g2);
		c1.setSolo(s3);
		c1.play();
		c1.perform();
		
		c2.setGuitar(g3);
		c2.setSolo(s1);
		c2.play();
		c2.perform();
		
		c3.setGuitar(g1);
		c3.setSolo(s2);
		c3.play();
		c3.perform();
	}
}
