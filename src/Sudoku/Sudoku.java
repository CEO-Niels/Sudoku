package Sudoku;

public class Sudoku {
	
	/** Nombre de lignes. */
	public final static int NB_ROW = 9;
	
	/** Nombre de colonnes */
	public final static int NB_COL = 9;
	
	/** Taille d'un bloc */
	public final static int SIZE = 3;
	
	/** Valeur maximale */
	public final static int MAX_VALUE = 9;
	
	/** Nombre de blocs */
	public final static int NB_BLOC = 9;
	
	/** Le AbstractSudoSolve qui va permettre de résoudre le sudoku */
	private AbstractSudoSolve solver;
	
	/** Représente la grille du sudoku */
	private int grid[][];
	
	/**
	 * Instancie un <b>Sudoku</b> vide.
	 */
	public Sudoku() {
		grid = new int[NB_ROW][NB_COL];
	}

	
	/**
	 * Reset la grille de sudoku.
	 * 
	 */
	public void reset() {
		for (int i = 0; i < NB_ROW; i++) {
			for (int j = 0; j < NB_COL; j++) {
				grid[i][j] = 0;
			}
		}
	}
	
	/**
	 * Affecte le solver
	 *
	 * @param solver AbstractSudoSolve à affecter
	 */
	public void setSolver(AbstractSudoSolve solver) {
		this.solver = solver;
	}
	
	/**
	 * Permet de résoudre le sudoku
	 */
	public void solve() {
		if(solver == null)
			throw new RuntimeException("Veuillez affecter le solver");
		solver.solve();
	}
	
	/**
	 * Affecte une valeur à une case
	 *
	 * @param row la ligne de la case
	 * @param col la colonne de la case
	 * @param value la valeur à affecter
	 */
	public void setValue(int row, int col, int value) {
		if(row >= NB_ROW || row < 0 || col >= NB_COL || col < 0)
			throw new RuntimeException("Les coordonnées ne peuvent pas dépasser la grille");
		if(value < 0 || value > MAX_VALUE)
			throw new RuntimeException("La valeur doit être compris entre 1 et " + MAX_VALUE);
		
		grid[row][col] = value;
	}
	
	/**
	 * Retourne la valeur contenue dans une case
	 *
	 * @param row la ligne de la case
	 * @param col la colonne de la case
	 * 
	 * @return la valeur
	 * 
	 * @throws RuntimeException si row >= NB_ROW || row < 0 || col >= NB_COL || col < 0
	 */
	public int getValue(int row, int col) throws RuntimeException {
		if(row >= NB_ROW || row < 0 || col >= NB_COL || col < 0)
			throw new RuntimeException("Les coordonnées ne peuvent pas dépasser la grille");
		
		return grid[row][col];
	}
	
	
	/**
	 * Vérifie sur la sudoku est valide.
	 * Un sudoku est valide, s'il est possible de le résoudre
	 *
	 * @return true si valide, false sinon
	 * 
	 */
	public boolean isValid() {
		for (int i = 0; i < NB_ROW; i++) {
			for (int j = 0; j < NB_COL; j++) {
				if(getValue(i,j) != 0) {
					for (int col = 0; col < NB_COL; col++)
				        if (getValue(i,col) == getValue(i,j) && col != j)
				        	return false;
					
					for (int row = 0; row < NB_ROW; row++)
				        if (getValue(row,j) == getValue(i,j) && row != i)
				        	return false;
					
					for (int row = 0; row < SIZE; row++)
				        for (int col = 0; col < SIZE; col++)
				            if (getValue(row + i/SIZE*SIZE,col+ j/SIZE*SIZE) == getValue(i,j) && 
				            		(row + i/SIZE*SIZE != i && col+ j/SIZE*SIZE !=j) )
				                return false;
				}
			}
		}
		
		return true;
		
	}
	
	/**
	 * Vérifie si un sudoku est complet
	 * Un sudoku est complet si le sudoku n'as plus de cases vide et est valide
	 *
	 * @return true, si complet , false sinon
	 */
	public boolean isComplete() {
		for (int i = 0; i < NB_ROW; i++)
			for (int j = 0; j < NB_COL; j++)
				if(getValue(i,j) == 0)
					return false;
		
		if(!isValid())
			return false;
		
		
		return true;
		
	}
	
	
	/**
	 * Clone le sudoku : seul la grille est copié.
	 * Le solveur et le générateur sera nul
	 *
	 * @return le sudoku cloné
	 *
	 * @see java.lang.Object#clone()
	 */
	public Sudoku clone() {
		Sudoku s  = new Sudoku();
		for (int i = 0; i < NB_ROW; i++)
			for (int j = 0; j < NB_COL; j++)
				s.setValue(i,j, getValue(i,j));
		return s;
		
	}

	/**
	 * Retourne sous forme de chaine le sudoku.
	 * Permet d'afficher le sudoku sur une console par exemple
	 *
	 * @return Le sudoku sous form de chaine
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < NB_ROW; i++) {
			if(i%SIZE ==0) {
				for (int j = 0; j < NB_COL+NB_COL/2; j++)
					s += "- ";
			s+="\n";
			}
			
			for (int j = 0; j < NB_COL; j++) {
				if(j%SIZE ==0)
					s+="| ";
				s += grid[i][j] + " ";
				
			}
			s+="|\n";
		}
		for (int j = 0; j < NB_COL+NB_COL/2; j++)
			s += "- ";
		return s;
		
	}

}
