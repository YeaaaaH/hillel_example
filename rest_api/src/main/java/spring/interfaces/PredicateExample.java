package spring.interfaces;

@FunctionalInterface
public interface PredicateExample<T> {
    boolean test(T t);
}
