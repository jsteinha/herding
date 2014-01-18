import java.io.*;
import java.util.*;
public class IncrementalReader {
  Scanner scanner;
  public IncrementalReader(String filename) throws Exception {
    scanner = new Scanner(new File(filename));
  }

  public Example nextExample(){
    String[] tokens = scanner.nextLine().split("\\s");
    int label = Integer.parseInt(tokens[0]);
    Example ret = new Example(label);
    for(int i = 1; i < tokens.length; i++){
      String[] tokens2 = tokens[i].split(":");
      ret.add(Integer.parseInt(tokens2[0])-1, Double.parseDouble(tokens2[1]));
    }
    return ret;
  }

}
