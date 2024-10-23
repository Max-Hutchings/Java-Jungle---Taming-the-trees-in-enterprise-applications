package org.example.Nodes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Game;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalNode extends Node {

    private String animalName;


    @Override
    public void interact(Game game) {
        System.out.println("Is your animal a " + animalName + "?");
        String response = game.getYesOrNoInput();

        if (response.equals("yes")){
            System.out.println("I guessed it!");
        }else{
            game.updateTree(this);
        }
    }
}
