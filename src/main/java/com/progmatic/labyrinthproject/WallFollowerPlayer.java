package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

import java.util.Scanner;
import java.util.SortedMap;

public class WallFollowerPlayer implements Player {
    @Override
    public Direction nextMove(Labyrinth l) {/*
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Válaszd ki meylik kezed tartod a falon. Írd be a megfelelő gyorsgombot");
            System.out.println("Jobbkéz : 1");
            System.out.println("Balkéz : 2");
           switch (sc.nextInt()){
               case
           }

        }
        */
        return null ;
    }
    private Direction leftHand(){
        return null ;
    }

    private Direction rightHand(){
        return null ;
    }
}
