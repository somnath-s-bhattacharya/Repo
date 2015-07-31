package com.PerformanceTest_Proquote;

import java.io.IOException;
import java.util.Scanner;

import com.PerformanceTest_Proquote.ProquoteRunnable1;

public class PerformanceTestExample {

	public static int i;	
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws IOException, InterruptedException {
		
		int users;
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the number of concurrent users: ");
		users = reader.nextInt();
		ProquoteRunnable1 PR1 = new ProquoteRunnable1();
			
		Thread[] threads = new Thread[users];
		for (i = 0; i < threads.length; i++) 
		{
		    threads[i] = new Thread(PR1);
		    threads[i].start();
		    threads[i].sleep(500);
		}
	    			
	}

}
