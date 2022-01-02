package Utilitiy;

import java.nio.FloatBuffer;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;


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
		return new float[] { pixels[0], pixels[1], pixels[2] };
	}


}
