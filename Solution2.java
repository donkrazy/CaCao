class Solution {
	int MOD = 20170805;
	int m;
	int n;
	int[][] cityMap;

	public int solution(int m, int n, int[][] cityMap) {
		this.m = m;
		this.n = n;
		this.cityMap = cityMap;
		return searchFrom(0, 0, true, true) % MOD;
	}

	public int searchFrom(int i, int j, boolean canDown, boolean canRight) {
		// base case
		if (i == m - 1 && j == n - 1) {
			return 1;
		}

		if (i == m - 1) {
			canDown = false;
		}
		if (j == n - 1) {
			canRight = false;
		}

		if (i >= m || j >= n) {
			return 0;
		}

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

		return rightPath + downPath;
	}
}