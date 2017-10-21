package constants;

/***
 * A class to store the properties of each material.
 * 
 * Data taken from http://devernay.free.fr/cours/opengl/materials.html
 * 
 * @author Callistus
 */
public class MaterialStore {

	// these prevent spelling errors during coding
	public static String WIREFRAME = "Wireframe";
	public static String GOLD = "Gold";
	public static String COPPER = "Copper";
	public static String WHITE_PLASTIC = "White Plastic";

	public static float[] GOLD_COLOR = {225f/255, 215f/255, 0f};
	public static float[] GOLD_AMBIENT = {0.24725f, 0.1995f, 0.0745f};
	public static float[] GOLD_DIFFUSE = {0.75164f, 0.60648f, 0.22648f};
	public static float[] GOLD_SPECULAR = {0.628281f, 0.555802f, 0.366065f};
	public static float GOLD_SHINE = 0.4f*128f;

	public static float[] COPPER_COLOR = {225f/255, 215f/255, 0f};
	public static float[] COPPER_AMBIENT = {0.19125f, 0.0735f, 0.0225f};
	public static float[] COPPER_DIFFUSE = {0.7038f, 0.27048f, 0.0828f};
	public static float[] COPPER_SPECULAR = {0.256777f, 0.137622f, 0.086014f};
	public static float COPPER_SHINE = 0.1f*128f;

	public static float[] WHITE_PLASTIC_COLOR = {1f, 1f, 1f};
	public static float[] WHITE_PLASTIC_AMBIENT = {0.0f, 0.0f, 0.0f};
	public static float[] WHITE_PLASTIC_DIFFUSE = {0.55f, 0.55f, 0.55f};
	public static float[] WHITE_PLASTIC_SPECULAR = {0.70f, 0.70f, 0.70f};
	public static float WHITE_PLASTIC_SHINE = 0.25f*128f;

}
