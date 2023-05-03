import java.util.*;
public class Main {
    final static String LETTERS = "RLRFR";
    final static int ROUTE_LENGTH = 100;
    final static int AMOUNT_OF_THREADS = 1000;
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();
    public static void main(String[] args) {

        for (int i = 0; i < AMOUNT_OF_THREADS; i++) {
            new Thread(() -> {
                String rout = generateRoute(LETTERS, ROUTE_LENGTH);
                int frequency = (int) rout.chars().filter(ch -> ch == 'R').count();

                synchronized (sizeToFreq){
                    if(sizeToFreq.containsKey(frequency)){
                        sizeToFreq.put(frequency, sizeToFreq.get(frequency) + 1);
                    }else {
                        sizeToFreq.put(frequency,1);
                    }
                }
            }).start();
        }
            Map.Entry<Integer, Integer> maxCount = sizeToFreq
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .get();
        System.out.println("Самое частое количество повторений " + maxCount.getKey()
        + " Встретилось ("+  maxCount.getValue() + " раз )");

        System.out.println("Другие размеры :");
        sizeToFreq.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(к -> System.out.println(" - " + к.getKey() + " (" + к.getValue() + " раз)"));
    }
    public static String generateRoute(String letters, int length) {

            Random random = new Random();
            StringBuilder route = new StringBuilder();
            for (int i = 0; i < length; i++) {
                route.append(letters.charAt(random.nextInt(letters.length())));
            }
            return route.toString();
    }
}