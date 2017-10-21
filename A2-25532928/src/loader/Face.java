package loader;

import java.util.ArrayList;

/***
 * A class that represents a face
 * 
 * @author Callistus
 */
public class Face {

	private ArrayList<Integer> indices;
	
	public Face(ArrayList<Integer> a_indices) {
		indices = a_indices;
	}
	
	public ArrayList<Integer> getIndices() {
		return indices;
	}
	
	public void setIndices(ArrayList<Integer> a_indices) {
		indices = a_indices;
	}
	
}
