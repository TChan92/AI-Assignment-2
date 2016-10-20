import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TC on 10/18/2016.
 */
public class CSPZebra extends CSP{

	static Integer[] dom = {1,2,3,4,5};

	static Set<Object> varCol = new HashSet<Object>(
			Arrays.asList(new String[] {"blue", "green", "ivory", "red", "yellow"}));
	static Set<Object> varDri = new HashSet<Object>(
			Arrays.asList(new String[] {"coffee", "milk", "orange-juice", "tea", "water"}));
	static Set<Object> varNat = new HashSet<Object>(
			Arrays.asList(new String[] {"englishman", "japanese", "norwegian", "spaniard", "ukranian"}));
	static Set<Object> varPet = new HashSet<Object>(
			Arrays.asList(new String[] {"dog", "fox", "horse", "snails", "zebra"}));
	static Set<Object> varCig = new HashSet<Object>(
			Arrays.asList(new String[] {"chesterfield", "kools", "lucky-strike", "old-gold", "parliament"}));

	public boolean isGood(Object X, Object Y, Object x, Object y) {
		// Not mentioned in constraints
		if (!C.containsKey(X)) return true;

		// Check to see if there is arc between X and Y
		if (!C.get(X).contains(Y)) return true;

		// Englishman lives in red house
		if (X.equals("englishman") && Y.equals("red") && !x.equals(y)) return false;
		if (X.equals("spaniard") && Y.equals("dog") && !x.equals(y)) return false;
		if (X.equals("coffee") && Y.equals("green") && !x.equals(y)) return false;
		if (X.equals("ukranian") && Y.equals("tea") && !x.equals(y)) return false;
		if (X.equals("green") && Y.equals("ivory")  && (Integer) x + 1 != (Integer) y) return false;
		if (X.equals("old-gold") && Y.equals("snails") && !x.equals(y)) return false;
		if (X.equals("kools") && Y.equals("yellow") && !x.equals(y)) return false;
		if (X.equals("lucky-strike") && Y.equals("orange-juice") && !x.equals(y)) return false;
		if (X.equals("japanese") && Y.equals("parliament") && !x.equals(y)) return false;

		// Uniqueness Constraints
		if (varCol.contains(X) && varCol.contains(Y) && !X.equals(Y) && x.equals(y)) return false;

		return true;
	}

	private static void AddUniquenessConstraint(CSPZebra csp, Set<Object> set) {
		for(Object X: set) {
			for (Object Y : set) {
				csp.addBidirectionalArc(X, Y);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		CSPZebra csp = new CSPZebra();

		for(Object X : varCol) csp.addDomain(X, dom);
		for(Object X : varDri) csp.addDomain(X, dom);
		for(Object X : varNat) csp.addDomain(X, dom);
		for(Object X : varPet) csp.addDomain(X, dom);
		for(Object X : varCig) csp.addDomain(X, dom);

		csp.addBidirectionalArc("englishman", "red");
		csp.addBidirectionalArc("spaniard", "dog");
		csp.addBidirectionalArc("coffee", "green");
		csp.addBidirectionalArc("ukranian", "tea");
		// Green house is directly to the right of the ivory house
		csp.addBidirectionalArc("old-gold", "snails");
		csp.addBidirectionalArc("kools", "yellow");
		// Milk is being drunk in the middle house
		// Norwegian lives in the first house to the left
		// Chesterfield smoker lives next to the fox owner
		// Kools are smoked in the house next to the house where the horse is kept
		csp.addBidirectionalArc("lucky-strike", "orange-juice");
		csp.addBidirectionalArc("japanese", "parliament");
		// Norwegian lives next to the blue house


		AddUniquenessConstraint(csp, varCol);

		AddUniquenessConstraint(csp, varDri);

		AddUniquenessConstraint(csp, varNat);

		AddUniquenessConstraint(csp, varPet);

		AddUniquenessConstraint(csp, varCig);

		CSPSearch cspSearch = new CSPSearch(csp);
		System.out.println(cspSearch.BacktrackingSearch());
	}
}
