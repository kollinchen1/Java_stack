package com.weekone.bankaccount;

public class BankAccountTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankAccount b1 = new BankAccount();
		System.out.println(b1.getChecking());
		b1.deposit(10000,"checking");
		System.out.println(b1.getChecking());
		System.out.println(b1.getAccNum());
		b1.withdraw(1000, "saving");
	}

}
