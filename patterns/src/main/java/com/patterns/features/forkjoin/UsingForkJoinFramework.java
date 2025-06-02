package com.example.features.forkjoin;

import com.example.Adesh;

/**
 * 
 * The Fork/Join Framework
 * It helps to execute tasks in parallel by breaking them down into smaller subtasks.
 * It is part of the java.util.concurrent package and is designed to take advantage of multi-core processors.
 * The framework uses a work-stealing algorithm to efficiently manage the execution of tasks.
 * It is particularly useful for tasks that can be broken down into smaller, independent subtasks.
 * The framework provides the ForkJoinPool class, which is a specialized implementation of the ExecutorService interface.
 * The ForkJoinPool class allows you to submit tasks that can be executed in parallel.
 * The framework also provides the RecursiveTask and RecursiveAction classes, which are used to define tasks that can be executed in parallel.
 * The RecursiveTask class is used for tasks that return a result, while the RecursiveAction class is used for tasks that do not return a result.
 * 
 * Limitations
 * 
 * One requirement for using the Fork/Join Framework is that all of the Subtasks
 * must be "completable and independent" of each other to be truly parallel, so
 * not every problem can be solved using this method. In general, the ForkJoin Framework 
 * is to be used by CPU-intensive computations, not IO bound computations, due to the 
 * long wait periods that could happen.
 * 
 * How it works
 * 
 * A thread from the pool has it's own double ended queue, for the matter of
 * storing all the tasks that are being executed/to be executed. The double
 * ended queue nature enables inserts or deletes to both the head and last
 * position of the queue.
 * 
 * The work-stealing algorithm is the greatest functionality for the speed up
 * aspect of the ForkJoin Framework. The algorithm balances the workload between
 * threads, allowing the threads that doesn't have any task at the moment to
 * "steal" from last position of a thread's queue that can't process his own
 * last task at the moment. In theory, there will be more task being processed.
 * 
 * 
 *  Framework architecture overview
 * 
 * - ForkJoinPool: Base class for the pools, used to balance tasks that can be
 * "work-stealed".
 * 
 * - ForkJoinTask: Represents a task to be executed in a ForkJoinPool.
 * 
 * - RecursiveTask: Specialization of ForkJoinTask, holds a result.
 * 
 * - RecursiveAction: Specialization of ForkJoinTask, just process something
 * without yielding a result.
*/
@Adesh
public class UsingForkJoinFramework {

	/**
	 * Common Pool
	 * 
	 * Default instance of a fork join pool in a Java app, used by
	 * CompletableFuture, and parallel streams. All threads used by the common pool
	 * can be reused, released and reinstated after some time. This approach reduces
	 * the resource consumption. It doesn't need to be closed/shutdown.
	 * 
	 */
    public ForkJoinPool getCommonPool() {
        return ForkJoinPool.commonPool();
    }

    /**
	 * Customize ForkJoinPool
	 * 
	 * Parallelism: Parallelism level, default is Runtime#availableProcessors
	 * 
	 * ForkJoinWorkerThreadFactory: Factory used for creating threads for the pool.
	 * 
	 * UncaughtExceptionHandler: handles worker threads that terminates due some
	 * "unrecoverable" problem.
	 * 
	 * True-value AsyncMode: FIFO scheduling mode, used by tasks that are never
	 * joined, like event-oriented asynchronous tasks.
	 * 
	 */
    public ForkJoinPool getCustomPool(int parallelism, 
			ForkJoinPool.ForkJoinWorkerThreadFactory factory,
			UncaughtExceptionHandler handler, 
			boolean asyncMode) {
       return new ForkJoinPool(parallelism, factory, handler, asyncMode);
    }

    /**
     * RecursiveTask
     *
     * Represents a result of a computation.
     *
     * In the example bellow, it follows the algorithm, partitioning the numbers
     * list in half, using fork and join to control the task flow.
     *
     */
    static class RecSumTask extends RecursiveTask<BigInteger>{

    }

    public static void main(String[] args) {

    }
}
