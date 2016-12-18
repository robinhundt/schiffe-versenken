import java.io.*;
import java.net.*;

public class SVClient {

	private static final int GRID_SIZE = 10;


	/*private static boolean[][] initializeBoard(int[] shipPos) {
		boolean[][] grid = new boolean[GRID_SIZE][GRID_SIZE];
		for (int k=0; k<shipPos.length; k= k+2) {
			grid[shipPos[k]][shipPos[k+1]] = true;
			System.err.println(shipPos[k]);
		}

		return grid;
	}*/

	private static boolean[][] getShipsPos(Grid gameGrid) {
		boolean[][] shipsPos = new boolean[GRID_SIZE][GRID_SIZE];
		String input;
		int leftX;
		int leftY;
		int rightX;
		int rightY;
		boolean schlachter = false;
		int kreuzer = 0;
		int zerstoerer = 0;
		int uboote = 0;

		while (!schlachter) {
			System.out.println("Gebe die Postition deines Schlachtschiffes ein (5 Kästchen).");
			input = StdIn.readLine();
			if (input.length() == 8) {
				leftX = Integer.parseInt(input.substring(0, 1));
				leftY = Integer.parseInt(input.substring(1, 2));
				rightX = Integer.parseInt(input.substring(6, 7));
				rightY = Integer.parseInt(input.substring(7, 8));
				if (leftX == rightX && input.length() == 8) {
					if (leftY == rightY-4 || leftY == rightY+4) {
						schlachter = true;
						if (leftY > rightY) {
							for (int i=0; i<=4; i++) {
								shipsPos[leftX][rightY+i] = true;
							}
							gameGrid.printShips(shipsPos);
						} else {
							for (int i=0; i<=4; i++) {
								shipsPos[leftX][leftY+i] = true;
							}
							gameGrid.printShips(shipsPos);
						}

					}
				} else if(leftY == rightY && input.length() == 8) {
					if (leftX == rightX-4 || leftY == rightX+4) {
						schlachter = true;
						if (leftX > rightX) {
							for (int i=0; i<=4; i++) {
								shipsPos[rightX+i][rightY] = true;
							}
							gameGrid.printShips(shipsPos);
						} else {
							for (int i=0; i<=4; i++) {
								shipsPos[leftX+i][rightY] = true;
							}
							gameGrid.printShips(shipsPos);
						}

					}
				} else {
					System.out.println("Flasche Eingabe der Schlachtschiffposition.");
				} 
			} else {
				System.out.println("Falsche Koordinateneingabe");
			}
		}
		while (kreuzer < 2) {
			System.out.println("Gebe die Postition deines Kreuzers ein (4 Kästchen).");
			input = StdIn.readLine();
			if (input.length() == 8) {
				leftX = Integer.parseInt(input.substring(0, 1));
				leftY = Integer.parseInt(input.substring(1, 2));
				rightX = Integer.parseInt(input.substring(6, 7));
				rightY = Integer.parseInt(input.substring(7, 8));
				if (leftX == rightX) {
					if (leftY == rightY-3 || leftY == rightY+3) {
						kreuzer++;
						if (leftY > rightY) {
							for (int i=0; i<=3; i++) {
								shipsPos[leftX][rightY+i] = true;
							}
							gameGrid.printShips(shipsPos);
						} else {
							for (int i=0; i<=3; i++) {
								shipsPos[leftX][leftY+i] = true;
							}
							gameGrid.printShips(shipsPos);
						}

					}
				} else if(leftY == rightY) {
					if (leftX == rightX-3 || leftY == rightX+3) {
						kreuzer++;
						if (leftX > rightX) {
							for (int i=0; i<=3; i++) {
								shipsPos[rightX+i][rightY] = true;
							}
							gameGrid.printShips(shipsPos);
						} else {
							for (int i=0; i<=3; i++) {
								shipsPos[leftX+i][rightY] = true;
							}
							gameGrid.printShips(shipsPos);
						}

					}
				} else {
					System.out.println("Flasche Eingabe der Kreuzerposition.");
				}
			} else {
				System.out.println("Falsche Koordinateneingabe");
			}
		}
		while (zerstoerer < 3) {
			System.out.println("Gebe die Postition deines Zerstoerers ein (3 Kästchen).");
			input = StdIn.readLine();
			if (input.length() == 8) {
				leftX = Integer.parseInt(input.substring(0, 1));
				leftY = Integer.parseInt(input.substring(1, 2));
				rightX = Integer.parseInt(input.substring(6, 7));
				rightY = Integer.parseInt(input.substring(7, 8));
				if (leftX == rightX && input.length() == 8) {
					if (leftY == rightY-2 || leftY == rightY+2) {
						zerstoerer++;
						if (leftY > rightY) {
							for (int i=0; i<=2; i++) {
								shipsPos[leftX][rightY+i] = true;
							}
							gameGrid.printShips(shipsPos);
						} else {
							for (int i=0; i<=2; i++) {
								shipsPos[leftX][leftY+i] = true;
							}
							gameGrid.printShips(shipsPos);
						}

					}
				} else if(leftY == rightY && input.length() == 8) {
					if (leftX == rightX-2 || leftY == rightX+2) {
						zerstoerer++;
						if (leftX > rightX) {
							for (int i=0; i<=2; i++) {
								shipsPos[rightX+i][rightY] = true;
							}
							gameGrid.printShips(shipsPos);
						} else {
							for (int i=0; i<=2; i++) {
								shipsPos[leftX+i][rightY] = true;
							}
							gameGrid.printShips(shipsPos);
						}

					}
				} else {
					System.out.println("Flasche Eingabe der Zerstoererposition.");
				}
			} else {
				System.out.println("Falsche Koordinateneingabe");
			}
		}
		while (uboote < 4) {
			System.out.println("Gebe die Postition deines Kreuzers ein (4 Kästchen).");
			input = StdIn.readLine();
			if (input.length() == 8) {
				leftX = Integer.parseInt(input.substring(0, 1));
				leftY = Integer.parseInt(input.substring(1, 2));
				rightX = Integer.parseInt(input.substring(6, 7));
				rightY = Integer.parseInt(input.substring(7, 8));
				if (leftX == rightX && input.length() == 8) {
					if (leftY == rightY-1 || leftY == rightY+1) {
						uboote++;
						if (leftY > rightY) {
							for (int i=0; i<=1; i++) {
								shipsPos[leftX][rightY+i] = true;
							}
							gameGrid.printShips(shipsPos);
						} else {
							for (int i=0; i<=1; i++) {
								shipsPos[leftX][leftY+i] = true;
							}
							gameGrid.printShips(shipsPos);
						}

					}
				} else if(leftY == rightY && input.length() == 8) {
					if (leftX == rightX-1 || leftY == rightX+1) {
						uboote++;
						if (leftX > rightX) {
							for (int i=0; i<=1; i++) {
								shipsPos[rightX+i][rightY] = true;
							}
							gameGrid.printShips(shipsPos);
						} else {
							for (int i=0; i<=1; i++) {
								shipsPos[leftX+i][rightY] = true;
							}
							gameGrid.printShips(shipsPos);
						}

					}
				} else {
					System.out.println("Flasche Eingabe der Schlachtschiffposition.");
				}
			} else {
				System.out.println("Falsche Koordinateneingabe");
			}
		}
		return shipsPos;
	}

	public static void main(String[] args) {
		int[] shipPos = new int[2];
		String hostName = "";
		int portNumber = -1;
		boolean[][] ships;
		boolean gotHit;
		boolean isPlayOneTurn = true;
		int shotAtx = -1;
		int shotAty = -1;

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

				// reads in Ship positions and displays them on the grid
				/*System.out.println("Position der Schiffe eingeben:");
				for (int i=0; i<shipPos.length; i++)
					shipPos[i] = Integer.parseInt(stdIn.readLine()) -1;	//-1 da Feld 1 1 im Array 0 0 abgespeichert wird
				ships = initializeBoard(shipPos);*/
				ships = getShipsPos(gameGrid);
				//gameGrid.printShips(ships); 

				while (true) {
					if (stdIn.ready() == true && isPlayOneTurn == false) {
						fromPlayer = stdIn.readLine();
						System.out.println("You: " + fromPlayer);
						shotAtx = Integer.parseInt(fromPlayer.substring(0,1));
						shotAty = Integer.parseInt(fromPlayer.substring(2,3));
						gameGrid.printOwnShot(shotAtx, shotAty, false);
						out.println(fromPlayer);
						isPlayOneTurn = true;
					}
					if (in.ready() == true) {
						fromEnemy = in.readLine();
						if (fromEnemy.equals("EXIT")) {
							System.out.println("User disconnected.");
							break;
						} else if(fromEnemy.equals("HIT")) {
							gameGrid.printOwnShot(shotAtx, shotAty, true);

						} else if (!fromEnemy.equals("")) {
							int enemyShotAtx = Integer.parseInt(fromEnemy.substring(0,1));
							int enemyShotAty = Integer.parseInt(fromEnemy.substring(2,3));
							System.out.println("Enemy: " + fromEnemy);
							gotHit = gameGrid.printEnemyShot(ships, enemyShotAtx, enemyShotAty);
							if (gotHit)
								out.println("HIT");
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

				// reads in Ship positions and displays them on the grid
				/*System.out.println("Position der Schiffe eingeben:");
				for (int i=0; i<shipPos.length; i++)
					shipPos[i] = Integer.parseInt(stdIn.readLine()) -1;	//-1 da Feld 1 1 im Array 0 0 abgespeichert wird
				ships = initializeBoard(shipPos);*/
				ships = getShipsPos(gameGrid);
				//gameGrid.printShips(ships); 

				while (true) {
					if (stdIn.ready() == true && isPlayOneTurn == true) {
						fromPlayer = stdIn.readLine();
						System.out.println("You: " + fromPlayer);
						shotAtx = Integer.parseInt(fromPlayer.substring(0,1));
						shotAty = Integer.parseInt(fromPlayer.substring(2,3));
						gameGrid.printOwnShot(shotAtx, shotAty, false);
						out.println(fromPlayer);
						isPlayOneTurn = false;
					}
					if (in.ready() == true) {
						fromEnemy = in.readLine();
						if (fromEnemy.equals("EXIT")) {
							System.out.println("User disconnected.");
							break;
						} else if(fromEnemy.equals("HIT")) {
							gameGrid.printOwnShot(shotAtx, shotAty, true);

						} else if (!fromEnemy.equals("")) {
							int enemyShotAtx = Integer.parseInt(fromEnemy.substring(0,1));
							int enemyShotAty = Integer.parseInt(fromEnemy.substring(2,3));
							System.out.println("Enemy: " + fromEnemy);
							gotHit = gameGrid.printEnemyShot(ships, enemyShotAtx, enemyShotAty);
							if (gotHit)
								out.println("HIT");
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
