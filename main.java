import java.util.Scanner;
public class main {
	
	public static void main(String[] args) {
		
		GerenciadorBaseDados bd= new GerenciadorBaseDados();
		
		Scanner stdin= new Scanner(System.in);
		
		System.out.println("Digite o n da mesa");
		int nome= stdin.nextInt();
		System.out.println("Digite o disponivel");
		boolean b= stdin.nextBoolean();
		System.out.println("Digite o grupo");
		int qtd= stdin.nextInt();
		
		Mesas m= new Mesas(nome,b,qtd);
		System.out.println("o que quer fazer?");
		int f=stdin.nextInt();
		if(f==1){
			try{
			bd.insereMesa(m);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(f==2){
			try{
				bd.deletaMesas(m.getID());
			}
			catch(Exception e){
				e.printStackTrace();
			}}
		else if(f==3){
			try{
				bd.alteraMesas(m);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(f==4){
			try{
				Mesas i1=bd.buscaMesas(m.getID());
				if (i1!=null){
					System.out.println("achou");
				}
				else{
					System.out.println("nao achou");
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		}
		
		
		}

