/**
 * Represents an enum listing all the VendingItems
 * @author xwu319
 * @version 1.0
 */
public enum VendingItem {
    Lays(1.5),
    Doritos(1.5),
    Coke(2.5),
    Ramblin_Reck_Toy(180.75),
    Rubiks_Cube(30.0),
    Rat_Cap(15.0),
    FASET_Lanyard(10.0),
    Graphing_Calculator(120.0),
    UGA_Diploma(0.1),
    Pie(3.14),
    Clicker(55.55),
    Cheetos(1.25),
    Sprite(2.5),
    Red_Bull(4.75),
    Ramen(3.15);

    private final double price;

    /**
     * contrcuct a VendingItem that can be selected
     * @param price the price of the VendingItem
     */
    VendingItem(double price) {
        this.price = price;
    }

    /**
     * @return the price of the vending item
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the string representation of the vending item
     */
    public String toString() {
        return this.name() + ": $" + price;
    }
}