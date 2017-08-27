import java.util.Arrays;
import java.util.regex.*;

/**
 * Created by ultra on 14.07.2017.
 */
public class ComplexNumberImpl implements ComplexNumber {
    private double re, im;
    public ComplexNumberImpl(){
        re = 0;
        im = 0;
    }

    public ComplexNumberImpl(double re1, double im1)
    {
        re = re1;
        im = im1;
    }

    public ComplexNumberImpl(String value)
    {
        this.set(value);
    }

    //Getters and setters
    /**
     * @return real part of this complex number
     */
    public double getRe()
    {
        return re;
    }

    /**
     * @return imaginary part of this complex number
     */
    public double getIm()
    {
        return im;
    }

    /**
     * @return true if this complex number has real part only (otherwise false)
     */
    public boolean isReal()
    {
        return im == 0;
    }
    /**
     * Sets both real and imaginary part of this number.
     * @param re
     * @param im
     */
    public void set(double re, double im)
    {
        this.re = re;
        this.im = im;
    }

    /**
     * Parses the given string value and sets the real and imaginary parts of this number accordingly.<br/>
     * The string format is "re+imi", where re and im are numbers (floating point or integer) and 'i' is a special symbol
     *  denoting imaginary part (if present, it's always the last character in the string).<br/>
     * Both '+' and '-' symbols can be the first characters of re and im; but '*' symbol is NOT allowed.<br/>
     * Correct examples: "-5+2i", 1+i", "+4-i", "i", "-3i", "3". Incorrect examples: "1+2*i", "2+2", "j".<br/>
     * Note: explicit exception generation is an OPTIONAL requirement for this task,
     *   but NumberFormatException can be thrown by {@link Double#parseDouble(String)}).<br/>
     * Note: it is not reasonable to use regex while implementing this method: the parsing logic is too complicated.
     * @param value
     * @throws NumberFormatException if the given string value is incorrect
     */
    public void set(String value) throws NumberFormatException
    {
        boolean firstPositive = true;
        boolean secondPositive = true;
        if (value.charAt(0) == '-')
            firstPositive = false;
        if (value.substring(1).contains("-"))
            secondPositive = false;

        // double
        Pattern p_re = Pattern.compile("\\d+([.]\\d*)?");
        Matcher m_re = p_re.matcher(value);
        // [double]i or i
        Pattern p_im = Pattern.compile("(\\d+([.]\\d*)?)?[i]");
        Matcher m_im = p_im.matcher(value);
        // all valid examples
        Pattern p_full = Pattern.compile("[-+]?\\d+([.]\\d*)?[-+](\\d+([.]\\d*)?)?[i]");
        Matcher m_full = p_full.matcher(value);

        String[] s = value.split("[-+i ]");
        // if number begins with '-' the first character will be "", to avoid this i made the following:
        if (s.length != 0) {
            if (s[0].equals("")) {
                s[0] = s[1];
                if (s.length == 3)
                    s[1] = s[2];
            }
        }

        //all valid representations of complex number
        if (m_full.find())
        {
            re = Double.parseDouble((firstPositive ? "+" : "-") + s[0]);
            if ((!firstPositive && s.length == 2) || (firstPositive && s.length == 1))
                im = secondPositive ? 1 : -1;
            else
                im = Double.parseDouble((secondPositive ? "+" : "-") + s[1]);
            return;
        }

        //5.3i, i, -i, 4i and so on
        if (m_im.find())
        {
            re = 0;
            if (s.length == 0)
                im = firstPositive ? 1 : -1;
            else
                im = Double.parseDouble((firstPositive ? "+" : "-") + s[0]);
            return;
        }

        if (m_re.find()) {
            //4.5, 5, 33.2323 and so on
            re = Double.parseDouble((firstPositive ? "+" : "-") + s[0]);
            im = 0;
        }
    }

//Methods of java.lang.Object and similar methods
    /**
     * Creates and returns a copy of this object: <code>x.copy().equals(x)</code> but <code>x.copy()!=x</code>.
     * @see #clone()
     * @return the copy of this number
     */
    public ComplexNumber copy()
    {
        ComplexNumber result = new ComplexNumberImpl();
        result.set(re, im);
        return result;
    }
    /**
     * Creates and returns a copy of this object: the same as {@link #copy()}.<br/>
     * Note: when implemented in your class, this method overrides the {@link Object#clone()} method but changes
     * the visibility and the return type of the Object's method: the visibility modifier is changed
     * from protected to public, and the return type is narrowed from Object to ComplexNumber (see also
     * <a href="http://docs.oracle.com/javase/specs/jls/se7/html/jls-8.html#d5e11368">covariant types example from JLS</a>).
     * @see Object#clone()
     * @return the copy of this number
     */
    public ComplexNumber clone() throws CloneNotSupportedException
    {
        return (ComplexNumber) super.clone();
    }

    /**
     * Returns a string representation of this number, which must be compatible with {@link #set(String)}:
     * for any ComplexNumber x, the code <code>x.set(x.toString());</code> must not change x.<br/>
     * For example: 12.5-1.0i or 0.0 or 0.3333333333333333i<br/>
     * If the imaginary part of the number is 0, only "re" must be returned (where re is the real part).<br/>
     * If the real part of the number is 0 and the imaginary part is not 0,
     *  "imi" must be returned (where im is the imaginary part).<br/>
     * Both re and im must be converted to string "as is" - without truncation of last digits,
     * i.e. the number of characters in their string representation is not limited
     *   (it is determined by {@link Double#toString(double)}).
     * @see Object#toString()
     */
    public String toString()
    {
        String result;
        if (im == 0)
            return Double.toString(re);
        if (re == 0)
            return Double.toString(im) + "i";

        if (im < 0)
            return Double.toString(re) + "-" + Double.toString(Math.abs(im)) + "i";
        else
            return Double.toString(re) + "+" + Double.toString(im) + "i";
    }
    /**
     * Checks whether some other object is "equal to" this number.
     * @param other Any implementation of {@link ComplexNumber} interface (it may not be instance of ComplexNumberImpl!)
     * @see Object#equals(Object)
     */
    public boolean equals(Object other)
    {
        if ( other == null ){
            return false;
        }else if (!(other instanceof ComplexNumber)){
            return false;
        }else {
            ComplexNumber othercomplexnumber = (ComplexNumber) other;
            return (Double.compare(re, othercomplexnumber.getRe()) == 0) && (Double.compare(im, othercomplexnumber.getIm()) == 0);
        }
    }

//Ordering methods
    /**
     * Compares this number with the other number by the absolute values of the numbers:
     * x < y if and only if |x| < |y| where |x| denotes absolute value (modulus) of x.<br/>
     * Can also compare the square of the absolute value which is defined as the sum
     * of the real part squared and the imaginary part squared: |re+imi|^2 = re^2 + im^2.
     * @see Comparable#compareTo(Object)
     * @param other the object to be compared with this object.
     * @return a negative integer, zero, or a positive integer as this object
     *   is less than, equal to, or greater than the given object.
     */
    public int compareTo(ComplexNumber other)
    {
        if (re*re + im*im > Math.pow(other.getRe(),2) + Math.pow(other.getIm(),2))
            return 1;
        if (re*re + im*im < Math.pow(other.getRe(),2) + Math.pow(other.getIm(),2))
            return -1;
        return 0;
    }
    /**
     * Sorts the given array in ascending order according to the comparison rule defined in
     *   {@link #compareTo(ComplexNumber)}.<br/>
     * It's strongly recommended to use {@link Arrays} utility class here
     *   (and do not transform the given array to a double[] array).<br/>
     * Note: this method could be static: it does not use this instance of the ComplexNumber.
     *    Nevertheless, it is not static because static methods can't be overridden.
     * @param array an array to sort
     */
    public void sort(ComplexNumber[] array)
    {
        for(int i = array.length - 1 ; i > 0 ; i--) {
            for(int j = 0 ; j < i ; j++){
                if(array[j].compareTo(array[j+1]) == 1) {
                    ComplexNumber tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
    }

//Mathematical operations
    /**
     * Changes the sign of this number. Both real and imaginary parts change their sign here.
     * @return this number (the result of negation)
     */
    public ComplexNumber negate()
    {
        re = -re;
        im = -im;
        return this;
    }
    /**
     * Adds the given complex number arg2 to this number. Both real and imaginary parts are added.
     * @param arg2 the second operand of the operation
     * @return this number (the sum)
     */
    public ComplexNumber add(ComplexNumber arg2)
    {
        re += arg2.getRe();
        im += arg2.getIm();
        return this;
    }
    /**
     * Multiplies this number by the given complex number arg2. If this number is a+bi and arg2 is c+di then
     * the result of their multiplication is (a*c-b*d)+(b*c+a*d)i<br/>
     * The method should work correctly even if arg2==this.
     * @param arg2 the second operand of the operation
     * @return this number (the result of multiplication)
     */
    public ComplexNumber multiply(ComplexNumber arg2)
    {
        double prevreal = re;
        re = re*arg2.getRe() - im*arg2.getIm();
        im = im*arg2.getRe() + prevreal*arg2.getIm();
        return this;
    }
}
