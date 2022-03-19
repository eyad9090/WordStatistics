package wordstatistics;

import java.io.File;
import java.util.ArrayList;

public class Read_dir {

    private ArrayList<String> pathsAns = new ArrayList<String>();
    private ArrayList<String> Files = new ArrayList<String>();

    //-------get all directories-------------
    private void paths(String path, int level) {
        if (level == 2) {
            return;
        }
        File dir = new File(path);
        if (level == 0) {
            pathsAns.add(path);
        }
        String[] children = dir.list();
        for (int i = 0; i < children.length; i++) {
            File check = new File(path + "\\" + children[i]);
            if (check.isDirectory()) {
                pathsAns.add(check.getAbsolutePath());
                paths(check.getAbsolutePath(), level + 1);
            }
        }
    }

    //-------read all files------
    public ArrayList<String> getFiles(String path, boolean cheek) {
        if (cheek) {
            paths(path, 0);
        }
        else{
            pathsAns.add(path);
        }
        for (int i = 0; i < pathsAns.size(); i++) {
            File dir = new File(pathsAns.get(i));
            String[] children = dir.list();
            for (int i2 = 0; i2 < children.length; i2++) {
                String filename = children[i2];
                File check = new File(dir.getAbsoluteFile() + "\\" + filename);
                if (!check.isDirectory()) {
                    Files.add(check.getAbsolutePath());
                }
            }
        }
        return Files;
    }
}
