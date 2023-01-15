class AAFileToTestThings {
    public static void main(String[] args) {
        long k = (Integer.MAX_VALUE);
        k -= Integer.MIN_VALUE;
        System.out.print(Math.log(k+1)/Math.log(2));

        B b = new B();
        b.start();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

class B extends Thread{
    public void run(){
        while (true){
            System.out.println("a");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
