package homework02;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.io.UnsupportedEncodingException;
//import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Homework02 {

    private static final String ENCODING_UTF8 = "UTF-8";

    public static void main(String[] args) throws UnsupportedEncodingException {
        
        String name = "";
        int level;
        
        //try(Scanner in = new Scanner(new FileReader("src\\file.txt"))){
        //    name = in.nextLine();
        try(DataInputStream dis = new DataInputStream(new FileInputStream("src\\Pudge.bin"))){
            name = dis.readUTF();
        }catch (FileNotFoundException e){
            Scanner in = new Scanner(System.in);
            System.out.println("Введите имя: ");
            name = in.nextLine();
        }catch (IOException e){

        }
        System.out.println("имя: " + name);

        Scanner in = new Scanner(System.in);
        System.out.println("Введите уровень: ");
        while (true){
            if (in.hasNextInt()){
                level = in.nextInt();
                System.out.println("уровень: " + level);
                break;
            }else{
                in.next();
            }
        }
        in.close();
        
        Locale locale = new Locale(args[0], args[1]);
        ResourceBundle rb = ResourceBundle.getBundle("text", locale);

        //System.out.println(rb.keySet());

        Set<String> phrasesSet = new HashSet<String>();
        int phraseLevel;
        for (String key : rb.keySet()) {
            phraseLevel = Integer.parseInt(key.substring(5,key.indexOf(".")));
            String value = new String(rb.getString(key).getBytes(StandardCharsets.ISO_8859_1), ENCODING_UTF8);
            //System.out.println(value);
            if(phraseLevel==level){
                phrasesSet.add(value);
            }    
        }

        String[] phrases = phrasesSet.toArray(new String[phrasesSet.size()]);
        System.out.println(Arrays.asList(phrases));
        
        Pudge p = new Pudge(name, level, phrases);
        
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("src\\Pudge.bin"))){
            dos.writeUTF(p.getName());
            dos.writeInt(p.getLevel());
            for (int i=0; i<phrases.length; i++){
                dos.writeUTF(phrases[i]);
            }
            System.out.println("Success");
        }catch(IOException ex){
            
        }
    }

}
