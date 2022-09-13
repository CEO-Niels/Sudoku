package Sudoku;

public abstract class AbstractSudoSolve {
	
	protected Sudoku sudoku;
	
	/**
	 * Instancie un <b>AbstractSudoSolve</b> qui va permettre de résoudre 
	 * la grille du Sudoku passé en paramètre
	 * 
	 * @param sudoku le sudoku
	 *
	 */
	public AbstractSudoSolve(Sudoku sudoku) {
		this.sudoku = sudoku;
	}
	
	
	/**
	 * Résout la grille du Sudoku
	 *
	 */
	public abstract void solve();

	
}
