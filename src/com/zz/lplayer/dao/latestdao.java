package com.zz.lplayer.dao;

import java.util.ResourceBundle.Control;

import android.R.integer;
import android.R.string;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract.Helpers;
import android.util.Log;

public class latestdao  {
	private static  dbOpenHelper helper;
	private static SQLiteDatabase db;
	public  latestdao(Context context) {
		helper=new dbOpenHelper(context);
		
		Log.v("test","latestdao Construct");
	}
	
	// LatestList.insertData(fullPath);��ÿһ��������в��ŵ�listener�������������������б�Ĳ������
	
	public static void insertData(String fullPath,String name) { 
		Log.v("test","latestdao URL " +fullPath);
		
		db=helper.getWritableDatabase();
        // ���ж����ݿ����Ƿ�����������  
        String sql1 = "select mname from latestlist where mpath = ? "; 
        String sql2="delete from latestlist where mpath='"+fullPath+"'"; 
        String sql3="insert into latestlist(mname,mpath) values('" + name+"','"+fullPath+"')";
       //�ж��Ƿ���� ������ɾ��֮ǰ�Ѵ��ڵ� �Ҳ����µ�
       // String string;
       Cursor cursor =db.rawQuery(sql1, new String[] {String.valueOf(fullPath)} );
       int num=cursor.getCount();
       Log.v("test","latestdao insert get num "+ num);
       if (num!=0){
    	  //�ҵ���ɾ���������
    	  db.execSQL(sql2);
    	  Log.v("test","latestdao exec sql2 " +sql2);
    	  db.execSQL(sql3);  
      }
      else{
    	  //�Ҳ���ֱ��insert ���fullpath
    	  Log.v("test","exec "+ sql3);
    	  db.execSQL(sql3);
    	  
      }
      
    }  
	 
	//ÿ�ε�����������������ж�ʮ�����ݵ�url 
	public static String[] latestlist (  ){
		//���ز���url�ļ���
		db=helper.getWritableDatabase();
		String[] string=new String[10];
	// String sql1 = "select mpath from latestlist "; 
		String[] columns ={"mpath"};
	 Cursor cursor =db.query("latestlist",columns , null, null, null, null, null);
	 int num=cursor.getCount();
	 Log.v("test" , "latestdao getString[] num "+ num);
	 if(num>10)
	     num = 10;
	 int i=0;
	 cursor.moveToLast();
	 while(num>0)
	 {
		 string[i++] = cursor.getString(cursor.getColumnIndex("mpath"));
		 cursor.moveToPrevious();
		 num--;
	 }
	 

	  return string;
	}
	
	//ÿ�ε����������������ݿ����������б�
	
}
