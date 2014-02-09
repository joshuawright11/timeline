/**
 * 
 */
package entities;
import java.sql.Date;
/**
 * @author Josh Wright
 * Created: Jan 29, 2014
 * Package: backend
 *
 */
public class Atomic extends TLEvent {
	private Date date;
	public Atomic(String name, String category, Date date){
		super(name, category);
		this.setDate(date);
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/* (non-Javadoc)
	 * @see backend.TLEvent#typeName()
	 */
	@Override
	public String typeName() {
		return "atomic";
	}
}
