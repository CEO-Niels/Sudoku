package InOut;

import java.io.FileNotFoundException;
import SudoSolve.SudoSolveBT;
import Sudoku.Sudoku;

public class Console {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		Sudoku s = new Sudoku();
		s.setSolver(new SudoSolveBT(s));

			System.out.println(s);
			long debut = System.nanoTime() ; 
	
			
	
	
			
			
			// Resolution du Sudoku
			s.solve();


			long fin = System.nanoTime();
		
			System.out.println("Méthode exécutée en " +(fin - debut)/1000000f + " millisecondes");
//		}
	}

}
