
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Drawing extends Canvas {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
        JFrame frame = new JFrame();
        Canvas canvas = new Drawing();
        canvas.setSize(400, 400);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        canvas.setBackground(new Color(255, 255, 255, 80));
    }

	public void paint(Graphics g) {
        g.drawOval(0, 0, 300, 300);
    }
}