import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.text.DecimalFormat;
import com.vypeensoft.util.*;

/*
*/
public class FolderDuplicateFinder {
  public static Map<String, Long> folderSizeMap = new HashMap<String, Long>();
  private static DecimalFormat decimalFormatter = new DecimalFormat("###,###,000");
  /******************************************************************************************/
  public static void main(String[] args) throws Exception {
    String propertyFilePath = locatePropertiesFile();
    System.out.println("REM propertyFilePath="+propertyFilePath);
    Map<String,List<String>> hm = PropertiesLoader.loadToHashMap(propertyFilePath);
    
    
    List<String> fileList = null;
    if((args != null) && (args.length > 0)) {
        folderSizeMap = loadFileLists(args);
    } else {
        folderSizeMap = loadFileLists(new String[]{"."}); // if no arguments are given, process current directory
    }
    //System.out.println("REM folderSizeMap="+folderSizeMap);

    MapKeyValueArray mkva = new MapKeyValueArray();
    folderSizeMap.forEach((k,v)->mkva.add(v+"",k));
    
    List<String> multiValuedKeys = mkva.getMultiValueKeys(2);
    List<Long> multiValuedKeysLong = multiValuedKeys.stream()
									.map(s -> Long.parseLong(s))
//                                    .sorted(Comparator.comparing(String::toString).reversed())
									.collect(Collectors.toList());
    Collections.sort(multiValuedKeysLong);
    System.out.println("REM multiValuedKeys="+multiValuedKeys);
    multiValuedKeysLong.forEach(k->{
        System.out.println("REM : folderSize="+FormatUtil.formatNumberWithComma(k));
        CollectionUtil.print(mkva.get(k+"")); 
        System.out.println();
        });
    
    
    System.out.println("\r\n\r\n\r\n\r\n\r\nREM program end");
    
  }
  /******************************************************************************************/
  public static Map<String, Long> loadFileLists(String[] args) {
    Map<String, Long> folderSizeMap = new HashMap<String, Long>();
    if(args.length  >= 1) {
        FileUtil.getFolderSizeRecursive(args[0]);
    }
    if(args.length  >= 2) {
        FileUtil.getFolderSizeRecursive(args[1]);
    }
    if(args.length  >= 3) {
        FileUtil.getFolderSizeRecursive(args[2]);
    }
    if(args.length  >= 4) {
        FileUtil.getFolderSizeRecursive(args[3]);
    }
    if(args.length  >= 5) {
        FileUtil.getFolderSizeRecursive(args[4]);
    }
    return FileUtil.folderSizeMap;
  }
  /******************************************************************************************/
  public static String locatePropertiesFile() {
      String filePath="";
      filePath = "C:\\DUPLICATE_FINDER_PROPERTIES.TXT";
      if((new File(filePath)).exists()) return filePath;
      
      filePath = "D:\\DUPLICATE_FINDER_PROPERTIES.TXT";
      if((new File(filePath)).exists()) return filePath;
      
      filePath = "D:\\Programs_Portable_GIT\\Java_Utils\\DUPLICATE_FINDER_PROPERTIES.TXT";
      if((new File(filePath)).exists()) return filePath;

      filePath = "C:\\Programs_Portable_GIT\\Java_Utils\\DUPLICATE_FINDER_PROPERTIES.TXT";
      if((new File(filePath)).exists()) return filePath;

      String classPath = System.getProperty("java.class.path"); //assuming only one path in CLASSPATH
      File file = new File(classPath);
      filePath = file.getParent() + "\\DUPLICATE_FINDER_PROPERTIES.TXT";
      if((new File(filePath)).exists()) return filePath;

      return "";
  }
  /******************************************************************************************/

  /******************************************************************************************/

  /******************************************************************************************/

  /******************************************************************************************/
}


