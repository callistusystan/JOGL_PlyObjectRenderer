package utils;

import java.nio.FloatBuffer;

import com.jogamp.common.nio.Buffers;

import loader.Vector;

/***
 * A utility class that converts between types and performs useful vector calculations
 * 
 * @author Callistus
 */
public class Utils {
	
	/***
	 * A helper method to convert a float array to a float buffer
	 * 
	 * @param arr
	 * @return a FloatBuffer that corresponds to arr 
	 */
	public static FloatBuffer toFloatBuffer(float arr[]) {
		return Buffers.newDirectFloatBuffer(arr);
	}
	
	/*
	 * A method that normalizes a vector to 1 unit
	 * 
	 * Implemented according to http://www.falloutsoftware.com/tutorials/gl/gl8.htm
	 */
	/***
	 * A method that normalizes a vector to 1 unit
	 * 
	 * Implemented according to http://www.falloutsoftware.com/tutorials/gl/gl8.htm
	 * 
	 * @param vector
	 * @return an array of floats representing the normalized vector
	 */
	public static float[] normalize(float[] vector) {
		float normalized[] = new float[3];
		
		// calculate the length of the vector
	    float len = (float)(Math.sqrt((vector[0] * vector[0]) + (vector[1] * vector[1]) + (vector[2] * vector[2])));

	    // avoid division by 0
	    if (len == 0.0f)
	    	len = 1.0f;

	    // reduce to unit size
	    normalized[0] = vector[0]/len;
	    normalized[1] = vector[1]/len;
	    normalized[2] = vector[2]/len;
		
		return normalized;
	}

	/***
	 * A method that computes the normal vector for a 3-point face
	 * 
	 * Implemented according to http://www.falloutsoftware.com/tutorials/gl/gl8.htm
	 * 
	 * @param vertices
	 * @return an array representing the normal to the vector
	 */
	public static float[] computeNormal(Vector vertices[]) {		
		// Currently, the points are given in CCW order, but it would be safer to place a check here
		
		// get the vertices
		Vector v0 = vertices[0], v1 = vertices[1], v2 = vertices[2];

		// compute a and b, where a = v0-v1 and b = v1-v2
		Vector a = new Vector(v0.getX()-v1.getX(), v0.getY()-v1.getY(), v0.getZ()-v1.getZ());
		Vector b = new Vector(v1.getX()-v2.getX(), v1.getY()-v2.getY(), v1.getZ()-v2.getZ());
		
	    // calculate the cross product and place the resulting vector into normal
		float normal[] = new float[3];
		normal[0] = (a.getY()*b.getZ()) - (a.getZ()*b.getY());
		normal[1] = (a.getZ()*b.getX()) - (a.getX()*b.getZ());
		normal[2] = (a.getX()*b.getY()) - (a.getY()*b.getX());
		
		return normal;
	}
}
