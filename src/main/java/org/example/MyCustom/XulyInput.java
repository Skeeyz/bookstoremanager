
package MyCustom;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class XulyInput {
    public JTextField tf;
    public void live_input_tf(JTextField tf){
        this.tf=tf;
        tf.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e){
                checkText(tf.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e){
                checkText(tf.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e){
                checkText(tf.getText());
            }
        });
    }
    
    private void checkdataTF(String x){
        if(x.length()==0){
            
        }
        return;
    }
    
    public boolean dataso(String x){
        for(char r: x.toCharArray()){
            if(!isNumber(r)){
                return false;
            }
        }
        return true;
    }
    
    public boolean khongkitudacbiet(String x){
        for(char r: x.toCharArray()){
            if(isSpecialCharacter(r)){
                return false;
            }
        }
        return true;
    }
    
    private void checkText(String text){
        for(char r : text.toCharArray()){
        }
        return;
    }
    private boolean isNumber(char x){
        if(x>='0'&& x<='9') return true;
        return false;
    }
    
    private boolean isLowerCaseCharacter(char x){
        if(x>='A' && x<='Z') return true;
        return false;
    }

    private boolean isUpperCaseCharacter(int x){
        if(x>='a' && x<='z') return true;
        return false;
    }
    
        private boolean isSpecialCharacter(char x){
        if(!isNumber(x) && !isLowerCaseCharacter(x) && !isUpperCaseCharacter(x)) return true;
        return false;
    }
}
  
