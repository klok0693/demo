package org.example.demo.realization.level.async;

/**
 * Speciality of GUI application is that there is always<p>
 * must be one non-freezable thread, redrawing the screen.<p>
 * Through all thread have the same priority for JVM, GUI<p>
 * java libraries contains special thread classes, that <p>
 * able to perform heavy operations in background without<p>
 * freezing GUI thread. Such classes are platfrom-dependent,<p>
 * that's why every GUI library must implement this interface,<p>
 * that wrap casual {@link Runnable} into non-freezing one
 * <p>
 * @author Pilip Yurchanka
 * @since v1.0
 */
@FunctionalInterface
public interface RunnableWrapper {

    Runnable wrap(Runnable runnable);
}
