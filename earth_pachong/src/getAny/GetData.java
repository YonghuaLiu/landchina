package getAny;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import writeDown.ToTable;

public class GetData {
	// 获取子网页-------------------------------------------------------
	public Document getChildPage(Map<String, String> canshu) {
		Map<String, String> canshu1=canshu;
		try {
			Document childPage = Jsoup
					.connect("http://www.landchina.com/default.aspx?")
					.data("tabid", "386").data("comname", "default")
					.data("wmguid", "75c72564-ffd9-426a-954b-8ac2df0903b7")
					.data("recorderguid", canshu1.get("recorderguid"))
					.userAgent("Mozilla").cookie("auth", "token")
					.timeout(30000).get();
			return childPage;
		} catch (Exception e) {
			System.out.println(ToTable.getTimeNow()+" 子网页获取超时,正在重新获取");
			return null;
		}
	}

	// 解析子网页,把其变成list<string>数组-------------------------
	public List<String> getData(Document doc) {
		Elements eles = doc.getElementsByClass("cellBordy");
		int i = 0;
		List<String> list = new ArrayList<String>();
		for (Element ele : eles) {
			if (i < 22 | i == 25) {
				if (i % 2 == 1) {
					String str = ele.text();
					list.add(str);
				}
			}
			if (i == 28) {
				String str = ele.text();
				String rong[] = str.split(" ");
				String floor;
				String ceil;
				if(str.contains(".")&&rong.length>3){
					floor = rong[1];
					ceil = rong[3];
				}else{
					floor = "无";
					ceil = "无";
				}
				list.add(floor);
				list.add(ceil);
			}
			if (i >= 30 && i <= 42) {
				if (i % 2 == 0) {
					String str = ele.text();
					list.add(str);
				}
			}
			i++;
		}
		return list;
	}

	// -------------------------------------------------------------------------
	// 获取很多行数据--------------------------------------------------------------
	public List<List> getRows(List<Map> canshu_list) {
		List<List> rows = new ArrayList<List>();
		for (int i = 0; i < canshu_list.size(); i++) {
			Map map = canshu_list.get(i);
			Document childPage = getChildPage(map);
			System.out.print(ToTable.getTimeNow()+"  已获取"+(i+1)+"个子页面");
			List<String> everyRow = getData(childPage);
			System.out.print(",已获取"+(i+1)+"行数据");
			System.out.println();
			rows.add(everyRow);
		}
		return rows;
	}
	// ------------------------------------------------------------------------
}
