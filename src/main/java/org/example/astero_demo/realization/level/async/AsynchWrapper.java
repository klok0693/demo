package org.example.astero_demo.realization.level.async;

import org.example.astero_demo.realization.level.WrapperLink;

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
public abstract class AsynchWrapper<T> extends WrapperLink<T> {
    protected final Executor executor;

    protected AsynchWrapper(final T wrappedElement, final Executor executor) {
        super(wrappedElement);
        this.executor = executor;
    }
}
