import java.util.LinkedList;
public class GerenciadorBaseDados extends ConectorJDBC {

	private static final String PASSWORD = "fernandoxd2";
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

	
	public void insereIngrediente(Ingrediente i) throws Exception {
		abreConexao();
		preparaComandoSQL("insert into estoque (ingredientes, qtd, preco) values (?, ?, ?)");
		pstmt.setString(1, i.getNome());
		pstmt.setInt(2, i.getQtd());
		pstmt.setDouble(3, i.getPreco());
		pstmt.execute();
		fechaConexao();
	}
	
	public Ingrediente buscaIngrediente(String nome) throws Exception{
		abreConexao();
		preparaComandoSQL("select ingredientes,qtd,preco from estoque where ingredientes = ? ");
		pstmt.setString(1, nome);
		
		rs= pstmt.executeQuery();

		Ingrediente i =null;
	if(rs.next()){
		 String ingrediente=rs.getString(1);
		int	qtd= rs.getInt(2);
		double	preco =rs.getDouble(3);
		i=new Ingrediente(ingrediente, preco,qtd);
	}
			

	
		
		fechaConexao();
			return i;
			
		}
	
	public void deletaIngrediente(String nome) throws Exception{
		
		Ingrediente i=buscaIngrediente(nome);
		if(i==null){
			Exception e= new Exception("Ingrediente inexistente");
			throw e;
		}
		abreConexao();
		preparaComandoSQL("delete from estoque where ingredientes = ? ");
		pstmt.setString(1,nome);
		pstmt.execute();
		
		fechaConexao();
		
	}
	
	public void alteraIngrediente(Ingrediente i) throws Exception{ 	
	
		Ingrediente i1=buscaIngrediente(i.getNome());
		if(i1==null){
			Exception e= new Exception("Ingrediente inexistente");
			throw e;

		}
		abreConexao();
		preparaComandoSQL("update estoque set qtd = ? , preco =? where ingredientes = ? ");
		pstmt.setInt(1, i.getQtd()); 
		pstmt.setDouble(2, i.getPreco());
		pstmt.setString(3, i.getNome());
		pstmt.execute();
		fechaConexao();
		
	}
	
	
}
