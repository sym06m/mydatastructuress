public class BST<K extends Comparable<K>, V> {
    // Вложенный класс для узлов дерева
    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node<K, V> root; // Корень дерева

    // Метод для добавления ключа и значения
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) return new Node<>(key, value); // Создаем новый узел
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value); // Идем влево
        else if (cmp > 0) node.right = put(node.right, key, value); // Идем вправо
        else node.value = value; // Обновляем значение, если ключ уже существует
        return node;
    }

    // Метод для получения значения по ключу
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node<K, V> node, K key) {
        if (node == null) return null; // Ключ не найден
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key); // Ищем в левом поддереве
        else if (cmp > 0) return get(node.right, key); // Ищем в правом поддереве
        else return node.value; // Найден ключ
    }

    // Метод для удаления ключа
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node<K, V> delete(Node<K, V> node, K key) {
        if (node == null) return null; // Ключ не найден
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key); // Ищем в левом поддереве
        else if (cmp > 0) node.right = delete(node.right, key); // Ищем в правом поддереве
        else {
            // Найден ключ для удаления
            if (node.right == null) return node.left; // Нет правого поддерева
            if (node.left == null) return node.right; // Нет левого поддерева
            Node<K, V> temp = node;
            node = min(temp.right); // Находим минимальный узел в правом поддереве
            node.right = deleteMin(temp.right); // Удаляем минимальный узел
            node.left = temp.left; // Переносим левое поддерево
        }
        return node;
    }

    // Метод для нахождения минимального узла
    private Node<K, V> min(Node<K, V> node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    // Метод для удаления минимального узла
    private Node<K, V> deleteMin(Node<K, V> node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    // Итератор для обхода дерева (in-order traversal)
    public Iterable<K> keys() {
        Queue<K> queue = new Queue<>();
        inOrder(root, queue);
        return queue;
    }

    private void inOrder(Node<K, V> node, Queue<K> queue) {
        if (node == null) return;
        inOrder(node.left, queue); // Левое поддерево
        queue.enqueue(node.key); // Текущий узел
        inOrder(node.right, queue); // Правое поддерево
    }
}
