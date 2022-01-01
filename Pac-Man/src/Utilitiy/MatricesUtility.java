package Utilitiy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class MatricesUtility {
	
	public static boolean isArraysEqual(int[] first, int[] secound) {
		boolean result = false;
		for (int i = 0; i < first.length; i++) {
			if (first[i] != secound[i]) {
				break;
			}
			if (i + 1 == first.length) {
				result = true;
			}
		}
		return result;
	}

	public static boolean isArraysEqual(float[] first, float[] secound) {
		boolean result = false;
		for (int i = 0; i < first.length; i++) {
			if (first[i] != secound[i]) {
				break;
			}
			if (i + 1 == first.length) {
				result = true;
			}
		}
		return result;
	}

	public static boolean isArraysEqual2(float[] first, float[] secound) {
		return Arrays.equals(first, secound);
	}

	public static int[][] shiftX(int[][] matrix, int value) {
		for (int[] is : matrix) {
			is[0] += value;
		}
		return matrix;
	}

	public static int[][] shiftY(int[][] matrix, int value) {
		for (int[] is : matrix) {
			is[1] += value;
		}
		return matrix;
	}

	public static List<List<int[][]>> pixelsShifte(List<List<int[][]>> list, int xShift, int yShift) {
		for (List<int[][]> list2 : list) {
			for (int[][] list3 : list2) {
				shiftX(list3, xShift);
				shiftY(list3, yShift);
			}
		}
		return list;
	}
	
}
