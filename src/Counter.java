/**
 * @author Tom Bekor 213338726 <tom.bekor@gmail.com>
 * @version 13.0.2
 * @since 2020-06-19
 */
public class Counter {
    private int count;

    /**
     * creates and initialize a new Counter.
     * @param num the num that we start counting from.
     */
    public Counter(int num) {
        this.count = num;
    }

    /**
     * add number to current count.
     * @param number the number we want to add.
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * subtract number from current count.
     * @param number the number we want to subtract.
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * returns the current count.
     * @return the current count.
     */
    public int getValue() {
        return count;
    }

    @Override
    public String toString() {
        return String.valueOf(count);
    }
}