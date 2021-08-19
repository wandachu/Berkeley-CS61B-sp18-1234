public class Quick {
    public static void main(String[] args) {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        sad.addFirst(0);
        sad.addLast(1);
        sad.addFirst(2);
        sad.removeFirst();
        sad.addLast(4);
        sad.addLast(5);
        sad.removeFirst();
        sad.removeLast();
        sad.removeLast();
        sad.removeFirst();
        sad.addFirst(10);
        sad.addFirst(11);
        sad.removeLast();
        sad.addFirst(13);
        sad.removeFirst();
        sad.removeFirst();
        sad.addLast(16);
        sad.removeLast();
        sad.addLast(18);
        sad.removeLast();
        sad.addFirst(20);
        sad.addFirst(21);
        sad.addLast(22);
        sad.removeFirst();
        sad.removeFirst();
        sad.addLast(25);
        sad.removeFirst();
        sad.addFirst(27);
        sad.removeLast();
        sad.addFirst(29);
        sad.addFirst(30);
        sad.removeFirst();
        sad.removeFirst();
        sad.removeFirst();
        sad.addLast(34);
        sad.addLast(35);
        sad.addFirst(36);
        sad.addLast(37);
        sad.addFirst(38);
        sad.addFirst(39);
        sad.removeLast();
        sad.addFirst(41);
        sad.addLast(42);
        Integer a = sad.removeLast();
        System.out.println(a);
        sad.addFirst(44);
        sad.addLast(45);
        System.out.println(sad.get(sad.size()));
        System.out.println(sad.get(0));
        sad.printDeque();
    }

}
