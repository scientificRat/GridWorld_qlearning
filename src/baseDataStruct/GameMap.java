package baseDataStruct;

import java.util.ArrayList;

/**
 * Created by huangzhengyue on 5/29/16.
 */
public class GameMap {
    private int width;
    private int height;
    private double[][] reward;
    private BlockState[][] states;

    public GameMap(int width,int height,ArrayList<String> strmap){

        this.width=width;
        this.height=height;
        reward=new double[height][width];
        states=new BlockState[height][width];
//        for (int i = 0; i < height; i++) {
//            states[i]=new BlockState[width];
//            for (int j = 0; j < width; j++) {
//                states[i][j]=new BlockState();
//            }
//        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width ; j++) {
                int index=i*width+j;
                if (strmap.get(index).equals("0")){
                    states[i][j]=BlockState.NORMAL;
                    reward[i][j]=0;
                }
                else if(strmap.get(index).equals("#")){
                    states[i][j]=BlockState.OBSTACLE;
                    reward[i][j]=0;
                }
                else{
                    states[i][j]=BlockState.TERMINAL;
                    double value=Double.valueOf(strmap.get(index));
                    reward[i][j]=value;
                }

            }
        }

    }
    public BlockState getState(int x,int y){
        return states[x][y];
    }

    public BlockState getState(Vect2 pos){
        return states[pos.getX()][pos.getY()];
    }

    public boolean isObstacle(int x,int y){
        if(getState(x, y)==BlockState.OBSTACLE){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean isObstacle(Vect2 pos){
        return isObstacle(pos.getX(),pos.getY());
    }

    public boolean isTerminal(int x,int y){
        if(getState(x, y)==BlockState.TERMINAL){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean isTerminal(Vect2 pos){
        return isTerminal(pos.getX(),pos.getY());
    }

    public double getTerminalReward(int x,int y){
        return reward[x][y];
    }
    public double getTerminalReward(Vect2 pos){
        return reward[pos.getX()][pos.getY()];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
