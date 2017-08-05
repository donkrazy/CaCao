import java.util.Arrays;

class Solution {
	int MOD = 20170805;
	int m;
	int n;
	int[][] cityMap;
	int[][] solutionMap;

	public int solution(int m, int n, int[][] cityMap) {
		this.m = m;
		this.n = n;
		this.cityMap = cityMap;
		this.solutionMap = new int[m][n];
		for (int[] row : solutionMap) {
			Arrays.fill(row, -1);
		}
		int answer = searchFrom(0, 0, true, true) % MOD;
		for (int[] row : solutionMap) {
			System.out.println(Arrays.toString(row));
		}
		return answer;
	}

	public int searchFrom(int i, int j, boolean canDown, boolean canRight) {
		// memorization
		if (solutionMap[i][j] != -1) {
			return solutionMap[i][j];
		}

		// base case
		if (i == m - 1 && j == n - 1) {
			return 1;
		}

		// edge case
		if (i == m - 1) {
			canDown = false;
		}
		if (j == n - 1) {
			canRight = false;
		}

		// // out case
		// if (i >= m || j >= n) {
		// return 0;
		// }

		// go down case
		int downPath = 0;
		if (canDown) {
			int downSign = cityMap[i + 1][j];
			if (downSign == 0) {
				downPath = searchFrom(i + 1, j, true, true);
			} else if (downSign == 1) {
				downPath = 0;
			} else if (downSign == 2) {
				downPath = searchFrom(i + 1, j, true, false);
			}
		}

		// go right case
		int rightPath = 0;
		if (canRight) {
			int rightSign = cityMap[i][j + 1];
			if (rightSign == 0) {
				rightPath = searchFrom(i, j + 1, true, true);
			} else if (rightSign == 1) {
				rightPath = 0;
			} else if (rightSign == 2) {
				rightPath = searchFrom(i, j + 1, false, true);
			}
		}

		// memorization
		int ret = rightPath + downPath;
		solutionMap[i][j] = ret;
		return ret;
	}
}