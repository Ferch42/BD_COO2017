import java.util.LinkedList;
import java.util.Scanner;
public class main {
	
	public static void main(String[] args) {
		
		GerenciadorBaseDados bd= new GerenciadorBaseDados();
		
		Scanner stdin= new Scanner(System.in);
		
		Mesas m=new Mesas(50,false,1);
		LinkedList <Prato> p=new LinkedList<Prato> ();
		try {
			 p= bd.buscaLogTemp(m);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Prato pi:p){
			System.out.println(pi.getNome());
		}

}
}