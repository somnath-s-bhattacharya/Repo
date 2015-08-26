package com.ThreadSelection;

import java.io.IOException;

import com.PerformanceTest_GetMethod.GET_ThreadHandler;
import com.PerformanceTest_MultiPartFormData_PostMethod.MultiPart_FormData_POST_ThreadHandler;
import com.PerformanceTest_PostMethod.POST_ThreadHandler;
import com.PerformanceTest_Website.Website_ThreadHandler;
import com.View.PerformanceTestExecutor;
import com.logging.CouchLogger;

public class WebMethodSelector 
{
	public static int methodselectorIndex =0;
	
	public void webMethodSelector(int selectorIndex, int rampUpTime) throws IOException, InterruptedException
	{	
		methodselectorIndex = selectorIndex;
			if (selectorIndex == 1)
			{
				GET_ThreadHandler GTH = new GET_ThreadHandler();
				PerformanceTestExecutor PTE = new PerformanceTestExecutor();
				GTH.ThreadHandler(PTE.concUsers, rampUpTime, PTE.webURL);
			}
			else if (selectorIndex == 2)
				{	
					POST_ThreadHandler PTTH = new POST_ThreadHandler();
					PerformanceTestExecutor PTE = new PerformanceTestExecutor();
					PTTH.ThreadHandler(PTE.concUsers, rampUpTime, PTE.webURL, PTE.JSONFilePath);
				}
				else if (selectorIndex == 3)
					{	
						System.out.println("Functionality Under Dev");
						//PerformanceTestExecutor PTE = new PerformanceTestExecutor();
						//PTTH.ThreadHandler(PTE.concUsers, rampUpTime, PTE.webURL, PTE.JSONFilePath);
					}	
					else if (selectorIndex == 4)
						{
							System.out.println("Functionality Under Dev");
							//PerformanceTestExecutor PTE = new PerformanceTestExecutor();
							//PTTH.ThreadHandler(PTE.concUsers, rampUpTime, PTE.webURL, PTE.JSONFilePath);
						}
						else if (selectorIndex == 5) 
							{
								Website_ThreadHandler WS = new Website_ThreadHandler();
								PerformanceTestExecutor PTE = new PerformanceTestExecutor();
								WS.ThreadHandler(PTE.concUsers, rampUpTime, PTE.webURL);
							}
							else if(selectorIndex == 6)
							{
								MultiPart_FormData_POST_ThreadHandler MP = new MultiPart_FormData_POST_ThreadHandler();
								PerformanceTestExecutor PTE = new PerformanceTestExecutor();
								MP.ThreadHandler(PTE.concUsers, rampUpTime, PTE.webURL);
							}
		}
		
}
