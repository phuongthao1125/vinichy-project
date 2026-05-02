package com.vinichy.shop.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * Chuyển đổi chuỗi tiếng Việt có dấu thành không dấu và viết thường.
     * Ví dụ: "Túi xách" -> "tui xach"
     */
    public static String removeAccents(String str) {
        if (str == null) return "";
        
        // Handle Telex-style typing (e.g., tuis -> túi, ddorr -> đỏ)
        String s = str.toLowerCase()
                .replace("aa", "a")
                .replace("ee", "e")
                .replace("oo", "o")
                .replace("dd", "d")
                .replace("aw", "a")
                .replace("ow", "o")
                .replace("uw", "u")
                .replaceAll("([aeiouy])([sfrxj])", "$1") // Remove Telex tone marks
                .replace("w", ""); // Remove stray 'w'
        
        try {
            String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("")
                    .replace('đ', 'd')
                    .trim();
        } catch (Exception e) {
            return s;
        }
    }

    /**
     * Kiểm tra xem chuỗi 'target' có chứa chuỗi 'query' hay không (không phân biệt dấu và hoa thường).
     */
    public static boolean fuzzyMatch(String target, String query) {
        if (target == null || query == null || query.trim().isEmpty()) return false;
        String normalizedTarget = removeAccents(target);
        String normalizedQuery = removeAccents(query);
        
        // Split by words and check if all query words are in target (order doesn't matter)
        String[] queryWords = normalizedQuery.split("\\s+");
        for (String word : queryWords) {
            if (!normalizedTarget.contains(word)) return false;
        }
        return true;
    }
}
