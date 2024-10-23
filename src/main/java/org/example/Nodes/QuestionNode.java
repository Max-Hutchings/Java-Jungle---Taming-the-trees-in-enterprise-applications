package org.example.Nodes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Game;
import org.example.Nodes.Node;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionNode extends Node {

    private String question;
    private Node yes;
    private Node no;

    @Override
    public void interact(Game game){
        System.out.println(question);
        String response = game.getYesOrNoInput();
        if (response.equals("yes")){
                this.yes.interact(game);
        }else{
            this.no.interact(game);
        }
    }

}
