package entities;

import constants.MaterialStore;
import constants.TypeStore;

/***
 * A singleton class that manages the application options
 * 
 * @author Callistus
 */
public class OptionsManager {
	
	private static final OptionsManager instance = new OptionsManager();
	private float translateX;
	private float translateY;
	private float translateZ;
	private float rotateX;
	private float rotateY;
	private float rotateZ;
	private float zoomFactor = 1.0f;
	private String rendering = MaterialStore.WIREFRAME;
	private String projection = TypeStore.PERSPECTIVE;
	private boolean isLightOn = true;
	
	public static OptionsManager getInstance() {
		return instance;
	}
	
	public float getTranslateX() {
		return translateX;
	}
	
	public float getTranslateY() {
		return translateY;
	}
	
	public float getTranslateZ() {
		return translateZ;
	}
	
	public float getRotateX() {
		return rotateX;
	}
	
	public float getRotateY() {
		return rotateY;
	}
	
	public float getRotateZ() {
		return rotateZ;
	}
	
	public float getZoomFactor() {
		return zoomFactor;
	}
	
	public String getRendering() {
		return rendering;
	}
	
	public String getProjection() {
		return projection;
	}
	
	public boolean isLightOn() {
		return isLightOn;
	}
	
	public void setTranslateX(float a_translateX) {
		translateX = a_translateX;
	}
	
	public void setTranslateY(float a_translateY) {
		translateY = a_translateY;
	}
	
	public void setTranslateZ(float a_translateZ) {
		translateZ = a_translateZ;
	}
	
	public void setRotateX(float a_rotateX) {
		rotateX = a_rotateX;
	}
	
	public void setRotateY(float a_rotateY) {
		rotateY = a_rotateY;
	}
	
	public void setRotateZ(float a_rotateZ) {
		rotateZ = a_rotateZ;
	}
	
	public void setZoomFactor(float a_zoomFactor) {
		zoomFactor = a_zoomFactor;
	}
	
	public void setRendering(String a_rendering) {
		rendering = a_rendering;
	}
	
	public void setProjection(String a_projection) {
		projection = a_projection;
	}
	
	public void setIsLightOn(boolean a_isLightOn) {
		isLightOn = a_isLightOn;
	}
	
}
