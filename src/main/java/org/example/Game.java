package org.example;


import org.example.Nodes.AnimalNode;
import org.example.Nodes.Node;
import org.example.Nodes.QuestionNode;
import org.example.Services.ProgressSaver;

import java.util.Scanner;

public class Game {

    private Node root;

    public Game(){
        this.root = ProgressSaver.loadTree(root);
        this.printTree();
        if (this.root == null){
            this.root = new AnimalNode("Elephant");
        }
    }
    public void play(){
        do{
            System.out.println("Think of an animal, and I will try to guess it");
            root.interact(this);
            System.out.println("Do you want to play again? (yes/no)");
        }while(getYesOrNoInput().equals("yes"));
        ProgressSaver.saveTree(root);
    }

    public String getYesOrNoInput(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();

        while(!input.equals("yes") && !input.equals("no")){
            System.out.println("Please enter yes or no");
            input = scanner.nextLine().trim().toLowerCase();
        }
        return input;
    }

    public void updateTree(AnimalNode incorrectAnimal){
        Scanner scanner = new Scanner(System.in);
        System.out.println("I give up. What was the name of the animal");
        String userAnimal = scanner.nextLine().trim();

        System.out.println("Please provide a question that distinguishes a " + userAnimal +
                " from a " + incorrectAnimal.getAnimalName() + ".");
        String newQuestion = scanner.nextLine().trim();

        System.out.println("For a " + userAnimal + ", what is the answer to your question? ");
        String newQuestionAnswer = this.getYesOrNoInput();

        AnimalNode newAnimal = new AnimalNode(userAnimal);
        QuestionNode newQuestionNode;

        if (newQuestionAnswer.equals("yes")){
            newQuestionNode = new QuestionNode(newQuestion, newAnimal, incorrectAnimal);
        }else{
            newQuestionNode = new QuestionNode(newQuestion, incorrectAnimal, newAnimal);
        }

        replaceNode(root, incorrectAnimal, newQuestionNode);
    }


    private boolean replaceNode (Node currentNode, AnimalNode targetNode, QuestionNode newQuestion){
        if (currentNode instanceof QuestionNode){
            QuestionNode questionNode = (QuestionNode) currentNode;

            if (questionNode.getYes() == targetNode){
                questionNode.setYes(newQuestion);
                return true;
            }else if (questionNode.getNo() == targetNode){
                questionNode.setNo(newQuestion);
                return true;
            } else {
            return replaceNode(questionNode.getYes(), targetNode, newQuestion) ||
                    replaceNode(questionNode.getNo(), targetNode, newQuestion);
            }
        }
        if (currentNode == targetNode){
            root = newQuestion;
            return true;
        }
        return false;
    }



        public void printTree() {
            System.out.println("Animal Guessing Game Tree:");
            printTree(root, "");
        }

        private void printTree(Node node, String indent) {
            if (node instanceof QuestionNode) {
                QuestionNode questionNode = (QuestionNode) node;
                System.out.println(indent + "Q: " + questionNode.getQuestion());
                printTree(questionNode.getYes(), indent + "  ");
                printTree(questionNode.getNo(), indent + "  ");
            } else if (node instanceof AnimalNode) {
                AnimalNode animalNode = (AnimalNode) node;
                System.out.println(indent + "A: " + animalNode.getAnimalName());
            }
        }



}
