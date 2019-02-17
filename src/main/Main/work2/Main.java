package work2;

import java.io.*;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        CheckBMI check = new CheckBMI();
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayList<String> ANS = new ArrayList<String>();
        BufferedReader br = null;
        FileReader fr = null;
        String box = "";
        String temp = "";
        try {

            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader("data.txt");
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.equals("")) {
                } else {
                    arrayList.add(sCurrentLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : arrayList) {
            if (s.contains("4n+1") || s.contains("4n+1") || s.contains("6n+1") || s.contains("5n") || s.contains("7n")) {
                ANS.add(s);
            } else if (s.contains("Box")) {
                String[] strings = s.split(" ");
                box = strings[1];
                ANS.add(s);
            } else if (s.contains("Min X:")) {
                ANS.add(s);
            } else if (s.contains("Min Y:")) {
                ANS.add(s);
            } else {
                // 2: (2,2)
                String[] strings = s.split(" ");
                String[] strings1 = strings[1].split(",");
                String x = "";
                String y = "";
                for (int i = 1; i < (strings1[0]).length(); i++) {
                    x += strings1[0].charAt(i);
                }
                for (int i = 0; i < (strings1[1]).length() - 1; i++) {
                    y += strings1[1].charAt(i);
                }

                s += " " + check.checkb(x, y, box);

                ANS.add(s);
            }
        }
        try {
            FileWriter fileWriter = new FileWriter("ans.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String an : ANS) {
                bufferedWriter.write(an);
                bufferedWriter.newLine();
                System.out.println(an);
            }
            bufferedWriter.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

}

