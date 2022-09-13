package InOut.SudoGuiSwing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
public class Menu implements ActionListener {
    
    private final MainWindow gui;
    
    /**
     * Instancie un Menu avec en param�tre un MainWindow pour permettre
     * d'int�ragir avec le Sudoku
     *
     * @param gui the gui
     */
    public Menu (MainWindow gui)
    {
    	this.gui = gui;
    }

    
    
    /**
     * Cr�er un JMenuBar en enregistrant les �v�nements pour chaque item du
     * menu.
     *
     * @return le JMenuBar
     */
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;

        menuBar = new JMenuBar();

        menu = new JMenu("Fichier");
        menuBar.add(menu);

        menuItem = new JMenuItem("Vider le Sudoku");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();
        
        menuItem = new JMenuItem("R�soudre le Sudoku");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);

        
        menu.addSeparator();
        
        menuItem = new JMenuItem("Quitter");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);

        
        return menuBar;
    }
    
	
    /**
     * M�thode du � l'impl�mentation de ActionListener.
     * Permet de g�rer les �v�nements li�s au menu, et appelle les m�thodes
     * ad�quats dans MainWindow
     *
     */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JMenuItem source = (JMenuItem)(e.getSource());
		String cmd = source.getActionCommand().toLowerCase();
		
		switch (cmd) {
		case "vider le sudoku":
			gui.clear();
			break;
		case "r�soudre le sudoku":
			gui.solve();
			break;
		case "quitter":
			gui.quit();
			break;
		default:
			break;
		}
		
	}
}
