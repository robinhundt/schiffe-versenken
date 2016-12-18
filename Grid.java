public class Grid {

	public void printShips(boolean ships[][]) {
		//System.out.println("Printing Ship");
		for (int i=0; i<=9; i++) {
			for (int j=0; j<=9; j++) {
				if (ships[i][j] == true)
					StdDraw.filledSquare(i+0.5, 9.5-j, 0.5);
			}
		}
	}
	public boolean printEnemyShot(boolean ships[][], int x, int y) {
		if (ships[x][y] == true) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledSquare(x+0.5, 9.5-y, 0.5);
			StdDraw.setPenColor(StdDraw.BLACK);
			return true;
		} else {
			StdDraw.setPenColor(StdDraw.GRAY);
			StdDraw.filledSquare(x+0.5, 9.5-y, 0.5);
			StdDraw.setPenColor(StdDraw.BLACK);
			return false;
		}

	}

	public void printOwnShot(int x, int y, boolean hit) {
		if (hit == false) {
			StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
			StdDraw.filledSquare(x+12.5, 9.5-y, 0.5);
			StdDraw.setPenColor(StdDraw.BLACK);
		}
		if (hit == true) {
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledSquare(x+12.5, 9.5-y, 0.5);
			StdDraw.setPenColor(StdDraw.BLACK);
		}

	}

	public Grid(String player) {
		StdDraw.setCanvasSize((int)(512*(26.0/15)), 512);
		StdDraw.setXscale(-2,26);
		StdDraw.setYscale(-2,15);

		StdDraw.text(5,14,"Schiffe versenken!");
		StdDraw.text(5,13,player);

		for (int i=0; i<= 10; i++) {
			if (i<10) {
				StdDraw.text(i+0.5, 10.5, String.valueOf(i));
				StdDraw.text(-0.5, 9.5-i, String.valueOf(i));
			}
			StdDraw.line(0,i,10,i);
			StdDraw.line(i,0,i,10);
		}
		for (int i=10; i<= 20; i++) {
			if (i<20) {
				StdDraw.text(i+2.5, 10.5, String.valueOf(i-10));
				StdDraw.text(11.5, 19.5-i, String.valueOf(i-10));
			}
			StdDraw.line(12,i-10,22,i-10);
			StdDraw.line(i+2,0,i+2,10);
		}

	}
}
