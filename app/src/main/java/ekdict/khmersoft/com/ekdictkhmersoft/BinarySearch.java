package ekdict.khmersoft.com.ekdictkhmersoft;

/**
 * Created by Arcjun5 on 8/9/2016.
 */
public class BinarySearch {
    public static final int NOT_FOUND = -1;
    public static final int CLEAR_FOUND = 0;
    public static int foundIndex;

    public static int search(String[] array, String word){
        int left = 0;
        int right = array.length - 1;
        int index = binarySearch(array, word, left, right);
        while(index > NOT_FOUND && strCmp(array[index].trim(), word.trim()) == CLEAR_FOUND) index--;
        return index + 1;
    }

    public static int strCmp(String lhr, String prefix){
        if(lhr == prefix) return CLEAR_FOUND;
        int prefixLeng = prefix.length();
        int lhrLeng = lhr.length();
        if(prefixLeng < lhrLeng){
            return lhr.substring(0, prefixLeng).compareToIgnoreCase(prefix.toString()); //lhr - prefix:lhr length is higher
        }else if(prefixLeng > lhrLeng){
            return lhr.compareToIgnoreCase(prefix.substring(0, lhrLeng).toString()); //lhr - prefix:prefix length is higher
        }else{
            return lhr.compareToIgnoreCase(prefix.toString()); //lhr - prefix:lhr length = prefix length
        }
    }

    public static int binarySearch(String[] array, String word, int left, int right){
        if(right < left){
            return NOT_FOUND;
        }

        int mid = (left + right) >>> 1;
        if(strCmp(array[mid], word) < 0){
            return binarySearch(array, word, mid+1, right);
        }else if(strCmp(array[mid], word) > 0){
            return binarySearch(array, word, left, mid - 1);
        }else{
            return mid;
        }
    }
}
