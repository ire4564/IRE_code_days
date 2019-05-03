package Client;

import java.io.Serializable;
import java.util.ArrayList;
//12.15.19.02
public class Customer implements Serializable{
   // �ʵ�
   private String name, ID, PW, address, phone;
   private ArrayList<Account> AccountList;

   // ������
   public Customer(String name, String ID, String PW, String address, String phone) { // ���� �� ��Ű��!!!!
      AccountList = new ArrayList<>(); // ���¸�� ����
      setName(name);
      setID(ID);
      setPW(PW);
      setAddress(address);
      setPhone(phone);
      addAccount(new Account(2, this));
   }

   // ������
   public void setName(String name) { // �̸�
      this.name = name;
   }

   public void setID(String ID) { // ID
      this.ID = ID;
   }

   public void setPW(String PW) { // PW
      this.PW = PW;
   }

   public void setAddress(String address) { // �ּ�
      this.address = address;
   }

   public void setPhone(String phone) { // ��ȭ��ȣ
      this.phone = phone;
   }

   public void setAccount(int index, Account account) { // Ư�� ��ġ�� ���� ����
      AccountList.set(index, account);
   }

   // ������
   public String getName() { // �̸�
      return this.name;
   }

   public String getID() { // ID
      return this.ID;
   }

   public String getPW() { // PW
      return this.PW;
   }

   public String getAddress() { // �ּ�
      return this.address;
   }

   public String getPhone() { // ��ȭ��ȣ
      return this.phone;
   }
   public ArrayList<Account> getAccountList(){
	   return this.AccountList;
   }

   // ���� ���� �޼ҵ�
   public Account searchAccount(String Account) { // Ư�� ���� ���� Ž��
      for (int i = 0; i < AccountList.size(); i++) {
         if (AccountList.get(i).getAccountNumber().equals(Account))
            return AccountList.get(i);
      }
      return null;
   }

   public Account getAccount(int index) { // index ��ġ�� ���� ���
      try {
         return AccountList.get(index);
      } catch (Exception e) {
         return null;
      }

   }

   public boolean addAccount(Account account) { // ���� �߰�
      try {
         AccountList.add(account);
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   public boolean deleteAccount(Account account) { // ���� ����
      try {
         AccountList.remove(account);
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   public int numberOfAccount() { // ���� ������ ��
      return AccountList.size();
   }

   public int totalBalance() { // ���ڻ�
      int total = 0;
      for (int i = 1; i < AccountList.size(); i++) {
         total += AccountList.get(i).getBalance();
      }
      return total;
   }

   public int debt(int money) { // ���� �� ��
      return AccountList.get(0).addBalance(money);
   }

   public int searchDebt() { // ����ݾ� Ȯ��
      return AccountList.get(0).getBalance();
   }

   // �ֽ� ���� �޼ҵ�
   public String balanceToString(int balance) {// 10000000��(1000����)�� 10,000,000 ���� ǥ�����ִ� �޼ҵ�
      String balanceString = "" + balance;
      if (balanceString.length() > 3) {
         String returnString = "";
         for (int i = 0; i < balanceString.length() % 3; i++) {
            returnString += balanceString.charAt(i);
         }
         if (balanceString.length() % 3 != 0)
            returnString += ",";// 10, ���� �������
         for (int i = 0; i < balanceString.length() - balanceString.length() % 3; i++) {// 8-2�ϱ� 6�� �ݺ���
            returnString += balanceString.charAt(i + balanceString.length() % 3);// 2��° �ε������� (0,1,2)����ϹǷ� 2�� ���Ѵ�

            if ((i % 3) == 2 && i != (balanceString.length() - balanceString.length() % 3) - 1) {
               // 10,000,000 (012345) 2,5,8��° �ε������� �ĸ��� ��������
               returnString += ",";
            }
         }

         return returnString;
      } else {
         return balanceString;
      }
   }

   // Ȯ�� �ڵ�
   public String toString() {
      String str = "�̸� : " + getName() + "\nID : " + getID() + "\nPW : " + getPW() + "\n�ּ� : " + getAddress()
            + "\n��ȭ��ȣ : " + getPhone();
      str += "\n���ڻ� : " + balanceToString(totalBalance()) + "\n���� ���(�� " + numberOfAccount() + "��) :\n";
      for (int i = 0; i < AccountList.size(); i++) {
         str += AccountList.get(i).getAccountNumber();
         if (i != AccountList.size() - 1)
            str += "\n";
      }
      return str;
   }
}