package com.example.features.parallel_stream;



/**
 *
 * Warning
 *
 * Parallel Streams uses the ForkJoin Pool, so be aware of using it in Java
 * EE/Jakarta EE environments!
 *
 * The Stream API allows, with methods like parallel() or parallelStream(), the
 * execution of streams's operations in parallel.
 *
 * It enables each element to be processed in parallel, having a thread for each
 * one of them, depending on the number of cores available. Like the Fork/Join
 * Framework, it has an overhead, so the speed of execution can get much better
 * in some cases, getting worse in some others.
 *
 * Streams are composed of a Source, several intermediate operations and a
 * terminal operation. Streams are executed only when a terminal operation is
 * executed, so they're lazy too. The intermediate operations respect the order
 * that you used, they're sequential. The work of each intermediate operation is
 * parallel.
 *
 * CPU intensive tasks benefits from this feature.
 *
 *
 */
public class UsingParallelStream {
}
