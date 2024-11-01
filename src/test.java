class test {
    static int x = 10;

    public static void main(String[] args) {
        test obj1 = new test();
        test obj2 = new test();

        test.x = 20;
        System.out.println(obj1.x);
        System.out.println(obj2.x);
        System.out.println(test.x);
    }
}
