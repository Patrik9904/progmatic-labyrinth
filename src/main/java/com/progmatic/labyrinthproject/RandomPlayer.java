package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

import java.util.ArrayList;

public class RandomPlayer implements Player {
    @Override
    public Direction nextMove(Labyrinth l) {
        ArrayList<Direction> possibleMovesList = (ArrayList<Direction>) l.possibleMoves();
        int randomChoice = ((int) (Math.random() * possibleMovesList.size()));
        return possibleMovesList.get(randomChoice);
    }
}
