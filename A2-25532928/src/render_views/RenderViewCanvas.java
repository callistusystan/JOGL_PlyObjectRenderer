package render_views;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_TEST;
import static com.jogamp.opengl.GL.GL_LEQUAL;
import static com.jogamp.opengl.GL.GL_NICEST;
import static com.jogamp.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

import constants.MaterialStore;
import entities.OptionsManager;
import loader.PlyObjectLoader;
import loader.Vector;
import utils.Utils;
import loader.Face;
import loader.PlyObject;

/***
 * The main view that renders the 3d model
 * 
 * Boilerplate code mostly copied from examples provided on Moodle
 * 
 * @author Callistus
 */
@SuppressWarnings("serial")
public class RenderViewCanvas extends GLCanvas implements GLEventListener, MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
	
	private GLU glu;
	private PlyObject plyObject;
	private Integer prevX;
	private Integer prevY;
	private float width;
	private float height;
	
	public RenderViewCanvas() {
		this.addGLEventListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		this.addKeyListener(this);
	}
 
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL graphics context
		glu = new GLU(); // get GL Utilities
		
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); 				// set background (clear) color
		gl.glClearDepth(1.0f); 									// set clear depth value to farthest
		gl.glEnable(GL_DEPTH_TEST); 							// enables depth testing
		gl.glDepthFunc(GL_LEQUAL); 								// the type of depth test to do
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); 	// best perspective correction
		gl.glShadeModel(GL_SMOOTH); 							// blends colors nicely, and smoothes out lighting

		// load object
		plyObject = PlyObjectLoader.loadObject("./data/bun_zipper.ply");
	}
 
	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
        
		// declare variables
        ArrayList<Vector> vertices = plyObject.getVertices();
        ArrayList<Face> faces = plyObject.getFaces();
        Vector center = plyObject.getCenter();
        float maxRange = plyObject.getMaxRange();
		float aspect = (float)width / height;
        
        // extract options
        String rendering = OptionsManager.getInstance().getRendering();
		String projection = OptionsManager.getInstance().getProjection();
    	float zoomFactor = OptionsManager.getInstance().getZoomFactor();
    	boolean isLightOn = OptionsManager.getInstance().isLightOn();
        float translateX, translateY, translateZ;
        float conversion = (2*maxRange)/50f; // maps (-50 to 50) to (-2*maxRange to 2*maxRange)
        translateX = OptionsManager.getInstance().getTranslateX() * conversion;
        translateY = OptionsManager.getInstance().getTranslateY() * conversion;
        translateZ = OptionsManager.getInstance().getTranslateZ() * 0.4f * conversion;
		
        // set projection
    	gl.glMatrixMode(GL_PROJECTION);  // choose projection matrix
		gl.glLoadIdentity();             // reset projection matrix
        if (projection.equals("Perspective")) {
    		glu.gluPerspective(45.0*zoomFactor, aspect, 0.1, 100.0);
    		glu.gluLookAt(0.0f, 0.0f, 2.0*maxRange, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        } else if (projection.equals("Orthographic")) {
        	gl.glOrtho(-maxRange*zoomFactor,maxRange*zoomFactor,-maxRange*zoomFactor,maxRange*zoomFactor,-2*maxRange, 2*maxRange);
        }
		
		// Enable the model-view transform
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity(); // reset
		
		// reset and clear
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
        gl.glLoadIdentity();  // reset the model-view matrix
        gl.glPushMatrix();
        
        /*
         * SET LIGHTING
         */
        
        if (isLightOn) {
            gl.glEnable(GL2.GL_LIGHTING);
            gl.glEnable(GL2.GL_LIGHT0);
        	
            // set up light
            float[] ambient = { 0.8f, 0.8f, 0.8f, 1.0f };
            float[] diffuse = { 0.8f, 0.8f, 0.8f, 1.0f };
            float[] specular = { 0.5f, 0.5f, 0.5f, 1.0f };
            float[] position = { 0.0f, 0.0f, 1.0f, 1.0f };

            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, Utils.toFloatBuffer(ambient));
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, Utils.toFloatBuffer(diffuse));
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, Utils.toFloatBuffer(specular));
            gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, Utils.toFloatBuffer(position));
        } else {
            gl.glDisable(GL2.GL_LIGHTING);
            gl.glDisable(GL2.GL_LIGHT0);
        }
        
        /*
         * SET MATERIAL
         */
        
        float color[] = MaterialStore.WHITE_PLASTIC_COLOR;
        if (rendering.equals(MaterialStore.WIREFRAME)) {
            gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
        } else if (rendering.equals(MaterialStore.GOLD)) {
            gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
            color = MaterialStore.GOLD_COLOR;
            
            gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, Utils.toFloatBuffer(MaterialStore.GOLD_AMBIENT));
            gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, Utils.toFloatBuffer(MaterialStore.GOLD_DIFFUSE));
            gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, Utils.toFloatBuffer(MaterialStore.GOLD_SPECULAR));
            
            gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, (float) (MaterialStore.GOLD_SHINE));
        } else if (rendering.equals(MaterialStore.COPPER)) {
            gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
            color = MaterialStore.COPPER_COLOR;
            
            gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, Utils.toFloatBuffer(MaterialStore.COPPER_AMBIENT));
            gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, Utils.toFloatBuffer(MaterialStore.COPPER_DIFFUSE));
            gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, Utils.toFloatBuffer(MaterialStore.COPPER_SPECULAR));
            
            gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, (float) (MaterialStore.COPPER_SHINE));
        } else if (rendering.equals(MaterialStore.WHITE_PLASTIC)) {
            gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
            
            gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, Utils.toFloatBuffer(MaterialStore.WHITE_PLASTIC_AMBIENT));
            gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, Utils.toFloatBuffer(MaterialStore.WHITE_PLASTIC_DIFFUSE));
            gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, Utils.toFloatBuffer(MaterialStore.WHITE_PLASTIC_SPECULAR));
            
            gl.glMaterialf(GL2.GL_FRONT, GL2.GL_SHININESS, (float) (MaterialStore.WHITE_PLASTIC_SHINE));        
        }
        
        /*
         * TRANSLATE
         */
        
        gl.glTranslatef(translateX, translateY, translateZ);
        
        /*
         * ROTATE
         */
        
        gl.glRotatef(OptionsManager.getInstance().getRotateX(), 1.0f, 0.0f, 0.0f);
        gl.glRotatef(OptionsManager.getInstance().getRotateY(), 0.0f, 1.0f, 0.0f);
        gl.glRotatef(OptionsManager.getInstance().getRotateZ(), 0.0f, 0.0f, 1.0f);
        gl.glTranslatef(-center.getX(), -center.getY(), -center.getZ());
        
        /*
         * RENDER MODEL
         */
        
        for (Face face : faces) {
            gl.glBegin(GL2.GL_POLYGON);
            
            // get vertices that correspond to this face
            Vector faceVertices[] = new Vector[3];
            for (int i=0;i<3;i++) {
            	Vector v = vertices.get(face.getIndices().get(i));
            	faceVertices[i] = v;
            }
            
            // compute the normal of the face
            float normal[] = Utils.computeNormal(faceVertices);
            
            // normalize normal
            float normalizedNormal[] = Utils.normalize(normal);
            
        	gl.glNormal3f(normalizedNormal[0], normalizedNormal[1], normalizedNormal[2]);
            for (Vector v : faceVertices) {
            	gl.glColor3f(color[0], color[1], color[2]);
            	gl.glVertex3f(v.getX(), v.getY(), v.getZ());
            }
            gl.glEnd();
        }

        gl.glFlush();
        gl.glPopMatrix();
    }
 
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
		if (height == 0) height = 1;   // prevent divide by zero

		this.width = width;
		this.height = height;
		
		// Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);
	}
   	
	@Override
	public void dispose(GLAutoDrawable drawable) { }
	
	/***
	 * A helper method that updates the rotation values relative to previous values
	 * 
	 * @param diffRotateX
	 * @param diffRotateY
	 * @param diffRotateZ
	 */
	private void changeRotate(float diffRotateX, float diffRotateY, float diffRotateZ) {
		// update rotations relative to the previous values
		float prevRotateX, prevRotateY, prevRotateZ;
		prevRotateX = OptionsManager.getInstance().getRotateX();
		prevRotateY = OptionsManager.getInstance().getRotateY();
		prevRotateZ = OptionsManager.getInstance().getRotateZ();
		
		OptionsManager.getInstance().setRotateX(prevRotateX + diffRotateX);
		OptionsManager.getInstance().setRotateY(prevRotateY + diffRotateY);
		OptionsManager.getInstance().setRotateZ(prevRotateZ + diffRotateZ);
	}
	
	/***
	 * A helper method that updates the zoomFactor values based on previous value
	 * 
	 * @param amount
	 */
	private void changeZoomFactor(int amount) {
		// update zoom factors relative to the previous values
		float prevZoomFactor = OptionsManager.getInstance().getZoomFactor();
		float newZoomFactor = prevZoomFactor + amount*0.2f;
		
		// ensure newScale is in the range 0.1 - 2.0, so that 0 < FoV < 90
		newZoomFactor = Math.max(newZoomFactor, 0.1f);
		newZoomFactor = Math.min(newZoomFactor, 2.0f);
		
		OptionsManager.getInstance().setZoomFactor(newZoomFactor);
	}

	/**
	 * MOUSE AND KEYBOARD LISTENERS
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// update prevX and prevY when mouse pressed
		prevX = e.getX();
		prevY = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// update rotations relative to previous values
		int diffX, diffY;
		diffX = e.getX() - prevX;
		diffY = e.getY() - prevY;

		float angleRotateX, angleRotateY;
		angleRotateX = (float)diffY/getHeight()*360.0f;
		angleRotateY = (float)diffX/getWidth()*360.0f;

		changeRotate(angleRotateX, angleRotateY, 0.0f);
		
		prevX = e.getX();
		prevY = e.getY();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// perform transformations based on pressed key
		char c = e.getKeyChar();
		
		// zoom factor
		if (c == '+' || c == '=') {
			changeZoomFactor(-1);
		} else if (c == '-') {
			changeZoomFactor(1);
		}
		
		// rotate
		if (c == 'a' || c == 'A') {
			changeRotate(-12.0f, 0.0f, 0.0f);
		} else if (c == 'd' || c == 'D') {
			changeRotate(12.0f, 0.0f, 0.0f);			
		} else if (c == 'x' || c == 'X') {
			changeRotate(0.0f, -12.0f, 0.0f);
		} else if (c == 'w' || c == 'W') {
			changeRotate(0.0f, 12.0f, 0.0f);			
		} else if (c == 'z' || c == 'Z') {
			changeRotate(0.0f, 0.0f, -12.0f);			
		} else if (c == 'e' || c == 'E') {
			changeRotate(0.0f, 0.0f, 12.0f);			
		}
	}
	
	/***
	 * UNUSED methods
	 */

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		changeZoomFactor(e.getWheelRotation());
	}

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseMoved(MouseEvent e) { }

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }
	
	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) { }

}
