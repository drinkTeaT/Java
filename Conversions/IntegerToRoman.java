package Conversions;

/**
 * Converting Integers into Roman Numerals
 *
 * <p>('I', 1); ('IV',4); ('V', 5); ('IV',9); ('X', 10); ('XL',40; ('L', 50); ('XC',90); ('C', 100);
 * ('D', 500); ('M', 1000);
 */
public class IntegerToRoman {
  private static int[] allArabianRomannumbers =
      new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
  private static String[] allRomannumbers =
      new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

  // Value must be > 0

  public static String integerToRoman(int num) {
    if (num <= 0) {
      return "";
    }

    StringBuilder builder = new StringBuilder();

    for (int a = 0; a < allArabianRomannumbers.length; a++) {
      int times = num / allArabianRomannumbers[a];
      for (int b = 0; b < times; b++) {
        builder.append(allRomannumbers[a]);
      }

      num -= times * allArabianRomannumbers[a];
    }

    return builder.toString();
  }

  public static void main(String[] args) {
    System.out.println(IntegerToRoman.integerToRoman(2131));
  }
}
