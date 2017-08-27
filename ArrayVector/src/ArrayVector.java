/**
 * Created by ultra on 14.07.2017.
 */
/**
 * Task authors
 * @author Andrey Gavrilov
 * @author Alexander Kharichkin
 * @author Alexey Evdokimov
 */
public interface ArrayVector {
    /**
     * Sets the vectors elements
     * Transferring array doesn't copy
     * @param elements not null
     */
    void set(double... elements);
    /**
     * Return without copy elements of vector
     */
    double[] get();
    /**
     * Return copy of vector, which doesn't lead to changes in initial vector
     */
    ArrayVector clone();
    /**
     * Return size of a vector
     */
    int getSize();

    /**
     * Change element by an index
     * @param index In case of out of border
     *  а) if index<0, nothing happens
     *  б) if index>=0, size of array should be increased to make an index the last index of our array
     */
    void set(int index, double value);
    /**
     * Return element by index
     * @param index In case of out of border
     * there must be an exception ArrayIndexOutOfBoundsException
     */
    double get(int index) throws ArrayIndexOutOfBoundsException;

    /**
     * Return max element of array
     */
    double getMax();
    /**
     * Return min elemen of array
     */
    double getMin();
    /**
     * Sort all elements of array
     */
    void sortAscending();

    /**
     * Multiply vector by number
     * @param factor
     */
    void mult(double factor);
    /**
     * Concatenate 2 vectors
     * If vectors have different sizes, the last elements should not be processed
     * If our vector is bigger, then don't change last elements
     * @param anotherVector not null
     * @return reference on itself
     */
    ArrayVector sum(ArrayVector anotherVector);
    /**
     * Returns scalar multiplication
     * If vectors have different sizes, the last elements should not be processed
     * @param anotherVector not null
     */
    double scalarMult(ArrayVector anotherVector);
    /**
     * Return Euclidian norm
     */
    double getNorm();
}