package com.mycompany.java_3;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author dieppv
 */
public final class Main {
    /**
     * main function.
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException 
     */
    public static void main(final String[] args)
            throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        List<FileReaderCallable> listFileReaderCallable = new ArrayList<>();
        List<Future<LinkedHashMap<String,Integer>>> listFuture
                = new ArrayList<>();
        LinkedHashMap<String,Integer> result = new LinkedHashMap<>();
        
        String folderPath = "/home/dieppv/Assigment1/Java_3/src/main/resources";
        File files = new File(folderPath);
        File [] listOfFiles = files.listFiles();
        
        System.out.println(listOfFiles.length);
        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            listFileReaderCallable
                    .add(new FileReaderCallable(file.getAbsolutePath()));
        }
        
        for (int i = 0; i < listFileReaderCallable.size(); i++) {
            listFuture
                    .add(executorService.submit(listFileReaderCallable.get(i)));
        }
        
        for (int i = 0; i < listFuture.size(); i++) {
            LinkedHashMap<String, Integer> temp = listFuture.get(i).get();
            temp.forEach((k,v) -> {
                if (result.containsKey(k)) {
                    result.put(k, result.get(k) + v);
                } else {
                    result.put(k, v);
                }
            });
        }

        System.out.println("Top 10 words appear at least.");
        result.entrySet()
                .stream()
                .sorted((e1,e2)->Integer.compare(e1.getValue(), e2.getValue()))
                .limit(10)
                .forEach(System.out::println);
        System.out.println("Top 10 words appear the most.");
        result.entrySet()
                .stream()
                .sorted((e1,e2)->Integer.compare(e2.getValue(), e1.getValue()))
                .limit(10)
                .forEach(System.out::println);

        executorService.shutdown();
    }
}
