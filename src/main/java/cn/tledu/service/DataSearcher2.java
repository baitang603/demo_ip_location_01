package cn.tledu.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import cn.tledu.pojo.IPLocPojo;
import cn.tledu.util.IPUtil;

public class DataSearcher2 {
	private List<IPLocPojo> list;
	private String path = "ip_location_relation.txt";//"ip_loc.txt";
	
	public DataSearcher2() {
		this(new ArrayList<IPLocPojo>(), "ip_location_relation.txt", false, null);
	}
	
	public DataSearcher2(Comparator<IPLocPojo> comp) {
		this(new ArrayList<IPLocPojo>(), "ip_location_relation.txt", true, comp);
	}
	
	public DataSearcher2(List<IPLocPojo> list, String path, boolean needSort, Comparator<IPLocPojo> comp) {
		super();
		this.path = path;
		DataLoader2 dl = new DataLoader2(path, list);
		if (needSort) {
			this.list = dl.sort(comp);
		} else {
			this.list = dl.load();
		}
	}

	public String getLocation(String ipStr) {
		String res = "未找到";
		long ipLong = IPUtil.stringToLong(ipStr);
		for (IPLocPojo pojo : list) {
			if (ipLong >= pojo.getStartIPLong() && ipLong <= pojo.getEndIPLong()) {
				res = pojo.getLocation();
			}
		}
		return res;
	}
	
	public String getLocationByBinSearch(String ipStr) {
		String res = "未找到";
		//要有一个排序后的list
//		for (IPLocPojo pojo : list) {
//			System.out.println(pojo);
//		}
		//调用二分查找算法
		long ipLong = IPUtil.stringToLong(ipStr);
		int index = binSearch(list, ipLong, 0, list.size() - 1);
		if (index > -1) {
			res = list.get(index).getLocation();
		}
		return res;
	}
	
	private int binSearch(List<IPLocPojo> array, long key, int startPos, int endPos) {
		int res = -1;
		if (startPos <= endPos) {
			//注意这是下标mid   与array.get[mid]区分
			int mid = (startPos + endPos) / 2;
			IPLocPojo pojo = array.get(mid);
			if (key >= pojo.getStartIPLong() && key <= pojo.getEndIPLong()) {
				return mid;
			} else if (key < pojo.getStartIPLong()) {
				return binSearch(array, key, startPos, mid - 1);
			} else if (key > pojo.getEndIPLong()) {
				return binSearch(array, key, mid + 1, endPos);
			}
		}
		return res;
	}
	
}
