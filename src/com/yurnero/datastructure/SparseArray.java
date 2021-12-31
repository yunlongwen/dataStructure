package com.yurnero.datastructure;

/**
 * 
 * 把 11*11的棋盘数组转换为稀疏数组，最后在还原成棋盘数组
 * 
 * @author yunlong.wen
 *
 */

public class SparseArray {
	public static void main(String[] args) {
		String tab = "  ";
		int defaultValue = 0;
		int originalRow = 11;
		int originalCol = 11;

		int[][] chessArray = new int[originalRow][originalCol];
		chessArray[1][2] = 1;
		chessArray[3][4] = 2;

		System.out.println("原始数组：");
		int sum = 0;
		for (int[] row : chessArray) {
			for (int item : row) {
				System.out.printf("%d%s", item, tab);
				if (item != defaultValue) {
					sum++;
				}
			}
			System.out.println();
		}

		int[][] sparseArray = new int[sum + 1][3];
		sparseArray[0][0] = originalRow;
		sparseArray[0][1] = originalCol;
		sparseArray[0][2] = sum;

		int count = 0;
		for (int i = 0; i < originalRow; i++) {
			for (int j = 0; j < originalCol; j++) {
				if (chessArray[i][j] != defaultValue) {
					count++;
					sparseArray[count][0] = i;
					sparseArray[count][1] = j;
					sparseArray[count][2] = chessArray[i][j];
				}
			}
		}

		System.out.println("稀疏数组：");
		for (int[] row : sparseArray) {
			for (int item : row) {
				System.out.printf("%d\t", item);
				if (item != defaultValue) {
					sum++;
				}
			}
			System.out.println();
		}

		int[][] chessArray2 = new int[sparseArray[0][0]][sparseArray[0][1]];

		for (int i = 1; i < sparseArray.length; i++) {
			chessArray2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
		}

		System.out.println("还原后的数组：");
		for (int[] row : chessArray2) {
			for (int item : row) {
				System.out.printf("%d%s", item, tab);
			}
			System.out.println();
		}
	}

}
