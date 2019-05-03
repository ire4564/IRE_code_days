package Stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
//20.41
/*
 * StockPrice Ŭ������ ���� ������ ������ �����ϰ�
 * ����ڵ鿡�� �ֽ��� �Ȱ� �� �� �ֵ��� ���ִ� Ŭ����
 * Runnable�� ���� �����带 ���� ������ ������ ���� ������ �Ͼ��
 */
public class StockMarket implements Serializable{
   private static ArrayList<StockPrice> stockPrice;// �ֽ��� ����, ���ݺ����� ���� ������ ��� �ִ� list
   private boolean open;
   
   public void setOpen(boolean a) {
      open = a;
   }
   public boolean getOpen() {
      return open;
   }
   /*
    * �ֽ� �ü����� ������ ����ִ� StockPrice Ŭ���� �̰��� ������ ���ϸ鼭 ������ ������Ų��.
    */
   public class StockPrice implements Runnable{
      private String stockName;// �̸����� �����ϴϱ� �̸� ö���� ���Ѿ���
      private int currentPrice;// ������ ����, �ֽ��� �ŷ��� �� �������� �̷������
      private int dailyPrice;// �ֽ� ������ ���ư����� ù �������� todayFluctation�� �����ȴ�
      private double recentFluctation;
      private double tempFluctation;
      private double dailyFluctation;
      private int fluctationFactor;

      /*
       * StockPrice�� ������, �ֽ� ������ ������ �ڵ����� �����ϸ鼭 StockPrice�� ó�� �����Ҷ��� currentPrice��
       * dailyPrice�� �ȴ�.
       */
      public StockPrice(String sn, int dp, int ff) {
         setStockName(sn);
         setDailyPrice(dp);
         setCurrentPrice(dp);
         setFluctationFactor(ff);
      }

      // getter Setter ����
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


      // getter setter ��
      /*
       * ������ ������ŭ �����ϴ� �޼ҵ� 0.001 * fluctationFactor % ��ŭ �����Ѵ�. ������ �� ������ price�� 1 �̻�ŭ
       * ������ų ������ �ƴϸ� temp�� ��� �ٽ� ȣ���Ѵ�
       */
      public void fluctate(int a) {
         Random r = new Random();
         tempFluctation += a *fluctationFactor*r.nextDouble() / 100000 / 100;// %�̱� ������ /100�� �����ִ� ��

         if (Math.abs(Math.round(currentPrice * tempFluctation)) >= 1) {// ������ 1���� ũ�ٸ�
            currentPrice += Math.round(currentPrice * tempFluctation);// �� ����ŭ ���ݿ� �����ش�
            dailyFluctation += tempFluctation/100;// ������ ������ �ֱ� �������� �� ��ġ�� ��
            recentFluctation = tempFluctation/100;// �ֱ� ������ ������ �Ͼ������ ����
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
         str += "�ֽ� �̸� : " + getStockName() + "\n";
         str += "���� ���� : " + getCurrentPrice() + "\n";
         str += "���� ���� : " + getDailyPrice() + "\n";
         str += "�ֱ� ���� : " + getRecentFluctation() + "\n";
         str += "���� ���� : " + getDailyFluctation() + "\n";
         str += "���� ���� : " + getFluctationFactor();
         return str;
      }

      /*
       * �������� 1�ʸ��� ����ؼ� �ü��� ������Ű�� �޼ҵ� fluctationFactor�� ���� ���� ������ ���� �� �ִ�.
       * �ֽ� ������ ���� ���� ���¶�� �ֽ� ���忡 �����ϴ� StockPrice���� ��� ������ �����带 ���� ������ ���۵ȴ�.
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
    * �ֽ� ���忡 ����Ǿ��ִ� stockPrice getter
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