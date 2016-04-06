import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import java.util.*;

public class OnScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	int width, height;
	double L;

	public OnScreen(double L, int width, int height) {
		this.width = width;
		this.height = height;
		this.L = L;
		setSize(width, height);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setLocationRelativeTo(null);
	}

	public void draw(Set<Particle> particles) {

		Graphics g = this.getGraphics();
		g.clearRect(0, 0, getWidth(), getHeight());
		Color c = Color.ORANGE;
		
		for (Particle p : particles) {
			double angle = p.getV().angle;
			
			if(0<=angle && angle<(Math.PI/2)){
				c=Color.BLUE;
			}else if((Math.PI/2)<=angle && angle<Math.PI){
				c=Color.RED;
			}else{
				c=Color.GREEN;
			}
			
			g.setColor(c);
			double x, y, x2, y2;
			double radious = 2.0;
			
			x = ((p.getPosition().getX())/L) * width;
			y = ((p.getPosition().getY())/L) * height;
			x2 = ((p.getV().getXVelocity())/L) * width;
			y2 = ((p.getV().getYVelocity())/L) * height;

			Graphics2D g2 = (Graphics2D) g;
			Line2D lin = new Line2D.Double(x, y, x + x2, y + y2);
			g2.draw(lin);
			g2.setColor(Color.BLACK);
			Ellipse2D circle = new Ellipse2D.Double(x + x2-radious/2, y+y2-radious/2, radious, radious);
			g2.draw(circle);
		}

	}

	/*public void saveImage() {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.paint(img.getGraphics());
		try {
			String nombre = "SS2-" + Calendar.getInstance().getTimeInMillis() + ".png";
			ImageIO.write(img, "png", new File(nombre));
			System.out.println("Imagen guardada como " + nombre);
		} catch (Exception e) {
			System.out.println("Imagen no guardada. " + e.getMessage());
		}
	}

	public void captureScreen() throws Exception {

		String name = "SSTP02-"+Calendar.getInstance().getTimeInMillis() + ".png";

		Rectangle screenRectangle = new Rectangle(this.getX(), this.getY()+20, this.width, this.height);
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		ImageIO.write(image, "png", new File(name));
		System.out.println("Captured screen " + name);
	}*/

}
