/**
 * Created by TC on 10/17/2016.
 */
public class StateNim extends State{
	public int num = 13;

	public StateNim() {}

	public StateNim(StateNim state) {
		num = state.num;
		player = state.player;
	}

	public String toString() {
		return String.format("%d", num);
	}
}
