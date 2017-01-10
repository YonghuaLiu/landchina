package imageEntry;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import jxl.format.Colour;

public class ImageEntry extends JFrame {
	private JTextField date;
	private JTextField page;
	private JTextField savewhere;
	private JComboBox way_rang;
	private JComboBox use;
	private JComboBox xingzhengqu;
	private final JLayeredPane layeredPane = new JLayeredPane();

	public ImageEntry() {
		// ��Ҫ�ֶ����ӵĲ�������!!!!------------------------------------------------
		// ��������������ʾ����
		String[] cities = new String[] { "420100��~�人�б���", "420200��~��ʯ�б���",
				"421182��~��Ѩ��", "420600��~�差�б���", "421200��~�����б���",
				"421300��~�����б���", "420700��~�����б���", "420900��~Т���б���",
				"420500��~�˲��б���" ,"421102��~������"};
		// �������ķ�ʽ����ʾ����
		String[] sellWay = new String[] { "1��~����", "2��~���Ĺҳ���", "3��~Э�����" };
		// �����������ʵ���ʾ����
		String[] earthNature = new String[] { "05��~�̷��õ�", "07��~סլ�õ�" };
		// -----------------------------------------------------------------------
		// -----------------------------------------------------------------------
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		layeredPane.setBounds(0, 0, 984, 562);
		getContentPane().add(layeredPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setOpaque(true);
		panel.setBounds(396, 0, 589, 562);
		layeredPane.add(panel);
		panel.setLayout(null);

		JLabel label_4 = new JLabel("��Ӧ��ʽ");
		label_4.setForeground(new Color(0, 0, 0));
		label_4.setBounds(300, 378, 62, 31);
		panel.add(label_4);
		label_4.setFont(new Font("΢���ź�", Font.BOLD, 14));

		JLabel qu = new JLabel("��������");
		qu.setForeground(new Color(0, 0, 0));
		qu.setBounds(300, 322, 62, 31);
		panel.add(qu);
		qu.setFont(new Font("΢���ź�", Font.BOLD, 14));

		JLabel label = new JLabel("ǩ������");
		label.setBounds(59, 325, 63, 24);
		panel.add(label);
		label.setForeground(new Color(0, 0, 0));
		label.setBackground(Color.BLACK);
		label.setFont(new Font("΢���ź�", Font.BOLD, 14));

		JLabel label_1 = new JLabel("������;");
		label_1.setForeground(new Color(0, 0, 0));
		label_1.setBounds(59, 378, 63, 31);
		panel.add(label_1);
		label_1.setFont(new Font("΢���ź�", Font.BOLD, 14));

		JLabel label_2 = new JLabel("��ҳ����");
		label_2.setForeground(new Color(0, 0, 0));
		label_2.setBounds(59, 431, 63, 31);
		panel.add(label_2);
		label_2.setFont(new Font("΢���ź�", Font.BOLD, 14));

		page = new JTextField();
		page.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		page.setForeground(Color.BLACK);
		page.setBounds(123, 434, 159, 31);
		panel.add(page);
		page.setColumns(10);
		page.setOpaque(false);
		page.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));

		JLabel label_5 = new JLabel("�洢·��");
		label_5.setForeground(new Color(0, 0, 0));
		label_5.setBounds(300, 434, 62, 24);
		panel.add(label_5);
		label_5.setFont(new Font("΢���ź�", Font.BOLD, 14));

		savewhere = new JTextField();
		savewhere.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		savewhere.setForeground(Color.BLACK);
		savewhere.setBackground(new Color(85, 107, 47));
		savewhere.setBounds(362, 434, 165, 31);
		savewhere.setColumns(10);
		savewhere.setOpaque(false);
		savewhere.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
		panel.add(savewhere);

		JButton btnNewButton = new JButton("���ҿ�ʼ��,Ԥ���輸����ʱ��");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("΢���ź�", Font.BOLD, 16));
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(59, 494, 468, 31);
		panel.add(btnNewButton);

		way_rang = new JComboBox();
		way_rang.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		way_rang.setForeground(Color.BLACK);
		way_rang.setBackground(new Color(255, 255, 255));
		way_rang.setBounds(362, 378, 165, 31);
		way_rang.setModel(new DefaultComboBoxModel(sellWay));
		way_rang.setOpaque(false);
		panel.add(way_rang);

		use = new JComboBox();
		use.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		use.setForeground(Color.BLACK);
		use.setBackground(new Color(255, 255, 255));
		use.setBounds(123, 379, 159, 31);
		use.setModel(new DefaultComboBoxModel(earthNature));
		use.setOpaque(false);
		panel.add(use);

		xingzhengqu = new JComboBox();
		xingzhengqu.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		xingzhengqu.setForeground(Color.BLACK);
		xingzhengqu.setBackground(new Color(255, 255, 255));
		xingzhengqu.setBounds(362, 323, 165, 31);
		xingzhengqu.setModel(new DefaultComboBoxModel(cities));
		xingzhengqu.setOpaque(false);
		panel.add(xingzhengqu);

		date = new JTextField();
		date.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		date.setForeground(Color.BLACK);
		date.setBackground(Color.WHITE);
		date.setBounds(123, 325, 159, 31);
		date.setOpaque(false);
		date.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
		panel.add(date);
		date.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ImageEntry.class
				.getResource("/imageEntry/\u5730\u56FE_\u5C0F.jpg")));
		lblNewLabel.setBounds(40, 54, 338, 239);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("�����г���");
		lblNewLabel_2.setFont(new Font("΢���ź�", Font.BOLD, 35));
		lblNewLabel_2.setBounds(353, 119, 175, 47);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("www.landchina.com");
		lblNewLabel_3.setFont(new Font("Consolas", Font.BOLD, 18));
		lblNewLabel_3.setBounds(353, 174, 170, 22);
		panel.add(lblNewLabel_3);

		JLabel label_3 = new JLabel("�ɽ���Ϣ���� by-lanyx");
		label_3.setFont(new Font("΢���ź�", Font.BOLD, 12));
		label_3.setBounds(378, 205, 129, 17);
		panel.add(label_3);
		btnNewButton.addMouseListener(new MouseAdapter() {
			// �����ʼ�����¼�
			public void mouseClicked(MouseEvent e) {
				String during = date.getText();
				String city = (String) xingzhengqu.getSelectedItem();
				String earth_use = (String) use.getSelectedItem();
				String way = (String) way_rang.getSelectedItem();
				int pagecount = Integer.valueOf(page.getText());
				String where = savewhere.getText();
				System.out.println("�ѻ�֪����:" + during + "�ڼ�," + city + "��" + "��"
						+ way + "�ɽ���" + earth_use);
				List<Map> list = new ArrayList<Map>();
				for (int i = 1; i <= pagecount; i++) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("during", during);
					map.put("city", city);
					map.put("earth_use", earth_use);
					map.put("way", way);
					map.put("pagecount", String.valueOf(i));
					map.put("savewhere", where);
					list.add(map);
				}
				MainProcess mp = new MainProcess(list);
			}
		});

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(0, 0, 397, 562);
		layeredPane.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(ImageEntry.class
				.getResource("/imageEntry/\u57CE\u5E02.jpg")));

		// ҳ�������Ϣ����--------------------------------------------------------------
		super.setSize(1000, 600);
		super.setVisible(true);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// --------------------------------------------------------------------------
	}
}
