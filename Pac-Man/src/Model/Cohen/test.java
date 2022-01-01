package Model.Cohen;

import Model.Box;
import Model.Line;
import Model.Point;

public class test {
	
	public boolean isCrosing(Line line, Box box) {
		//Line Cohen clipping test
		int[] cohen1 = getCohenBit(line.getPoint1(),box);
		int[] cohen2 = getCohenBit(line.getPoint1(),box);
		if(isEqualZero(cohen1)&&isEqualZero(cohen2)) {
			return true;
		}
		
		return false;
	}
	
	private int[] isAndOperationEqualZero(int[] array1,int[] array2) {
		int[] result = new int[4];
		for (int i : result) {
		}
		
		
		return result;
	}
	
	private boolean isEqualZero(int[] array) {
		for (int i : array) {
			if(i!=0) {
				return false;
			}
		}
		return true;
	}

	private int[] getCohenBit(Point point, Box box) {
		int top = checkTop(point.getY(), box.getMaxY());
		int bottom = checkBottom(point.getY(), box.getMinY());
		int right = checkRight(point.getX(), box.getMaxX());
		int left= checkLeft(point.getX(), box.getminX());
		return new int[]{top,bottom,right,left};
	}

	private int checkLeft(int x, int maxX) {
		if (x > maxX) {
			return 1;
		}
		return 0;
	}

	private int checkRight(int x, int minX) {
		if (x < minX) {
			return 1;
		}
		return 0;
	}

	private int checkTop(int y, int maxY) {
		if (y > maxY) {
			return 1;
		}
		return 0;
	}

	private int checkBottom(int y, int minY) {
		if (y < minY) {
			return 1;
		}
		return 0;
	}

}
