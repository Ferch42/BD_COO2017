import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

//import basedados.BaseDadosException;
//import utilidades.Log;

public class GerenciadorBaseDados extends ConectorJDBC {

	private static final String PASSWORD = "Qwertyuiop@0987654321";
	private static final String USER = "root";
	private static final String HOST = "localhost";
	private static final String DB_NAME = "restaurantemagico";

	public GerenciadorBaseDados() {
		super(DB.MYSQL);
	}

	@Override
	protected String getUser() {
		return USER;
	}

	@Override
	protected String getPassword() {
		return PASSWORD;
	}

	@Override
	protected String getDbHost() {
		return HOST;
	}

	@Override
	protected String getDbName() {
		return DB_NAME;
	}

	// INGREDIENTES DO ESTOQUE
	public void insereIngrediente(Ingrediente i) throws Exception {
		abreConexao();
		preparaComandoSQL("insert into estoque (ingredientes, qtd, preco) values (?, ?, ?)");
		try{
		pstmt.setString(1, i.getNome());
		pstmt.setInt(2, i.getQtd());
		pstmt.setDouble(3, i.getPreco());
		pstmt.execute();
		} catch (SQLException e) {
			throw new BaseDadosException(
					"Erro nos parametros do ingrediente");
		}
	
		fechaConexao();
	}

	public Ingrediente buscaIngrediente(String nome) throws Exception {
		abreConexao();
		preparaComandoSQL("select ingredientes,qtd,preco from estoque where ingredientes = ? ");
		pstmt.setString(1, nome);
                try{   
		rs = pstmt.executeQuery();

		Ingrediente i = null;
		if (rs.next()) {
			String ingrediente = rs.getString(1);
			int qtd = rs.getInt(2);
			double preco = rs.getDouble(3);
			i = new Ingrediente(ingrediente, preco, qtd);
		}
 		} catch (SQLException e) {
			//Log.recordLog(e);
			throw new BaseDadosException(
					"Re-confirme o nome do ingrediente");
		}
		fechaConexao();
		return i;

	}
	
	private Ingrediente buscaIngredienteAux(String nome) throws Exception {

		preparaComandoSQL("select ingredientes,qtd,preco from estoque where ingredientes = ? ");
		pstmt.setString(1, nome);

		ResultSet rs1 = pstmt.executeQuery();

		Ingrediente i = null;
		if (rs1.next()) {
			String ingrediente = rs1.getString(1);
			int qtd = rs1.getInt(2);
			double preco = rs1.getDouble(3);
			i = new Ingrediente(ingrediente, preco, qtd);
		}

	
		return i;

	}

	public void deletaIngrediente(String nome) throws Exception {

		Ingrediente i = buscaIngrediente(nome);
		if (i == null) {
			Exception e = new BasedadosException("Ingrediente inexistente");
			throw e;
		}
		
		abreConexao();
		preparaComandoSQL("delete from estoque where ingredientes = ? ");
		try{
		pstmt.setString(1, nome);
		pstmt.execute();
		} catch (SQLException e) {
			//Log.recordLog(e);
			throw new BaseDadosException(
					"Re-confirme o nome do ingrediente");
		}
		fechaConexao();

	}

	public void alteraIngrediente(Ingrediente i) throws Exception {

		Ingrediente i1 = buscaIngrediente(i.getNome());
		if (i1 == null) {
			Exception e = new BasedadosException("Ingrediente inexistente");
			throw e;

		}
		
		abreConexao();
		preparaComandoSQL("update estoque set qtd = ? , preco =? where ingredientes = ? ");
		try{
		pstmt.setInt(1, i.getQtd());
		pstmt.setDouble(2, i.getPreco());
		pstmt.setString(3, i.getNome());
		pstmt.execute();
		} catch (SQLException e) {
			//Log.recordLog(e);
			throw new BaseDadosException(
					"Erro nos parametros do ingrediente a ser atualizado");
		}
	        fechaConexao();
	} 
     


	
	
	public LinkedList<Ingrediente> listaIngredientes() throws Exception {
		LinkedList<Ingrediente> ingredientes = new LinkedList<Ingrediente>();
		abreConexao();
		preparaComandoSQL("select ingredientes,qtd,preco from estoque");
		
		try {
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String nome = rs.getString(1);
				int qtd = rs.getInt(2);
				double preco = rs.getDouble(3);
				Ingrediente ingrediente = new Ingrediente(nome, preco, qtd);
				ingredientes.add(ingrediente);
			}
			
		} catch (SQLException e) {
			throw new BasedadosException("Problemas ao ler o resultado da consulta.");
		}
		
		fechaConexao();
		return ingredientes;
	}

                


	// MESAS DO RESTAURANTE

	public void insereMesa(Mesas m) throws Exception {
		
		abreConexao();
		preparaComandoSQL("insert into mesas (ID, disponivel, grupo) values (?, ?, ?)");
		try{
		pstmt.setInt(1, m.getID());
		pstmt.setBoolean(2, m.isDisponivel());
		pstmt.setInt(3, m.getGrupo());
		pstmt.execute();
		} catch (SQLException e) {
			//Log.recordLog(e);
			throw new BaseDadosException(
					"Erro nos parametros da mesa a ser inserida");
		}
		fechaConexao();
	}

	public Mesas buscaMesas(int ID) throws Exception {
		
		abreConexao();
		preparaComandoSQL("select ID,disponivel, grupo from mesas where ID = ? ");
		pstmt.setInt(1, ID);
                try{
		rs = pstmt.executeQuery();
		Mesas m = null;
		if (rs.next()) {
			int id = rs.getInt(1);
			boolean b = rs.getBoolean(2);
			int grupo = rs.getInt(3);
			m = new Mesas(id, b, grupo);
		}
		} catch (SQLException e) {
			//Log.recordLog(e);
			throw new BaseDadosException(
					"Re-confirme o ID da mesa");
		}
		fechaConexao();
		return m;
	}

	public void deletaMesas(int ID) throws Exception {

		Mesas m = buscaMesas(ID);
		if (m == null) {
			Exception e = new BasedadosException("Mesa inexistente");
			throw e;
		}
		
		abreConexao();
		preparaComandoSQL("delete from mesas where ID = ? ");
		try{
		pstmt.setInt(1, ID);
		pstmt.execute();
		} catch (SQLException e) {
			//Log.recordLog(e);
			throw new BaseDadosException(
					"Re-confirme o ID da mesa");
		}
		fechaConexao();
	}

	public void alteraMesas(Mesas m) throws Exception {

		Mesas m1 = buscaMesas(m.getID());
		if (m1 == null) {
			Exception e = new BasedadosException("Mesa inexistente");
			throw e;

		}
		abreConexao();
		preparaComandoSQL("update mesas set disponivel = ? , grupo =? where ID = ? ");
	        pstmt.setBoolean(1, m.isDisponivel());
		pstmt.setInt(2, m.getGrupo());
		pstmt.setInt(3, m.getID());
		try{
		pstmt.execute();
		} catch (SQLException e) {
			//Log.recordLog(e);
			throw new BaseDadosException(
					"Erro na mesa enviada");
		}
		fechaConexao();

	}
	
	public LinkedList<Mesas> listaMesas() throws Exception {
		LinkedList<Mesas> mesas = new LinkedList<Mesas>();
		abreConexao();
		preparaComandoSQL("select ID,disponivel, grupo from mesas");
		
		try {
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				boolean disponivel = rs.getBoolean(2);
				int grupo = rs.getInt(3);
				Mesas mesa = new Mesas(id, disponivel, grupo);
				mesas.add(mesa);
			}
			
		} catch (SQLException e) {
			throw new Exception("Problemas ao ler o resultado da consulta.");
		}
		
		fechaConexao();
		return mesas;
	}

	// PRATOS DO RESTAURANTE
	public void inserePrato(Prato p) throws Exception {
		abreConexao();
		preparaComandoSQL("insert into pratos (nome,preco) values (?, ?)");
		pstmt.setString(1, p.getNome());
		pstmt.setDouble(2, p.getPreco());
		pstmt.execute();

		for (Ingrediente i : p.receita) {
			preparaComandoSQL("insert into receitas (prato, ingredientes, qtd) values (?,?,?)");
			pstmt.setString(1, p.getNome());
			pstmt.setString(2, i.getNome());
			pstmt.setInt(3, i.getQtd());
			pstmt.execute();
		}
		preparaComandoSQL("insert into codigos (codigo,prato) values (?, ?)");
-		pstmt.setInt(1, p.getCodigo());
-		pstmt.setString(2, p.getNome());
		pstmt.execute();
		fechaConexao();
	}

	public Prato buscaPrato(String nome) throws Exception {
		abreConexao();
		preparaComandoSQL("select preco from pratos where nome = ? ");
		pstmt.setString(1, nome);

		rs = pstmt.executeQuery();

		Prato p = null;
		double preco = -1;
		if (rs.next()) {
			preco = rs.getDouble(1);
		}
		rs = null;
		preparaComandoSQL("select ingredientes,qtd from receitas where prato = ? ");
		pstmt.setString(1, nome);
		LinkedList<Ingrediente> l = new LinkedList<Ingrediente>();
		rs = pstmt.executeQuery();

		while (rs.next()) {
			String s = rs.getString(1);
			int a = rs.getInt(2);
			Ingrediente i1 = buscaIngredienteAux(s);
			if (i1 == null) {
				Exception e = new Exception("Ingrediente fora do estoque");
				throw e;
			}
			double precoi = i1.getPreco();
			Ingrediente i2 = new Ingrediente(s, precoi, a);
			l.add(i2);
		}
		if (l != null && preco != -1) {
			preparaComandoSQL("select codigo from codigos where prato = ?");
-			pstmt.setString(1, nome);
-			rs = pstmt.executeQuery();
-			int codigo = rs.getInt(1);
-			p = new Prato(nome, preco, l, codigo);
		}

		fechaConexao();
		return p;
	}
	
	private Prato buscaPratoAux(String nome) throws Exception {
	
		preparaComandoSQL("select preco from pratos where nome = ? ");
		pstmt.setString(1, nome);

		ResultSet aux = pstmt.executeQuery();

		Prato p = null;
		double preco = -1;
		if (aux.next()) {
			preco = aux.getDouble(1);
		}
		aux = null;
		preparaComandoSQL("select ingredientes,qtd from receitas where prato = ? ");
		pstmt.setString(1, nome);
		LinkedList<Ingrediente> l = new LinkedList<Ingrediente>();
		aux = pstmt.executeQuery();

		while (aux.next()) {
			String s = aux.getString(1);
			int a = aux.getInt(2);
			Ingrediente i1 = buscaIngrediente(s);
			if (i1 == null) {
				Exception e = new Exception("Ingrediente fora do estoque");
				throw e;
			}
			double precoi = i1.getPreco();
			Ingrediente i2 = new Ingrediente(s, precoi, a);
			l.add(i2);
		}
		if (l != null && preco != -1) {
			preparaComandoSQL("select codigo from codigos where prato = ?");
-			pstmt.setString(1, nome);
-			rs = pstmt.executeQuery();
-			int codigo = rs.getInt(1);
-			p = new Prato(nome, preco, l, codigo);
		}

		
		return p;
	}

	public void deletaPrato(String nome) throws Exception {

		Prato p = buscaPrato(nome);
		if (p == null) {
			Exception e = new BasedadosException("Prato inexistente");
			throw e;
		}
		abreConexao();
		preparaComandoSQL("delete from pratos where nome = ? ");
		pstmt.setString(1, nome);
		pstmt.execute();

		preparaComandoSQL("delete from receitas where prato = ?");
		pstmt.setString(1, nome);
		pstmt.execute();

		preparaComandoSQL("delete from codigos where prato = ?");
-		pstmt.setString(1, nome);
-		pstmt.execute();
		
		fechaConexao();
	}
	
	public LinkedList<Prato> listaPratos() throws Exception {
		LinkedList<Prato> pratos = new LinkedList<Prato>();
		abreConexao();
		preparaComandoSQL("select nome, preco from pratos");
		
		try {
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ResultSet aux;
				String nome = rs.getString(1);
				double preco = rs.getDouble(2);
				preparaComandoSQL("select codigo from codigos where prato = ?");
-				pstmt.setString(1, nome);
-				aux = pstmt.executeQuery();
-				int codigo = aux.getInt(1);
				LinkedList<Ingrediente> ingredientes = new LinkedList<Ingrediente>();
				preparaComandoSQL("select ingredientes,qtd from receitas where prato = ? ");
				pstmt.setString(1, nome);
				aux = pstmt.executeQuery();
				
				
				while(aux.next()) {
					String s = aux.getString(1);
					int a = aux.getInt(2);
					Ingrediente i1 = buscaIngredienteAux(s);
					if (i1 == null) {
						Exception e = new Exception("Problemas ao ler o resultado da consulta.");
						throw e;
					}
					
					ingredientes.add(i1);
				
				}
				Prato prato = new Prato(nome, preco, ingredientes, codigo);
				pratos.add(prato);
			}
			
		} catch (SQLException e) {
			throw new BasedadosException("Problemas ao ler o resultado da consulta.");
		}
		
		fechaConexao();
		return pratos;
	}

	// INGREDIENTES LOG
	public void insereLogI(Ingrediente i) throws Exception {
		abreConexao();
		preparaComandoSQL("insert into ingredienteslog (ingredientes,qtd) values (?, ?)");
		try{
		pstmt.setString(1, i.getNome());
		pstmt.setDouble(2, i.getQtd());
		pstmt.execute();
		} catch (SQLException e) {
			//Log.recordLog(e);
			throw new BaseDadosException(
					"Reconfirme o nome e/ou a quantidade do ingrediente");
		}
		fechaConexao();
	}

	public void apagaLogI() throws Exception {
		abreConexao();
		preparaComandoSQL("delete from ingredienteslog");
		pstmt.execute();
		fechaConexao();
	}

	// PEDIDOS LOG

	public void insereLogP(Prato p, Mesas m) throws Exception {
		abreConexao();
		preparaComandoSQL("insert into pedidoslog (mesa, prato,grupo) values (?, ?, ?)");
		try{
		pstmt.setInt(1, m.getID());
		pstmt.setString(2, p.getNome());
		pstmt.setInt(3, m.getGrupo());
		pstmt.execute();
		} catch (SQLException e) {
			//Log.recordLog(e);
			throw new BaseDadosException(
					"Erro nos parametros do pedido/grupo da mesa");
		}
		fechaConexao();
	}

	public void apagaLogP() throws Exception {
		abreConexao();
		preparaComandoSQL("delete from pratoslog");
		pstmt.execute();
		fechaConexao();
	}

	// PEDIDOS TEMP
	public void insereLogTemp(Prato p, Mesas m) throws Exception {
		abreConexao();
		preparaComandoSQL("insert into pedidostemp (mesa, prato) values (?, ?)");
		try{
		pstmt.setInt(1, m.getID());
		pstmt.setString(2, p.getNome());
		pstmt.execute();
		} catch (SQLException e) {
			//Log.recordLog(e);
			throw new BaseDadosException(
					"Erro nos parametros do pedido");
		}	
		fechaConexao();
	}

	public boolean buscaLogTemp(Prato p, Mesas m) throws Exception {
		abreConexao();
		preparaComandoSQL("select * from pedidostemp where mesa=? and prato=?");
		pstmt.setInt(1, m.getID());
		pstmt.setString(2, p.getNome());
		rs = pstmt.executeQuery();

		if (rs.next()) {
			fechaConexao();
			return true;
		}
		fechaConexao();
		return false;

	}
	
	public LinkedList<Prato> buscaLogTemp(Mesas mesa) throws Exception {
		LinkedList<Prato> pratos = new LinkedList<Prato>();
		abreConexao();
		preparaComandoSQL("select * from pedidostemp where mesa=?");
		pstmt.setInt(1, mesa.getID());
		ResultSet rs2 = pstmt.executeQuery();
		
		while(rs2.next()) {
			Prato prato = buscaPratoAux(rs2.getString(2));
			pratos.add(prato);
		}
		fechaConexao();
		return pratos;
	}

	public void deletaLogTemp(Prato p, Mesas m) throws Exception {
		if (buscaLogTemp(p, m)) {
			abreConexao();
			preparaComandoSQL("delete from pedidostemp where mesa= ? and prato=?");
			pstmt.setInt(1, m.getID());
			pstmt.setString(2, p.getNome());
			try{
			pstmt.execute();
			} catch (SQLException e) {
			//Log.recordLog(e);
			throw new BaseDadosException(
					"Re-confirme o prato ou/e a mesa");
		}
			fechaConexao();
		} else {
			Exception e = new BasedadosException("Pedido inexistente");
			throw e;
		}
	}

	// CAIXA
	public void insereDinheiroCaixa(int dinheiro) throws Exception {
		abreConexao();
		preparaComandoSQL("insert into caixa (dinheiro) values (?)");
		pstmt.setInt(1, dinheiro);
		pstmt.execute();
		fechaConexao();
	}

	public int buscaDinheiroCaixa() throws Exception {
		abreConexao();
		preparaComandoSQL("select * from caixa");
		rs = pstmt.executeQuery();
		if (rs.next()) {
			fechaConexao();
			return rs.getInt(1);
		}
		fechaConexao();
		return 0;
	}

	public void deletaDinheiroCaixa() throws Exception {
		int i = buscaDinheiroCaixa();
		if (i == 0) {
			Exception e = new BasedadosException("Caixa inexistente");
			throw e;
		}
		abreConexao();
		preparaComandoSQL("delete from caixa");
		pstmt.execute();
		fechaConexao();
	}

	// SENHA ADM
	public void insereSenha(String senha) throws Exception {
		abreConexao();
		preparaComandoSQL("insert into senhaadm (senha) values (?)");
		pstmt.setString(1, senha);
		try{
		pstmt.execute();
		} catch (SQLException e) {
			//Log.recordLog(e);
			throw new BaseDadosException(
					"Senha a ser inserida e invalida (Pode conter apenas 10 caracteres)";
		}
		fechaConexao();
	}

	public boolean buscaSenha(String senha) throws Exception {
		abreConexao();
		preparaComandoSQL("select from senhaadm where senha = ?");
		pstmt.setString(1, senha);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			fechaConexao();
			return true;
		}
		fechaConexao();
		return false;
	}

	public void deletaSenha(String senha) throws Exception {
		if (buscaSenha(senha)) {
			abreConexao();
			preparaComandoSQL("delete from senhaadm where senha = ?");
			pstmt.setString(1, senha);
			pstmt.execute();
			fechaConexao();
		} else {
			Exception e = new BasedadosException("Senha inexistente");
			throw e;
		}
	}
}
