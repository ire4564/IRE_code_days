package Client;

import java.io.Serializable;
import java.util.ArrayList;
//12.15.19.02
public class Customer implements Serializable{
   // 필드
   private String name, ID, PW, address, phone;
   private ArrayList<Account> AccountList;

   // 생성자
   public Customer(String name, String ID, String PW, String address, String phone) { // 순서 꼭 지키기!!!!
      AccountList = new ArrayList<>(); // 계좌목록 생성
      setName(name);
      setID(ID);
      setPW(PW);
      setAddress(address);
      setPhone(phone);
      addAccount(new Account(2, this));
   }

   // 설정자
   public void setName(String name) { // 이름
      this.name = name;
   }

   public void setID(String ID) { // ID
      this.ID = ID;
   }

   public void setPW(String PW) { // PW
      this.PW = PW;
   }

   public void setAddress(String address) { // 주소
      this.address = address;
   }

   public void setPhone(String phone) { // 전화번호
      this.phone = phone;
   }

   public void setAccount(int index, Account account) { // 특정 위치의 계좌 변경
      AccountList.set(index, account);
   }

   // 접근자
   public String getName() { // 이름
      return this.name;
   }

   public String getID() { // ID
      return this.ID;
   }

   public String getPW() { // PW
      return this.PW;
   }

   public String getAddress() { // 주소
      return this.address;
   }

   public String getPhone() { // 전화번호
      return this.phone;
   }
   public ArrayList<Account> getAccountList(){
	   return this.AccountList;
   }

   // 계좌 관련 메소드
   public Account searchAccount(String Account) { // 특정 소유 계좌 탐색
      for (int i = 0; i < AccountList.size(); i++) {
         if (AccountList.get(i).getAccountNumber().equals(Account))
            return AccountList.get(i);
      }
      return null;
   }

   public Account getAccount(int index) { // index 위치의 계좌 출력
      try {
         return AccountList.get(index);
      } catch (Exception e) {
         return null;
      }

   }

   public boolean addAccount(Account account) { // 계좌 추가
      try {
         AccountList.add(account);
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   public boolean deleteAccount(Account account) { // 계좌 삭제
      try {
         AccountList.remove(account);
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   public int numberOfAccount() { // 보유 계좌의 수
      return AccountList.size();
   }

   public int totalBalance() { // 총자산
      int total = 0;
      for (int i = 1; i < AccountList.size(); i++) {
         total += AccountList.get(i).getBalance();
      }
      return total;
   }

   public int debt(int money) { // 대출 및 상납
      return AccountList.get(0).addBalance(money);
   }

   public int searchDebt() { // 대출금액 확인
      return AccountList.get(0).getBalance();
   }

   // 주식 관련 메소드
   public String balanceToString(int balance) {// 10000000원(1000만원)을 10,000,000 으로 표시해주는 메소드
      String balanceString = "" + balance;
      if (balanceString.length() > 3) {
         String returnString = "";
         for (int i = 0; i < balanceString.length() % 3; i++) {
            returnString += balanceString.charAt(i);
         }
         if (balanceString.length() % 3 != 0)
            returnString += ",";// 10, 까지 만들어짐
         for (int i = 0; i < balanceString.length() - balanceString.length() % 3; i++) {// 8-2니까 6번 반복함
            returnString += balanceString.charAt(i + balanceString.length() % 3);// 2번째 인덱스부터 (0,1,2)써야하므로 2를 더한다

            if ((i % 3) == 2 && i != (balanceString.length() - balanceString.length() % 3) - 1) {
               // 10,000,000 (012345) 2,5,8번째 인덱스에서 컴마를 찍어줘야함
               returnString += ",";
            }
         }

         return returnString;
      } else {
         return balanceString;
      }
   }

   // 확인 코드
   public String toString() {
      String str = "이름 : " + getName() + "\nID : " + getID() + "\nPW : " + getPW() + "\n주소 : " + getAddress()
            + "\n전화번호 : " + getPhone();
      str += "\n총자산 : " + balanceToString(totalBalance()) + "\n계좌 목록(총 " + numberOfAccount() + "개) :\n";
      for (int i = 0; i < AccountList.size(); i++) {
         str += AccountList.get(i).getAccountNumber();
         if (i != AccountList.size() - 1)
            str += "\n";
      }
      return str;
   }
}