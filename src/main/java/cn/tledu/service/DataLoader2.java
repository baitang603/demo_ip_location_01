package cn.tledu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.tledu.pojo.IPLocPojo;
import cn.tledu.util.IOUtil;

public class DataLoader2 {
	private String path = "ip_loc.txt";
	private List<IPLocPojo> list;
	
	public DataLoader2() {
		this(new ArrayList<IPLocPojo>());
	}
	
	public DataLoader2(List<IPLocPojo> list) {
		this("ip_loc.txt", list);
	}
	
	public DataLoader2(String path, List<IPLocPojo> list) {
		this.path = path;
		this.list = list;
		List<String> strList = IOUtil.readLines(path);
		for (String str : strList) {
			String[] strarr = str.split("\\t");
			if (strarr.length == 3) {
				IPLocPojo p = new IPLocPojo(strarr[0], strarr[1], strarr[2]);
				list.add(p);
			}
		}
	}
	
	public List<IPLocPojo> load() {
		return list;
	}
	
	public List<IPLocPojo> sort(Comparator<IPLocPojo> comp) {
		if (comp != null) {
			Collections.sort(list, comp);
		} else {
			Collections.sort(list);
		}
		return list;
	}
}
