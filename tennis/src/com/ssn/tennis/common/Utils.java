
package com.ssn.tennis.common;

public class Utils {

  public static String getParentsFromServletPath(String path) {
    {
      int count = 0;
      for (int i = 0; i < path.length(); i++) {
        if (path.charAt(i) == '/') {
          count++;
        }
      }

      String result = "";
      for (int i = 1; i < count; i++) {
        result += "../";
      }
      return result;
    }
  }

}
