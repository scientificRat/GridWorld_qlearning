package input;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by huangzhengyue on 5/31/16.
 */
public class ConsoleInput implements Input {
    Scanner inputer=new Scanner(System.in);
    @Override
    public ArrayList<String> readMap(int length) {
        ArrayList<String> map=new ArrayList<>();
        for (int i = 0; i <length ; i++) {
            map.add(inputer.next());
        }
        return map;
    }

    @Override
    public int readWidth() {
        return inputer.nextInt();
    }

    @Override
    public int readHeight() {
        return inputer.nextInt();
    }
}
