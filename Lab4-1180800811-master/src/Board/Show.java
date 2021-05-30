package Board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class Show {

	/**
	 * ���Ʊ��
	 * @param a ���ĵ�һ��
	 * @param t ����ÿһ����Ϣ
	 * @param jf JFrame����
	 */
	public static void show(String[] a ,Object[][] t , JFrame jf) {
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel panel = new JPanel() ;
		JTable table = new JTable(t, a) ;
		// ���ñ�ͷ
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // ���ñ�ͷ����������ʽ
        table.getTableHeader().setForeground(Color.RED);                // ���ñ�ͷ����������ɫ
        table.getTableHeader().setResizingAllowed(false);               // ���ò������ֶ��ı��п�
        table.getTableHeader().setReorderingAllowed(false);             // ���ò������϶������������
		table.setRowHeight(30);
	    table.getColumnModel().getColumn(0).setPreferredWidth(80);
	    table.setPreferredScrollableViewportSize(new Dimension(1800, 1200));
	    JScrollPane scrollPane = new JScrollPane(table);
	    panel.add(scrollPane);
	    jf.setContentPane(panel);
	    jf.pack();
	    jf.setLocationRelativeTo(null);
	    jf.setVisible(true);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   		
		r.setHorizontalAlignment(JLabel.CENTER);   		
		table.setDefaultRenderer(Object.class, r);
	}
}
