package robot;

import baseDataStruct.*;
import environment.Environment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by huangzhengyue on 5/28/16.
 */
public class QLearningRobot {

    private double alpha;
    private double epsilon;
    private double discount;
    private Environment environment;
    private long steps=0;
    private long iterations=0;
    private ValueNode[][] valueNodes;
    private Vect2 startPos=new Vect2(0,0);
    private Vect2 currentPos=new Vect2(0,0);
    private Random randomEngine=new Random();
    private ArrayList<String> trace=new ArrayList<>();


    private Action takeAction(){
        //nextDouble() will generate random double in(0,1)
        if(randomEngine.nextDouble() < epsilon){
            //go random
            return Action.values()[randomEngine.nextInt(4)];
        }
        else {
            return valueNodes[currentPos.getX()][currentPos.getY()].getPolicy();
        }
    }
    private void updateQValue(Action action,FeedBack feedBack){
        int x=currentPos.getX();
        int y=currentPos.getY();
        if(feedBack.isTerminal()){
            valueNodes[x][y].setTerminalValue(feedBack.getReward());
            return;
        }

//        alpha-=0.00001;
//        if(alpha<0){
//            alpha=0;
//        }

        double oldQValue=valueNodes[x][y].getQvalue(action);
        double newQValue=(1-alpha)*oldQValue + alpha * ( feedBack.getReward() +valueNodes[feedBack.getPosition().getX()][feedBack.getPosition().getY()].getValue()*discount );
        valueNodes[x][y].setQvalue(action,newQValue);
    }

    private boolean goOneStepAndReturnIsTerminal(){
        Action action=takeAction();
        FeedBack feedBack=environment.getActionFeedback(currentPos,action);
        updateQValue(action,feedBack);
        currentPos=feedBack.getPosition();
        steps++;
        return feedBack.isTerminal();
    }

    private void restartGame(){
        iterations++;

        //generate random start place
        int index=0;
        int length=environment.getMapWidth()*environment.getMapHeight();
        do{
            index=randomEngine.nextInt(length);
        }while (environment.getBlockState(index /environment.getMapWidth(),index%environment.getMapWidth()) != BlockState.NORMAL);

        startPos.setX(index/environment.getMapWidth());
        startPos.setY(index%environment.getMapWidth());

        currentPos=startPos;
    }

    //constructor
    public QLearningRobot(double alpha, double epsilon, double discount, Environment environment) {
        this.alpha = alpha;
        this.epsilon = epsilon;
        this.discount=discount;
        this.environment = environment;
        valueNodes=new ValueNode[environment.getMapHeight()][];
        for (int i = 0; i < environment.getMapHeight(); i++) {
            valueNodes[i]=new ValueNode[environment.getMapWidth()];
            for (int j = 0; j < environment.getMapWidth(); j++) {
                valueNodes[i][j]=new ValueNode();
            }
        }
    }


    public void runIterations(int iters){
        int x=0;
        int y=0;
        while (iters>0){
            while (!goOneStepAndReturnIsTerminal());
            trace.add(valueNodes[x][y].getQvalue(Action.UP)+"\t"+
                    valueNodes[x][y].getQvalue(Action.RIGHT)+"\t"+
                    valueNodes[x][y].getQvalue(Action.DOWN)+"\t"+
                    valueNodes[x][y].getQvalue(Action.LEFT));
            restartGame();
            iters--;
        }
    }

    public void runSteps(int deltaSteps){
        while(deltaSteps > 0){
            if(goOneStepAndReturnIsTerminal()){
                restartGame();
            }
            deltaSteps--;
        }
    }

    //setters getters

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public long getSteps() {
        return steps;
    }

    public long getIterations() {
        return iterations;
    }

    public Vect2 getCurrentPos() {
        return currentPos;
    }

    public void setEnvironment(Environment environment){
        this.environment=environment;
        valueNodes=new ValueNode[environment.getMapHeight()][];
        for (int i = 0; i < environment.getMapHeight(); i++) {
            valueNodes[i]=new ValueNode[environment.getMapWidth()];
            for (int j = 0; j < environment.getMapWidth(); j++) {
                valueNodes[i][j]=new ValueNode();
            }
        }
    }
    //very dangerous method
//    public ValueNode[][] getValueNodes(){
//        return valueNodes;
//    }

    public double getValue(int x,int y){
        return valueNodes[x][y].getValue();
    }
    public double getValue(Vect2 pos){
        return valueNodes[pos.getX()][pos.getY()].getValue();
    }

    public double[] getAllQvalueOfOneBlock(int x,int y){
        return valueNodes[x][y].getAllQValue();
    }
    public double[] getAllQvalueOfOneBlock(Vect2 pos){
        return valueNodes[pos.getX()][pos.getY()].getAllQValue();
    }

    public Action getPolicy(int x,int y){
        return valueNodes[x][y].getPolicy();
    }

    public Action getPolicy(Vect2 pos){
        return valueNodes[pos.getX()][pos.getY()].getPolicy();
    }

    public void saveQvalueDataTrace(){
        FileOutputStream out= null;
        try {
            out = new FileOutputStream("trace.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream p=new PrintStream(out);

        Iterator<String> it= trace.iterator();
        while (it.hasNext()) {
            p.println(it.next());
        }

        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
