import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BijectionGroup {

    // number of bijections from A -> A is n!
    // we have a set
    // we need to find all one to one and onto functions aka bijection
    // Going to return the set of all the bijections
    public static <T> Set<Function<T,T>> bijectionsOf (Set<T> domain) {
        List<T> domain_list = new ArrayList<>(domain);


        return null;
    }

//    public static bijectionGroup {
//
//    }

    public static void main(String... args) {
        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
        // you have to figure out the data type in the line below
        Set<Function<Integer, Integer>> bijections = bijectionsOf(a_few);
        bijections.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });

//        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
//        // you have to figure out the data types in the lines below
//        // some of these data types are functional objects, so look into java.util.function.Function
//        __________________________ g = bijectionGroup(a_few);
//        __________________________ f1 = bijectionsOf(a_few).stream().findFirst().get();
//        __________________________ f2 = g.inverseOf(f1);
//        __________________________ id = g.identity();
    }

}
