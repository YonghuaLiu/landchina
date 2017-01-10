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

	// ���캯��,��������ݵ�ÿһҳ�Ľ��,��һҳ��������������Ϊһ��Map,����list����
	public MainProcess(List<Map> list) {
		this.pageParaList = list;
		List<Map> allLittleLinks = new ArrayList<Map>();
		// -ѭ����ȡ�������� ------------------------
		GetLinks gl = new GetLinks();
		for (int i = 0; i < pageParaList.size(); i++) {
			if (i == 0) {
				System.out.println(ToTable.getTimeNow() + "  ��ʼ��ȡ��ҳ��������");
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
					// ��ȡ���е�a��ǩ
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
		System.out.println(ToTable.getTimeNow() + "  �ѻ�ȡ" + allLittleLinks.size()
				+ "������");
		// -��ȡ��������--------------------------------
		GetData gd = new GetData();
		List<List> rows = gd.getRows(allLittleLinks);
		
		// д�����ݿ�(���������ľ�а�װ���ݿ�,������һ����)-----
		// ToMySQL tms=new ToMySQL();
		// tms.writeAll(rows);
		// д��excel���:-------------------------------
		if(rows==null||rows.size()==0){
			System.out.println(ToTable.getTimeNow() +"  δ��ȡ������");
			System.exit(0);
		}else{
			System.out.println(ToTable.getTimeNow() +"  �ѻ�ȡ��" + rows.size() + "������");
			ToTable tt = new ToTable();
			String address = (String) pageParaList.get(0).get("savewhere");
			tt.createTable(rows, address);
		}
	}
}
