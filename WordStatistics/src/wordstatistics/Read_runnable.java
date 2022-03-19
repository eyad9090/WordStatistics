package wordstatistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Read_runnable implements Runnable {

    private ArrayList<String> files = new ArrayList<String>();
    private static final int slp = 350;
    Syncro_uptodate Start = new Syncro_uptodate();
    public void setfiles(ArrayList<String> S) {
        files = S;
    }
    public synchronized int gefiles_size() {
        return files.size();
    }
    public synchronized String getfile_name() {
        String temp = files.get(0);
        files.remove(0);
        return temp;

    }
    @Override
    public void run() {
        while (gefiles_size() != 0) {
            String file = getfile_name();
            int cnt_is = 0;
            int cnt_are = 0;
            int cnt_you = 0;
            String longest = "";
            String Shortest = "";
            try {
                File myObj = new File(file);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] childreen = data.split(" ");
                    ArrayList<String> uptodate = new ArrayList<String>();
                    for (int i = 0; i < childreen.length; i++) {
                        if (childreen[i].equals("is")) {
                            cnt_is++;
                        } else if (childreen[i].equals("you")) {
                            cnt_you++;
                        } else if (childreen[i].equals("are")) {
                            cnt_are++;
                        }
                        if (Shortest.length() == 0) {
                            Shortest = childreen[i];
                        }
                        if (childreen[i].length() > longest.length()) {
                            longest = childreen[i];
                        }
                        if (childreen[i].length() < Shortest.length()) {
                            Shortest = childreen[i];
                        }
                    }
                    String[] full_path = file.split("\\\\");
                    uptodate.add(full_path[full_path.length - 1]);
                    uptodate.add(full_path[full_path.length - 2]);
                    uptodate.add(longest);
                    uptodate.add(Shortest);
                    uptodate.add(Integer.toString(cnt_is));
                    uptodate.add(Integer.toString(cnt_are));
                    uptodate.add(Integer.toString(cnt_you));
                    uptodate.add(Integer.toString(childreen.length));
                    cnt_is = 0;
                    cnt_are = 0;
                    cnt_you = 0;
                    Start.synch_uptodate(uptodate);
                    try {
                        Thread.sleep(slp);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Read_runnable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        }
    }

}
