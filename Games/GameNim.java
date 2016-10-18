import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TC on 10/17/2016.
 */
public class GameNim extends Game {

	private boolean isValid(int n) {return n > 0;}

	public boolean isWinState(State state){
		StateNim nState = (StateNim) state;
		return (nState.num == 0);
	}

	public static boolean isLoseState(State state) {
		StateNim nState = (StateNim) state;
		return (nState.num == 1);
	}

	int WinningScore = 10;
	int LosingScore = -10;
	int NeutralScore = 0;

	public GameNim() {currentState = new StateNim();}

	public boolean isStuckState(State state){
//		Shouldn't have stuck state
		return false;
	}

	public Set<State> getSuccessors(State state){
		if (isWinState(state)) return null;

		Set<State> successors = new HashSet<State>();
		StateNim nState = (StateNim) state;

		StateNim successor_state;

		for (int i = 1; i < 4; i++) {
			StateNim temp = new StateNim(nState);
			temp.num -=i;
			if (isValid(temp.num)) successors.add(temp);
		}

		return successors;
	}

	public double eval(State state){
		if(isWinState(state)) {
			//player who made last move
			int previous_player = state.player;

			if (previous_player==0) //computer wins
				return WinningScore;
			else //human wins
				return LosingScore;
		}
		return NeutralScore;
	}

	private static boolean isValid(Game game) {
		StateNim state = (StateNim) game.currentState;
		return state.num > 0;
	}

	private static StateNim readInput(BufferedReader in, Game game) throws IOException{
		int input= 0;
		while (true) {
			System.out.print("Enter your *valid* move: ");
			input = Integer.parseInt(in.readLine());
			if (input > 0 && input < 4 && isValid(game)) break;
			else {
				System.out.println("That isn't a valid input, please try again.");
			}
		}

		StateNim nextState = new StateNim((StateNim)game.currentState);
		nextState.player = 0;
		nextState.num -= input;

		return nextState;
	}

	public static void main(String[] args) throws Exception {
		Game game = new GameNim();
		Search search = new Search(game);
		int depth = 8;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StateNim nextState = null;

			switch(game.currentState.player) {
				case 1:
					nextState = readInput(in, game);
					System.out.println("Human: \n" + nextState);
					break;
				case 0:
					nextState = (StateNim)search.bestSuccessorState(depth);
					nextState.player = 1;
					System.out.println("Computer: \n" + nextState);
					break;
				default:
					System.out.println("FATAL ERROR");
					System.exit(0);
			}

			game.currentState = nextState;

			if (isLoseState(game.currentState)) {
				if (game.currentState.player == 1) {
					System.out.println("Computer wins!");
				}
				else {
					System.out.println("You win!");
				}
				break;
			}

			if (game.isWinState(game.currentState)) {
				if (game.currentState.player == 0) {
					System.out.println("Computer wins!");
				}
				else {
					System.out.println("You win!");
				}
				break;
			}
		}
	}
}
