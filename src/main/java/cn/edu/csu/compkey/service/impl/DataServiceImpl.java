package cn.edu.csu.compkey.service.impl;

import cn.edu.csu.compkey.service.DataService;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DataServiceImpl implements DataService {
    @Value("${CompKey.midkey-num}")
    private int midkeyNum;

    /**
     * 数据预处理
     * TODO 路径不能写死
     */
    @Override
    public void preprocessing() throws IOException {
        List<String> stopWords = readStopWords("src/main/resources/data/stopwords.txt");
        for (int i = 0; i < 100; i++) {
            // 读数据
            List<String> searchStrings = readRawData("src/main/resources/data/user_tag_query.10W.TRAIN", 1000 * i, 1000 * (i + 1));
            // 分词
            List<List<String>> splitStrings = Segment(searchStrings);
            // 洗数据
            List<List<String>> data = cleanData(splitStrings, stopWords);
            // 保存数据
            saveData(data, "src/main/resources/data/cleaned_data.txt");
        }
        encode(readCleanData("src/main/resources/data/cleaned_data.txt"));
    }

    /**
     * @param filePath
     * @return
     * @throws IOException 读原始数据集
     */
    public List<String> readRawData(String filePath, int start, int end) throws IOException {
        List<String> searchStrings = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "gbk"))) {
            String str;
            int count = 0;
            while ((str = in.readLine()) != null) {
                if (count >= start && count < end) {
                    String[] strings = str.split("\t");
                    searchStrings.addAll(Arrays.asList(strings).subList(4, strings.length));
                }
                count++;
            }
            System.out.println(start + "~" + end + "开始处理");
        }
        return searchStrings;
    }

    /**
     * @param searchStrings
     * @return HanLP分词器
     */
    public List<List<String>> Segment(List<String> searchStrings) {
        HanLP.Config.ShowTermNature = false;
        // 开启多线程分词
        Segment segment = HanLP.newSegment().enableMultithreading(true);
        List<List<String>> res = new ArrayList<>();
        for (String record : searchStrings) {
            List<Term> terms = segment.seg(record);
            res.add(terms.stream().map(term -> term.word).collect(Collectors.toList()));
        }
        return res;
    }

    /**
     * @param filePath
     * @return
     * @throws IOException 读取无效词清单
     */
    public List<String> readStopWords(String filePath) throws IOException {
        List<String> stopWords = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String str;
            while ((str = in.readLine()) != null) {
                stopWords.add(str);
            }
        }
        return stopWords;
    }

    /**
     * @param data
     * @param stopWords
     * @return 洗数据
     */
    public List<List<String>> cleanData(List<List<String>> data, List<String> stopWords) {
        List<List<String>> listsToRemove = new ArrayList<>();
        for (List<String> list : data) {
            // 清除单个字符和纯数字
            List<String> elementsToRemove = new ArrayList<>();
            for (String temp : list) {
                if (temp.length() == 1 || temp.matches("\\d+")) {
                    elementsToRemove.add(temp);
                }
            }
            list.removeAll(elementsToRemove);
            // 清除StopWords.txt中的数据
            list.removeIf(stopWords::contains);
            // 清除洗数据后产生的空数组（整条记录都被洗掉的数组）
            if (list.isEmpty()) {
                listsToRemove.add(list);
            }
        }
        data.removeAll(listsToRemove);
        // 去重
        return data.stream()
                .map(innerList -> innerList.stream().distinct().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    /**
     * @param data
     * @param filePath 保存清洗后的数据，以便下次使用
     */
    public void saveData(List<List<String>> data, String filePath) {
        // TODO true代表追加
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true), StandardCharsets.UTF_8))) {
            for (List<String> row : data) {
                String line = String.join(",", row); // 使用英文逗号分隔每个元素
                writer.write(line);
                writer.newLine(); // 写入换行符
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param filePath
     * @return
     */
    public List<List<String>> readCleanData(String filePath) {
        List<List<String>> searchStrings = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String str;
            while ((str = in.readLine()) != null) {
                String[] strings = str.split(",");
                List<String> tempList = new ArrayList<>(Arrays.asList(strings));
                searchStrings.add(tempList);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return searchStrings;
    }

    public void encode(List<List<String>> data) throws IOException {
        List<List<Integer>> encodedList = new ArrayList<>();  // 编码后的数据（需保存）
        Map<String, Integer> encodingMap = new HashMap<>();  // 编码表（需保存）
        Map<Integer, String> decodingMap = new HashMap<>();  // 反向编码表（需保存）
        int code = 1; // 编码从1开始，0可以保留给未知或空值
        for (List<String> sentence : data) {
            List<Integer> encodedSentence = new ArrayList<>();
            for (String word : sentence) {
                if (!encodingMap.containsKey(word)) {
                    encodingMap.put(word, code);
                    decodingMap.put(code, word);
                    code++;
                }
                encodedSentence.add(encodingMap.get(word));
            }
            encodedList.add(encodedSentence);
        }
        log.info("编码完毕");
        // 持久化
        String dataPath = "src/main/resources/data/EncodeData.txt";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataPath))) {
            oos.writeObject(encodedList);
        }
        String encodeMapPath = "src/main/resources/data/EncodeMap.txt";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(encodeMapPath))) {
            oos.writeObject(encodingMap);
        }
        String decodeMapPath = "src/main/resources/data/DecodeMap.txt";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(decodeMapPath))) {
            oos.writeObject(decodingMap);
        }
        log.info("保存成功");
    }

    @Override
    public List<List<Integer>> readEncodeData(String filePath) {
        List<List<Integer>> encodeData;
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            encodeData = (List<List<Integer>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return encodeData;
    }

    @Override
    public Map<String, Integer> readEncodeMap(String filePath) {
        Map<String, Integer> encodeMap;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            encodeMap = (Map<String, Integer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return encodeMap;
    }

    @Override
    public Map<Integer, String> readDecodeMap(String filePath) {
        Map<Integer, String> decodeMap;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            decodeMap = (Map<Integer, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return decodeMap;
    }


    /**
     * 获取中间关键词
     */
    @Override
    public Map<Integer, Integer> getMidKeys(List<List<Integer>> data, Integer seed) {
        Map<Integer, Integer> midKeyword = new HashMap<>();
        for (List<Integer> tempList : data) {
            if (tempList.contains(seed)) {
                // 统计词频
                for (Integer word : tempList) {
                    midKeyword.put(word, midKeyword.getOrDefault(word, 0) + 1);
                }
            }
        }
        return midKeyword;
    }

    /**
     * 获取竞争关键词
     */
    @Override
    public Map<Integer, Double> getCompKeys(List<List<Integer>> data, Map<Integer, Integer> midKeys, Integer seed) {
        Map<Integer, Double> result = new HashMap<>();

        int s = midKeys.get(seed);
        midKeys.remove(seed);

        // 筛选中介关键词
        Map<Integer, Integer> sortMidKeys = midKeys.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(midkeyNum)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        // 分别对每个中介关键词进行查找
        for (Map.Entry<Integer, Integer> midKey : sortMidKeys.entrySet()) {
            int sa = midKey.getValue();
            // 统计词频
            Map<Integer, Integer> frequency = new HashMap<>();
            for (List<Integer> tempList : data) {
                if (tempList.contains(midKey.getKey())) {
                    for (Integer word : tempList) {
                        frequency.put(word, frequency.getOrDefault(word, 0) + 1);
                    }
                }
            }
            // 排序
            Map<Integer, Integer> compKeys = frequency.entrySet().stream()
                    .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            int a = compKeys.get(midKey.getKey());
            compKeys.remove(midKey.getKey());
            // 去除种子关键词
            compKeys.remove(seed);

            // 计算竞争度并保存
            for (Map.Entry<Integer, Integer> compKey : compKeys.entrySet()) {
                int ak = compKey.getValue();

                // 计算竞争度
                double res = ((double) sa / s) * ((double) ak / (a - sa));
                // 累加竞争度
                result.put(compKey.getKey(), result.getOrDefault(compKey.getKey(), 0.0) + res);
            }
        }

        return result;
    }
}
