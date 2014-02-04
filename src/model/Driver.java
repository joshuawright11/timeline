/**
 * 
 */
package model;

/**
 * Driver.java
 * @author Andrew Thompson and Josh Wright
 *
 */
public class Driver {
	/**
	 * Create a TimelineMaker object and start it on a new Thread.
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new TimelineMaker()).start();
	}
}
