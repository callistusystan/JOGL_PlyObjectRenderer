package loader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/***
 * A loader that parses a Ply file into a PlyObject
 * 
 * @author Callistus
 */
public class PlyObjectLoader {
	
	public static PlyObject loadObject(String filename) {
        try {
        	// declare variables that need to be set 
            ArrayList<String> vertexVariables = new ArrayList<>();
            ArrayList<Vector> vertices = new ArrayList<>();
            ArrayList<Face> faces = new ArrayList<>();
            PlySections section = PlySections.METADATA;
            int nVertices = 0, nFaces = 0, sectionLine = 0;
            
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            /*
             * PARSE HEADER
             */
            
            String line = null;
            while((line = bufferedReader.readLine()) != null && !line.equals("end_header")) {            	
            	// check if next section
            	if (line.startsWith("element")) {
            		section = section.getNext();
            		// reset sectionLine
            		sectionLine = 0;
            	}

            	// split line by whitespace
    			String[] splitLine = line.split("\\s+");
            	
            	if (section == PlySections.VERTEX_DESCRIPTION) {
            		if (sectionLine == 0) {
            			// parse section info
            			nVertices = Integer.parseInt(splitLine[2]);
            		} else {
            			// parse variable description
            			String name;
            			name = splitLine[2];
            			vertexVariables.add(name);
            		}            		            		            		
            	} else if (section == PlySections.FACE_DESCRIPTION) {
            		if (sectionLine == 0) {
            			// parse section info
            			nFaces = Integer.parseInt(splitLine[2]);
            		} 
            	}
            	sectionLine++;
            }
            
            // take note of which position in row corresponds to x,y,z 
			int indexOfX, indexOfY, indexOfZ;
			indexOfX = vertexVariables.indexOf("x");
			indexOfY = vertexVariables.indexOf("y");
			indexOfZ = vertexVariables.indexOf("z");
            
			// compute min and max of each axis for object center and maxRange  
            float minX, maxX, minY, maxY, minZ, maxZ;
            minX = minY = minZ = Float.MAX_VALUE;
            maxX = maxY = maxZ = Float.MIN_VALUE;

            // read vertices
            for (int i=0;i<nVertices;i++) {
            	line = bufferedReader.readLine();
    			String[] splitLine = line.split("\\s+");
    			
    			float x,y,z;
    			x = Float.parseFloat(splitLine[indexOfX]);
    			y = Float.parseFloat(splitLine[indexOfY]);
    			z = Float.parseFloat(splitLine[indexOfZ]);
    			
    			vertices.add(new Vector(x, y, z));
    			
    			// update mins and maxs
    			minX = Math.min(minX, x);
    			minY = Math.min(minY, y);
    			minZ = Math.min(minZ, z);
    			
    			maxX = Math.max(maxX, x);
    			maxY = Math.max(maxY, y);
    			maxZ = Math.max(maxZ, z);
            }
            
            // read faces
            for (int i=0;i<nFaces;i++) {
            	line = bufferedReader.readLine();
    			String[] splitLine = line.split("\\s+");
    			
    			int nFaceVertices = Integer.parseInt(splitLine[0]);
    			
    			// store only the indices instead of a new Vertex to save space
    			ArrayList<Integer> indices = new ArrayList<>();
    			for (int j=1;j<=nFaceVertices;j++) {
    				indices.add(Integer.parseInt(splitLine[j]));    				
    			}
    			faces.add(new Face(indices));
            }
            
            // always close the reader
            bufferedReader.close();
            
            // compute center of object, and largest range in any axis
            Vector center = new Vector((minX+maxX)/2.0f, (minY+maxY)/2.0f, (minZ+maxZ)/2.0f);
            
            float maxRange = maxX-minX;
            maxRange = Math.max(maxRange, maxY-minY);
            maxRange = Math.max(maxRange, maxZ-minZ);
            
            return new PlyObject(vertices, faces, center, maxRange);
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + filename + "'");                  
        }
        
        // this means that loading failed
		return null;
	}

}
