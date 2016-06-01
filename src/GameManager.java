import baseDataStruct.GameMap;
import display.BoardDisplay;
import display.ConsoleDisplay;
import display.printable.PrintableMap;
import display.printable.PrintableNode;
import environment.Environment;
import input.ConsoleInput;
import input.Input;
import robot.QLearningRobot;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by huangzhengyue on 5/29/16.
 */
public class GameManager {

    private BoardDisplay boardDisplay;
    private QLearningRobot qLearningRobot;
    private Environment environment;

    public GameManager( BoardDisplay boardDisplay, QLearningRobot qLearningRobot, Environment environment) {
        this.boardDisplay = boardDisplay;
        this.qLearningRobot = qLearningRobot;
        this.environment = environment;

    }

    private PrintableMap generatePrintableMap(){
        int width=environment.getMapWidth();
        int height=environment.getMapHeight();
        PrintableNode[][] printableNodes=new PrintableNode[height][];
        for (int i = 0; i < height; i++) {
            printableNodes[i]=new PrintableNode[width];
            for (int j = 0; j < width ; j++) {
                printableNodes[i][j]=new PrintableNode();
            }
        }
        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < width ; j++) {
                printableNodes[i][j].setBlockState(environment.getBlockState(i,j))
                                    .setValue(qLearningRobot.getValue(i,j))
                                    .setqValue(qLearningRobot.getAllQvalueOfOneBlock(i,j))
                                    .setPolicy(qLearningRobot.getPolicy(i,j));
            }
        }
        return new PrintableMap(width,height,qLearningRobot.getCurrentPos(),printableNodes);
    }


    public void runIterations(int iters){
        qLearningRobot.runIterations(iters);
        boardDisplay.printValueMap(generatePrintableMap());
    }
    public void runIterationsWithoutDisplay(int iters){
        qLearningRobot.runIterations(iters);
    }

    public void runSteps(int steps){
        qLearningRobot.runSteps(steps);
        boardDisplay.printValueMap(generatePrintableMap());
    }

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        Input input=new ConsoleInput();

        double alpha=0.2;
        double epsilon=0.5;
        double discount=0.9;

        double noise=0.2;
        double living_reward=0;

        System.out.println("Please Input The Map:");
        System.out.print("width:");
        int width=input.readWidth();
        System.out.print("height:");
        int height=input.readHeight();
        System.out.println("map:(#:wall block, 0:normal block, other number: terminal block)");
        ArrayList<String> strMap= input.readMap(width*height);

        GameMap gameMap=new GameMap(width,height,strMap);

        Environment environment=new Environment(gameMap,noise,living_reward);

        QLearningRobot robot=new QLearningRobot(alpha,epsilon,discount,environment);

        GameManager gameManager=new GameManager(new ConsoleDisplay(),robot,environment);

        while (true){
            System.out.printf("next iterations:\n>>>");
            int iters=in.nextInt();
            if(iters<0){
                break;
            }
            else {
                gameManager.runIterations(iters);
                System.out.printf("iterations:%d\n",robot.getIterations());

            }

        }
        robot.saveQvalueDataTrace();


    }


}
