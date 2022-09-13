package Sudoku;

public abstract class AbstractSudoSolve {
	
	protected Sudoku sudoku;
	
	/**
	 * Instancie un <b>AbstractSudoSolve</b> qui va permettre de r�soudre 
	 * la grille du Sudoku pass� en param�tre
	 * 
	 * @param sudoku le sudoku
	 *
	 */
	public AbstractSudoSolve(Sudoku sudoku) {
		this.sudoku = sudoku;
	}
	
	
	/**
	 * R�sout la grille du Sudoku
	 *
	 */
	public abstract void solve();

	
}
