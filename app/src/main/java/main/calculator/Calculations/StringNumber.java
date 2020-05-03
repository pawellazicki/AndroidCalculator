package main.calculator.Calculations;

public class StringNumber {
    private StringBuilder numberBuilder;
    private boolean isNumberMinus;

    public StringNumber() {
        this.numberBuilder = new StringBuilder();
        isNumberMinus = false;
    }

    public boolean isReady(){
        if(numberBuilder.length() > 0 && !numberBuilder.toString().equals("0."))
            return true;
        return false;
    }

    public void clear(){
        numberBuilder = new StringBuilder();
        isNumberMinus = false;
    }

    public String append(String str){
        if(str.equals(".") && numberBuilder.toString().contains("."))
            return "";
        if(str.equals(".") && numberBuilder.length() == 0) {
            numberBuilder.append("0.");
            return "0.";
        }
        numberBuilder.append(str);
        return str;
    }

    public void setMinus(){
        isNumberMinus = !isNumberMinus;
    }

    public double getDouble(){
        if(isReady()) {
            double result;
            if (isNumberMinus)
                result = Double.parseDouble(numberBuilder.toString()) * (double) -1;
            else
                result = Double.parseDouble(numberBuilder.toString());
            clear();
            return result;
        }
        return 0;
    }

    public void delete(){
        if(numberBuilder.length() > 0)
            if(numberBuilder.charAt(numberBuilder.length()-1) == '.' && numberBuilder.charAt(numberBuilder.length() - 2) == '0')
                numberBuilder.delete(0,1);
            else
                numberBuilder.deleteCharAt(numberBuilder.length() - 1);
    }

    public String getStr(){
        return numberBuilder.toString();
    }
}
