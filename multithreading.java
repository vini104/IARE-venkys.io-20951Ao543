import java.util.concurrent.*;

public class SubstringReverse {
    public static String reverse(String str, int start, int end) {
    StringBuilder sb = new StringBuilder(str.substring(start, end));
    return sb.reverse().toString();
  }

  public static void main(String[] args) throws Exception {
    String str = "Hello World!";
    int start = 0;
    int end = str.length();
    int numThreads = Runtime.getRuntime().availableProcessors();

    ExecutorService executor = Executors.newFixedThreadPool(numThreads);
    CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

    int segmentLength = (end - start) / numThreads;

    
    for (int i = 0; i < numThreads; i++) {
      final int segmentStart = start + i * segmentLength;
      final int segmentEnd = (i == numThreads - 1) ? end : segmentStart + segmentLength;

      completionService.submit(() -> reverse(str, segmentStart, segmentEnd));
    }

    
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < numThreads; i++) {
      sb.append(completionService.take().get());
    }

    executor.shutdown();

    System.out.println(sb.reverse().toString());
  }
}

