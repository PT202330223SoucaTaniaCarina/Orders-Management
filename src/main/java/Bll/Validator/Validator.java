package Bll.Validator;

/**
 * Interfata pentru validarea parametrilor.
 * @author tania
 * @param <T> este clasa obiectului pentru care se face validarea.
 */

public interface Validator<T> {
    public default void validate(T t) {
    }
}
