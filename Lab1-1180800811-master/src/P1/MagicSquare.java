package P1;

import java.io.*;
import java.util.Scanner;

public class MagicSquare {

	static boolean isLegalMagicSquare(String fileName) throws IOException {
		String filename = fileName;
		int row = 0;// 列数
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line = in.readLine();
			// 读取矩阵的行数
			while (line != null) {
				row++;
				line = in.readLine();
			}
			in.close();// 关闭文件
		} catch (IOException e) {
			System.out.println("读取文件错误：" + filename);
			return false;
		}
		int[][] a = new int[row][row];// 保存矩阵
		String line = null;
		BufferedReader ftr = new BufferedReader(new FileReader(filename));
		int t = 0;// 矩阵第几行
		String[] str =null ;
		while ((line = ftr.readLine()) != null) {
			 str = line.split("\t");
			// 判断矩阵行列数是否相等
			if (line.split(" ").length > 1) {
				System.out.println("矩阵不是用分隔符隔开");
				ftr.close();
				return false;
			}

			if (str.length != row) {
				System.out.println("不是矩阵");
				ftr.close();
				return false;
			}
			for (int i = 0; i < str.length; i++) {
				if (str[i].charAt(0) == '0') {
					System.out.println("元素错误，最高位不能是0");
					ftr.close();
					return false;
				}

				int j = str[i].length();
				// 判断矩阵元素是否为整数
				while (j > 0) {
					j--;
					if (!Character.isDigit(str[i].charAt(j))) {
						System.out.println("矩阵元素不是整数");
						ftr.close();
						return false;
					}

				}
			}
			// 读取矩阵元素并保存在二维数组中
			for (int i = 0; i < str.length; i++) {
				if ((a[t][i] = Integer.valueOf(str[i])) == 0) {
					System.out.println("矩阵元素为0");
					ftr.close();
					return false;
				}
			}
			t++;
		}
		ftr.close();

		int rows[] = new int[row];
		int columns[] = new int[row];
		int duijiaoxian1 = 0, duijaioxian2 = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < row; j++) {
				rows[i] += a[i][j];// 计算每行的和
				columns[j] += a[i][j];// 计算每列的和
			}
			duijiaoxian1 += a[i][i];
			duijaioxian2 += a[i][row - 1 - i];
		}
		int sum = rows[0];
		for (int k = 0; k < row; k++) {
			if (rows[k] != sum || columns[k] != sum || duijiaoxian1 != sum || duijaioxian2 != sum) {
				System.out.println("矩阵的行列或对角线元素不相等");
				return false;
			}
		}
		return true;
	}

	public static boolean generateMagicSquare(int n) throws IOException {
		if (n < 0) {
			System.out.println("不能输入负数，请输入一个正整数");
			return false;
		}
		if (n % 2 == 0) {
			System.out.println("不能输入偶数，请输入一个奇数");
			return false;
		}
		int magic[][] = new int[n][n];// 用于保存矩阵
		int row = 0, col = n / 2, i, j, square = n * n;// square代表要写到矩阵的元素个数
		// 将1-square这n个元素填写到矩阵中，填写规则是首先填写第一行正中间的数，然后依次向右上角填写，
		// 第一行右上角在最后一行，最后一列右上角在第一列。如果右上角的位置已经填写，则写到下一行，然后再往右上角写
		for (i = 1; i <= square; i++) {
			magic[row][col] = i;// 将1-square这n个元素填写到矩阵中
			if (i % n == 0)
				row++;// 要填入的位置被写了，则换一行
			else {
				if (row == 0)
					row = n - 1;// 第一行的右上角为最后一行
				else
					row--;// 右上角的行数比当前的行数少一
				if (col == n - 1)
					col = 0;// 最后一列右上角为第一列
				else
					col++;// 右上角的列行数比当前的列行数多少一
			}
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				System.out.print(magic[i][j] + "\t");
			System.out.println();

		}
		String filename1 = "src/P1/txt/6.txt";
		try {
			BufferedWriter s = new BufferedWriter(new FileWriter(filename1));
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					s.write(magic[i][j] + "\t");
				}
				s.write("\n");
			}
			s.close();
		} catch (IOException e) {
			System.out.println("打开文件出错");
		}

		return true;
	}

	public static void main(String[] args) throws IOException {

		String[] filename = new String[6];
		filename[0] = "src/P1/txt/1.txt";
		filename[1] = "src/P1/txt/2.txt";
		filename[2] = "src/P1/txt/3.txt";
		filename[3] = "src/P1/txt/4.txt";
		filename[4] = "src/P1/txt/5.txt";
		filename[5] = "src/P1/txt/6.txt";
		for (int i = 0; i < 5; i++) {
			System.out.println((i + 1) + ":" + isLegalMagicSquare(filename[i]));

		}
		Scanner in = new Scanner(System.in);
		System.out.println("请输入一个正奇数");
		int n = in.nextInt();

		System.out.print("6:" + generateMagicSquare(n));// 根据输入的阶数n产生幻方
		isLegalMagicSquare(filename[5]);
		in.close();
	}
}
