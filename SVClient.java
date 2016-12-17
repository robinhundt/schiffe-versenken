import java.io.*;
import java.net.*;

public class SVClient {

	private static final int GRID_SIZE = 10;


	private static boolean[][] initializeBoard(int[] shipPos) {
		boolean[][] grid = new boolean[GRID_SIZE][GRID_SIZE];
		for (int k=0; k<shipPos.length; k= k+2) {
			grid[shipPos[k]][shipPos[k+1]] = true;
			System.err.println(shipPos[k]);
		}

		return grid;
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

		/*if (args.length != 2) {
			System.err.println(
					"Usage: java EchoClient <host name> <port number>");
			System.exit(1);
		}*/

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
				Grid gameGrid = new Grid("Player 1");

				// reads in Ship positions and displays them on the grid
				System.out.println("Position der Schiffe eingeben:");
				for (int i=0; i<shipPos.length; i++)
					shipPos[i] = Integer.parseInt(stdIn.readLine()) -1;	//-1 da Feld 1 1 im Array 0 0 abgespeichert wird
				ships = initializeBoard(shipPos);
				gameGrid.printShips(ships); 

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
				Grid gameGrid = new Grid("Player 2");

				// reads in Ship positions and displays them on the grid
				System.out.println("Position der Schiffe eingeben:");
				for (int i=0; i<shipPos.length; i++)
					shipPos[i] = Integer.parseInt(stdIn.readLine()) -1;	//-1 da Feld 1 1 im Array 0 0 abgespeichert wird
				ships = initializeBoard(shipPos);
				gameGrid.printShips(ships); 

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
