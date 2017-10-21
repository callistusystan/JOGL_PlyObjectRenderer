package loader;

/***
 * A class that represents a vector
 * 
 * @author Callistus
 */
public class Vector {
	
	private float x;
	private float y;
	private float z;
	
	public Vector(float a_x, float a_y, float a_z) {
		x = a_x;
		y = a_y;
		z = a_z;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}
	
	public void setX(float a_x) {
		x = a_x;
	}
	
	public void setY(float a_y) {
		y = a_y;
	}
	
	public void setZ(float a_z) {
		z = a_z;
	}

}
