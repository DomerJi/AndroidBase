package com.jsp.base.data;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class Images {
	public final static String[] imageUrls = new String[] {
			"http://img.my.csdn.net/uploads/201309/01/1378037235_3453.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037234_3539.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037234_6318.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037194_2965.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037193_1687.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037193_1286.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037192_8379.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037178_9374.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037177_1254.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037177_6203.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037152_6352.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037151_9565.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037151_7904.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037148_7104.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037129_8825.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037128_5291.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037128_3531.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037127_1085.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037095_7515.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037094_8001.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037093_7168.jpg",
			"http://img.my.csdn.net/uploads/201309/01/1378037091_4950.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949643_6410.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949642_6939.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949630_4505.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949630_4593.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949629_7309.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949629_8247.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949615_1986.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949614_8482.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949614_3743.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949614_4199.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949599_3416.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949599_5269.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949598_7858.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949598_9982.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949578_2770.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949578_8744.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949577_5210.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949577_1998.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949482_8813.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949481_6577.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949480_4490.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949455_6792.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949455_6345.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949442_4553.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949441_8987.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949441_5454.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949454_6367.jpg",
			"http://e.hiphotos.baidu.com/image/w%3D400/sign=9a43780e8594a4c20a23e62b3ef51bac/f636afc379310a55ffd247f5b54543a982261038.jpg",
			"http://e.hiphotos.baidu.com/image/w%3D400/sign=56fae6dc2c738bd4c421b331918b876c/42166d224f4a20a4ca6da5c692529822720ed044.jpg",
			"http://d.hiphotos.baidu.com/image/w%3D400/sign=481f015fdfc451daf6f60deb86fc52a5/42a98226cffc1e17d94815304890f603738de938.jpg",
			"http://a.hiphotos.baidu.com/image/w%3D400/sign=0d7e1a3138f33a879e6d011af65c1018/b8389b504fc2d5625d7249ece51190ef76c66cbd.jpg",
			"http://e.hiphotos.baidu.com/image/w%3D400/sign=9915223123a446237ecaa462a8237246/11385343fbf2b2116724dd05c98065380cd78e77.jpg",
			"http://b.hiphotos.baidu.com/image/w%3D400/sign=937884d0d5ca7bcb7d7bc62f8e086b3f/64380cd7912397dd403225175b82b2b7d0a2875e.jpg",
			"http://a.hiphotos.baidu.com/image/w%3D400/sign=55b64afc79899e51788e3b1472a7d990/f9198618367adab42aa1824289d4b31c8701e4ba.jpg",
			"http://b.hiphotos.baidu.com/image/w%3D400/sign=b8f437614dc2d562f208d1edd71090f3/18d8bc3eb13533fa83470a2faad3fd1f41345b39.jpg",
			"http://a.hiphotos.baidu.com/image/w%3D400/sign=989b512e4334970a4773112fa5cad1c0/b7fd5266d01609249b503b1ad60735fae6cd34bd.jpg",
			"http://img.my.csdn.net/uploads/201308/31/1377949442_4562.jpg" };

	public static ArrayList<String[]> getArrayPics(){
		ArrayList<String[]> pics = new ArrayList<>();
		int size = imageUrls.length;
		int temp = 0;
		Random r = new Random();
		while (size!=temp){
			int len = r.nextInt(6)+3;
			if(len+temp>size) {
				len = size - temp;
			}
			String[] s = new String[len];
			System.arraycopy(imageUrls,temp,s,0,len);
			temp += len;
			pics.add(s);
		}
		return pics;
	}



}
