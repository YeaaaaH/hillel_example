package example.spring.interfaces;

@FunctionalInterface
public interface PredicateExample<T> {
    boolean test(T t);
}
