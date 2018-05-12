class ABC {
    
    int i;
    double x = 0.0;

    void dobbelt(){
        i *= 2;
    }

    double settX(double y) {
        double sub = x;
        x = y;
        return sub;
    }

    void test() {
        for (int i = 0; i < 10000; i++) {
            System.out.print(settX(i+1) + "    ");
        }
    }
}