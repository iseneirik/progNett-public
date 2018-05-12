class Oppgave3 {
    public static void main(String[] args) {
        Stuff s = new Stuff();
        int[] x = {1, 2, 3, 4, 5};
        System.out.println(s.sum(x));
        System.out.println(s.snitt(x));
    }
}

class Stuff {
    
    public int sum(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
        sum += array[i];
        }
        return sum;
    }

    /*
    * a) Den ville ikke kompilert fordi array{i} skal vere array[i]
    *    Den maa ogsaa ha riktig return type, var void...
    *    Og det var : i stedet for ;
    */

    // b) en metode som git snittet av en int array

    public double snitt(int[] array) {
        return sum(array) / (double)array.length; 
    } 
}

