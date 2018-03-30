import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class MovingObjectsPanel extends JPanel {
	
	private Timer t;
	final static int WIDTH=1000;
	final static int HEIGHT=600;
	GameMap m = new GameMap();
	
	public MovingObjectsPanel() {
		this(new Dimension(WIDTH,HEIGHT));
	}
	public MovingObjectsPanel(Dimension dim) {
		this.setPreferredSize(dim);
		makeGameMap();
		setUpKeyMappings();
		t.start();
	}
	private void makeGameMap() {		
		//timer called every 7 ms
		t = new Timer(7, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				m.tick();
				repaint();
			}
		});
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.clearRect(0, 0, WIDTH, HEIGHT);
		m.draw(g);
	}
	private void setUpKeyMappings() {
			
		this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"shoot");
		this.getActionMap().put("shoot",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//for fireballs or shooting
			}
		});
		
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"right");
		this.getActionMap().put("right",new MoveAction(1));
		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"left");
		this.getActionMap().put("left",new MoveAction(2));
		
		this.getInputMap().put(KeyStroke.getKeyStroke("released LEFT"),"rel");
		this.getInputMap().put(KeyStroke.getKeyStroke("released RIGHT"),"rel");
		this.getActionMap().put("rel",new MoveAction(0));

		this.getInputMap().put(KeyStroke.getKeyStroke("UP"),"up");
		this.getActionMap().put("up",new MoveAction(3));
		this.requestFocusInWindow();		
	}
	//Simplifies setting keystrokes
	private class MoveAction extends AbstractAction {
		int direction;
        MoveAction(int direction) {
            this.direction = direction;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
        	m.setPlayerDir(direction);
        }
    }
}

