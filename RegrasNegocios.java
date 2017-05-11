package negocios;

import java.util.List;
import java.util.LinkedList;
import basedados.GerenciadorBaseDados;

public class RegrasNegocios{
   private GerenciadorBaseDados database = new GerenciadorBaseDados();

   private LinkedList<String> listaEspera = new LinkedList<String>();
  
  public boolean confirmarSenha(String senha){
    boolean ret = buscaSenha(senha);
    return ret;
  }
   
  public bool HaLista(){
    String c = listaespera.getfirst();
    if(c ==null){
     return false;
    } else{
    return true;
    }
  }
   
  public void incrementaIngrediente(String nome, int inc){
   try{
      Ingrediente i = database.buscaIngrediente(nome);
      int quant = i.getQtd();  
      i.setQtd(quant+inc);
      database.alteraIngrediente();
    }catch(Exception e) {
    //  Log.recordLog(e);
      throw new NegociosException("Nao foi possivel atualizar a quantidade do ingrediente, re-confirme o nome");
    }
  }
  
  public void decrementaIngrediente(String nome, int inc){
    try{
      Ingrediente i = database.buscaIngrediente(nome);
      int quant = i.getQtd();  
      i.setQtd(quant-inc);
      database.alteraIngrediente();
    }catch(Exception e) {
    //  Log.recordLog(e);
      throw new NegociosException("Nao foi possivel atualizar a quantidade do ingrediente, re-confirme o nome");
    }
  }
  
  public void exibirHistorico();
  
  public void exibirHistoricoPedidos(Mesas m, int grupo){
    
     
  
  };
  
  public void exibirIngredientes();
  
  public void exibirPratos();
  
  public void exibirReceitas();
  
  public void cadastrarIngrediente (String nome,double preco, int qtd) throws NegociosException{
        Ingrediente ingrediente = new Ingrediente(nome, qtd, preco);
    try{
       database.insereIngrediente(ingrediente);
    }
    catch(Exception e){
     // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel cadastrar o Ingrediente (problemas no acesso ao banco de dados)");
    }
    
  }
  
  public void cadastrarPrato (String nome,  double preco, LinkedList<ingredientes> lista) throws NegociosException {
   
    Prato prato = new prato(nome, preco, lista);
    
    try{
      database.inserePrato(prato);
    }catch (Exception e){
    //Log.recordLog(e);
      throw new NegociosException("Nao foi possivel cadastrar prato, re-confirme as entradas");
    }
    
  }
    
  public void cadastrarMesas(int ID, boolean d, int grupo){
  Mesas m = new Mesas(ID,d,grupo);
    try{
      database.insereMesa(m);
    }catch(Exception e){
   // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel adicionar a Mesa, reconfirme as entradas");
    } 
  }
  
  public void atualizarEstoqueIngrediente(String nome, int qtd)throws NegociosException{
      Ingrediente atual = database.buscaIngrediente(nome);
      atual.setQtd(qtd);
    try{
      database.alteraIngrediente(atual);
    }catch(Exception e) {
    //  Log.recordLog(e);
      throw new NegociosException("Nao foi possivel atualizar a quantidade do ingrediente, re-confirme o nome");
    }
       
  }
  public void atualizarPrecoIngrediente(String nome, double preco)throws NegociosException{
   Ingrediente atual = database.buscaIngrediente(nome);
      atual.setPreco(preco);
    try{
      database.alteraIngrediente(atual);
    }catch(Exception e) {
     // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel atualizar o preco do ingrediente, re-confirme o nome");
    }
  
  }
   public void atualizarNomeIngrediente(String nome, String nome2)throws NegociosException{
    Ingrediente atual = database.buscaIngrediente(nome);
      atual.setNome(nome2);
    try{
      database.alteraNomeIngrediente(atual,nome);
    }catch(Exception e) {
     // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel atualizar o preco do ingrediente");
       }
    }   
  
  
  void atualizarPrecoPrato(String nome, double preco )throws NegociosException{
    try{ 
    database.alteraPrato(nome, preco);
    }catch(Exception e) {
     // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel atualizar o preco do Prato, re-confirme o nome");
       }
  }

  public void altualizarDisponivelMesas(int ID, bool disp){
    Mesas m = buscaMesas(ID);
    m.setID(ID);
    m.setDisponivel(disp);
    try{
      database.alteraMesas(m);
    }catch(Exception e){
      // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel atualizar a mesa");      
    }
  
  }
  
  public void atualizarGrupoMesas(int ID, int grupo){
    Mesas m = buscaMesas(ID);
    m.setGrupo(grupo);
    try{
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
  
  public void menuRemoverMesa(int ID) throws NegociosException{
    try{
      database.deletaMesas(ID);
    }catch(Exception e){
      // Log.recordLog(e);
      throw new NegociosException("Nao foi possivel remover a mesa");      
    }
    int id2 = ID+1;
    while(true){
    Mesas m1 = buscaMesas(id2);
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
  /*
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
  public void retornarCaixa()throws NegociosException{
    try{
      int c = database.buscaDinheiroCaixa();
    }catch(Exception e){
  //  Log.recordLog(e);
      throw new NegociosException("Erro ao buscar o valor de caixa");
    }
    if(c==null){throw new NegociosException("Valor de caixa nao existe");}
    return c;
  }
  
  public void fazerPedido(int cod, int ID)throws NegociosException{
    try{
      Mesas m = buscaMesas(ID);
      String nome = database.buscaPratoID(cod);
      Prato p = database.buscaPrato(nome);
      database.insereLogTemp(p,m);
      for(Ingrediente i : p.getReceita()){
        try{
          Ingrediente idb = database.buscaIngrediente(i.getNome());
           }catch(Exception e){
            throw new NegociosException("O ingrediente " +i.getNome()+" esta esgotado");
        }
      }
      for(Ingrediente i2 : p.getReceita()){
        Ingrediente idb2 = database.buscaIngrediente(i2.getNome());
        idb2.setQtd(idb2.getQtd()-i2.getQtd());
        database.alteraIngrediente(idb2);
      }
    } catch(Exception e){
       throw new NegociosException("Refer to the error indicated by the database");
    }
  }
  
  public void cancelarPedido(int cod, int ID){
    try{
     Mesas m = buscaMesas(ID);
     String nome = database.buscapratoID(cod);
     Prato p = database.buscaPrato(nome);
     LinkedList<Prato> temp = buscaLogTemp(m); 
      for(Prato p: temp){
        database.deletaLogTemp(p,m);
      } 
      for (Ingrediente i : p.getReceita()){
        Ingrediente idb = database.buscaIngrediente(i.getNome());
        idb.setQtd(idb.getQtd()+i.getQtd());
        database.alteraIngrediente(idb);
      } 
    }catch(Exception e){
     throw new NegociosException("Erro ao cancelar o pedido, re-confirme o codigo do prato e da mesa");
    }
    
  }
  
  public void inserirPedidoEncerrado(Mesas m)throws NegociosException{
    LinkedList<Prato> temp = buscaLogTemp(m); 
    for (Prato p : temp){
      try{
        database.insereLogP(p,m);
        database.deletaLogTemp(p,m);
      }catch(Exception e){
       // Log.recordLog(e);
        throw new NegociosException("Erro ao registrar o pedido");
      }
    }
    atualizarGrupoMesas(m.getID(),m.getGrupo()+1);
  }
  
  public void encerrarPedido(Mesas m) throws NegociosException{
     inserirPedidoEncerrado(m);
     int gains = 0;
    for(Prato p : temp){
     gains+= p.getPreco();
    }
    m.setDisponivel(true);
    try {
     database.insereDinheiroCaixa(gains);
     database.alteraMesas(m); 
    }catch(Exception e){
    // Log.recordLog(e);
      throw new NegociosException("Erro ao acrescentar ao caixa");
    }  
  }
  
  public void apagarLog() throws NegociosException{
    try{
      database.apagaLog();
       }catch(Exception e){
     // Log.recordLog(e);
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
         break;
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
    database.insereDinheiroCaixa(i);
    }catch(Exception e){
     // Log.recordLog(e);
      throw new NegociosException("Erro ao acessar o banco de dados para incrementar a caixa");
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
