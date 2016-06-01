package input;

import java.util.ArrayList;

/**
 * Created by huangzhengyue on 5/31/16.
 */
public interface Input {
    ArrayList<String> readMap(int length);
    int readWidth();
    int readHeight();
}
