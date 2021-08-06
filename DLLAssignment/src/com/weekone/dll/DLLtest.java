package com.weekone.dll;

public class DLLtest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DLL dll = new DLL();
        Node n1 = new Node(100);
        Node n2 = new Node(20);
        Node n3 = new Node(50);
        Node n4 = new Node(60);
        Node n5 = new Node(50);
        Node n6 = new Node(20);
        Node n7 = new Node(100);
        
        dll.push(n1);
        dll.push(n2);
        dll.push(n3);
        dll.push(n4);
        dll.push(n5);
        dll.push(n6);
        dll.push(n7);
        System.out.println("Size: "+ dll.size());
        dll.printValuesForward();
        dll.printValuesBackward();
        System.out.println("contains 2? "+dll.contains(2));
        System.out.println("contains 100? "+dll.contains(100));
//        System.out.println("contains 20? "+dll.contains(20));
//        System.out.println("contains 50? "+dll.contains(50));
//        System.out.println("contains 60? "+dll.contains(60));
//        System.out.println("contains 80? "+dll.contains(80));
//        System.out.println("contains 100? "+dll.contains(100));
        Node popped = dll.pop();
//        dll.insertAt(n7, 1);
//        dll.removeAt(0);
//        dll.removeAt(0);
        dll.pop();
        dll.pop();
        dll.pop();
//        dll.pop();
//        dll.pop();
        System.out.println("Popped:" + popped.value);
        dll.printValuesForward();
        dll.printValuesBackward();
        System.out.println("Size: "+ dll.size());
        System.out.println("Palindrome: "+ dll.isPalindrome());
//        System.out.println("contains 100? "+dll.contains(100));
	}

}
