/**
 * Usually events are used for transport information or signals between<p>
 * different parts of the application. In complex, especially microservice,<p>
 * applications, event's routing is very important part of the job, that can<p>
 * affect application behavior dramatically, which require strong 'transport'<p>
 * logic, which, from the other side, only blurry the business logic. That's why<p>
 * it's good practice to separate transport logic in standalone place. As usual,<p>
 * I use wrappers for this, where each wrapper can be counted as 'input-ouput port'<p>
 * between business and transport logic
 * <p>
 * Here this concept is implemented on a very primitive way, just for demonstration<p>
 * Adapters and processors communicate with each other via events, through it is covered<p>
 * from them
 * <p>
 * @author Pilip Yurchanka
 * @since v1.0
 */
package org.example.astero_demo.realization.level.transport;