import java.util.concurrent.*;

public class ReverseSubstring {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String str = "Hello World!";
        int startIndex = 2;
        int endIndex = 7;
        
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new ReverseTask(str, startIndex, endIndex));
        
        String reversed = future.get();
        System.out.println(reversed);
        
        executor.shutdown();
    }
    
    private static class ReverseTask implements Callable<String> {
        private String str;
        private int startIndex;
        private int endIndex;
        
        public ReverseTask(String str, int startIndex, int endIndex) {
            this.str = str;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
       
        public String call() {
            StringBuilder sb = new StringBuilder(str);
            String reversed = sb.substring(startIndex, endIndex + 1);
            reversed = new StringBuilder(reversed).reverse().toString();
            sb.replace(startIndex, endIndex + 1, reversed);
            return sb.toString();
        }
    }
}
