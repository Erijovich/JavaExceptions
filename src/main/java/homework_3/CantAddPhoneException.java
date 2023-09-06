package homework_3;

public class CantAddPhoneException extends RuntimeException{
    CantAddPhoneException(){
        super();
    }
    CantAddPhoneException(String message){
        super(message);
    }
}
