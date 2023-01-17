class AAFileToTestThings {
    public static void main(String[] args) {

        String[] b = "hi my name is bob".split(" ");

        int[][] a = new int[][]{{470+829, 560},
            {670+2029, 1169},
            {810+2029, 916},
            {787+2029, 468},
            {578+2029, 390},
            {500+2029, 458},
            {365+2029, 839},
            {199+2029, 951},
            {82+2029, 987},
            {24+2029, 1095},
            {570+2029, 1243},
        };
        
        for (int[] i: a){
            //System.out.println("{" + i[0] + ", " + i[1] + "},");
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
