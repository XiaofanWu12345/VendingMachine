import java.util.Random;

/**
 * Represents a vending machine newly initialized.
 * @author xwu319
 * @version 1.0
 */
public class VendingMachine {
    private static double totalSales = 0.0;
    private VendingItem[][][] grid;
    private int freeChance;
    private Random rand;

    /**
     * Construct a VendingMachine object and initialize the fields in the class
     * and call the restock method to fill the positions on the vending machine
     * with randomly selected vending items
     */
    public VendingMachine() {
        this.grid = new VendingItem[6][3][5];
        this.freeChance = 0;
        this.rand = new Random();
        restock();
    }

    /**
     * Vend an item from the vending machine, update the positions of items on
     * the vending machine and update the total sales across vending machines
     * to represent the success of this venture.
     * @param code the code that represents the position of the item vended
     * @return the item vended
     */
    public VendingItem vend(String code) {
        char letter = code.charAt(0);
        int i1 = letter - 65;
        int i2 = Integer.parseInt(code.substring(1)) - 1;
        if (i1 < 0 || i1 > 5 || i2 < 0 || i2 > 2) {
            System.out.println("Error: please enter a valid vending code.");
            return null;
        }
        if (grid[i1][i2][0] == null) {
            System.out.println("No item left in the position you selected.");
            return null;
        } else {
            VendingItem object = grid[i1][i2][0];
            if (!free()) {
                totalSales += object.getPrice();
            }
            for (int i = 0; i < 4; i++) {
                grid[i1][i2][i] = grid[i1][i2][i + 1];
            }
            grid[i1][i2][4] = null;
            return object;
        }
    }

    private boolean free() {
        int randnum = rand.nextInt(100);
        if (randnum < freeChance) {
            freeChance = 0;
            return true;
        } else {
            freeChance += 1;
            return false;
        }
    }

    /**
     * a method that restock the new vending machine initialized by picking
     * random items from the values of the enum VendingItem
     */
    public void restock() {
        for (int n1 = 0; n1 < 6; n1++) {
            for (int n2 = 0; n2 < 3; n2++) {
                for (int n3 = 0; n3 < 5; n3++) {
                    if (grid[n1][n2][n3] == null) {
                        int randint = rand.nextInt(15);
                        VendingItem item = VendingItem.values()[randint];
                        grid[n1][n2][n3] = item;
                    }
                }
            }
        }
    }

    /**
     * @return the total sales across vending machines
     */
    public static double getTotalSales() {
        return totalSales;
    }

    /**
     * @return the number of items left in the vending machine
     */
    public int getNumberOfItems() {
        int num = 0;
        for (int n1 = 0; n1 < 6; n1++) {
            for (int n2 = 0; n2 < 3; n2++) {
                for (int n3 = 0; n3 < 5; n3++) {
                    if (grid[n1][n2][n3] != null) {
                        num += 1;
                    }
                }
            }
        }
        return num;
    }

    /**
     * @return the total value of the items left in the vending machine
     */
    public double getTotalValue() {
        double value = 0.0;
        for (int n1 = 0; n1 < 6; n1++) {
            for (int n2 = 0; n2 < 3; n2++) {
                for (int n3 = 0; n3 < 5; n3++) {
                    if (grid[n1][n2][n3] != null) {
                        value += grid[n1][n2][n3].getPrice();
                    }
                }
            }
        }
        return value;
    }

    /**
     * @return a string representation of the VendingMachine for use in
     * simulation
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("----------------------------------------------------------"
            + "------------\n");
        s.append("                            VendaTron 9000                "
            + "            \n");
        for (int i = 0; i < grid.length; i++) {
            s.append("------------------------------------------------------"
                + "----------------\n");
            for (int j = 0; j < grid[0].length; j++) {
                VendingItem item = grid[i][j][0];
                String str = String.format("| %-20s ",
                    (item == null ? "(empty)" : item.name()));
                s.append(str);
            }
            s.append("|\n");
        }
        s.append("----------------------------------------------------------"
            + "------------\n");
        s.append(String.format("There are %d items with a total "
            + "value of $%.2f.%n", getNumberOfItems(), getTotalValue()));
        s.append(String.format("Total sales across vending machines "
            + "is now: $%.2f.%n", getTotalSales()));
        return s.toString();
    }
}

