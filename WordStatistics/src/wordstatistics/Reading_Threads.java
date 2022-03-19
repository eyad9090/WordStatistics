package wordstatistics;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reading_Threads {

    public void reading_threads(String S, boolean ch) {
        ArrayList<String> files = new ArrayList();
        Read_dir R = new Read_dir();
        files = R.getFiles(S,ch);
        Read_runnable f1 = new Read_runnable();
        f1.setfiles(files);
        Thread t1 = new Thread(f1);
        Thread t2 = new Thread(f1);
        Thread t3 = new Thread(f1);
        Thread t4 = new Thread(f1);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        }
    }
