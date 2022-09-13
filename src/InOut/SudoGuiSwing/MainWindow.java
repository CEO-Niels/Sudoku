package InOut.SudoGuiSwing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

import SudoSolve.SudoSolveBT;
import Sudoku.Sudoku;

public class MainWindow extends JPanel implements ActionListener
{
	private CaseButton[][] board = new CaseButton[Sudoku.NB_ROW][Sudoku.NB_COL];
	private JFrame fenetre;
	private Menu menu;
	private Sudoku sudoku;
    
	
	/**
	 * Construit l'application Sudoku
	 * @param args Ignored
	 */
	public static void main(String[] args)
	{
		new MainWindow();
	}
	
	/**
	 * Instancie la fenêtre, la grille de Sudoku, le generateur et le
	 * solver du Sudoku
	 */
	public MainWindow()
	{
		initBoard();
		sudoku = new Sudoku();
		sudoku.setSolver(new SudoSolveBT(sudoku));
		
		fenetre = new JFrame("Sudoku");
		menu = new Menu(this);
		fenetre.setJMenuBar(menu.createMenuBar());
		fenetre.getContentPane().add(this);
		
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setSize(500, 530);
		fenetre.setResizable(false);
		fenetre.setVisible(true);	
		
		GridLayout grid = new GridLayout(Sudoku.NB_ROW, Sudoku.NB_COL); 
		grid.setVgap(2);
		grid.setHgap(2);

		this.setLayout(grid);

	}
	
	
	
	
	/**
	 * actionPerformed est nécessaire puisque la classe implémente ActionListener.
	 * Cette méthode gère les évenements liés au clic sur une case du Sudoku.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() instanceof CaseButton)
		{
			CaseButton temp = (CaseButton)e.getSource();
			String in = JOptionPane.showInputDialog(null);

			try
			{	
				int oldValue = temp.getValue();
				temp.setValue(Integer.parseInt(in));
				sudoku.setValue(temp.getRow(), temp.getCol(), Integer.parseInt(in));
				
				if(!sudoku.isValid()) {
					JOptionPane.showMessageDialog(this, "Valeur incorrecte", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					sudoku.setValue(temp.getRow(), temp.getCol(), oldValue);
					temp.setValue(oldValue);
				}
					
			}
			catch (NumberFormatException err)
			{

			}
		}
	}
	
	/**
	 * Efface la grille de sudoku
	 */
	public void clear()
	{
		sudoku.reset();
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++)
				board[row][col].clear();

	}

	/**
	 * Copie les valeurs de la grille de Sudoku dans le tableau de CaseButton 
	 * 
	 */
	private void sudokuToBoard() {
		for (int i = 0; i < Sudoku.NB_ROW; i++) {
			for (int j = 0; j < Sudoku.NB_COL; j++) {
				board[i][j].setValue(sudoku.getValue(i, j));
			}
		}
	}
	
	/**
	 * Permet de quitter l'application
	 */
	public void quit()
	{
		fenetre.dispose();
	}

	
	/**
	 * Permet d'initialiser la liste de CaseButton
	 */
	private void initBoard()
	{
		Font font = new Font("Ariel", Font.PLAIN, 31);
		CaseButton caseButton;
		
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				caseButton = new CaseButton(row, col);
				caseButton.setBackground(Color.white);
				
				if (row <= 2 && (col <= 2 || col > 5)){
					caseButton.setBackground (Color.gray);
				}
				if (row >2 && row <= 5 &&  col > 2 && col  <= 5){
					caseButton.setBackground (Color.gray);
				}
				if (row > 5 && ((col <= 2) || (col > 5))){
					caseButton.setBackground (Color.gray);
				}
				
				caseButton.setValue(0);
				caseButton.addActionListener(this);
				caseButton.setFont(font);
				caseButton.setForeground(Color.black);
				caseButton.setFocusable(false);
				
				add(caseButton);
				board[row][col] = caseButton;
			}
		}
	}

	/**
	 * Permet de résoudre le sudoku
	 */
	public void solve() {
		sudoku.solve();
		sudokuToBoard();
		
	}
}
