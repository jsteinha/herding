import java.util.*;
public class Example {
  int label, mult;
  Map<Integer, Double> features;
  public Example(int label){
    features = new HashMap<Integer, Double>();
    this.label = label;
    this.mult = label == 2 ? 1 : -1;
  }
  void add(int index, double value){
    features.put(index, value * mult);
  }

}
