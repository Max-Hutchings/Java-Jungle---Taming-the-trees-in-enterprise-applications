package org.example.Nodes;

import org.example.Game;

import java.io.Serializable;

public abstract class Node implements Serializable {
    public abstract void interact(Game game);
}
