package example.abe.com.android.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by abe on 16/9/1.
 */
public class ImageURLUtil {

    public static final Random RANDOM = new Random(System.currentTimeMillis());
    public static final List<String> LIST_IMAGE_URL = Arrays.asList(
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "1.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "2.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "3.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "4.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "5.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "6.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "7.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "8.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "9.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "10.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "11.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "12.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "13.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "14.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "15.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "16.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "17.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "18.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "19.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "20.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "21.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "22.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "23.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "24.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "25.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "26.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "27.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "28.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "29.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "30.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "31.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "32.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "33.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "34.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "35.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "36.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "37.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "38.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "39.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "40.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "41.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "42.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "43.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "44.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "45.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "46.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "47.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "48.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "49.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "50.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "51.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "52.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "53.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "54.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "55.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "56.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "57.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "58.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "59.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "60.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "61.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "62.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "63.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "64.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "65.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "66.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "67.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "68.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "69.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "70.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "71.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "72.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "73.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "74.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "75.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "76.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "77.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "78.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "79.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "80.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "81.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "82.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "83.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "84.jpg",
            ApiUtil.BASE_URL + ApiUtil.PATTERN_IMAGE + ApiUtil.SEPARATOR + "85.jpg");

    public static String getRandomImageUrl(){
        int location = RANDOM.nextInt(LIST_IMAGE_URL.size());
        return LIST_IMAGE_URL.get(location);
    }
}
