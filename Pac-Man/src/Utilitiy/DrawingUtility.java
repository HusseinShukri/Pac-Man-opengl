package Utilitiy;

import java.nio.FloatBuffer;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL3;


public class DrawingUtility {

	public static void setPixelColor(GL2 gl, float[] rgb, int x, int y) {
		gl.glBegin(GL.GL_POINTS);
		gl.glColor3f(rgb[0], rgb[1], rgb[2]);
		gl.glVertex2d(x, y);
		gl.glEnd();
	}

	public static float[] getPixelColor(GL2 gl, int x, int y) {
		// The following code reads the current pixel color at the center of the screen
		FloatBuffer buffer = FloatBuffer.allocate(4);
		gl.glReadPixels(x, y, 1, 1, GL.GL_RGBA, GL.GL_FLOAT, buffer);
		float[] pixels = new float[3];
		pixels = buffer.array();
//		System.out.println(pixels[0]+" "+pixels[1]+" "+pixels[2]);
		return new float[] { pixels[0], pixels[1], pixels[2] };
	}


//	public static void drawLines(GL2 gl, LinkedList<Line> lines) {
//		for (Line line : lines) {
//			gl.glColor3f(line.getColor()[0], line.getColor()[1], line.getColor()[2]);
//			gl.glBegin(GL.GL_LINES);
//			gl.glVertex2f(line.getPoint1().getX(), line.getPoint1().getY());
//			gl.glVertex2f(line.getPoint2().getX(), line.getPoint2().getY());
//			gl.glEnd();
//
//		}
//	}

}
