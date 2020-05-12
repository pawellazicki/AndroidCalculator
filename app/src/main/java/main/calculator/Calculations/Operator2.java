package main.calculator.Calculations;

public class Controller2 {
    private StringBuilder builder = new StringBuilder();

    public String append(String text){
        builder.append(text);

        return  builder.toString();
    }

    public String operate(String operation){
        String builded = builder.toString() + operation;
        String leftNum = "", rightNum = "";

        for(int i = 0; i < builded.length(); i++){
            if(!Character.isDigit(builded.charAt(i)) && builded.charAt(i) != '.') {
                leftNum = builded.substring(0, i++);
                if(i == builded.length()) {
                    builder.append(operation);
                    return builded;
                }
                rightNum = builded.substring(i, builded.length() -1);
                break;
            }
        }
        double result = Double.parseDouble(leftNum) + Double.parseDouble(rightNum);
        return String.valueOf(result);
    }

    public void clear(){
        builder = new StringBuilder();
    }
}
