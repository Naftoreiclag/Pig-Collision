package naftoreiclag.pigcollision;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel
{
	Space space;
	Circle mainCircle;
	Circle mainCircle2;
	Circle orbitCircle;

	double cirleSpd = 3;
	boolean mouseDown;
	int mouseX;
	int mouseY;
	
	public MainPanel()
	{
		this.setSize(500, 500);
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		space = new Space();
		
		mainCircle = new Circle(250, 250, 20, 5, 5);
		space.circles.add(mainCircle);
		mainCircle2 = new Circle(300, 250, 15, 5, 5);
		space.circles.add(mainCircle2);
		orbitCircle = new Circle(300, 100, 30, 5, 5);
		space.circles.add(orbitCircle);
		
		space.circles.add(new Circle(300, 350, 20, 5, -1));
		
		// Box
		space.lines.add(new Line(50, 50, 200, 50));
		space.lines.add(new Line(200, 50, 200, 200));
		space.lines.add(new Line(200, 200, 50, 200));
		space.lines.add(new Line(50, 200, 50, 50));
		
		// Concave test
		// right-ish angle
		space.lines.add(new Line(57, 328, 102, 373));
		space.lines.add(new Line(102, 373, 147, 329));
		// obtuse
		space.lines.add(new Line(187, 363, 269, 396));
		space.lines.add(new Line(269, 396, 333, 358));
		// acute
		space.lines.add(new Line(379, 302, 403, 397));
		space.lines.add(new Line(403, 397, 425, 282));
		// ???
		space.lines.add(new Line(91, 413, 254, 443));
		space.lines.add(new Line(75, 476, 194, 449));
		
		(new Thread()
		{
			double lastTick = System.currentTimeMillis();
			
			@Override
			public void run()
			{
				while(true)
				{
					if(System.currentTimeMillis() > lastTick + 10d)
					{
						lastTick = System.currentTimeMillis();
				        space.simulate(1);
				        mMove(null);
				        // System.out.println(lastTick);
						repaint();
					}
				}
		    }
		}).start();
		
		
		this.addMouseMotionListener(new MouseMotionListener()
		{
			@Override
			public void mouseDragged(MouseEvent e){ mMove(e); }
			@Override
			public void mouseMoved(MouseEvent e){ mMove(e); }
		});
		
		this.addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent e) { kPress(e); }
			@Override
			public void keyReleased(KeyEvent e) { kRelease(e); }
			@Override
			public void keyTyped(KeyEvent e) { }
		});
		
		this.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) { }
			@Override
			public void mouseEntered(MouseEvent e) { }
			@Override
			public void mouseExited(MouseEvent e) { }
			@Override
			public void mousePressed(MouseEvent e) { mPress(e); }
			@Override
			public void mouseReleased(MouseEvent e) { mRelease(e); }
		});
	}
	private void mMove(MouseEvent e)
	{
		if(e != null)
		{
			mouseX = e.getX();
			mouseY = e.getY();
		}
		
		if(mouseDown)
		{
			Vector2d go = new Vector2d(mouseX, mouseY);
			go.subtractLocal(mainCircle.loc);
			
			mainCircle.velocity = go.normalizeLocal().multiplyLocal(cirleSpd);
		}
	}
	private void mPress(MouseEvent e)
	{
		mouseDown = true;
		
		Vector2d go = new Vector2d(e.getX(), e.getY());
		go.subtractLocal(mainCircle.loc);
		
		mainCircle.velocity = go.normalizeLocal().multiplyLocal(cirleSpd);
	
		this.repaint();
	}
	private void mRelease(MouseEvent e)
	{
		mouseDown = false;
		
		mainCircle.velocity.a = 0.0d;
		mainCircle.velocity.b = 0.0d;
	}
	private void kPress(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == 37)
		{
			mainCircle.velocity.a = -cirleSpd;
		}
		else if(key == 39)
		{
			mainCircle.velocity.a = cirleSpd;
		}
		else if(key == 38)
		{
			mainCircle.velocity.b = -cirleSpd;
		}
		else if(key == 40)
		{
			mainCircle.velocity.b = cirleSpd;
		}
	}
	private void kRelease(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == 37 || key == 39)
		{
			mainCircle.velocity.a = 0;
		}
		else if(key == 38 || key == 40)
		{
			mainCircle.velocity.b = 0;
		}
	}
	//private void k
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.WHITE);

		g2.fillRect(0, 0, 500, 500);
		
		g2.setColor(Color.RED);
		
		//g2.drawOval((int) (mx - mainCircle.rad), (int) (my - mainCircle.rad), (int) mainCircle.rad * 2, (int) mainCircle.rad * 2);

		g2.setColor(Color.BLACK);
		
		for(Circle c : space.circles)
		{
			g2.drawOval((int) (c.loc.a - c.rad), (int) (c.loc.b - c.rad), (int) c.rad * 2, (int) c.rad * 2);
		}
		
		for(Line l : space.lines)
		{
			g2.drawLine((int) l.a.a, (int) l.a.b, (int) l.b.a, (int) l.b.b);
		}
	}
}
