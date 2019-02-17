package work2;



public class CheckBMI {
    public String checkb(String x,String y,String box){
        double m=Double.parseDouble(x)/100;
        double kg=Double.parseDouble(y);
        double bmi =kg/(m*m);
        String resultbmi="";
       if(bmi<18.5){
           if(box.equals("8")||box.equals("9")||box.equals("4")||box.equals("6")){
               resultbmi="Expected Result:skinny Accept";
           }else {
               resultbmi="Expected Result:skinny Reject";
           }

       }else if (bmi>=18.5&&bmi<=25){
           if(box.equals("3")||box.equals("7")||box.equals("5")){
               resultbmi="Expected Result:Normal Accept";
           }else {
               resultbmi="Expected Result:Normal Reject";
           }
       }else {
           if(box.equals("1")||box.equals("2")){
               resultbmi="Expected Result:fat Accept";
           } else {
               resultbmi="Expected Result:fat Reject";
           }
       }
        return resultbmi;
    }
}
