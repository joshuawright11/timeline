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
public class Duration extends TLEvent {
	private Date startDate;
	private Date endDate;
	public Duration(String name, Date startDate, Date endDate){
		super(name);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/* (non-Javadoc)
	 * @see backend.TLEvent#typeName()
	 */
	@Override
	public String typeName() {
		return "duration";
	}
}
