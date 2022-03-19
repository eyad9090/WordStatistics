package wordstatistics;

import java.util.ArrayList;

public class Syncro_uptodate {
    public synchronized void synch_uptodate(ArrayList<String> S)
    {
        HomeGui.update(S);
    }
}
