package imageEntry;

import getAny.GetData;
import getAny.GetLinks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import writeDown.ToMySQL;
import writeDown.ToTable;

public class MainProcess {
	public static List<Map> pageParaList = null;
	public static List<Map> allPageArgu = null;

	// 构造函数,查出来数据的每一页的结果,这一页的请求参数打包作为一个Map,放入list中了
	public MainProcess(List<Map> list) {
		this.pageParaList = list;
		List<Map> allLittleLinks = new ArrayList<Map>();
		// -循环获取所有链接 ------------------------
		GetLinks gl = new GetLinks();
		for (int i = 0; i < pageParaList.size(); i++) {
			if (i == 0) {
				System.out.println(ToTable.getTimeNow() + "  开始获取分页数据链接");
			}
			Map every_page_map = pageParaList.get(i);
			Document everyMenuPage = gl.getMainHtml(every_page_map);
			if (everyMenuPage == null) {
				System.out.println(ToTable.getTimeNow()
						+ "[Read Time Out~555555]");
			} else {
				Elements links = everyMenuPage
						.getElementsByClass("queryCellBordy");
				for (Element link : links) {
					Elements alink = link.children();
					String str = alink.outerHtml();
					// 获取所有的a标签
					if (str.contains("<a href")) {
						Map<String, String> map = new HashMap<String, String>();
						String[] str1 = str.split("href=\"");
						String[] str2 = str1[1].split("\" target");
						String url = "http://www.landchina.com/"
								+ str2[0].replaceAll("amp;", "");
						map.put("url", url);
						String[] requests = url.split("&");
						map.put("recorderguid", requests[3].split("=")[1]);
						allLittleLinks.add(map);
					}
				}
			}
		}
		System.out.println(ToTable.getTimeNow() + "  已获取" + allLittleLinks.size()
				+ "条链接");
		// -获取所有数据--------------------------------
		GetData gd = new GetData();
		List<List> rows = gd.getRows(allLittleLinks);
		
		// 写入数据库(如果机子上木有安装数据库,忽略这一部份)-----
		// ToMySQL tms=new ToMySQL();
		// tms.writeAll(rows);
		// 写入excel表格:-------------------------------
		if(rows==null||rows.size()==0){
			System.out.println(ToTable.getTimeNow() +"  未获取到数据");
			System.exit(0);
		}else{
			System.out.println(ToTable.getTimeNow() +"  已获取到" + rows.size() + "条数据");
			ToTable tt = new ToTable();
			String address = (String) pageParaList.get(0).get("savewhere");
			tt.createTable(rows, address);
		}
	}
}
