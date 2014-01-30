/**
 * 
 */
package backend;

/**
 * @author Josh Wright
 * Created: Jan 29, 2014
 * Package: backend
 *
 */

public abstract class TLEvent {
	private String name;
	TLEvent(String name){
		this.setName(name);
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return
	 */
	public abstract String typeName();
}
