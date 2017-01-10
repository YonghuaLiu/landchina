package getAny;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;




public class CellStyle {
	// ----------------��ͷ�ĸ�ʽ����---------------------------------------
	public WritableCellFormat getHeaderCellStyle() {
		WritableFont font = new WritableFont(WritableFont.createFont("����"), 10,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat headerFormat = new WritableCellFormat(
				NumberFormats.TEXT);
		try {
			// �����������
			headerFormat.setFont(font);
			// ���õ�Ԫ�񱳾�ɫ����ͷΪ��ɫ
			headerFormat.setBackground(Colour.YELLOW);
			// ���ñ�ͷ���߿���ʽ
			// ���������Ϊ���ߡ���ɫ
			headerFormat.setBorder(Border.ALL, BorderLineStyle.THICK,
					Colour.BLACK);
			// ��ͷ����ˮƽ������ʾ
			headerFormat.setAlignment(Alignment.CENTRE);
		} catch (Exception e) {
			System.out.println("��ͷ��Ԫ����ʽ����ʧ�ܣ�");
		}
		return headerFormat;
	}

	// --------------����ĸ�ʽ����------------------------------------
	public WritableCellFormat getBodyCellStyle() {
		WritableFont font = new WritableFont(WritableFont.createFont("����"), 10,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat bodyFormat = new WritableCellFormat(font);
		try {
			// ���õ�Ԫ�񱳾�ɫ������Ϊ��ɫ
			bodyFormat.setBackground(Colour.WHITE);
			// ���ñ�ͷ���߿���ʽ
			// ���������Ϊϸ�ߡ���ɫ
			bodyFormat
					.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		} catch (WriteException e) {
			System.out.println("���嵥Ԫ����ʽ����ʧ�ܣ�");
		}
		return bodyFormat;
	}

	// --------------------------------------------------------------
}
