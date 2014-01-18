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
    @Option(required=true, gloss="learner type")
    public static int learnerType; // 0 = EG, 1 = MW, 2 = AEG
    @Option(gloss="zero-one vs hinge")
    public static boolean zeroOne = false;

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
        Info info = new Info("../data/"+dataset+"/info");
        IncrementalReader reader = new IncrementalReader("../data/"+dataset+"/shuffled");
        Learner learner;
        switch(learnerType){
          case 0 : learner = new EGLearner(info); break;
          case 1 : learner = new MWLearner(info); break;
          case 2 : learner = new AEGLearner(info); break;
          default : throw new RuntimeException("invalid learnerType");
        }
        int T = info.numExamples();
        double[] losses = new double[T];
        double loss = 0.0;
        LogInfo.begin_track("Predicting");
        for(int t = 0; t < T; t++){
          loss += learner.predict(reader.nextExample());
          LogInfo.logs("loss[%d] = %f / %f", t, loss/(t+1), 0.01*(loss-losses[Math.max(0, t-100)]));
          losses[t] = loss;
        }
        LogInfo.end_track();
    }

    public static void main(String[] args){
        Execution.run(args, new Main());
    }
}
