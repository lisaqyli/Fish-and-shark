/* Ocean.java */
import java.util.*;

/**
 *  The Ocean class defines an object that models an ocean full of sharks and
 *  fish.  Descriptions of the methods you must implement appear below.  They
 *  include a constructor of the form
 *
 *      public Ocean(int i, int j, int starveTime);
 *
 *  that creates an empty ocean having width i and height j, in which sharks
 *  starve after starveTime timesteps.
 *
 *  See the README file accompanying this project for additional details.
 */

public class Ocean {

  /**
   *  Do not rename these constants.  WARNING:  if you change the numbers, you
   *  will need to recompile Test4.java.  Failure to do so will give you a very
   *  hard-to-find bug.
   */

  public final static int EMPTY = 0;
  public final static int SHARK = 1;
  public final static int FISH = 2;

  /**
   *  Define any variables associated with an Ocean object here.  These
   *  variables MUST be private.
   */
    private int width;
    private int height;
    private int sTime;
    private int[][][] cell;

  /**
   *  The following methods are required for Part I.
   */

  /**
   *  Ocean() is a constructor that creates an empty ocean having width i and
   *  height j, in which sharks starve after starveTime timesteps.
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   */

  public Ocean(int i, int j, int starveTime) {
	width = i;
	height = j;
	sTime = starveTime;
	cell = new int[i][j][2];
  }

  /**
   *  width() returns the width of an Ocean object.
   *  @return the width of the ocean.
   */

  public int width() {
    // Replace the following line with your solution.
    return this.width;
  }

  /**
   *  height() returns the height of an Ocean object.
   *  @return the height of the ocean.
   */

  public int height() {
    // Replace the following line with your solution.
    return this.height;
  }

  /**
   *  starveTime() returns the number of timesteps sharks survive without food.
   *  @return the number of timesteps sharks survive without food.
   */

  public int starveTime() {
    // Replace the following line with your solution.
    return this.sTime;
  }

  /**
   *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
   *  cell is already occupied, leave the cell as it is.
   *  @param x is the x-coordinate of the cell to place a fish in.
   *  @param y is the y-coordinate of the cell to place a fish in.
   */
public int getHunger(int x, int y) {
	return cell[x][y][1];
}
public void setHunger(int x, int y, int n) {
	cell[x][y][1] = n;
}
  public void addFish(int x, int y) {
      x = mod(x, width);
      y = mod(y, height);
      if (cell[x][y][0] == EMPTY) {
	  cell[x][y][0] = FISH;
      }
  }

  /**
   *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
   *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
   *  just eaten.  If the cell is already occupied, leave the cell as it is.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   */

  public void addShark(int x, int y) {
      x = mod(x, width);
      y = mod(y, height);
      if (cell[x][y][0] == EMPTY) {
	  cell[x][y][0] = SHARK;
	  cell[x][y][1] = sTime;
      }
  }


    public static int mod(int input, int length){
  	  if(input < 0){
  		  input = input % length + length;
  	  }
  	  else{
  		  input = input % length;
  	  }
  	  return input;
    }
    /**
     *  mod1()  checks if x-coordinate is out of
     *  boundary. If it is then it helps to determine the actual
     *  coordinates of the cell.
     *  @param x is the x-coordinate of the cell.
     */
    /**
    public int mod1(int x) {
	if (x >= width) {
	    x = x % width;
	} else if (x < 0) {
            while (x < 0) {
                x = x+ width;
            }
        }
        return x;
    }
    */
    /**
     *  mod2() checks if y-coord is out of boundary.
     * @param y  is the y-coord of the cell.
     */
    /**
    public int mod2(int y) {
	if (y >= height) {
	    y = y % height;
	} else if (y <0) {
            while (y<0) {
                y = y +height;
            }
        }
        return y;
    }
    */

  /**
   *  cellContents() returns EMPTY if cell (x, y) is empty, FISH if it contains
   *  a fish, and SHARK if it contains a shark.
   *  @param x is the x-coordinate of the cell whose contents are queried.
   *  @param y is the y-coordinate of the cell whose contents are queried.
   */

  public int cellContents(int x, int y) {
      x = mod(x, width);
      y = mod(y, height);
      if (cell[x][y][0] == EMPTY) {
          return EMPTY;
      } else if (cell[x][y][0] == FISH) {
	  return FISH;
      } else {
	  return SHARK;
      }
  }
    /**
     *  neighbor() checks what does the neighboring cell contains. It returns an
     *  array storing the values of each neighboring cells.
     *  @param x is the x-coordinate of the cell whose contents are queried.
     *  @param y is the y-coordinate of the cell whose contents are queried.
     *  @return an array storing all the values of neighboring cells.
     */

    public int[] neighbor(int x, int y) {
	x = mod(x, width);
        y = mod(y, height);
	int[] ngb = new int[8];
	/**if (x == 0) {
	    x  = x + width;
	}
	if (y ==0) {
	    y = y +height;
	}
	if (x ==  width) {
	    x = x - width;
	}
	if (y == height) {
	    y = y - height;
	}
        */
	ngb[0] = cell[mod((x-1), width)][mod((y-1), height)][0];
	ngb[1] = cell[x][mod((y-1), height)][0];
	ngb[2] = cell[mod((x+1), width)][mod((y-1), height)][0];
	ngb[3] = cell[mod((x+1), width)][y][0];
	ngb[4] = cell[mod((x+1), width)][mod((y+1), height)][0];
	ngb[5] = cell[x][mod((y+1), height)][0];
	ngb[6] = cell[mod((x-1), width)][mod((y+1), height)][0];
	ngb[7] = cell[mod((x-1), width)][y][0];
	return ngb;
    }

    public int[] checkNei(int x, int y) {
        x = mod(x, width);
        y = mod(y, height);
        int[] count = new int[3];
        count[0] = 0;
        count[1] = 0;
        count[2] = 0;
        for (int k = 0; k < 8; k++) {
            if (neighbor(x, y)[k] == EMPTY) {
                count[0]++;
            } else if (neighbor(x, y)[k] == FISH) {
                count[1]++;
            } else {
                count[2]++;
            }
        }
        return count;
    } 


  /**
   *  timeStep() performs a simulation timestep as described in README.
   *  @return an ocean representing the elapse of one timestep.
   */

  public Ocean timeStep() {
      Ocean nOcean = new Ocean(this.width, this.height, this.sTime);
      for (int i = 0; i < width; i++) {
    	  for (int j = 0; j<height; j++) {
    		  if (this.cellContents(i, j) == SHARK) {
                  nOcean.cell[i][j][0] =SHARK;
                  nOcean.cell[i][j][1] = this.cell[i][j][1];
                  nOcean.cell[i][j][1]--;
                  if (this.checkNei(i, j)[1] >= 1) {
			  nOcean.cell[i][j][1] = sTime;
                  }
                  if (nOcean.cell[i][j][1] < 0) {
                      nOcean.cell[i][j][0] = EMPTY;
		      nOcean.cell[i][j][1] = 0;
                  }
    		  }
              if (this.cellContents(i, j) == FISH) {
                  nOcean.cell[i][j][0] = FISH;
                  nOcean.cell[i][j][1] = 0;
                  if (this.checkNei(i, j)[2] == 1) { //neighbor
                      nOcean.cell[i][j][0] = EMPTY;
                  }else if (this.checkNei(i, j)[2] >= 2) {
                      nOcean.cell[i][j][0] = SHARK;
		      nOcean.cell[i][j][1] =sTime;
                  }
              }
              if (this.cellContents(i, j) == EMPTY) {
                  nOcean.cell[i][j][0] = EMPTY;
		  nOcean.cell[i][j][1] = 0;
                  if (this.checkNei(i, j)[1] >= 2  && this.checkNei(i, j)[2] <=1) {
                      nOcean.cell[i][j][0] = FISH;
                  } else if (this.checkNei(i, j)[2] >= 2 && this.checkNei(i, j)[1] >= 2) {
                      nOcean.cell[i][j][0] = SHARK;
		      nOcean.cell[i][j][1] = sTime;
      }
              }
          }
      }
      return nOcean;
  }

  /**
   *  The following method is required for Part II.
   */

  /**
   *  addShark() (with three parameters) places a shark in cell (x, y) if the
   *  cell is empty.  The shark's hunger is represented by the third parameter.
   *  If the cell is already occupied, leave the cell as it is.  You will need
   *  this method to help convert run-length encodings to Oceans.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   *  @param feeding is an integer that indicates the shark's hunger.  You may
   *         encode it any way you want; for instance, "feeding" may be the
   *         last timestep the shark was fed, or the amount of time that has
   *         passed since the shark was last fed, or the amount of time left
   *         before the shark will starve.  It's up to you, but be consistent.
   */

  public void addShark(int x, int y, int feeding) {
	  x = mod(x, width);
	  y = mod(y, height);
    if (cell[x][y][0] == EMPTY) {
    	cell[x][y][0] = SHARK;
    	cell[x][y][1] = feeding; 
    }
  }

  /**
   *  The following method is required for Part III.
   */

  /**
   *  sharkFeeding() returns an integer that indicates the hunger of the shark
   *  in cell (x, y), using the same "feeding" representation as the parameter
   *  to addShark() described above.  If cell (x, y) does not contain a shark,
   *  then its return value is undefined--that is, anything you want.
   *  Normally, this method should not be called if cell (x, y) does not
   *  contain a shark.  You will need this method to help convert Oceans to
   *  run-length encodings.
   *  @param x is the x-coordinate of the cell whose contents are queried.
   *  @param y is the y-coordinate of the cell whose contents are queried.
   */

  public int sharkFeeding(int x, int y) {
	  if (this.cellContents(x, y) == SHARK){
		  return this.cell[x][y][1];
	  } else {
		  return 10;
	  }
  	}
}
