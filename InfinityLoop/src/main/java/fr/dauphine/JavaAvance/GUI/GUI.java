package fr.dauphine.JavaAvance.GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.*;

import fr.dauphine.JavaAvance.Components.Orientation;
import fr.dauphine.JavaAvance.Components.Piece;
import fr.dauphine.JavaAvance.Components.PieceType;
import fr.dauphine.JavaAvance.Solve.Checker;

/**
 * This class handles the GUI
 * 
 *
 */
public class GUI implements ActionListener {

	private JFrame frame;
	private final int widthOfPiece = 80;
	private final int heightOfPiece = 80;
	JButton[][] places;
	Grid grid;

	/**
	 *
	 * @param inputFile String
	 * @throws NullPointerException
	 * if there is a problem with the gui
	 */
	public static void startGUI(String inputFile) throws NullPointerException {
		// We have to check that the grid is generated before to launch the GUI
		// construction
		Runnable task = new Runnable() {
			public void run() {

				try {
					Grid grid = Checker.readGrid(inputFile);
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							GUI window = null;
							try {
								window = new GUI(grid);
							} catch (MalformedURLException e) {
								e.printStackTrace();
							}
							window.frame.setVisible(true);
						}
					});
				} catch (IOException e) {
					throw new NullPointerException("Error with input file");
				}

			}
		};
		new Thread(task).start();
	}

	/**
	 * Create the application.
	 * @param grid Grid
	 * @throws MalformedURLException ImageIcon
	 */
	public GUI(Grid grid) throws MalformedURLException {
		this.grid = grid;
		frame = new JFrame("Infinity Loops");
		initialize(grid);

	}

	/**
	 *
	 * @param grid Grid
	 * @throws MalformedURLException ImageIcon
	 */
	private void initialize(Grid grid) throws MalformedURLException {
		frame.setVisible(true);
		frame.setSize(grid.getWidth() * widthOfPiece,grid.getHeight() * heightOfPiece);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		places = new JButton[grid.getHeight()][grid.getWidth()];
		JPanel panelForButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panelForButtons.setLayout(new GridLayout(grid.getHeight(), grid.getWidth()));

		for (int line = 0; line < grid.getHeight(); line++) {
			for (int column = 0; column < grid.getWidth(); column++) {
				Icon icon = new ImageIcon(this.getImageIcon(grid.getPiece(line, column)).getImage().getScaledInstance(widthOfPiece, heightOfPiece, Image.SCALE_SMOOTH));
				JButton temp = new JButton(icon);
				panelForButtons.add(temp);
				temp.addActionListener(this);
				places[line][column] = temp;

			}
		}
		frame.add(panelForButtons);
		frame.setVisible(true);
	}

	public static Grid solve( Grid grid) throws MalformedURLException {
		GUI gui = new GUI(grid);
		return gui.solveGrid(0, 0, grid);
	}

	public Grid solveGrid(int i, int j, Grid grid) throws MalformedURLException {
		boolean voidType = false;
		if (Checker.isSolved(grid)) {
			return grid;
		}
		if (i == grid.getHeight() || j == grid.getWidth()) {
			return null;
		}
		Grid grid1 = new Grid(grid);
		Grid grid2 = new Grid(grid);
		Grid grid3 = new Grid(grid);
		Grid grid4 = new Grid(grid);
		if (grid.getPiece(i, j).getType() != PieceType.VOID) {
			grid2.getPiece(i, j).turn();
			grid3.getPiece(i, j).turn();
			grid3.getPiece(i, j).turn();
			grid4.getPiece(i, j).turn();
			grid4.getPiece(i, j).turn();
			grid4.getPiece(i, j).turn();
		} else {
			voidType = true;
		}

		if (j == grid.getWidth() - 1) {
			j = 0;
			i += 1;
		} else {
			j += 1;
		}
		initialize(grid1);
		if (voidType) {
			return solveGrid(i, j, grid1);
		}
		Grid grid1New = solveGrid(i, j, grid1);
		if (grid1New != null) {
			return grid1New;
		}
		initialize(grid2);
		Grid grid2New = solveGrid(i, j, grid2);
		if (grid2New != null) {
			return grid2New;
		}
		initialize(grid3);
		Grid grid3New = solveGrid(i, j, grid3);
		if (grid3New != null) {
			return grid3New;
		}
		initialize(grid4);
		return solveGrid(i, j, grid4);
	}

	/**
	 * Display the correct image from the piece's type and orientation
	 * 
	 * @param p
	 *            the piece
	 * @return an image icon
	 */
	private ImageIcon getImageIcon(Piece p) throws MalformedURLException {
		String image = "";
		switch (p.getType()) {
			case VOID -> {
				image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/background.png";
			}
			case ONECONN -> {
				switch (p.getOrientation()) {
					case NORTH -> image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/1.png";
					case EAST -> image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/2.png";
					case SOUTH -> image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/3.png";
					case WEST -> image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/4.png";
				}
			}
			case BAR -> {
				switch (p.getOrientation()) {
					case NORTH -> image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/5.png";
					case EAST -> image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/6.png";
				}
			}
			case TTYPE -> {
				switch (p.getOrientation()) {
					case NORTH -> image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/7.png";
					case EAST -> image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/8.png";
					case SOUTH -> image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/9.png";
					case WEST -> image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/10.png";
				}
			}
			case FOURCONN -> {
				if (p.getOrientation() == Orientation.NORTH) {
					image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/11.png";
				}
			}
			case LTYPE -> {
				switch (p.getOrientation()) {
					case NORTH -> image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/12.png";
					case EAST -> image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/13.png";
					case SOUTH -> image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/14.png";
					case WEST -> image = "src/main/resources/fr/dauphine/JavaAvance/icons/io/15.png";
				}
			}
		}
		return new ImageIcon(image);
	}

	/**
	 * turn piece and change it in the gui
	 * @param e action
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < grid.getHeight(); i++) {
			for (int j = 0; j < grid.getWidth(); j++) {
				if (e.getSource() == places[i][j]) {
					grid.getPiece(i, j).turn();
					try {
						Icon icon = new ImageIcon(this.getImageIcon(grid.getPiece(i, j)).getImage().getScaledInstance(widthOfPiece, heightOfPiece, Image.SCALE_SMOOTH));
						places[i][j].setIcon(icon);
						if (Checker.isSolved(grid)) {
							JOptionPane.showMessageDialog(null, "Bravo");
						}
					} catch (MalformedURLException ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		frame.repaint();
	}
}
