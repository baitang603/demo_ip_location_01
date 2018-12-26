package cn.tledu.starter;

import cn.tledu.service.DataSearcher;
import cn.tledu.service.DataSearcher2;

public class MyStarter {
	public static int binSearch(int[] array, int key, int startPos, int endPos) {
		int res = -1;
		if (startPos <= endPos) {
			int mid = (startPos + endPos) / 2;
			if (key == array[mid]) {
				return mid;
			} else if (key < array[mid]) {
				return binSearch(array, key, startPos, mid - 1);
			} else {
				return binSearch(array, key, mid + 1, endPos);
			}
		}
		return res;
	}
//	public static void test() {
//		int[] array = new int[] {2, 3, 5, 10, 12};
//		int index = binSearch(array, 11, 0, array.length - 1);
//		System.out.println(index);
//	}
	
	public static void test() {
		String ipStr = "173.194.6.2";//"224.0.1.12";
		DataSearcher2 ds1 = new DataSearcher2();//未排序
		DataSearcher2 ds2 = new DataSearcher2(null);//排序的
		long t1 = System.currentTimeMillis();
		String loc = ds2.getLocationByBinSearch(ipStr);
		long t2 = System.currentTimeMillis();
		System.out.println("二分查找耗时"+(t2 - t1) +"ms");
		loc = ds1.getLocation(ipStr);
		long t3 = System.currentTimeMillis();
		System.out.println("顺序查找耗时"+(t3 - t2) +"ms");
	}
	public static void test3() {
		String ipStr = "224.0.1.12";
		long t1 = System.currentTimeMillis();
		String loc = DataSearcher.getLocationByBinSearch(ipStr);
		long t2 = System.currentTimeMillis();
		System.out.println("二分查找耗时"+(t2 - t1) +"ms");
		loc = DataSearcher.getLocation(ipStr);
		long t3 = System.currentTimeMillis();
		System.out.println("顺序查找耗时"+(t3 - t2) +"ms");
		
	}
	public static void test2() {
		String ipStr = "192.168.100.108";//"192.168.1.150";//"111.224.7.107";
		String loc = DataSearcher.getLocationByBinSearch(ipStr);
		System.out.println(loc);
	}
	public static void test1() {
		String ipStr = "192.168.1.150";//"111.224.7.107";
		String loc = DataSearcher.getLocation(ipStr);
		System.out.println(loc);
	}
	public static void main(String[] args) {
		test();
	}

}
