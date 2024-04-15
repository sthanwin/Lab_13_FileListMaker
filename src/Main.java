import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main {
    static ArrayList<String> list = new ArrayList<>();
    static Scanner in = new Scanner(System.in);
    static boolean needsToBeSaved = false;
    public static void main(String[] args) {



        final String menu = "A-Add  D-Delete  V-View  Q-Quit  O-Open  S-Save  C-Clear";
        boolean done = false;
        String cmd;
        do{
            displayList();
            cmd = SafeInput.getRegExString(in, menu, "[AaDdVvQqOoSsCc]");
            cmd = cmd.toUpperCase();

            switch (cmd){
                case "A":
                    String item = SafeInput.getNonZeroLenString(in, "Enter the item to add: ");
                    list.add(item);
                    needsToBeSaved = true;
                    break;
                case "D":
                    int index = SafeInput.getRangedInt(in, "Enter the item you want to remove: ",1,list.size());
                    list.remove(index-1);
                    needsToBeSaved = true;
                    break;
                case "V":
                    displayList();
                    break;
                case "Q":
                    boolean saveConfirm = SafeInput.getYNConfirm(in, "Do you want to save your list?");
                    if (saveConfirm)
                        saveList();
                    else {
                        boolean quit = SafeInput.getYNConfirm(in, "Do you want to quit?");
                        if (quit)
                            done = true;

                    }
                    break;
                case "O":
                    openFile();
                    break;
                case "S":
                    saveList();
                    break;
                case "C":
                    if (!needsToBeSaved) {
                        list.clear();
                        System.out.println("Your list has been cleared");
                    }
                    else {
                        boolean userConfirm = SafeInput.getYNConfirm(in, "Your list has not been saved yet. Clearing the list will terminate everything in your current list. Do you want to save your list first?");

                        if (userConfirm)
                            saveList();
                        else {
                            list.clear();
                            System.out.println("Your list has been cleared");
                        }
                    }
                    break;
            }

        } while (!done);

    }

    private static void displayList() {
        if(list.size() != 0) {

            for (int i = 0; i < list.size(); i++) {
                System.out.printf("\n%-3d%-30s", i + 1, list.get(i));

            }
        }
        else
            System.out.println("List is empty");
    }
    private static void saveList() {
        try {
            String fileName = SafeInput.getNonZeroLenString(in, "Name the file that you want to save your list in");
            fileName = fileName + ".txt";
            File myFile = new File(fileName);
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
                System.out.println("Saving list in file " + myFile.getName());



            } else {
                System.out.println("File already exists, overwriting");
            }
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(fileName));
            for (String rec : list) {
                writer.write(rec, 0, rec.length());
                writer.newLine();

            }
            writer.close();
            System.out.println("Data file written!");
            needsToBeSaved = false;


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }
    }
    private static void openFile() {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec;

        if (!needsToBeSaved) {
            try {
                File workingDirectory = new File(System.getProperty("user.dir"));

                chooser.setCurrentDirectory(workingDirectory);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    selectedFile = chooser.getSelectedFile();
                    Path file = selectedFile.toPath();
                    InputStream in =
                            new BufferedInputStream(Files.newInputStream(file, CREATE));
                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(in));
                    list.clear();

                    while(reader.ready())
                    {
                        rec = reader.readLine();
                        list.add(rec);
                    }

                } else {
                    System.out.println("Failed to choose a file to process");
                    System.out.println("Run the program again!");
                    System.exit(0);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found!!!");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            boolean userConfirm = SafeInput.getYNConfirm(in, "Your list has not been saved yet. Opening the file will terminate your current list. Do you want to save your list first?");
            if (userConfirm)
                saveList();
            else {
                try {
                    File workingDirectory = new File(System.getProperty("user.dir"));

                    chooser.setCurrentDirectory(workingDirectory);

                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        selectedFile = chooser.getSelectedFile();
                        Path file = selectedFile.toPath();
                        InputStream in =
                                new BufferedInputStream(Files.newInputStream(file, CREATE));
                        BufferedReader reader =
                                new BufferedReader(new InputStreamReader(in));
                        list.clear();

                        while (reader.ready()) {
                            rec = reader.readLine();
                            list.add(rec);
                        }

                    } else {
                        System.out.println("Failed to choose a file to process");
                        System.out.println("Run the program again!");
                        System.exit(0);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File not found!!!");
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}