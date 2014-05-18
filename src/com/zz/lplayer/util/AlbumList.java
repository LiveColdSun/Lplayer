package com.zz.lplayer.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.R.bool;
import android.content.Context;
import android.util.Log;

import com.zz.lplayer.domain.Album;
import com.zz.lplayer.domain.Artist;
import com.zz.lplayer.domain.Music;

public class AlbumList {
	
	//���ר����Ϣ
	public static List<Album> getAlbumData(Context context){
		
		List<Album> albumsList = new ArrayList<Album>();
		List<Music> musicList = new ArrayList<Music>();
		
		musicList = MusicList.getMusicData(context);//���Ȼ�ø����б�
		Iterator<Music> iterator = musicList.iterator();//������
		
		while(iterator.hasNext()){
			Music m = iterator.next();//�õ�ÿһ�׸�
			
			String albumname = m.getAlbum();
			Log.i("singer",albumname);
			boolean find = false;
			Iterator<Album> iterator2 = albumsList.iterator();
			
			while(iterator2.hasNext()){
				Album album = iterator2.next();
				String name = album.getAlbumName();
				Log.i("singer",name+" �Ա�");
				if(name.equals(albumname))
				{
					Log.i("singer", "���");
					find =true;
					album.addMusic(m);
					album.setAccount(album.getAccount()+1);
					break;
				}
			}
			if(!find)
			{
				Log.i("singer", albumname+"���");
				Album newAlbum = new Album();
				newAlbum.setAlbumName(albumname);
				newAlbum.setAccount(1);
				newAlbum.addMusic(m);
				albumsList.add(newAlbum);
			}
		}
		return albumsList;
	}

}
