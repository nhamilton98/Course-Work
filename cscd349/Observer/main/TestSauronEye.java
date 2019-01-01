package main;

import enemy.*;

public class TestSauronEye
{
	public static void main(String[] args)
	{
		EyeOfSauron eye = new EyeOfSauron();
		
		Commander saruman = new Commander(eye, "Saruman");
		Commander witchKing = new Commander(eye, "The Witch King");
		
		eye.scout(1, 1, 2, 0);
		
		saruman.defeated();
		System.out.println("\n" + saruman.getName() + " has been defeated.");
		
		eye.scout(4,  2,  2,  100);
		
		witchKing.defeated();
		System.out.println("\n" + witchKing.getName() + " has been defeated.");
	}
}
