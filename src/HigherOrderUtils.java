import java.util.function.BiFunction;

/*
First, write a static nested interface in HigherOrderUtils called NamedBiFunction. This interface must (15)
extend the interface java.util.Function.BiFunction. The interface should just have one additional
method declaration: String name();, i.e., a class implementing this interface must provide a “name”
for every instance of that class. Then, create public static NamedBiFunction instances as follows:
 */
public class HigherOrderUtils {
    interface NamedBiFunction<T,U,R> extends BiFunction<T,U,R> {
        String name();
    }

    public static NamedBiFunction<Double, Double, Double> add = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public Double apply(Double aDouble, Double aDouble2) {return aDouble + aDouble2;}
        @Override
        public String name() {return "plus";}
    };

    public static NamedBiFunction<Double, Double, Double> subtract = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {return "minus";}
        @Override
        public Double apply(Double aDouble, Double aDouble2) {return aDouble2 - aDouble;}
    };

    public static NamedBiFunction<Double, Double, Double> multiply = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {return "mult";}
        @Override
        public Double apply(Double aDouble, Double aDouble2) {return aDouble2 * aDouble;}
    };

    public static NamedBiFunction<Double, Double, Double> divide = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String name() {return "div";}
        @Override
        public Double apply(Double aDouble, Double aDouble2) {
            if (aDouble2 == 0) {
                throw new ArithmeticException("Division by zero");
            } else {
                return aDouble/aDouble2;
            }
        }
    };

    /**
     * Applies a given list of bifunctions -- functions that take two arguments of a certain type
     * and produce a single instance of that type -- to a list of arguments of that type. The
     * functions are applied in an iterative manner, and the result of each function is stored in
     * the list in an iterative manner as well, to be used by the next bifunction in the next
     * iteration. For example, given
     * List<Double> args = Arrays.asList(-0.5, 2d, 3d, 0d, 4d), and
     * List<NamedBiFunction<Double, Double, Double>> bfs = Arrays.asList(add, multiply, add, divide),
     * <code>zip(args, bfs)</code> will proceed as follows:
     * - the result of add(-0.5, 2.0) is stored in index 1 to yield args = [-0.5, 1.5, 3.0, 0.0, 4.0]
     * - the result of multiply(1.5, 3.0) is stored in index 2 to yield args = [-0.5, 1.5, 4.5, 0.0, 4.0]
     * - the result of add(4.5, 0.0) is stored in index 3 to yield args = [-0.5, 1.5, 4.5, 4.5, 4.0]
     * - the result of divide(4.5, 4.0) is stored in index 4 to yield args = [-0.5, 1.5, 4.5, 4.5, 1.125]
     *
     * @param args the arguments over which <code>bifunctions</code> will be applied.
     * @param bifunctions the list of bifunctions that will be applied on <code>args</code>.
     * @param <T> the type parameter of the arguments (e.g., Integer, Double)
     * @return the item in the last index of <code>args</code>, which has the final result
     * of all the bifunctions being applied in sequence.
     *
     * @throws IllegalArgumentException if the number of bifunction elements and the number of argument
     * elements do not match up as required.
     */
    public static <T> T zip(List<T> args, List<BiFunction<T, T, T>> bifunctions) {

        return null;
    }



}
