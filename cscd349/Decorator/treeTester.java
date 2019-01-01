import treePackage.*;

public class treeTester 
{
	public static void main(String[] args)
	{
		Tree t1 = new ColoradoBlueSpruce();
		t1 = Star.addStar(t1);
		t1 = new Ruffles(t1);
		t1 = Star.addStar(t1);
		t1 = new Ruffles(t1);
		printTree(t1);
		
		Tree t2 = new FraserFir();
		t2 = Star.addStar(t2);
		t2 = new Ribbons(t2);
		t2 = new BallsRed(t2);
		t2 = new LEDs(t2);
		printTree(t2);
		
		Tree t3 = new DouglasFir();
		t3 = Star.addStar(t3);
		t3 = new BallsSilver(t3);
		t3 = new LEDs(t3);
		t3 = new Ribbons(t3);
		printTree(t3);
		
		Tree t4 = new BalsamFir();
		t4 = Star.addStar(t4);
		t4 = new BallsBlue(t4);
		t4 = new BallsRed(t4);
		t4 = new BallsSilver(t4);
		t4 = new LEDs(t4);
		t4 = new Lights(t4);
		t4 = new Ribbons(t4);
		t4 = new Ruffles(t4);
		printTree(t4);
	}
	
	public static void printTree(Tree tree) { System.out.println(tree.getDescription() + " will cost $" + tree.cost() + "0."); }
}
