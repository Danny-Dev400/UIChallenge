public class LearnThreads extends Thread{
    private int myId ;

    public LearnThreads ( int id ) {
        myId = id ;
    }

    public void runn() {
        System.out.println("myId is " + this.myId);
        System.out.println(hashCode());
    }
}
