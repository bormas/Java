/**
 * Created by ultra on 14.07.2017.
 */
import java.util.Arrays;
public class ArrayVectorImpl implements ArrayVector {

    private double[] arr;
    /**
     * Sets the vectors elements
     * Transferring array doesn't copy
     * @param elements not null
     */
    public void set(double... elements)
    {
        arr = elements;
    }
    /**
     * Return without copy elements of vector
     */
    public double[] get()
    {
        return arr;
    }
    /**
     * Return copy of vector, which doesn't lead to changes in initial vector
     */
    public ArrayVector clone()
    {
        ArrayVectorImpl res = new ArrayVectorImpl();
        res.set(arr.clone());
        return res;
    }
    /**
     * Return size of a vector
     */
    public int getSize()
    {
        return arr.length;
    }

    /**
     * Change element by an index
     * @param index In case of out of border
     *  а) if index<0, nothing happens
     *  б) if index>=0, size of array should be increased to make an index the last index of our array
     */
    public void set(int index, double value)
    {
        if (index < 0)
            return;
        if (index >= arr.length)
        {
            arr = Arrays.copyOf(arr, index+1);
            arr[index] = value;
            return;
        }
        arr[index] = value;
        return;
    }
    /**
     * Return element by index
     * @param index In case of out of border
     * there must be an exception ArrayIndexOutOfBoundsException
     */
    public double get(int index) throws ArrayIndexOutOfBoundsException
    {
        try {
            return arr[index];
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw ex;
        }
    }

    /**
     * Return max element of array
     */
    public double getMax()
    {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }
    /**
     * Return min elemen of array
     */
    public double getMin()
    {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] < min)
                min = arr[i];
        return min;
    }
    /**
     * Sort all elements of array
     */
    public void sortAscending()
    {
        double tmp;
        for(int i = arr.length-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++) {
            /*Сравниваем элементы попарно,
              если они имеют неправильный порядок,
              то меняем местами*/
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * Multiply vector by number
     * @param factor
     */
    public void mult(double factor)
    {
        for (int i = 0; i < arr.length; i++)
            arr[i] *= factor;
    }
    /**
     * Concatenate 2 vectors
     * If vectors have different sizes, the last elements should not be processed
     * If our vector is bigger, then don't change last elements
     * @param anotherVector not null
     * @return reference on itself
     */
    public ArrayVector sum(ArrayVector anotherVector)
    {
        int minSize = arr.length > anotherVector.getSize() ? anotherVector.getSize() : arr.length;
        for (int i = 0; i < minSize; i++)
            arr[i] += anotherVector.get(i);
        return this;
    }
    /**
     * Returns scalar multiplication
     * If vectors have different sizes, the last elements should not be processed
     * @param anotherVector not null
     */
    public double scalarMult(ArrayVector anotherVector)
    {
        int minSize = arr.length > anotherVector.getSize() ? anotherVector.getSize() : arr.length;
        double res = 0;
        for (int i = 0; i < minSize; i++)
            res += arr[i] * anotherVector.get(i);
        return res;
    }
    /**
     * Return Euclidian norm
     */
    public double getNorm()
    {
        return Math.sqrt(scalarMult(this.clone()));
    }
}
