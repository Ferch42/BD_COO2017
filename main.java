import java.util.Scanner;
public class main {
	
	public static void main(String[] args) {
		
		GerenciadorBaseDados bd= new GerenciadorBaseDados();
		
		Scanner stdin= new Scanner(System.in);
		
		System.out.println("Digite o nome do ingrediente");
		String nome= stdin.nextLine();
		System.out.println("Digite o preço");
		Double preco=stdin.nextDouble();
		System.out.println("Digite a qtd");
		int qtd= stdin.nextInt();
		
		Ingrediente i= new Ingrediente(nome,preco,qtd);
		System.out.println("o que quer fazer?");
		int f=stdin.nextInt();
		if(f==1){
			try{
			bd.insereIngrediente(i);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(f==2){
			try{
				bd.deletaIngrediente(i.getNome());
			}
			catch(Exception e){
				e.printStackTrace();
			}}
		else if(f==3){
			try{
				bd.alteraIngrediente(i);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(f==4){
			try{
				Ingrediente i1=bd.buscaIngrediente(i.getNome());
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

