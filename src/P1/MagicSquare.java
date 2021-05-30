package P1;

import java.io.*;
import java.util.Scanner;

public class MagicSquare {

	static boolean isLegalMagicSquare(String fileName) throws IOException {
		String filename = fileName;
		int row = 0;// ����
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String line = in.readLine();
			// ��ȡ���������
			while (line != null) {
				row++;
				line = in.readLine();
			}
			in.close();// �ر��ļ�
		} catch (IOException e) {
			System.out.println("��ȡ�ļ�����" + filename);
			return false;
		}
		int[][] a = new int[row][row];// �������
		String line = null;
		BufferedReader ftr = new BufferedReader(new FileReader(filename));
		int t = 0;// ����ڼ���
		String[] str =null ;
		while ((line = ftr.readLine()) != null) {
			 str = line.split("\t");
			// �жϾ����������Ƿ����
			if (line.split(" ").length > 1) {
				System.out.println("�������÷ָ�������");
				ftr.close();
				return false;
			}

			if (str.length != row) {
				System.out.println("���Ǿ���");
				ftr.close();
				return false;
			}
			for (int i = 0; i < str.length; i++) {
				if (str[i].charAt(0) == '0') {
					System.out.println("Ԫ�ش������λ������0");
					ftr.close();
					return false;
				}

				int j = str[i].length();
				// �жϾ���Ԫ���Ƿ�Ϊ����
				while (j > 0) {
					j--;
					if (!Character.isDigit(str[i].charAt(j))) {
						System.out.println("����Ԫ�ز�������");
						ftr.close();
						return false;
					}

				}
			}
			// ��ȡ����Ԫ�ز������ڶ�ά������
			for (int i = 0; i < str.length; i++) {
				if ((a[t][i] = Integer.valueOf(str[i])) == 0) {
					System.out.println("����Ԫ��Ϊ0");
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
				rows[i] += a[i][j];// ����ÿ�еĺ�
				columns[j] += a[i][j];// ����ÿ�еĺ�
			}
			duijiaoxian1 += a[i][i];
			duijaioxian2 += a[i][row - 1 - i];
		}
		int sum = rows[0];
		for (int k = 0; k < row; k++) {
			if (rows[k] != sum || columns[k] != sum || duijiaoxian1 != sum || duijaioxian2 != sum) {
				System.out.println("��������л�Խ���Ԫ�ز����");
				return false;
			}
		}
		return true;
	}

	public static boolean generateMagicSquare(int n) throws IOException {
		if (n < 0) {
			System.out.println("�������븺����������һ��������");
			return false;
		}
		if (n % 2 == 0) {
			System.out.println("��������ż����������һ������");
			return false;
		}
		int magic[][] = new int[n][n];// ���ڱ������
		int row = 0, col = n / 2, i, j, square = n * n;// square����Ҫд�������Ԫ�ظ���
		// ��1-square��n��Ԫ����д�������У���д������������д��һ�����м������Ȼ�����������Ͻ���д��
		// ��һ�����Ͻ������һ�У����һ�����Ͻ��ڵ�һ�С�������Ͻǵ�λ���Ѿ���д����д����һ�У�Ȼ���������Ͻ�д
		for (i = 1; i <= square; i++) {
			magic[row][col] = i;// ��1-square��n��Ԫ����д��������
			if (i % n == 0)
				row++;// Ҫ�����λ�ñ�д�ˣ���һ��
			else {
				if (row == 0)
					row = n - 1;// ��һ�е����Ͻ�Ϊ���һ��
				else
					row--;// ���Ͻǵ������ȵ�ǰ��������һ
				if (col == n - 1)
					col = 0;// ���һ�����Ͻ�Ϊ��һ��
				else
					col++;// ���Ͻǵ��������ȵ�ǰ������������һ
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
			System.out.println("���ļ�����");
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
		System.out.println("������һ��������");
		int n = in.nextInt();

		System.out.print("6:" + generateMagicSquare(n));// ��������Ľ���n�����÷�
		isLegalMagicSquare(filename[5]);
		in.close();
	}
}
