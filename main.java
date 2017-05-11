package beans;
import basedados.GerenciadorBaseDados;
import java.util.LinkedList;
import java.util.Scanner;
import negocios.*;
public class main {
	
	public static void main(String[] args) {
		
		RegrasNegocios rn = new RegrasNegocios();
        String senha = "abacaxi";
        Ingrediente ar = new Ingrediente("arroz",17.0,51244);
        Ingrediente ab = new Ingrediente("feijao",17.0,100);
		LinkedList<Ingrediente> lista = new LinkedList<Ingrediente>();
		lista.add(ar);
		lista.add(ab);
		Mesas m = new Mesas(1,true,1);
        try{
         
        
         
		}catch(Exception e){
			e.printStackTrace();
			
		}	

        
	}
}