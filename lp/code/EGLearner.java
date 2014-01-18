public class EGLearner extends Learner {
  public EGLearner(Info info){
    super(info);
  }

  public void updateTheta(Example example){
    for(Integer index : example.features.keySet()){
      double value = example.features.get(index);
      theta[index][0] -= eta * value;
      theta[index][1] += eta * value;
    }
  }
}

