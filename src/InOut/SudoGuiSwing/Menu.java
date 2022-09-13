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
     * Instancie un Menu avec en paramètre un MainWindow pour permettre
     * d'intéragir avec le Sudoku
     *
     * @param gui the gui
     */
    public Menu (MainWindow gui)
    {
    	this.gui = gui;
    }

    
    
    /**
     * Créer un JMenuBar en enregistrant les évènements pour chaque item du
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
        
        menuItem = new JMenuItem("Résoudre le Sudoku");
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
     * Méthode du à l'implémentation de ActionListener.
     * Permet de gérer les évènements liés au menu, et appelle les méthodes
     * adéquats dans MainWindow
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
		case "résoudre le sudoku":
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
