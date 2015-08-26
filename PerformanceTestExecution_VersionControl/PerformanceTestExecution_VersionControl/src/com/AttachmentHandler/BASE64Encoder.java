package com.AttachmentHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class BASE64Encoder 
{
	public static void encoder(String filenames) throws FileNotFoundException
	{
		FileInputStream fileInputStream=null;
        File file = new File("/Users/user2/Desktop/Performance Test Executor Functional Doc.pdf");
        byte[] bFile = new byte[(int) file.length()];
        try 
        {
            //convert file into array of bytes
		    fileInputStream = new FileInputStream(file);
		    fileInputStream.read(bFile);
		    fileInputStream.close();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		String encoded = Base64.getEncoder().encodeToString(bFile);
		byte[] decoded = Base64.getDecoder().decode(encoded);

		System.out.println(new String(decoded, StandardCharsets.UTF_8));
	}
}
