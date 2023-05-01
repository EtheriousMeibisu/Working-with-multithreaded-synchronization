import java.util.*;
public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) {

                for (int i = 0; i < 10; i++) {
                    System.out.println(generateRoute("RLRFR", 100));
                }


           new Thread(() -> {  //Поток, который выводит результат на экран
        SortedMap<Integer, Integer> sortedMap = new TreeMap<>((o1, o2) -> o2 - o1);
        if (sizeToFreq.size() == 10) {
            sortedMap.putAll(sizeToFreq);

            Object[] key = sortedMap.keySet().toArray();

            System.out.println("Самое частое количество повторений " + key[0] + " Встретилось " + sortedMap.get(key[0]) + " раз \n" + "Другие размеры:");

            Set<Map.Entry<Integer, Integer>> entries = sizeToFreq.entrySet();

            for (Map.Entry<Integer, Integer> pair : entries) {
                Integer keys = pair.getKey();
                Integer value = pair.getValue();
                System.out.println("- " + keys + " (" + value + ")");
            }
        }
              }).start();
        }


    public static String generateRoute(String letters, int length) {


        Random random = new Random();
        StringBuilder route = new StringBuilder();

        new Thread(() -> { //Поток генерирующий текст
        int count = 0;
        for (int i = 0; i < length; i++) {
            char letter = letters.charAt(random.nextInt(letters.length()));
            route.append(letter);
            if (letter == 'R') {
                count++;
            }

        }

        letterСounter(count);

        }).start();
        return route.toString();

    }

    public static void letterСounter(int count) {
        new Thread(() -> {  // Поток, который считает количество букв
            synchronized (sizeToFreq) {
                int value = sizeToFreq.containsKey(count) ? sizeToFreq.get(count) : 0;
                sizeToFreq.put(count, value + 1);
            }
        }).start();

    }
}