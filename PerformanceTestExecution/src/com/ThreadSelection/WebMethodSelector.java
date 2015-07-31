package com.ThreadSelection;

import java.io.IOException;
import com.PerformanceTest_PostMethod.POST_ThreadHandler;
import com.View.PerformanceTestExecutor;

public class WebMethodSelector 
{
	public static void webMethodSelector(int selectorIndex, int rampUpTime) throws IOException, InterruptedException
	{	
		
		if (selectorIndex == 1)
		{
			
			POST_ThreadHandler PTTH = new POST_ThreadHandler();
			PerformanceTestExecutor PTE = new PerformanceTestExecutor();
			PTTH.ThreadHandler(PTE.concUsers, rampUpTime, PTE.webURL, PTE.JSONFilePath);
			
		}
	}
}
