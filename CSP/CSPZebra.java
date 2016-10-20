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

		if (X.equals("englishman") && Y.equals("red") && !x.equals(y)) return false;
		if (X.equals("spaniard") && Y.equals("dog") && !x.equals(y)) return false;
		if (X.equals("coffee") && Y.equals("green") && !x.equals(y)) return false;
		if (X.equals("ukranian") && Y.equals("tea") && !x.equals(y)) return false;
		if (X.equals("green") && Y.equals("ivory")  && (Integer) x != (Integer) y + 1) {return false;}
		if (X.equals("ivory") && Y.equals("green")  && (Integer) x + 1 != (Integer) y) {return false;}
		if (X.equals("old-gold") && Y.equals("snails") && !x.equals(y)) return false;
		if (X.equals("kools") && Y.equals("yellow") && !x.equals(y)) return false;
		if (X.equals("milk") && (Integer) x != 3) return false;
		if (X.equals("norwegian") && (Integer) x != 1) return false;
		if (X.equals("chesterfield") && Y.equals("fox") && (Math.abs((Integer) x - (Integer) y) != 1)) return false;
		if (X.equals("kools") && Y.equals("horse") && (Math.abs((Integer) x - (Integer) y) != 1)) return false;
		if (X.equals("lucky-strike") && Y.equals("orange-juice") && !x.equals(y)) return false;
		if (X.equals("japanese") && Y.equals("parliament") && !x.equals(y)) return false;
		if (X.equals("norwegian") && Y.equals("blue") && (Math.abs((Integer) x - (Integer) y) != 1)) return false;
		if (X.equals("blue") && Y.equals("norwegian") && (Math.abs((Integer) x - (Integer) y) != 1)) return false;

		// Uniqueness Constraints
		if (varCol.contains(X) && varCol.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
		if (varDri.contains(X) && varDri.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
		if (varNat.contains(X) && varNat.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
		if (varPet.contains(X) && varPet.contains(Y) && !X.equals(Y) && x.equals(y)) return false;
		if (varCig.contains(X) && varCig.contains(Y) && !X.equals(Y) && x.equals(y)) return false;

		return true;
	}

	private static void AddDomain(CSPZebra csp, Set<Object> set) {
		for(Object X : set) csp.addDomain(X, dom);
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

		AddDomain(csp, varCol);

		AddDomain(csp, varDri);

		AddDomain(csp, varNat);

		AddDomain(csp, varPet);

		AddDomain(csp, varCig);

		csp.addBidirectionalArc("englishman", "red");
		csp.addBidirectionalArc("spaniard", "dog");
		csp.addBidirectionalArc("coffee", "green");
		csp.addBidirectionalArc("ukranian", "tea");
		csp.addBidirectionalArc("green", "ivory");
		csp.addBidirectionalArc("old-gold", "snails");
		csp.addBidirectionalArc("kools", "yellow");
		csp.addBidirectionalArc("chesterfield", "fox");
		csp.addBidirectionalArc("kools", "horse");
		csp.addBidirectionalArc("lucky-strike", "orange-juice");
		csp.addBidirectionalArc("japanese", "parliament");
		csp.addBidirectionalArc("norwegian", "blue");


		AddUniquenessConstraint(csp, varCol);

		AddUniquenessConstraint(csp, varDri);

		AddUniquenessConstraint(csp, varNat);

		AddUniquenessConstraint(csp, varPet);

		AddUniquenessConstraint(csp, varCig);

		CSPSearch cspSearch = new CSPSearch(csp);
		System.out.println(cspSearch.BacktrackingSearch());
	}
}
