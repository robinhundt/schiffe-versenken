import java.io.*;
import java.net.*;

public class SVClient {

	private static final int GRID_SIZE = 10;

	private static void placeShip(boolean[][] shipsPos, int shipLength, int leftX, int leftY, int rightX, int rightY) {
		if (leftX > rightX) {
			for (int i=0; i<shipLength; i++) {
				shipsPos[rightX+i][rightY] = true;
			}
		} else if (rightX > leftX) {
			for (int i=0; i<shipLength; i++) {
				shipsPos[leftX+i][rightY] = true;
			}
		} else if (leftY > rightY) {
			for (int i=0; i<shipLength; i++) {
				shipsPos[leftX][rightY+i] = true;
			}
		} else if(rightY > leftY) {
			for (int i=0; i<shipLength; i++) {
				shipsPos[leftX][leftY+i] = true;
			}
		}
	}

	private static boolean legalPos(boolean[][] shipsPos, int shipLength, int leftX, int leftY, int rightX, int rightY) {
		boolean legal = true;
		if (leftX > rightX) {
			for (int i=-1; i<=shipLength; i++) {
				if (shipsPos[rightX+i][rightY] == true || shipsPos[rightX+i][rightY-1] == true || shipsPos[rightX+i][rightY+1] == true)
					legal = false;
			}
		} else if (rightX > leftX) {
			for (int i=-1; i<=shipLength; i++) {
				if (shipsPos[leftX+i][rightY] == true || shipsPos[leftX+i][rightY-1] == true || shipsPos[leftX+i][rightY+1] == true)
					legal = false;
			}
		} else if (leftY > rightY) {
			for (int i=-1; i<=shipLength; i++) {
				if (shipsPos[leftX][rightY+i] == true || shipsPos[leftX-1][rightY+i] == true || shipsPos[leftX+1][rightY+i] == true)
					legal = false;
			}
		} else if(rightY > leftY) {
			for (int i=-1; i<=shipLength; i++) {
				if (shipsPos[leftX][leftY+i] == true || shipsPos[leftX-1][leftY+i] == true || shipsPos[leftX+1][leftY+i] == true)
					legal = false;
			}
		}
		return legal;
	}
	private static boolean[][] getShipsPos(Grid gameGrid, BufferedReader stdIn) {
		boolean[][] shipsPos = new boolean[GRID_SIZE+2][GRID_SIZE+2];
		String input;
		int leftX;
		int leftY;
		int rightX;
		int rightY;
		boolean schlachter = false;
		int kreuzer = 0;
		int zerstoerer = 0;
		int uboote = 0;

		try {
			while (!schlachter) {
				System.out.println("Gebe die Postition deines Schlachtschiffes ein (5 Kästchen).");
				input = stdIn.readLine();
				if (input.length() == 8) {
					leftX = Integer.parseInt(input.substring(0, 1))+1;
					leftY = Integer.parseInt(input.substring(1, 2))+1;
					rightX = Integer.parseInt(input.substring(6, 7))+1;
					rightY = Integer.parseInt(input.substring(7, 8))+1;
					if ((leftY == rightY || leftX == rightX) && leftX<=10 && leftY>=0 && rightX<=10 && rightY>=0) {
						if (leftY == rightY-4 || leftY == rightY+4 || leftX == rightX-4 || leftY == rightX+4) {
							if (legalPos(shipsPos, 5, leftX, leftY, rightX, rightY)){
								placeShip(shipsPos, 5, leftX, leftY, rightX, rightY);
								schlachter = true;
								gameGrid.printShips(shipsPos);
							}
						}
					} else {
						System.out.println("Falsche Eingabe der Schlachtschiffposition.");
					} 
				} else {
					System.out.println("Falsche Koordinateneingabe");
				}
			}
			while (kreuzer < 2) {
				System.out.println("Gebe die Postition deines Kreuzers ein (4 Kästchen).");
				input = stdIn.readLine();
				if (input.length() == 8) {
					leftX = Integer.parseInt(input.substring(0, 1))+1;
					leftY = Integer.parseInt(input.substring(1, 2))+1;
					rightX = Integer.parseInt(input.substring(6, 7))+1;
					rightY = Integer.parseInt(input.substring(7, 8))+1;
					if ((leftX == rightX || leftY == rightY) && leftX<=10 && leftY>=0 && rightX<=10 && rightY>=0) {
						if (leftY == rightY-3 || leftY == rightY+3 || leftX == rightX-3 || leftY == rightX+3) {
							if (legalPos(shipsPos, 4, leftX, leftY, rightX, rightY)){
								placeShip(shipsPos, 4, leftX, leftY, rightX, rightY);
								kreuzer++;
								gameGrid.printShips(shipsPos);
							}
						}
					} else {
						System.out.println("Falsche Eingabe der Kreuzerposition.");
					}
				} else {
					System.out.println("Falsche Koordinateneingabe");
				}
			}
			while (zerstoerer < 3) {
				System.out.println("Gebe die Postition deines Zerstoerers ein (3 Kästchen).");
				input = stdIn.readLine();
				if (input.length() == 8) {
					leftX = Integer.parseInt(input.substring(0, 1))+1;
					leftY = Integer.parseInt(input.substring(1, 2))+1;
					rightX = Integer.parseInt(input.substring(6, 7))+1;
					rightY = Integer.parseInt(input.substring(7, 8))+1;
					if ((leftX == rightX || leftY == rightY) && leftX<=10 && leftY>=0 && rightX<=10 && rightY>=0) {
						if (leftY == rightY-2 || leftY == rightY+2 || leftX == rightX-2 || leftY == rightX+2) {
							if (legalPos(shipsPos, 3, leftX, leftY, rightX, rightY)){
								placeShip(shipsPos, 3, leftX, leftY, rightX, rightY);
								zerstoerer++;
								gameGrid.printShips(shipsPos);
							}
						}
					} else {
						System.out.println("Falsche Eingabe der Zerstoererposition.");
					}
				} else {
					System.out.println("Falsche Koordinateneingabe");
				}
			}
			while (uboote < 4) {
				System.out.println("Gebe die Postition deines U-Bootes ein (4 Kästchen).");
				input = stdIn.readLine();
				if (input.length() == 8) {
					leftX = Integer.parseInt(input.substring(0, 1))+1;
					leftY = Integer.parseInt(input.substring(1, 2))+1;
					rightX = Integer.parseInt(input.substring(6, 7))+1;
					rightY = Integer.parseInt(input.substring(7, 8))+1;
					if ((leftX == rightX || leftY == rightY) && leftX<=10 && leftY>=0 && rightX<=10 && rightY>=0) {
						if (leftY == rightY-1 || leftY == rightY+1 || leftX == rightX-1 || leftY == rightX+1) {
							if (legalPos(shipsPos, 2, leftX, leftY, rightX, rightY)){
								placeShip(shipsPos, 2, leftX, leftY, rightX, rightY);
								uboote++;
								gameGrid.printShips(shipsPos);
							}
						}
					} else {
						System.out.println("Falsche Eingabe der U-Bootposition.");
					}
				} else {
					System.out.println("Falsche Koordinateneingabe");
				}
			}

		} catch (IOException e) {

		}
		return shipsPos;
	}

	private static boolean enemyWon(boolean ships[][]) {
		boolean win = true;
		for (int i=0; i < GRID_SIZE; i++) {
			for (int j=0; j < GRID_SIZE; j++) {
				if (ships[i][j] == true)
					win = false;
			}
		}
		return win;
	}

	public static void main(String[] args) {
		String hostName = "";
		int portNumber = -1;
		boolean[][] ships;
		boolean gotHit;
		boolean isPlayOneTurn = true;
		int shotAtx = -1;
		int shotAty = -1;
		boolean gameOver = false;

		if (args.length != 2 && args.length != 1) {
			System.err.println(
					"Usage: 'java SVClient <port number>' to start a sever or 'java SVClient <hostname> <port number>'" +
					" to connect to a server");
			System.exit(1);
		}

		if (args.length == 1)
			portNumber = Integer.parseInt(args[0]);
		else if (args.length == 2) {
			hostName = args[0];
			portNumber = Integer.parseInt(args[1]);
		}

		if (args.length == 2) {
			try (

					Socket SVSocket = new Socket(hostName, portNumber);
					PrintWriter out = new PrintWriter(SVSocket.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(SVSocket.getInputStream()));
					BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
					) {

				String fromEnemy;
				String fromPlayer;
				Grid gameGrid = new Grid("Player 2");

				ships = getShipsPos(gameGrid, stdIn);

				while (!gameOver) {
					if (stdIn.ready() == true && isPlayOneTurn == false) {
						fromPlayer = stdIn.readLine();
						shotAtx = Integer.parseInt(fromPlayer.substring(0,1));
						shotAty = Integer.parseInt(fromPlayer.substring(2,3));
						if (shotAtx<10 && shotAtx>=0 && shotAty<10 && shotAty>=0) {
							gameGrid.printOwnShot(shotAtx, shotAty, false);
							System.out.println("Shooting at: " + fromPlayer);
							out.println(fromPlayer);
							isPlayOneTurn = true;
						} else {
							System.out.println("Falsche Koordinateneingabe");
						}
					}
					if (in.ready() == true) {
						fromEnemy = in.readLine();
						if (fromEnemy.equals("EXIT")) {
							System.out.println("User disconnected.");
							break;
						} else if(fromEnemy.equals("HIT")) {
							gameGrid.printOwnShot(shotAtx, shotAty, true);
						} else if (fromEnemy.equals("GAME OVER")) {
							System.out.println("YOU WON.");
							gameOver = true;
						} else if (!fromEnemy.equals("")) {
							int enemyShotAtx = Integer.parseInt(fromEnemy.substring(0,1))+1;
							int enemyShotAty = Integer.parseInt(fromEnemy.substring(2,3))+1;
							System.out.println("Enemy: " + fromEnemy);
							gotHit = gameGrid.printEnemyShot(ships, enemyShotAtx, enemyShotAty);
							if (gotHit) {
								out.println("HIT");
								ships[enemyShotAtx][enemyShotAty] = false;
								if (enemyWon(ships)) {
									System.out.println("GAME OVER. YOU LOST.");
									out.println("GAME OVER");
									gameOver = true;
								}
							}
							isPlayOneTurn = false;
						}
					}

				}
			} catch (UnknownHostException e) {
				System.err.println("Don't know about host " + hostName);
				System.exit(1);
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for the connection to " +
						hostName);
				System.exit(1);
			}

		} else if (args.length == 1) {
			try (
					ServerSocket SVServer = new ServerSocket(portNumber);
					Socket SVClient = SVServer.accept();
					PrintWriter out = new PrintWriter(SVClient.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(SVClient.getInputStream()));
					BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

					) {

				String fromEnemy;
				String fromPlayer;
				Grid gameGrid = new Grid("Player 1");

				ships = getShipsPos(gameGrid, stdIn);

				while (!gameOver) {
					if (stdIn.ready() == true && isPlayOneTurn == true) {
						fromPlayer = stdIn.readLine();
						shotAtx = Integer.parseInt(fromPlayer.substring(0,1));
						shotAty = Integer.parseInt(fromPlayer.substring(2,3));
						if (shotAtx<10 && shotAtx>=0 && shotAty<10 && shotAty>=0) {
							gameGrid.printOwnShot(shotAtx, shotAty, false);
							System.out.println("Shooting at: " + fromPlayer);
							out.println(fromPlayer);
							isPlayOneTurn = true;
						} else {
							System.out.println("Falsche Koordinateneingabe");
						}					}
					if (in.ready() == true) {
						fromEnemy = in.readLine();
						if (fromEnemy.equals("EXIT")) {
							System.out.println("User disconnected.");
							break;
						} else if(fromEnemy.equals("HIT")) {
							gameGrid.printOwnShot(shotAtx, shotAty, true);
						} else if (fromEnemy.equals("GAME OVER")) {
							System.out.println("YOU WON.");
							gameOver = true;
						} else if (!fromEnemy.equals("")) {
							int enemyShotAtx = Integer.parseInt(fromEnemy.substring(0,1))+1;
							int enemyShotAty = Integer.parseInt(fromEnemy.substring(2,3))+1;
							System.out.println("Enemy: " + fromEnemy);
							gotHit = gameGrid.printEnemyShot(ships, enemyShotAtx, enemyShotAty);
							if (gotHit) {
								out.println("HIT");
								ships[enemyShotAtx][enemyShotAty] = false;
								if (enemyWon(ships)) {
									System.out.println("GAME OVER. YOU LOST.");
									out.println("GAME OVER");
									gameOver = true;
								}
							}
							isPlayOneTurn = true;
						}
					}

				}

			} catch (UnknownHostException e) {
				System.err.println("Don't know about host " + hostName);
				System.exit(1);
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for the connection to " +
						hostName);
				System.exit(1);
			}
		}

	}
}
