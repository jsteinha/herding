import java.io.*;
public class Info {
  Map<String, String> map;
  public Info(String filename){
    map = new HashMap<String, String>();
    Scanner scanner = new Scanner(new File(filename));
    while(scanner.hasNextLine(){
      String[] line = scanner.nextLine().split("\\s");
      map.put(line[0], line[1]);
    }
  }

  public int numExamples(){
    return Integer.parseInt(map.get("numExamples"));
  }

  public int numFeatures(){
    return Integer.parseInt(map.get("numFeatures"));
  }

  public int numLabels(){
    return Integer.parseInt(map.get("numLabels"));
  }

}
