package org.example.Services;

import org.example.Nodes.Node;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProgressSaver {
    public static void saveTree(Node root) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("animal_game_tree.ser"))) {
            oos.writeObject(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Node loadTree(Node root) {
        File file = new File("animal_game_tree.ser");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (Node) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
