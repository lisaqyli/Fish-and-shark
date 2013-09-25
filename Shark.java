/* Shark.java */
public class Shark {
	private int starveTime;

	/**  Shark() is a constructor that constrcut a new shark.
	 * @param i is the initial starveTime.
	 */
	public Shark(int i) {
		starveTime = i;
	}
	
	/** getStaveTime() is a method that returns its starveTime.
	 * @return the remaining starveTime.
	 */
	
	public int getStarveTime() {
		return starveTime;
	}
/** deStarveTime is a method that decreases the shark's starve time after 
 *  a time step.
 */
	public void deStarveTime() {
		this.starveTime--;
	}


}
