package com.merka.pushe.akcardapp.web;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;

import android.content.Context;

/**
 * This class contains simple file operation methods.
 * I borrowed it from somewhere that I don't recall now.
 */
public class FileUtils {

	public static void close(Reader reader) {
		if (reader != null)
			try {
				reader.close();
			} catch (IOException e) {
			}
	}

	public static void close(Writer writer) {
		if (writer != null)
			try {
				writer.close();
			} catch (IOException e) {
			}
	}

	public static void close(InputStream inputStream) {
		if (inputStream != null)
			try {
				inputStream.close();
			} catch (IOException e) {
			}
	}

	public static void close(OutputStream outputStream) {
		if (outputStream != null)
			try {
				outputStream.close();
			} catch (IOException e) {
			}
	}
	
	public static void copyFile(String inputPath, String inputFile, String outputPath) {

	    InputStream in = null;
	    OutputStream out = null;
	    try {

	        //create output directory if it doesn't exist
	        File dir = new File (outputPath); 
	        if (!dir.exists())
	        {
	            dir.mkdirs();
	        }


	        in = new FileInputStream(inputPath + inputFile);        
	        out = new FileOutputStream(outputPath + inputFile);

	        byte[] buffer = new byte[1024];
	        int read;
	        while ((read = in.read(buffer)) != -1) {
	            out.write(buffer, 0, read);
	        }
	        in.close();
	        in = null;

	            // write the output file (You have now copied the file)
	            out.flush();
	        out.close();
	        out = null;        

	    }  catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static ByteBuffer readToByteBuffer(InputStream inStream, Context context) throws IOException
	{
	    //long startTime = System.currentTimeMillis();
	    BufferedReader in = new BufferedReader(new InputStreamReader(context.getAssets().open("test.pdf")));
	    StringBuilder total = new StringBuilder();
	    String line;
	    while ((line = in.readLine()) != null) {
	        total.append(line);
	    }

	    int length = total.length();
	    byte[] buffer = new byte[length];
	    ByteArrayOutputStream outStream = new ByteArrayOutputStream(length);
	    int read;
	    while (true) {
	      read = inStream.read(buffer);
	      if (read == -1)
	        break;
	      outStream.write(buffer, 0, read);
	    }
	    ByteBuffer byteData = ByteBuffer.wrap(outStream.toByteArray());
	    //long stopTime = System.currentTimeMillis();
	    //mGraphView.fileMillis = stopTime-startTime;
	    return byteData;
	  }
}
