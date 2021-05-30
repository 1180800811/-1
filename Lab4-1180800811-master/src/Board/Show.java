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
	 * 绘制表格
	 * @param a 表格的第一项
	 * @param t 表格的每一项信息
	 * @param jf JFrame容器
	 */
	public static void show(String[] a ,Object[][] t , JFrame jf) {
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JPanel panel = new JPanel() ;
		JTable table = new JTable(t, a) ;
		// 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列
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
