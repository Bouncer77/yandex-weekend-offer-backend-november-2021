package com.bouncer77.games;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.currentTimeMillis;

public class TaskC {
    public static void main(String[] args) {

        // long start = currentTimeMillis();

        Scanner scanner = new Scanner(System.in);


        // final int n = Integer.parseInt(args[0]);
        final int n = scanner.nextInt();
        // final int m = Integer.parseInt(args[1]);
        final int m = scanner.nextInt();


        Cell[][] cellMass = new Cell[n][m];

        // System.out.println("Размеры поля " + n + " на " + m + "    = " + (n * m));

        // init
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j++) {
                cellMass[i][j] = new Cell(i, j);
            }
        }

        if (!scanner.hasNext()) {
            System.out.println(1);
            System.exit(0);
        }

        // int mines = Integer.parseInt(args[2]);
        int mines = scanner.nextInt();


        for (int i = 0, k = 3; i < mines; i++) {

            if (!scanner.hasNext()) {
                break;
            }

            // int x = Integer.parseInt(args[k++]);
            int x = scanner.nextInt();

            if (!scanner.hasNext()) {
                break;
            }

            // int y = Integer.parseInt(args[k++]);
            int y = scanner.nextInt();

            --x;
            --y;
            if (k > args.length) {
                break;
            }

            cellMass[x][y].setMine(true);
            cellMass[x][y].setOpen(true);
        }

        long start = currentTimeMillis();

        // Cell.printField(cellMass, n, m);

        int res = 0;
        while(Cell.isGameСontinue(cellMass, n, m)) {
            Cell cell = Cell.clickOnCloseCell(cellMass, n, m);
            if (Objects.isNull(cell)) {
                // System.out.println("Метод isGameСontinue или clickOnCloseCell работает с ошибкой");
                break;
            }
            Cell.openOtherCells(cellMass, n, m, cell);


            res++;
        }

        System.out.println(res);
        // long end = currentTimeMillis();
        // System.out.println((end - start) / 1000.0);
    }
}

class Cell {

    private int x;
    private int y;
    private boolean isMine;
    private boolean isOpen;

    public Cell(int x, int y) {
        this(x, y, false);
    }

    public Cell(int x, int y, boolean isMine) {
        this.x = x;
        this.y = y;
        this.isMine = isMine;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", isMine=" + isMine +
                ", isOpen=" + isOpen +
                '}';
    }

    public static void printField(Cell[][] cellMass, int n, int m) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.println(cellMass[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean isGameСontinue(Cell[][] cellMass, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!cellMass[i][j].isOpen()) {
                    return true;
                }
            }
        }
        return false; // game is over - you win
    }

    public static Cell clickOnCloseCell(Cell[][] cellMass, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!cellMass[i][j].isOpen()) {
                    cellMass[i][j].setOpen(true);
                    // System.out.println("Click Cell = " + cellMass[i][j]);
                    return cellMass[i][j];
                }
            }
        }
        return null;
    }

    public static void openOtherCells(Cell[][] cellMass, int n, int m, Cell cell) {
        cell.setOpen(true);

        final int x = cell.getX();
        final int y = cell.getY();

        openNeighboringCell(cellMass, n, m, x - 1, y);
        openNeighboringCell(cellMass, n, m, x + 1, y);
        openNeighboringCell(cellMass, n, m, x, y - 1);
        openNeighboringCell(cellMass, n, m, x, y + 1);
    }

    public static void openNeighboringCell(Cell[][] cellMass, int n, int m, int x, int y) {
        if (x >= 0 && x < n && y >= 0 && y < m) {
            if (!cellMass[x][y].isMine() && !cellMass[x][y].isOpen()) {
                openOtherCells(cellMass, n, m, cellMass[x][y]);
            }
        }
    }
}
