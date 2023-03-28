import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered linked
 * list to simulate transit
 * 
 *
 * 
 */
public class Transit {
	
	/**
	 * Makes a layered linked list representing the given arrays of train stations, bus
	 * stops, and walking locations. Each layer begins with a location of 0, even though
	 * the arrays don't contain the value 0.
	 * 
	 * @param trainStations Int array listing all the train stations
	 * @param busStops Int array listing all the bus stops
	 * @param locations Int array listing all the walking locations (always increments by 1)
	 * @return The zero node in the train layer of the final layered linked list
	 */
	public static TNode makeList(int[] trainStations, int[] busStops, int[] locations) {

		if (trainStations.length == 0 || locations.length == 0 || busStops.length == 0) {return null;}

		TNode transit0 = new TNode (0);
		TNode transit1 = new TNode (0);
		TNode transit2 = new TNode (0);
		TNode ret = transit2;
		
		transit2.down = transit1;
		transit1.down = transit0;

		int a = 0;
		int b = 0;

		for (int c = 0; c <= locations.length; c++) {

			if (c == locations.length) {
				transit0.next = null;
				break;
			}
			
			transit0.next = new TNode (locations[c]);
			transit0.down = null;
			
			if (a < busStops.length && locations[c] == busStops[a]) {
				
				transit1.next = new TNode (busStops[a]);
				transit1.next.down = transit0.next;
				
				if (a == busStops.length - 1) {transit1.next.next = null;}
			
				if (b < trainStations.length && trainStations[b] == locations[c]) {
					transit2.next = new TNode (trainStations[b]);
					transit2.next.down = transit1.next;

					if (b == trainStations.length - 1) {
						transit2.next.next = null;}

					transit2 = transit2.next;
					b++;}
				
				transit1 = transit1.next;
				a++;
			}
		
			transit0 = transit0.next;
		}
		
		return ret;
	}
	
	/**
	 * Modifies the given layered list to remove the given train station but NOT its associated
	 * bus stop or walking location. Do nothing if the train station doesn't exist
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param station The location of the train station to remove
	 */
	public static void removeTrainStation(TNode trainZero, int station) {

		if (trainZero == null) {
			return;
		}

		while (trainZero.next != null) {
			if (trainZero.next.location == station) {
				if (trainZero.next.next != null) {
					trainZero.next = trainZero.next.next;
				}
				else {
					trainZero.next = null;
					break;
				}
				
			}
			trainZero = trainZero.next;
		}
	}

	/**
	 * Modifies the given layered list to add a new bus stop at the specified location. Do nothing
	 * if there is no corresponding walking location.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param busStop The location of the bus stop to add
	 */
	public static void addBusStop(TNode trainZero, int busStop) {

		if (trainZero == null) {
			return;
		}

		trainZero = trainZero.down;
		while (trainZero.next != null) {
			if (trainZero.next.location > busStop && trainZero.location < busStop) {
				
				TNode walkZero = trainZero.down;
				TNode ins = new TNode (busStop);

				while (walkZero.location < busStop) {
					walkZero = walkZero.next;
				}

				ins.next = trainZero.next;
				trainZero.next = ins;
				ins.down = walkZero;
			}
			trainZero = trainZero.next;
		}

		if (trainZero.location < busStop) {
			trainZero.next = new TNode (busStop);
			TNode walkZero = trainZero.down;
			while (walkZero.location < busStop) {
					walkZero = walkZero.next;
			}
			trainZero.next.down = walkZero;
			trainZero.next.next = null;
		}

	}
	
	/**
	 * Determines the optimal path to get to a given destination in the walking layer, and 
	 * collects all the nodes which are visited in this path into an arraylist. 
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param destination An int representing the destination
	 * @return
	 */

	public static ArrayList<TNode> bestPath(TNode trainZero, int destination) {

		ArrayList<TNode> ret = new ArrayList<TNode> ();

		if (trainZero == null) {return null;}

		while (trainZero != null) {
			ret.add(trainZero);

			if (trainZero.location == destination) {
				ret.remove(trainZero);
				while (trainZero != null) {
					ret.add(trainZero);
					trainZero = trainZero.down;
				}
				break;
			}

			if (trainZero.next == null || trainZero.next.location > destination) {
				trainZero = trainZero.down;
				ret.add(trainZero);
				if (trainZero.next == null || trainZero.next.location > destination) {
					trainZero = trainZero.down;
					ret.add(trainZero);
				}
			}

			trainZero = trainZero.next;		

		}
		return ret;
	}

	/**
	 * Returns a deep copy of the given layered list, which contains exactly the same
	 * locations and connections, but every node is a NEW node.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @return
	 */
	public static TNode duplicate(TNode trainZero) {

		if (trainZero == null) {
			return null;
		}
		
		int tsize = 0;
		int bsize = 0;
		int wsize = 0;

		TNode busZero = trainZero.down;
		TNode walkZero = trainZero.down.down;
		TNode temp = trainZero;

		while (walkZero.next != null) {
			walkZero = walkZero.next;
			wsize++;
		}
		
		while (busZero.next != null) {
			busZero = busZero.next;
			bsize++;
		}

		while (trainZero.next != null) {
			trainZero = trainZero.next;
			tsize++;
		}

		int [] trains = new int [tsize];
		int [] bus = new int [bsize];
		int [] walk = new int [wsize];
		
		TNode transit0 = temp.next;
		TNode bus0 = temp.down.next;
		TNode walk0 = temp.down.down.next;
		
		for (int a = 0; a < tsize; a++) {
			trains[a] = transit0.location;
			transit0 = transit0.next;
		}

		for (int b = 0; b < bsize; b++) {
			bus[b] = bus0.location;
			bus0 = bus0.next;
		}

		for (int c = 0; c < wsize; c++) {
			walk[c] = walk0.location;
			walk0 = walk0.next;
		}

		return makeList(trains, bus, walk);
		

	}

	/**
	 * Modifies the given layered list to add a scooter layer in between the bus and
	 * walking layer.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param scooterStops An int array representing where the scooter stops are located
	 */
	public static void addScooter(TNode trainZero, int[] scooterStops) {

		TNode fW = trainZero.down.down;
		TNode sW = new TNode (0);
		TNode bW = trainZero.down;
		int z = 0;

		if (trainZero == null || scooterStops.length == 0) {
			return;
		}
		
		while (fW != null) {
			if (fW.location == sW.location) {
				sW.down = fW;
				if (z < scooterStops.length) {
					sW.next = new TNode (scooterStops[z]);
				}
				else {
					sW.next = null;
				}if (bW != null && bW.location == sW.location) {
					bW.down = sW;
					if (bW.next != null) {
						bW = bW.next;
					}}if (z < scooterStops.length) {
					sW = sW.next;
					z++;}}

			fW = fW.next;
			
		}
	}
}