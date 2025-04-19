package org.example.astero_demo.realization.async;

import java.util.concurrent.Executor;

/**
 * When working with code, it is always necessary to separate WHAT we do<p>
 * from HOW we do it. A best practice is to keep implementation details, such as running<p>
 * heavy operations on a separate thread, out of our components. In this case, we use<p>
 * wrappers over the source classes to capture control of the execution thread and wrap it<p>
 * in the required thread. This allows to draw a clear boundary between the asynchronous and<p>
 * synchronous parts of the application. Now the boundary is in between the adapter and the view,<p>
 * and thanks to the use of DI, these changes will remain invisible from the point of view of the main code
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public abstract class AsynchWrapper<T> {
    protected final Executor executor;
    protected final T wrappedElement;

    protected AsynchWrapper(final Executor executor, final T wrappedElement) {
        this.executor = executor;
        this.wrappedElement = wrappedElement;
    }
}
