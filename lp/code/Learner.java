public abstract class Learner {
  double[][] theta;
  double lambda, eta;
  public Learner(Info info){
    if(info.numLabels() != 2){
      throw new RuntimeException("expected binary classification problem");
    } else {
      lambda = 20.0 /info.numFeatures();
      int n = info.numFeatures(), T = info.numExamples();
      eta = (lambda*n)*Math.sqrt(Math.log(n)/T);
      theta = new double[info.numFeatures()][2]; // two sets of weights (positive and negative)
      for(int i = 0; i < info.numFeatures(); i++){
        theta[i][0] = theta[i][1] = Math.log(lambda);
      }
    }
  }

  double weights(int index){
    return Math.exp(theta[index][0]) - Math.exp(theta[index][1]);
  }

  // hinge loss
  double loss(double y){
    return Math.max(1+y, 0);
  }

  abstract void updateTheta(Example example);

  public double predict(Example example){
    double y = 0.0;
    for(Integer index : example.features.keySet()){
      double value = example.features.get(index);
      y += weights(index) * value;
    }
    double ret = loss(y);
    if(y > -1){
      updateTheta(example);
    }
    if(Main.zeroOne){
      return ret > .999 ? 1 : 0;
    } else {
      return ret;
    }
  }

}
