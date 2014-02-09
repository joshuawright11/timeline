/**
 * 
 */
package entities;

/**
 * @author Josh Wright
 * Created: Jan 29, 2014
 * Package: backend
 *
 */

public abstract class TLEvent {
	private String name;
	private String category;
	
	TLEvent(String name, String category){
		this.setName(name);
		this.category = category;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	public String getCategory() {
		return category;
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
