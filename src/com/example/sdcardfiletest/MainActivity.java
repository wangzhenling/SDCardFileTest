package com.example.sdcardfiletest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	private String fileName ="content.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void write(String content){
		
		try {
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				File sdCardDir=Environment.getExternalStorageDirectory();
				File destFile=new File(sdCardDir.getCanonicalPath()+File.separator+fileName);
				RandomAccessFile raf=new RandomAccessFile(destFile,"rw");
				raf.seek(destFile.length());
				raf.write(content.getBytes());
				raf.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String read(){
		
		StringBuilder sbBuilder=new StringBuilder("");
		try {
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				File sdCardDir=Environment.getExternalStorageDirectory();
				File destFile=new File(sdCardDir.getCanonicalPath()+File.separator+fileName);
				FileInputStream fis=new FileInputStream(destFile);
				byte[] buffer=new byte[64];
				int hasRead;
				while((hasRead=fis.read(buffer))!=-1){
					sbBuilder.append(new String(buffer,0,hasRead));
				}
				return sbBuilder.toString(); 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
