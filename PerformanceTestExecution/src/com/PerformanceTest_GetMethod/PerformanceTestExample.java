package com.PerformanceTest_GetMethod;

import java.util.Scanner;

import com.PerformanceTest_GetMethod.GetLotDetailRunnable;

public class PerformanceTestExample {
	
	public static int i;	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {
		
		int users;
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the number of concurrent users: ");
		users = reader.nextInt();
		GetLotDetailRunnable GLDR = new GetLotDetailRunnable();
			
		Thread[] threads = new Thread[users];
		for (i = 0; i < threads.length; i++) 
		{
		    threads[i] = new Thread(GLDR);
		    threads[i].start();
		    threads[i].sleep(200);
		}

	}

}
