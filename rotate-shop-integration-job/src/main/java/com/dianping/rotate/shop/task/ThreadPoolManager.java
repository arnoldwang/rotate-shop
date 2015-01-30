package com.dianping.rotate.shop.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by luoming on 15/1/28.
 */
public class ThreadPoolManager {

    ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);

    private final BlockingQueue<Future<Integer>> queue = new LinkedBlockingDeque<Future<Integer>>(Runtime.getRuntime().availableProcessors() + 2);

    private final CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(exec, queue);

    public void execute(Callable<Integer> task) {
        completionService.submit(task);
    }

    public void shutdown() {
        exec.shutdown();
    }

}
