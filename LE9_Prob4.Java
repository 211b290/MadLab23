String pattern = "dummy\\sstring";
String input = "This is a dummy string that we want to match against.";
boolean matches = input.matches(pattern);
System.out.println(matches);
