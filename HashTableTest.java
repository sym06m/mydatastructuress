import java.util.Random;

public class HashTableTest {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>(10000);

        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            String randomName = generateRandomString(5);
            MyTestingClass key = new MyTestingClass(randomName);
            table.put(key, i);
        }

        // Test get, remove, and contains
        MyTestingClass testKey = new MyTestingClass("test");
        table.put(testKey, 42);
        System.out.println("Get testKey: " + table.get(testKey));
        System.out.println("Contains value 42: " + table.contains(42));
        System.out.println("Remove testKey: " + table.remove(testKey));
        System.out.println("Contains value 42 after removal: " + table.contains(42));
    }

    private static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder(length);
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return result.toString();
    }
}
