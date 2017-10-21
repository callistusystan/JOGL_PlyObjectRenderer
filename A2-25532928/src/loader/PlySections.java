package loader;

/***
 * An enumeration of sections in a Ply file 
 * 
 * @author Callistus
 */
public enum PlySections {
	METADATA,
	VERTEX_DESCRIPTION,
	FACE_DESCRIPTION,
	VERTICES_SECTION,
	FACES_SECTION,
	END;
	
	public PlySections getNext() {
		switch (this) {
			case METADATA:
				return VERTEX_DESCRIPTION;
			case VERTEX_DESCRIPTION:
				return FACE_DESCRIPTION;
			case FACE_DESCRIPTION:
				return VERTICES_SECTION;
			case VERTICES_SECTION:
				return FACES_SECTION;
			case FACES_SECTION:
				return END;
			default:
				return END;
		}
	}
}
