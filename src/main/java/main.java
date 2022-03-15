public class main {
    public static void main(String[] args) {
        LearnThreads learnThreads1 = new LearnThreads(1);
        LearnThreads learnThreads2 = new LearnThreads(2);
        learnThreads2.start();
        learnThreads1.start();

        learnThreads2.runn();
        learnThreads1.runn();
    }
}
