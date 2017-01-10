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

	// -----------��ȡ��ǰʱ��-----------------------------------------------
	public static String getTimeNow() {
		Date date = new Date();
		int hour = date.getHours();
		int minute = date.getMinutes();
		int second = date.getSeconds();
		String timeNow = "[��ǰʱ��" + hour + ":" + minute + ":" + second + "]";
		return timeNow;
	}

	// ------------��ͷ�����ݶ���------------------------------------------------
	public static String[] headers = new String[] { "������", "��Ŀ����", "��Ŀλ��",
			"���(����)", "������Դ", "������;", "���ط�ʽ", "����ʹ������", "��ҵ����", "���ؼ���",
			"�ɽ��۸�(��Ԫ)", "����ʹ��Ȩ��", "�ݻ�������:", "�ݻ�������:", "Լ������ʱ��", "Լ������ʱ��",
			"Լ������ʱ��", "ʵ�ʿ���ʱ��", "ʵ�ʿ���ʱ��", "��׼��λ", "��ͬǩ������" };

	// �������----------------------------------------------------------
	public void createTable(List<List> rows, String str) {
		try {
			File file = new File(str);
			if (!file.exists()) {
				file.createNewFile();
				System.out.println(getTimeNow() + " ����Ѵ���,·��Ϊ[" + str + "]");
			}
			wb = Workbook.createWorkbook(file);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WritableSheet sheet = wb.createSheet("���", 0);
		// �п����
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
		// ȥ��������
		sheet.getSettings().setShowGridLines(false);
		WritableCellFormat head = getHeaderCellStyle();
		WritableCellFormat body = getBodyCellStyle();
		// �����ͷ--------------------------------------------------
		for (int i = 0; i < headers.length; i++) {
			Label tempLable = new jxl.write.Label(i, 0, headers[i], head);
			try {
				sheet.addCell(tempLable);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// ------------------------------------------------------------------------
		// �������-----------------------------------------------------------------
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
			System.out.println(getTimeNow() + " ����д������,���Ե�");
			wb.write();
			System.out.println(getTimeNow() + " ������д�����");
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

	// ---���ñ�ͷ�ĸ�ʽ---------------------------------------------------------------------
	public WritableCellFormat getHeaderCellStyle() {
		WritableFont font = new WritableFont(WritableFont.createFont("΢���ź�"),
				12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat headerFormat = new WritableCellFormat(
				NumberFormats.TEXT);
		headerFormat.setFont(font);
		try {
			headerFormat.setBackground(Colour.LIGHT_GREEN);
			headerFormat.setBorder(Border.ALL, BorderLineStyle.THICK,
					Colour.GRAY_25);
			headerFormat.setAlignment(Alignment.CENTRE);
			System.out.println(getTimeNow() + " ��ͷ��ʽ�ѻ�ȡ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return headerFormat;
	}

	// ---���ñ���ĸ�ʽ-----------------------------------------
	public WritableCellFormat getBodyCellStyle() {
		WritableFont font = new WritableFont(WritableFont.createFont("΢���ź�"),
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
