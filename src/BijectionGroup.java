import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class BijectionGroup {

    /**
     * @param list the input list, may contain duplicates
     * @param <T>  the type of the element of the list
     * @return the list of possible permutations
     */
    private static <T> List<List<T>> possiblePermutations(List<T> list) {
        List<List<T>> permutations = new ArrayList<>();
        if (list == null || list.size() == 0) {
            return permutations;
        }
        List<T> tempList = new ArrayList<>(list);
        int n = tempList.size();
        int[] indices = new int[n];
        Arrays.fill(indices, 0);
        permutations.add(new ArrayList<>(tempList));

        int i = 0;
        while (i < n) {
            if (indices[i] < i) {
                Collections.swap(tempList, i % 2 == 0 ? 0 : indices[i], i);
                permutations.add(new ArrayList<>(tempList));
                indices[i]++;
                i = 0;
            } else {
                indices[i] = 0;
                i++;
            }
        }
        return permutations;
    }

    // we have a set
    // we need to find all one to one and onto functions aka bijection
    // Going to return the set of all the bijections
    public static <T> Set<Function<T, T>> bijectionsOf(Set<T> domain) {
        List<T> domain_as_list = new ArrayList<>(domain);
        // possible permutations
        List<List<T>> pp = possiblePermutations(domain_as_list);
        Set<Function<T, T>> bijections = new HashSet<>();
        // for each permutation, create a corresponding bijection
        for (List<T> p : pp) {
            // check if the permutation defines a bijection
            boolean is_bijection = true;
            Set<T> values = new HashSet<>();
            for (T value : p) {
                if (!values.add(value)) {
                    // value already present in the range, not a bijection
                    is_bijection = false;
                    break;
                }
            }
            if (is_bijection) {
                // create a bijection based on the permutation
                Function<T, T> bijection = t -> p.get(domain_as_list.indexOf(t));
                bijections.add(bijection);
            }
        }
        return bijections;
    }

    public static<T> Group<Function<T,T>> bijectionGroup(Set<T> domain) {
        Group<Function<T,T>> group = new Group<Function<T, T>>() {
            Set<Function<T,T>> bijectSet = bijectionsOf(domain);
            @Override
            public Function<T, T> binaryOperation(Function<T, T> one, Function<T, T> other) {
                return one.compose(other);
            }
            @Override
            public Function<T, T> identity() {
                return x -> x;
            }
            @Override
            public Function<T, T> inverseOf(Function<T, T> f) {
                for (Function<T, T> g : bijectSet) {
                    boolean isInverse = true;
                    for (T x : domain) {
                        if (!g.apply(f.apply(x)).equals(x) || !f.apply(g.apply(x)).equals(x)) {
                            isInverse = false;
                            break;
                        }
                    }
                    if (isInverse) {
                        return g;
                    }
                }
                // return identity function as default
                return x -> x;
            }
        };
        return group;
    }

    public static void main(String... args) {
        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
        // you have to figure out the data type in the line below
        Set<Function<Integer,Integer>> bijections = bijectionsOf(a_few);
        bijections.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });
        // you have to figure out the data types in the lines below
        // some of these data types are functional objects, so look into java.util.function.Function
        Group<Function<Integer,Integer>> g = bijectionGroup(a_few);
        Function<Integer,Integer> f1 = bijectionsOf(a_few).stream().findFirst().get();
        Function<Integer,Integer> f2 = g.inverseOf(f1);
        Function<Integer,Integer> id = g.identity();
    }
}
