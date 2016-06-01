package baseDataStruct;

/**
 * Created by huangzhengyue on 5/28/16.
 */
public class FeedBack {

    private Vect2 position;
    private double reward;
    private boolean terminal;
    //constructor
    public FeedBack(Vect2 position, double reward, boolean terminal) {
        this.position = position;
        this.reward = reward;
        this.terminal = terminal;
    }

    public FeedBack(int x,int y, double reward, boolean terminal) {
        this.position = new Vect2(x, y);
        this.reward = reward;
        this.terminal = terminal;
    }
    //getters
    public Vect2 getPosition() {
        return position;
    }

    public double getReward() {
        return reward;
    }

    public boolean isTerminal() {
        return terminal;
    }
}
