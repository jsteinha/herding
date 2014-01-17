import java.util.*;
import java.io.*;
import fig.basic.LogInfo;
import fig.basic.Option;
import fig.basic.StatFig;
import fig.exec.Execution;

public class Main implements Runnable {
    @Option(gloss="experiment name (for easier tracking)")
		public static String experimentName = "UNKNOWN";
    @Option(required=true, gloss="dataset")
		public static String dataset;

    public void run() {
        try {
            runWithException();
        } catch(Exception e) {
						e.printStackTrace();
            throw new RuntimeException();
        }
    }

    void runWithException() throws Exception {
        LogInfo.logs("EXPERIMENT NAME: %s", experimentName);
        Info info = new Info("../"+dataset+"/info");
        IncrementalReader reader = new IncrementalReader("../"+dataset+"/shuffled");
        Learner learner = new EGLearner(info);
        int T = info.numExamples();
        double[] losses = new double[T];
        LogInfo.begin_track("Predicting");
        for(int t = 0; t < T; t++){
          loss += learner.predict(reader.nextExample());
          LogInfo.logs("loss[%d] = %f", t, loss);
          losses[t] = loss;
        }
        LogInfo.end_Track();
    }

    public static void main(String[] args){
        Execution.run(args, new Main());
    }
}
