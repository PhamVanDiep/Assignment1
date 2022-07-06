package com.mycompany.java_3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *
 * @author dieppv
 */
public class FileReaderCallable
        implements Callable<LinkedHashMap<String,Integer>>{
    /**
     * file name.
     */
    private String fileName;

    /**
     * constructor.
     * @param paramFileName 
     */
    public FileReaderCallable(final String paramFileName) {
        this.fileName = paramFileName;
    }
    
    @Override
    public LinkedHashMap<String, Integer> call() throws Exception {
        LinkedHashMap<String,Integer> count = new LinkedHashMap<>();
        Stream<String> stream = Files.lines(Paths.get(fileName));
        
        stream.forEach(line -> {
            line = line.replaceAll("[^ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơ"
                    + "ƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềếểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢ"
                    + "ỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\w]", " ");
            line = removeAccent(line);
            String dataOfEachLine[] = line.split("\\s+");
            for (String data : dataOfEachLine) {
                if (!data.equals("")) {
                    if (count.containsKey(data)) {
                        count.put(data, count.get(data) + 1);
                    } else {
                        count.put(data, 1);
                    }
                }
            }
        });
        return count;
    }

    private static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
}
