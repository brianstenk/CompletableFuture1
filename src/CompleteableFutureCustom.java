import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class CompleteableFutureCustom {
    public static void main(String[] args) {

      //To run the process in bg without blocking - supply Asynch - that takes in a supplier
        CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return printTens(3);
            }
        }).thenAccept(num -> System.out.println("The result after waiting is "+num));

        sleepAlittle(5000); //otw , you will never see the result

        //If we just want a demon with no results to return
        //Use runAsyn instead of supplyAsynch
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                longProcess();
            }
        });

        //Since the Runnable and a supplier are finctional interfaces
        CompletableFuture.supplyAsync(() -> printTens(5))
           .thenAccept(value -> System.out.println(value));

        //for Demo without output
        CompletableFuture.runAsync(() -> longProcess());

    }

    public static int printTens(int num){
        sleepAlittle(3000);
        return 10*num;
    }

    public static void sleepAlittle(long sec){
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void longProcess(){
        sleepAlittle(5000);
    }
}
