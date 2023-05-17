public class StringFilter {
    
    public static String filter(String input) {
        // Find the index of the unique string
        int index = input.indexOf("wanderlust");
        if (index == -1) {
            return "";
        }
        // Extract the useful portion of the string
        return input.substring(index + "wanderlust".length());
    }

    public static void main(String[] args) {
        String input = "This is some useless content. Wanderlust is a unique string. This is the useful content.";
        String output = filter(input);
        System.out.println(output);
    }
}
