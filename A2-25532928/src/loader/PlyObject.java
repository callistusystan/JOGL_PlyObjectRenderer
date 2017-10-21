package loader;

import java.util.ArrayList;

/***
 * A class representing the object specified in a Ply file
 * 
 * @author Callistus
 */
public class PlyObject {
	
	private ArrayList<Vector> vertices = new ArrayList<>();
	private ArrayList<Face> faces = new ArrayList<>();
	private Vector center;
	private float maxRange;
	
	public PlyObject(ArrayList<Vector> a_vertices, ArrayList<Face> a_faces, Vector a_center, float a_maxRange) {
		vertices = a_vertices;
		faces = a_faces;
		center = a_center;
		maxRange = a_maxRange;
	}
	
	public ArrayList<Vector> getVertices() {
		return vertices;
	}
	
	public ArrayList<Face> getFaces() {
		return faces;
	}
	
	public Vector getCenter() {
		return center;
	}
	
	public float getMaxRange() {
		return maxRange;
	}
	
	public void setVertices(ArrayList<Vector> a_vertices) {
		vertices = a_vertices;
	}
	
	public void setFaces(ArrayList<Face> a_faces) {
		faces = a_faces;
	}
	
	public void setCenter(Vector a_center) {
		center = a_center;
	}
	
	public void setMaxRange(float a_maxRange) {
		maxRange = a_maxRange;
	}

}
