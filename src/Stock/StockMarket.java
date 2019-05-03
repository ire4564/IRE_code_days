package Stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
//20.41
/*
 * StockPrice 클래스를 통해 가격의 변동을 제공하고
 * 사용자들에게 주식을 팔고 살 수 있도록 해주는 클래스
 * Runnable을 통해 스레드를 만들어서 돌리면 무작위 가격 변동이 일어난다
 */
public class StockMarket implements Serializable{
   private static ArrayList<StockPrice> stockPrice;// 주식의 가격, 가격변동에 대한 정보를 담고 있는 list
   private boolean open;
   
   public void setOpen(boolean a) {
      open = a;
   }
   public boolean getOpen() {
      return open;
   }
   /*
    * 주식 시세들의 정보를 담고있는 StockPrice 클래스 이것이 스스로 변하면서 가격을 변동시킨다.
    */
   public class StockPrice implements Runnable{
      private String stockName;// 이름으로 구분하니까 이름 철저히 지켜야함
      private int currentPrice;// 현재의 가격, 주식의 거래는 이 가격으로 이루어진다
      private int dailyPrice;// 주식 시장이 돌아갈때의 첫 가격으로 todayFluctation은 결정된다
      private double recentFluctation;
      private double tempFluctation;
      private double dailyFluctation;
      private int fluctationFactor;

      /*
       * StockPrice의 생성자, 주식 시장이 열리면 자동으로 생성하면서 StockPrice를 처음 생성할때는 currentPrice가
       * dailyPrice가 된다.
       */
      public StockPrice(String sn, int dp, int ff) {
         setStockName(sn);
         setDailyPrice(dp);
         setCurrentPrice(dp);
         setFluctationFactor(ff);
      }

      // getter Setter 시작
      public void setStockName(String s) {
         stockName = s;
      }

      public String getStockName() {
         return stockName;
      }

      public void setCurrentPrice(int p) {
         currentPrice = p;
      }

      public int getCurrentPrice() {
         return currentPrice;
      }

      public void setDailyPrice(int p) {
         dailyPrice = p;
      }

      public int getDailyPrice() {
         return dailyPrice;
      }

      public void setRecentFluctation(int rf) {
         recentFluctation = rf;
      }

      public double getRecentFluctation() {
         return recentFluctation;
      }

      public void setDailyFluctation(int df) {
         dailyFluctation = df;
      }

      public double getDailyFluctation() {
         return dailyFluctation;
      }

      public void setFluctationFactor(int ff) {
         fluctationFactor = ff;
      }

      public int getFluctationFactor() {
         return fluctationFactor;
      }


      // getter setter 끝
      /*
       * 인자의 범위만큼 변동하는 메소드 0.001 * fluctationFactor % 만큼 변동한다. 하지만 이 변동이 price를 1 이상만큼
       * 변동시킬 정도가 아니면 temp에 담고 다시 호출한다
       */
      public void fluctate(int a) {
         Random r = new Random();
         tempFluctation += a *fluctationFactor*r.nextDouble() / 100000 / 100;// %이기 때문에 /100을 또해주는 것

         if (Math.abs(Math.round(currentPrice * tempFluctation)) >= 1) {// 절댓값이 1보다 크다면
            currentPrice += Math.round(currentPrice * tempFluctation);// 그 값만큼 가격에 더해준다
            dailyFluctation += tempFluctation/100;// 오늘의 변동은 최근 변동들을 다 합치면 됨
            recentFluctation = tempFluctation/100;// 최근 변동은 변동이 일어났을때의 변동
            tempFluctation = 0;
         }
      }

      /*
       * (non-Javadoc)
       * 
       * @see java.lang.Object#toString()
       */
      public String toString() {
         String str = "";
         str += "주식 이름 : " + getStockName() + "\n";
         str += "현재 가격 : " + getCurrentPrice() + "\n";
         str += "개장 가격 : " + getDailyPrice() + "\n";
         str += "최근 변동 : " + getRecentFluctation() + "\n";
         str += "오늘 변동 : " + getDailyFluctation() + "\n";
         str += "변동 인자 : " + getFluctationFactor();
         return str;
      }

      /*
       * 랜덤으로 1초마다 계속해서 시세를 변동시키는 메소드 fluctationFactor를 통해 변동 정도를 정할 수 있다.
       * 주식 시장이 만약 열린 상태라면 주식 시장에 존재하는 StockPrice들은 모두 각각의 스레드를 통해 변동이 시작된다.
       */
      public void run() {
         while (getOpen()) {
        	 
            Random r = new Random();
            int temp = r.nextInt(2);
            
            if (temp == 0)
               fluctate(-1);
            else {
               fluctate(1);
            }
           System.out.println(this); 
            try {
               Thread.currentThread().sleep(1000);
            } catch (Exception e) {
               // TODO: handle exception
            }
           
         }Thread.currentThread().interrupt();
      }
   }

   /*
    * 주식 시장에 저장되어있는 stockPrice getter
    */
   public void setStockPrice(ArrayList<StockPrice> a) {
      stockPrice = a;
   }
   public ArrayList<StockPrice> getStockPrice() {
      return stockPrice;
   }
   public static void main(String[] args) {
      
   }
}