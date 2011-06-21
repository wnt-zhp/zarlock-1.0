/**
 * 
 */
package zzId;

/**
 * @author jb
 *
 */
public class ElementId extends AbstractId {
	
	public ElementId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ElementId(ElementId rhs) {
		super(rhs);
		// TODO Auto-generated constructor stub
	}

	public ElementId(int rhs) {
		super(rhs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractId copy() {
		return new ElementId(this);
	}

}
