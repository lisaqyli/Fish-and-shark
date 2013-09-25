/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes an
 *  Ocean object.  Descriptions of the methods you must implement appear below.
 *  They include constructors of the form
 *
 *      public RunLengthEncoding(int i, int j, int starveTime);
 *      public RunLengthEncoding(int i, int j, int starveTime,
 *                               int[] runTypes, int[] runLengths) {
 *      public RunLengthEncoding(Ocean ocean) {
 *
 *  that create a run-length encoding of an Ocean having width i and height j,
 *  in which sharks starve after starveTime timesteps.
 *
 *  The first constructor creates a run-length encoding of an Ocean in which
 *  every cell is empty.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts an Ocean object into a run-length encoding of that object.
 *
 *  See the README file accompanying this project for additional details.
 */

public class RunLengthEncoding {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */

private DListNode curNode;
private int rWid;
private int rHei;
private int rsTim;
private DList oceanList = new DList(rWid, rHei, rsTim);


  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with three parameters) is a constructor that creates
   *  a run-length encoding of an empty ocean having width i and height j,
   *  in which sharks starve after starveTime timesteps.
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   */

  public RunLengthEncoding(int i, int j, int starveTime) {
	  this.rWid =i;
	  this.rHei = j;
	  this.rsTim = starveTime;
    this.oceanList = new DList(i, j, starveTime);
    oceanList.insertEnd(new DListNode(Ocean.EMPTY, starveTime, i*j));
    curNode = oceanList.getFirst();
  }

  /**
   *  RunLengthEncoding() (with five parameters) is a constructor that creates
   *  a run-length encoding of an ocean having width i and height j, in which
   *  sharks starve after starveTime timesteps.  The runs of the run-length
   *  encoding are taken from two input arrays.  Run i has length runLengths[i]
   *  and species runTypes[i].
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   *  @param runTypes is an array that represents the species represented by
   *         each run.  Each element of runTypes is Ocean.EMPTY, Ocean.FISH,
   *         or Ocean.SHARK.  Any run of sharks is treated as a run of newborn
   *         sharks (which are equivalent to sharks that have just eaten).
   *  @param runLengths is an array that represents the length of each run.
   *         The sum of all elements of the runLengths array should be i * j.
   */

  public RunLengthEncoding(int i, int j, int starveTime,
                           int[] runTypes, int[] runLengths) {
	  this.rWid = i;
	  this.rHei= j;
	  this.rsTim = starveTime;
	  this.oceanList = new DList(i, j, starveTime);
	  for (int k = 0; k < runTypes.length; k++) {
    	DListNode newNode = new DListNode(runTypes[k], starveTime, runLengths[k]);
    	oceanList.insertEnd(newNode);
    }
    curNode = oceanList.getFirst();
  }

  /**
   *  restartRuns() and nextRun() are two methods that work together to return
   *  all the runs in the run-length encoding, one by one.  Each time
   *  nextRun() is invoked, it returns a different run (represented as an
   *  array of two ints), until every run has been returned.  The first time
   *  nextRun() is invoked, it returns the first run in the encoding, which
   *  contains cell (0, 0).  After every run has been returned, nextRun()
   *  returns null, which lets the calling program know that there are no more
   *  runs in the encoding.
   *
   *  The restartRuns() method resets the enumeration, so that nextRun() will
   *  once again enumerate all the runs as if nextRun() were being invoked for
   *  the first time.
   *
   *  (Note:  Don't worry about what might happen if nextRun() is interleaved
   *  with addFish() or addShark(); it won't happen.)
   */

  /**
   *  restartRuns() resets the enumeration as described above, so that
   *  nextRun() will enumerate all the runs from the beginning.
   */

  public void restartRuns() {
	  curNode = oceanList.getFirst();
  }

  /**
   *  nextRun() returns the next run in the enumeration, as described above.
   *  If the runs have been exhausted, it returns null.  The return value is
   *  an array of two ints (constructed here), representing the type and the
   *  size of the run, in that order.
   *  @return the next run in the enumeration, represented by an array of
   *          two ints.  The int at index zero indicates the run type
   *          (Ocean.EMPTY, Ocean.SHARK, or Ocean.FISH).  The int at index one
   *          indicates the run length (which must be at least 1).
   */

  public int[] nextRun() {
	   int[] next = new int[2];
	   if (curNode != null) {
		  next[0] = curNode.getType();
		  next[1] = curNode.getLength();	
		  curNode = curNode.getNext();
	  } else {
		  return null;
	  }
	   return next;
  }

  /**
   *  toOcean() converts a run-length encoding of an ocean into an Ocean
   *  object.  You will need to implement the three-parameter addShark method
   *  in the Ocean class for this method's use.
   *  @return the Ocean represented by a run-length encoding.
   */

  public Ocean toOcean() {
    Ocean nOcean = new Ocean(this.rWid, this.rHei, this.rsTim);
    int x = 0;
    int y = 0;
    int w = nOcean.width(); //Why make everything private here?????????
    curNode = this.oceanList.getFirst();
    while (curNode != null) {
    	int tp = curNode.getType();
    	int lg = curNode.getLength();
    	for (int i =0; i < lg; i++) {
    		if (x == w) {
    			x = 0;
    			y++;
    		}
    		if (tp == Ocean.FISH) {
    			nOcean.addFish(x, y);
    		} else if (tp == Ocean.SHARK) {
    			nOcean.addShark(x, y, curNode.getFeed());
    		}
    		x++;	
    	}
    	curNode = curNode.getNext();
    }
    
    return nOcean;
  }

  /**
   *  The following method is required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of an input Ocean.  You will need to implement
   *  the sharkFeeding method in the Ocean class for this constructor's use.
   *  @param sea is the ocean to encode.
   */

  public RunLengthEncoding(Ocean sea) {
	 int wid = sea.width();
	 int hei = sea.height();
	 rWid = wid;
	 rHei = hei;
	 int sTim = sea.starveTime();
	 rsTim = sTim;
	 System.out.println(wid);
    oceanList = new DList(wid, hei, sTim);
    int x = 1;
    int y = 0;
    oceanList.insertEnd(new DListNode(sea.cellContents(0, 0), sea.sharkFeeding(x, y), 1 ));
    for (int i = 0; i < wid*hei-1; i++) {
    	if (x == wid) {
    		x = 0;
    		y++;
    	}
    	DListNode tmp = oceanList.getTail();
    	int curType = sea.cellContents(x, y);
    	if (curType == tmp.getType()) {
    		if (curType == Ocean.SHARK && sea.sharkFeeding(x,y) != tmp.getFeed()) {
    			oceanList.insertEnd(new DListNode(Ocean.SHARK, sea.sharkFeeding(x,y), 1));
    			tmp = oceanList.getTail();
    		} else {
    			tmp.setLength(tmp.getLength()+1);
    		}
    	} else {
    		oceanList.insertEnd(new DListNode(curType, sea.sharkFeeding(x, y), 1));
    		tmp = oceanList.getTail();
    	}
    	x++;
    }
    curNode = oceanList.getFirst();  
    check();
  }

  /**
   *  The following methods are required for Part IV.
   */

  /**
   *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
   *  cell is already occupied, leave the cell as it is.  The final run-length
   *  encoding should be compressed as much as possible; there should not be
   *  two consecutive runs of sharks with the same degree of hunger.
   *  @param x is the x-coordinate of the cell to place a fish in.
   *  @param y is the y-coordinate of the cell to place a fish in.
   */

  public void addFish(int x, int y) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
	  Ocean o = this.toOcean();
	  int Fish = Ocean.FISH;
	  int count = 0;
	  int num = 0;
	  num = x + x*y;
	  if (o.cellContents(x, y) == Ocean.EMPTY) {
		  DListNode iter = this.oceanList.getHead();
		  count = iter.getLength();
		  while (iter != null && count < num) {
			  count = count+iter.getLength();
			  iter = iter.getNext();
		  }
		  if (iter.getPrev() != null){
			  if (count == num) {
				  if (iter.getPrev().getType() == Fish && iter.getType()!= Fish) {
					  iter.getPrev().setLength(iter.getPrev().getLength()+1);
				  } else if (iter.getPrev().getType() == Fish && iter.getType() == Fish) {
					  iter.getPrev().setLength(iter.getLength()+1+ iter.getPrev().getLength());
				  } else if (iter.getType() == Fish) {
					  iter.setLength(iter.getLength()+1);
				  } else {
					  DListNode nNode = new DListNode(Fish, 10, 1);
					  this.oceanList.insertBefore(iter, nNode);
				  }
			  } else {
				  DListNode i = new DListNode(Ocean.EMPTY, 10, iter.getPrev().getLength()-(count-x));
				  DListNode j =  new DListNode(Ocean.EMPTY, 10, count-x);
				  DListNode k = new DListNode(Fish, 10, 1);
				  this.oceanList.insertAfter(iter.getPrev().getPrev(), i);
				  this.oceanList.insertAfter(i, k);
				  this.oceanList.insertAfter(k, j);
				  j.setNext(iter);
			  }
		  }
	  }
    check();
  }

  /**
   *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
   *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
   *  just eaten.  If the cell is already occupied, leave the cell as it is.
   *  The final run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs of sharks with the same degree
   *  of hunger.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   */

  public void addShark(int x, int y) {
	  Ocean o = this.toOcean();
	  int Shark = Ocean.SHARK;
	  int count = 0;
	  int num = 0;
	  num = x + x*y;
	  if (o.cellContents(x, y) == Ocean.EMPTY) {
		  DListNode iter = this.oceanList.getHead();
		  count = iter.getLength();
		  while (iter != null && count < num) {
			  count = count+iter.getLength();
			  iter = iter.getNext();
		  }
		  if (iter.getPrev() != null){
			  if (count == num) {
				  if (iter.getPrev().getType() == Shark && iter.getType()!= Shark) {
					  iter.getPrev().setLength(iter.getPrev().getLength()+1);
				  } else if (iter.getPrev().getType() == Shark && iter.getType() == Shark) {
					  iter.getPrev().setLength(iter.getLength()+1+ iter.getPrev().getLength());
				  } else if (iter.getType() == Shark) {
					  iter.setLength(iter.getLength()+1);
				  } else {
					  DListNode nNode = new DListNode(Shark, this.rsTim, 1);
					  this.oceanList.insertBefore(iter, nNode);
				  }
			  } else {
				  DListNode i = new DListNode(Ocean.EMPTY, 10, iter.getPrev().getLength()-(count-x));
				  DListNode j =  new DListNode(Ocean.EMPTY, 10, count-x);
				  DListNode k = new DListNode(Shark, this.rsTim, 1);
				  this.oceanList.insertAfter(iter.getPrev().getPrev(), i);
				  this.oceanList.insertAfter(i, k);
				  this.oceanList.insertAfter(k, j);
				  j.setNext(iter);
			  }
		  }
	  }
    check();
  }


  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same contents, or if the sum of all run
   *  lengths does not equal the number of cells in the ocean.
   */

  private void check() {
	  DListNode iterator = this.oceanList.getHead();
	  int count = 0;
	  while (iterator != null) {
		  if (iterator.getLength() < 1) {
			  throw new Error("Length too short.");
		  }
		  if (iterator.getPrev() != null && iterator.getPrev().getType() == iterator.getType() ) {
			  if (iterator.getType() != Ocean.SHARK) {
				  throw new Error("Two consecutive runs of same type.");
			  } else if (iterator.getType() == Ocean.SHARK && iterator.getFeed() == iterator.getPrev().getFeed()) {
				  throw new Error("Two consecutive runs of hungry sharks."); 
			  }
		  }
		  count = count + iterator.getLength();
		  iterator = iterator.getNext();
	  }
		  if (count != rWid*rHei) {
			  throw new Error("length does not equal to sum.");
		  }
  }

}
