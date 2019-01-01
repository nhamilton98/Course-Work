public class Profit
{
   private int buyDay;
   private int sellDay;
   private int profit;
   
   public Profit(int buyDay, int sellDay, int profit)
   {
      this.buyDay = buyDay;
      this.sellDay = sellDay;
      this.profit = profit;
   }
   
   public int getProfit() { return this.profit; }
   
   @Override
   public String toString() { return "[" + this.buyDay + ", " + this.sellDay + ", " + this.profit + "]"; }
}
