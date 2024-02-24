package io.goji.vtdesign;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TaskPool {


    private static final int DEFAULT_POOL_SIZE = 20;

    private static TaskPool task = new TaskPool(DEFAULT_POOL_SIZE);


    private TaskPool() {
    }

public static TaskPool getInstance() {
        return task;
    }

    private ThreadPoolExecutor pool;

    private List<String> dic;


    private TaskPool(int poolSize) {
        pool = new ThreadPoolExecutor(poolSize, poolSize, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        pool.prestartAllCoreThreads();
    }

    public void setDic(List<String> dic) {
        this.dic = dic;
    }

    public String matchString(String input) throws TimeoutException {
        StringBuilder sb = new StringBuilder();
        List<Future<String>> result = new ArrayList<>(dic.size());
        for (String s : dic) {
            result.add(pool.submit(new CharMatching(input, s)));
        }
        for(Future<String> f : result){
            try {
                String data = f.get(5, TimeUnit.SECONDS);
                if(data != null){
                    sb.append(data).append(" ");
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


}
