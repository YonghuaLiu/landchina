package writeDown;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ToTable {
	public static WritableWorkbook wb = null;

	// -----------获取当前时间-----------------------------------------------
	public static String getTimeNow() {
		Date date = new Date();
		int hour = date.getHours();
		int minute = date.getMinutes();
		int second = date.getSeconds();
		String timeNow = "[当前时间" + hour + ":" + minute + ":" + second + "]";
		return timeNow;
	}

	// ------------表头的内容定义------------------------------------------------
	public static String[] headers = new String[] { "行政区", "项目名称", "项目位置",
			"面积(公顷)", "土地来源", "土地用途", "供地方式", "土地使用年限", "行业分类", "土地级别",
			"成交价格(万元)", "土地使用权人", "容积率下限:", "容积率上限:", "约定交地时间", "约定开工时间",
			"约定竣工时间", "实际开工时间", "实际竣工时间", "批准单位", "合同签订日期" };

	// 创建表格----------------------------------------------------------
	public void createTable(List<List> rows, String str) {
		try {
			File file = new File(str);
			if (!file.exists()) {
				file.createNewFile();
				System.out.println(getTimeNow() + " 表格已创建,路径为[" + str + "]");
			}
			wb = Workbook.createWorkbook(file);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WritableSheet sheet = wb.createSheet("表格", 0);
		// 列宽调整
		for (int i = 0; i <= 20; i++) {
			if ((i == 1|i==2)|(i==5|i==11)){
				sheet.setColumnView(i, 33);
			}else{
				sheet.setColumnView(i, 17);
			}
		}
		CellView cellView = new CellView();
		cellView.setAutosize(true);
		sheet.setColumnView(1, cellView);
		// 去掉网格线
		sheet.getSettings().setShowGridLines(false);
		WritableCellFormat head = getHeaderCellStyle();
		WritableCellFormat body = getBodyCellStyle();
		// 输出表头--------------------------------------------------
		for (int i = 0; i < headers.length; i++) {
			Label tempLable = new jxl.write.Label(i, 0, headers[i], head);
			try {
				sheet.addCell(tempLable);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// ------------------------------------------------------------------------
		// 表体输出-----------------------------------------------------------------
		for (int i = 0; i < rows.size(); i++) {
			List<String> everyRow = rows.get(i);
			try {
				for (int j = 0; j < everyRow.size(); j++) {
					Label tempLable = new Label(j, 1 + i, everyRow.get(j), body);

					sheet.addCell(tempLable);
				}
			} catch (Exception e) {
			}
		}
		try {
			System.out.println(getTimeNow() + " 正在写入数据,请稍等");
			wb.write();
			System.out.println(getTimeNow() + " 数据已写入完毕");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// ------------------------------------------------------------------------
	}

	// --------------------------------------------------------------------------------

	// ---设置表头的格式---------------------------------------------------------------------
	public WritableCellFormat getHeaderCellStyle() {
		WritableFont font = new WritableFont(WritableFont.createFont("微软雅黑"),
				12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat headerFormat = new WritableCellFormat(
				NumberFormats.TEXT);
		headerFormat.setFont(font);
		try {
			headerFormat.setBackground(Colour.LIGHT_GREEN);
			headerFormat.setBorder(Border.ALL, BorderLineStyle.THICK,
					Colour.GRAY_25);
			headerFormat.setAlignment(Alignment.CENTRE);
			System.out.println(getTimeNow() + " 表头格式已获取");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return headerFormat;
	}

	// ---设置表身的格式-----------------------------------------
	public WritableCellFormat getBodyCellStyle() {
		WritableFont font = new WritableFont(WritableFont.createFont("微软雅黑"),
				10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat bodyFormat = new WritableCellFormat();
		bodyFormat.setFont(font);
		try {
			bodyFormat.setBackground(Colour.WHITE);
			bodyFormat.setBorder(Border.ALL, BorderLineStyle.THICK,
					Colour.GRAY_25);
			bodyFormat.setAlignment(Alignment.CENTRE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bodyFormat;
	}
}
