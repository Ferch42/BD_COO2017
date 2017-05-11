package negocios;
import beans.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.LinkedList;
import basedados.GerenciadorBaseDados;

public class RegrasNegocios {
   private final GerenciadorBaseDados database = new GerenciadorBaseDados();
  
   private LinkedList<String> listaEspera = new LinkedList<String>();
  
   public void setLista(LinkedList<String> lista){
	   this.listaEspera =lista;
   }
   
  public void cadastrarSenha(String senha)throws NegociosException, Exception{
	  if(senha.length()>10){
		  throw new NegociosException( "Digite uma senha com menos que 10 carateres");  
	  }
	  
	  database.insereSenha(senha);
  } 
  public boolean confirmarSenha(String senha)throws Exception{
    boolean ret = database.buscaSenha(senha);
    return ret;
  }
   
  public boolean HaLista(){
    String c = listaEspera.get(0);
       return c != null;
  }
   
  
  
  public void exibirHistoricoCaixa(){
	  
	  
  }
  
  public void exibirHistoricoPedidos(Mesas m, int grupo){
    
     
  
  };
  
  //public void exibirIngredientes();
  
  //public void exibirPratos();
  
  //public void exibirReceitas();
  
  public void cadastrarIngrediente (String nome,double preco, int qtd) throws NegociosException , Exception{
        Ingrediente ingrediente = new Ingrediente(nome, preco, qtd);
     try{
       database.insereIngrediente(ingrediente);
    }
    catch(Exception e){
   //   Log.recordLog(e);
    	e.printStackTrace();
      throw new NegociosException("Nao foi possivel cadastrar o Ingrediente (ingrediente ja existente)");
    }
    
  }
  
  public void decrementaIngrediente(String nome, int inc)throws NegociosException{
    try{
      Ingrediente i = database.buscaIngrediente(nome);
      int quant = i.getQtd();  
      i.setQtd(quant-inc);
      database.alteraIngrediente(i);
    }catch(Exception e) {
    //  Log.recordLog(e);
      throw new NegociosException("Nao foi possivel atualizar a quantidade do ingrediente, re-confirme o nome");
    }
  }

public void incrementaIngrediente(String nome, int inc)throws NegociosException{
   try{
      Ingrediente i = database.buscaIngrediente(nome);
      int quant = i.getQtd();  
      i.setQtd(quant+inc);
      database.alteraIngrediente(i);
    }catch(Exception e) {
    //  Log.recordLog(e);
      throw new NegociosException("Nao foi possivel atualizar a quantidade do ingrediente, re-confirme o nome");
    }
  }

public void cadastrarPrato (String nome,  double preco, LinkedList<Ingrediente> lista,int codigo) throws NegociosException {
   
    Prato prato = new Prato(nome, preco, lista,codigo);
    
    try{
      database.inserePrato(prato);
    }catch (Exception e){
    //Log.recordLog(e);
      throw new NegociosException("Nao foi possivel cadastrar prato, re-confirme as entradas");
    }
    
  }
    
public void deletarPrato(String nome)throws NegociosException  {
	  
	try{
	      database.deletaPrato(nome);
	    }catch (Exception e){
	    //Log.recordLog(e);
	    	e.printStackTrace();
	      throw new NegociosException("Nao foi possivel cadastrar prato, re-confirme as entradas");
	    }
}
public void deletarPrato(int codigo)throws NegociosException  {
	  
	try{
		String nome = database.buscaPratoID(codigo);
	      database.deletaPrato(nome);
	    }catch (Exception e){
	    //Log.recordLog(e);
	    	e.printStackTrace();
	      throw new NegociosException("Nao foi possivel cadastrar prato, re-confirme as entradas");
	    }
}
  public void cadastrarMesas(int ID, boolean d, int grupo)throws NegociosException{
  Mesas m = new Mesas(ID,d,grupo);
    try{
      database.insereMesa(m);
    }catch(Exception e){
   // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel adicionar a Mesa, reconfirme as entradas");
    } 
  }
  
  public void atualizarEstoqueIngrediente(String nome, int qtd)throws NegociosException{
    try{
    	Ingrediente atual = database.buscaIngrediente(nome);
    	atual.setQtd(qtd);
      database.alteraIngrediente(atual);
    }catch(Exception e) {
    //  Log.recordLog(e);
      throw new NegociosException("Nao foi possivel atualizar a quantidade do ingrediente, re-confirme o nome");
    }
       
  }
  public void atualizarPrecoIngrediente(String nome, double preco)throws NegociosException{
    try{
    	Ingrediente atual = database.buscaIngrediente(nome);
    	atual.setPreco(preco);
      database.alteraIngrediente(atual);
    }catch(Exception e) {
     // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel atualizar o preco do ingrediente, re-confirme o nome");
    }
  
  }
 /*  public void atualizarNomeIngrediente(String nome, String nome2)throws NegociosException{
    Ingrediente atual = database.buscaIngrediente(nome);
      atual.setNome(nome2);
    try{
      database.alteraNomeIngrediente(atual,nome);
    }catch(Exception e) {
     // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel atualizar o preco do ingrediente");
       }
    }   
  */
  
 /* void atualizarPrecoPrato(String nome, double preco )throws NegociosException{
    try{ 
    database.alteraPrato(nome, preco);
    }catch(Exception e) {
     // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel atualizar o preco do Prato, re-confirme o nome");
       }
  }*/

  public void altualizarDisponivelMesas(int ID, boolean disp)throws NegociosException{
    try{
    	Mesas m = database.buscaMesas(ID);
    	m.setID(ID);
    	m.setDisponivel(disp);
      database.alteraMesas(m);
    }catch(Exception e){
      // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel atualizar a mesa");      
    }
  
  }
  
  public void atualizarGrupoMesas(int ID, int grupo)throws NegociosException{
    try{
    	Mesas m = database.buscaMesas(ID);
    	m.setGrupo(grupo);
      database.alteraMesas(m);
    }catch(Exception e){
      // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel atualizar a mesa");      
    }
  }
  
  public void menuRemoverIngrediente(String nome) throws NegociosException{
    try{
      database.deletaIngrediente(nome);
    }catch(Exception e){
      // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel remover o Ingrediente");
    }
  }      
 
  public void menuRemoverPrato(String nome) throws NegociosException{
    try{
      database.deletaPrato(nome);
    }catch(Exception e){
      // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel remover o Prato");
    }
  }
  
 /* public void menuRemoverMesa(int ID) throws NegociosException{
    try{
      database.deletaMesas(ID);
    }catch(Exception e){
      // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel remover a mesa");      
    }
    int id2 = ID+1;
    while(true){
    Mesas m1 = database.buscaMesas(id2);
		if (m1 == null) {
			break;
		}
      id2--;
      try{ 
        database.alteraIDMesas(m1,id2);
      }catch(Exception e){
         //Log.recordLog(e);
        throw new NegociosException("Erro ao alterar os IDs das mesas restantes");
      }
    id2+= 2;
    }
  }
  
  public void alteraIDMesas(Mesas m, int id2)throws Exception{ 
    Mesas m1 = buscaMesas(m.getID());
		abreConexao();
		preparaComandoSQL("update mesas set ID = ? where ID = ? ");
		pstmt.setInt(1, id2);
		pstmt.setInt(2, m.getID());
		pstmt.execute();
		fechaConexao();
  }
  */
  public int retornarCaixa()throws NegociosException{
    try{
      int c = database.buscaDinheiroCaixa();
      if(c==-2000000000){throw new NegociosException("Valor de caixa nao existe");}
      return c;
    }catch(Exception e){
  //  Log.recordLog(e);
      throw new NegociosException("Erro ao buscar o valor de caixa");
    }
  }
  
  public void fazerPedido(int cod, int ID)throws NegociosException{
    try{
      Mesas m = database.buscaMesas(ID);
      String nome = database.buscaPratoID(cod);
      Prato p = database.buscaPrato(nome);
      database.insereLogTemp(p,m);
      for(Ingrediente i : p.getReceita()){
        try{
          Ingrediente idb = database.buscaIngrediente(i.getNome());
          if(idb.getQtd()<=0){throw new NegociosException("O ingrediente " +i.getNome()+" esta esgotado");}
           }catch(Exception e){
            throw new NegociosException("O ingrediente " +i.getNome()+" nao existe");
        }
      }
      for(Ingrediente i2 : p.getReceita()){
        Ingrediente idb2 = database.buscaIngrediente(i2.getNome());
        idb2.setQtd(idb2.getQtd()-i2.getQtd());
        database.alteraIngrediente(idb2);
      }
    } catch(Exception e){
    	e.printStackTrace();
       throw new NegociosException("Refer to the error indicated by the database");
    }
  }
  
  public void cancelarPedidoPrato(int cod, int ID)throws NegociosException{
    try{
     Mesas m = database.buscaMesas(ID);
     String nome = database.buscaPratoID(cod);
     Prato p = database.buscaPrato(nome);
     LinkedList<Prato> temp = database.buscaLogTemp(m); 
     Prato p1 = temp.getFirst();
        database.deletaLogTemp(p1,m);
      for (Ingrediente i : p.getReceita()){
        Ingrediente idb = database.buscaIngrediente(i.getNome());
        idb.setQtd(idb.getQtd()+i.getQtd());
        database.alteraIngrediente(idb);
      } 
    }catch(Exception e){
    	e.printStackTrace();
     throw new NegociosException("Erro ao cancelar o pedido, re-confirme o codigo do prato e da mesa");
    }
    
  }
  
  public void inserirPedidoEncerrado(Mesas m)throws NegociosException, Exception{
  
	  LinkedList<Prato> temp = database.buscaLogTemp(m); 
	  for (Prato p : temp){
      try{
        database.insereLogP(p,m);
        database.deletaLogTemp(p,m);
      }catch(Exception e){
       // Log.recordLog(e);
    	  e.printStackTrace();
        throw new NegociosException("Erro ao registrar o pedido");
      }
    }
  }
  
  public void encerrarPedido(int ID) throws NegociosException{
    try {
    	Mesas m = database.buscaMesas(ID);
    	LinkedList<Prato> temp = database.buscaLogTemp(m);
    	inserirPedidoEncerrado(m);
    	int gains = 0;
    	try{
    	for(Prato p : temp){
    		gains+= p.getPreco();
    	}
    	}catch(Exception e){
    	}
    int ant = m.getGrupo();	
   m.setDisponivel(true);
   m.setGrupo(ant+1);
     incrementarCaixa(gains);
     database.alteraMesas(m); 
    }catch(Exception e){
    	e.printStackTrace();
    // Log.recordLog(e);
      throw new NegociosException("Erro ao acrescentar ao caixa");
    }  
  }
  
  public void apagarLogPrato() throws NegociosException{
    try{
      database.apagaLogP();
       }catch(Exception e){
     // Log.recordLog(e);
    	   e.printStackTrace();
      throw new NegociosException("Erro ao apagar o Log");
    }
  }      
  
  public int buscaMesaDisponivel()throws NegociosException{
    try{
    LinkedList<Mesas> lm = database.listaMesas();
    for(Mesas m : lm){
      if (m.isDisponivel() == true){
        m.setDisponivel(false); 
        database.alteraMesas(m);
        return m.getID();
         
      }
    }
    }catch(Exception e){
    //  Log.recordLog(e);
      throw new NegociosException("Erro ao buscar mesa disponivel");
    }
    throw new NegociosException("Nao ha mesas disponiveis");
  }
  
  public void incrementarCaixa(int i)throws NegociosException{
    try{
    int atual = database.buscaDinheiroCaixa();
    database.alteraCaixa(atual+i);
    }catch(Exception e){
     // Log.recordLog(e);
    	e.printStackTrace();
      throw new NegociosException("Erro ao acessar o banco de dados para incrementar a caixa");
    }
  }
  
   public void comecarCaixa(int i){
	   try{
		   database.insereDinheiroCaixa(i);
	   }catch(Exception e ){
		   e.printStackTrace();
	   }
	   
   }
  public void decrementarCaixa(int i)throws NegociosException{
	  try{
		  int atual = database.buscaDinheiroCaixa();
		    database.alteraCaixa(atual-i);
		    }catch(Exception e){
		     // Log.recordLog(e);
		    	e.printStackTrace();
		      throw new NegociosException("Erro ao acessar o banco de dados para incrementar a caixa");
		    }
	  
	  
  }
  public void deletarCaixa()throws Exception{
	  try{
		  database.deletaDinheiroCaixa();
	  }catch(Exception e ){
		  
		  e.printStackTrace();
	  }
  }
  public void insereListaEspera(String nome){
   listaEspera.addLast(nome); 
  }
  
  public void retiraListaEspera()throws NegociosException{
    try{
      listaEspera.removeFirst();
    }catch(NoSuchElementException e){
     // Log.recordLog(e);
    throw new NegociosException("Lista de espera vazia");
    }

  }
  
}
