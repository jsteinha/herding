public class MWLearner extends Learner {
  public MWLearner(Info info){
    super(info);
  }

  public void updateTheta(Example example){
    for(Integer index : example.features.keySet()){
      double value = example.features.get(index);
      theta[index][0] += Math.log(1.0 - eta * value);
      theta[index][1] -= Math.log(1.0 - eta * value);
    }
  }
}

