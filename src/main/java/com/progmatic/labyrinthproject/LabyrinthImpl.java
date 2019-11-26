package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {
    private CellType[][] map ;
    private Coordinate playerposition;

    public LabyrinthImpl() {

    }

    @Override
    public int getWidth() {
        int windth = -1;
        if (map != null) {
            windth = map[0].length;
        }
        return windth;
    }

    @Override
    public int getHeight() {
        int height = -1;
        if (map != null) {
            height = map.length;
        }
        return height;
    }

    @Override
    public void loadLabyrinthFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int width = Integer.parseInt(sc.nextLine());
            int height = Integer.parseInt(sc.nextLine());
            map = new CellType[height][width];
            for (int hh = 0; hh < height; hh++) {
                String line = sc.nextLine();
                for (int ww = 0; ww < width; ww++) {
                    switch (line.charAt(ww)) {
                        case 'W':
                            map[ww][hh] = CellType.WALL;
                            break;
                        case 'E':
                            map[ww][hh] = CellType.END;
                            break;
                        case 'S':
                            map[ww][hh] = CellType.START;
                            playerposition = new Coordinate(hh, ww);
                            break;
                        case ' ':
                            map[ww][hh] = CellType.EMPTY;
                            break;
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {
        CellType cellType;
        try {
            cellType = map[c.getCol()][c.getRow()];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CellException(c.getRow(), c.getCol(), "A megadott cella nem szerepel a labirintus térképén");
        }

        return cellType;
    }

    @Override
    public void setSize(int width, int height) {
        map = new CellType[height][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[j][i] = CellType.EMPTY;
            }
        }
    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {
        try {
            if (type.equals(CellType.START)) {
                map[c.getCol()][c.getRow()] = type;
                playerposition = c;
            } else {
                map[c.getCol()][c.getRow()] = type;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CellException(c.getRow(), c.getCol(), "A megadott cella nem szerepel a labirintus térképén");
        }
    }

    @Override
    public Coordinate getPlayerPosition() {
        Coordinate c = new Coordinate(playerposition.getCol(), playerposition.getRow());
        return c;
    }

    @Override
    public boolean hasPlayerFinished() {
        if (map[playerposition.getCol()][playerposition.getRow()].equals(CellType.END)) {
            return true;
        }
        return false;
    }

    @Override
    public List<Direction> possibleMoves() {
        ArrayList<Direction> validDirection = new ArrayList<>();
        Coordinate actualPos = new Coordinate(playerposition.getCol(), playerposition.getRow());

        if (actualPos.getRow() != map[0].length-1 && map[actualPos.getCol()][actualPos.getRow() + 1].equals(CellType.EMPTY)) {
            validDirection.add(Direction.SOUTH);
        }
        if (actualPos.getRow() != 0 && map[actualPos.getCol()][actualPos.getRow() - 1].equals(CellType.EMPTY)) {
            validDirection.add(Direction.NORTH);
        }
        if (actualPos.getCol() != map.length-1 && map[actualPos.getCol() + 1][actualPos.getRow()].equals(CellType.EMPTY)) {
            validDirection.add(Direction.EAST);
        }
        if (actualPos.getCol() != 0 && map[actualPos.getCol() - 1][actualPos.getRow()].equals(CellType.EMPTY)) {
            validDirection.add(Direction.WEST);
        }
        return validDirection;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {
        ArrayList<Direction> possibleMoves = (ArrayList<Direction>) possibleMoves();
        boolean possibleStep = false;
        for (int i = 0; i < possibleMoves.size(); i++) {
            if (!possibleStep && direction.equals(possibleMoves.get(i))) {
                possibleStep = true;
            }
        }
        if (direction.equals(Direction.EAST) && possibleStep) {
            playerposition = new Coordinate(playerposition.getCol()+1, playerposition.getRow());
        }else if (direction.equals(Direction.WEST) && possibleStep) {
            playerposition = new Coordinate(playerposition.getCol()-1, playerposition.getRow() - 1);
        }else if (direction.equals(Direction.SOUTH) && possibleStep) {
            playerposition = new Coordinate(playerposition.getCol() , playerposition.getRow()+1);
        }else if (direction.equals(Direction.NORTH) && possibleStep) {
            playerposition = new Coordinate(playerposition.getCol() , playerposition.getRow()-1);
        }else {
            throw new InvalidMoveException();
        }
    }

}
