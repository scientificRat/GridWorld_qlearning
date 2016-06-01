package environment;

import baseDataStruct.*;

import java.util.Random;

/**
 * Created by huangzhengyue on 5/29/16.
 */
public class Environment {
    private GameMap gameMap;
    private int[][] direction={
            {-1,0},
            {0,1},
            {1,0},
            {0,-1}};

    private double noise;
    private double livingReward;
    private Random randomEngine=new Random();

    private boolean isValid(int nx,int ny){
        if( nx >= gameMap.getHeight() || nx<0 || ny>=gameMap.getWidth() || ny < 0 || gameMap.isObstacle(nx, ny)){
            return false;
        }
        else {
            return true;
        }
    }


    //constructor
    public Environment(GameMap gameMap, double noise, double livingReward) {
        this.gameMap = gameMap;
        this.noise = noise;
        this.livingReward = livingReward;
    }


    public FeedBack getActionFeedback(Vect2 posNow, Action action){

        if(gameMap.isTerminal(posNow)){
            return new FeedBack(posNow,gameMap.getTerminalReward(posNow),true);
        }
        //if not terminal state
        //the position ready to go
        int nx,ny;
        double r=randomEngine.nextDouble();
//        System.out.println("debug:"+r);
        if(r<=noise){
            if(randomEngine.nextBoolean()){
                //go left
                nx=posNow.getX()+direction[(action.ordinal()+3)%4][0];
                ny=posNow.getY()+direction[(action.ordinal()+3)%4][1];

            }
            else {
                //go right
                nx=posNow.getX()+direction[(action.ordinal()+1)%4][0];
                ny=posNow.getY()+direction[(action.ordinal()+1)%4][1];
            }
        }
        else{
            nx=posNow.getX()+direction[action.ordinal()][0];
            ny=posNow.getY()+direction[action.ordinal()][1];
        }
        //check if valid
        if(!isValid(nx,ny)){
            nx=posNow.getX();
            ny=posNow.getY();
        }

        //return feedback
        return new FeedBack(nx,ny,livingReward,false);
    }

    ///getters setters
    public int getMapWidth(){
        return gameMap.getWidth();
    }

    public int getMapHeight(){
        return gameMap.getHeight();
    }

    public BlockState getBlockState(int x,int y){
        return this.gameMap.getState(x,y);
    }

    public BlockState getBlockState(Vect2 pos){
        return this.gameMap.getState(pos.getX(),pos.getY());
    }

}
