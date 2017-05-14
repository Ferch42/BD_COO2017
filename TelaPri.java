import java.awt.EventQueue;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import negocios.*;
import beans.*;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TelaPri {

	private JFrame frame;
	private List<JPanel> paineis = new LinkedList<JPanel>();
	private JTextField textField;
	private JTable table;

	private RegrasNegocios rn= new RegrasNegocios();
	private JTextField field_nome;
	private JTextField field_qtde;
	private JTextField field_preco;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTable table_1;
	private JTextField textField_2;
	
	
	private LinkedList<Ingrediente> receita= new LinkedList<Ingrediente>();
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTable cardapio;
	private JTextField textField_7;
	private JTable Mesas;
	private JTable historico;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPri window = new TelaPri();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPri() {
		initialize();
	}

	private void mostraPainel(JPanel painel) {
		for (JPanel p : paineis) {
			if (p == painel) {
				p.setVisible(true);
			} else {
				p.setVisible(false);
			}
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	/*
	private void montaTabelaPedidos(int qtdeGrupos, int numMesa) {
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		for (int i = 1; i <= qtdeGrupos; i++){
			List<Prato> pratos = new LinkedList<Prato>();
			pratos = rn.devolvePratosGrupos(i, numMesa);
			
			for (Prato prato : pratos){
				model.addRow(new Object[] { i, prato.getNome() });
			}
			
		}

	}
	*/
	private boolean VerificaIngredienteReceita(String s){
		for(Ingrediente i: receita){
			if(i.getNome().equals(s))return true;
		}
		return false;
	}
	
	
	private void montaTabelaMesas() {
		
		DefaultTableModel model = (DefaultTableModel) Mesas.getModel();
		LinkedList<Mesas> lista = new LinkedList<Mesas>();
		try{
		  lista= rn.devolveMesas();
		}catch(Exception e1){
			System.out.println("noob");
		}
			for (Mesas i: lista){
				String s="ocupado";
				if(i.isDisponivel())s="disponivel";
				model.addRow(new Object[] { i.getID(),s });
			}
			
		}
	private void esvaziaTabelaMesas() {
		DefaultTableModel model = (DefaultTableModel) Mesas.getModel();
		model.setRowCount(0);
	
		}
	
	
	
	private void montaTabelaIngredientes() {
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		LinkedList<Ingrediente> lista = new LinkedList<Ingrediente>();
		try{
		  lista= rn.devolveIngredientes();
		}catch(Exception e1){
			System.out.println("noob");
		}
			for (Ingrediente i: lista){
				model.addRow(new Object[] { i.getNome(), i.getQtd(),i.getPreco() });
			}
			
		}
private void montaTabelaCardapio() {
		
		DefaultTableModel model = (DefaultTableModel) cardapio.getModel();
		LinkedList<Prato> lista = new LinkedList<Prato>();
		try{
		  lista= rn.devolvePratos();
		}catch(Exception e1){
			System.out.println("noob");
		}
			for (Prato p: lista){
				model.addRow(new Object[] { p.getNome(),p.getPreco(),p.getCodigo() });
			}
			
		}
	
	private void esvaziaTabela() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
	
		}
	
	private void montaTabelaIngredientes2() {
		
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		LinkedList<Ingrediente> lista = new LinkedList<Ingrediente>();
		try{
		  lista= rn.devolveIngredientes();
		}catch(Exception e1){
			System.out.println("noob");
		}
			for (Ingrediente i: lista){
				model.addRow(new Object[] { i.getNome(), i.getPreco() });
			}
			
		}
	
	private void esvaziaTabela2() {
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		model.setRowCount(0);
	
		}

	
private void montaTabelaHistorico(int ID, int grupo) {
		
		DefaultTableModel model = (DefaultTableModel) historico.getModel();
		LinkedList<Prato> lista = new LinkedList<Prato>();
		try{
		  lista= rn.devolvePratosGrupos(grupo,ID);
		}catch(Exception e1){
			System.out.println("noob");
		}
			for (Prato i: lista){
				model.addRow(new Object[] {  ID,grupo,i.getNome()});
			}
			
		}
	
	private void esvaziaTabelaHistorico() {
		DefaultTableModel model = (DefaultTableModel) historico.getModel();
		model.setRowCount(0);
	
		}


	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 697, 542);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel painel_gerente = new JPanel();
		JPanel painel_cadastrar = new JPanel();
		JPanel panel_senha_gerente = new JPanel();
		JPanel tabela = new JPanel();
		JPanel painel_bem_vindo = new JPanel();
		JPanel painel_cadastrar_ingrediente = new JPanel();
		JPanel panel_historico_pedidos = new JPanel();
		
		JPanel panel_mostra_historico = new JPanel();
		panel_mostra_historico.setBounds(0, 0, 681, 483);
		frame.getContentPane().add(panel_mostra_historico);
		panel_mostra_historico.setLayout(null);
		paineis.add(panel_mostra_historico);
		
		JButton btnMostraHistoricoPedidos = new JButton("mostra historico pedidos");
		btnMostraHistoricoPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraPainel(panel_historico_pedidos);
				try{
					LinkedList<Mesas> lm=rn.devolveMesas();
					int mesas=0;
					for(Mesas m: lm){
						mesas++;
					}
					for(int i=1;i<=mesas;i++){
						
							int j=rn.devolveGruposMesas(i);
								for(int u=1;u<=j;u++){
									montaTabelaHistorico(i, u);
								}
							
						
					}
					
				}catch(Exception e1){
					System.out.println("problemas para acessar bd");
				}
				
			}
		});
		btnMostraHistoricoPedidos.setBounds(209, 182, 301, 141);
		panel_mostra_historico.add(btnMostraHistoricoPedidos);
		
		
		
		
		panel_historico_pedidos.setBounds(0, 0, 681, 483);
		frame.getContentPane().add(panel_historico_pedidos);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		GroupLayout gl_panel_historico_pedidos = new GroupLayout(panel_historico_pedidos);
		gl_panel_historico_pedidos.setHorizontalGroup(
			gl_panel_historico_pedidos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_historico_pedidos.createSequentialGroup()
					.addGap(138)
					.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(145, Short.MAX_VALUE))
		);
		gl_panel_historico_pedidos.setVerticalGroup(
			gl_panel_historico_pedidos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_historico_pedidos.createSequentialGroup()
					.addGap(84)
					.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(180, Short.MAX_VALUE))
		);
		
		historico = new JTable();
		historico.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mesa", "Grupo", "Pedido"
			}
		));
		scrollPane_4.setViewportView(historico);
		panel_historico_pedidos.setLayout(gl_panel_historico_pedidos);
		paineis.add(panel_historico_pedidos);
		JPanel panel_mesas = new JPanel();
		
		
		Mesas = new JTable();
		panel_mesas.setBounds(0, 0, 681, 483);
		frame.getContentPane().add(panel_mesas);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		
		JButton btnSelecionaMesaDisponivel = new JButton("seleciona mesa disponivel");
		btnSelecionaMesaDisponivel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try{
				int i=rn.buscaMesaDisponivel();
				
				JOptionPane.showMessageDialog(null,"a mesa disponivel é "+i);
				esvaziaTabelaMesas();
				montaTabelaMesas();
				
			}catch(Exception e1){
				JOptionPane.showMessageDialog(null,"não tem mesas disponiveis");
			}
			}
		});
		GroupLayout gl_panel_mesas = new GroupLayout(panel_mesas);
		gl_panel_mesas.setHorizontalGroup(
			gl_panel_mesas.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_mesas.createSequentialGroup()
					.addContainerGap(162, Short.MAX_VALUE)
					.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 413, GroupLayout.PREFERRED_SIZE)
					.addGap(106))
				.addGroup(Alignment.LEADING, gl_panel_mesas.createSequentialGroup()
					.addGap(180)
					.addComponent(btnSelecionaMesaDisponivel)
					.addContainerGap(412, Short.MAX_VALUE))
		);
		gl_panel_mesas.setVerticalGroup(
			gl_panel_mesas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_mesas.createSequentialGroup()
					.addGap(73)
					.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(btnSelecionaMesaDisponivel)
					.addContainerGap(40, Short.MAX_VALUE))
		);
		
		
		Mesas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mesa", "Disponivel"
			}
		));
		scrollPane_3.setViewportView(Mesas);
		panel_mesas.setLayout(gl_panel_mesas);
		paineis.add(panel_mesas);
		
		JPanel panel_mostrar_mesas = new JPanel();
		panel_mostrar_mesas.setBounds(0, 0, 681, 483);
		frame.getContentPane().add(panel_mostrar_mesas);
		panel_mostrar_mesas.setLayout(null);
		paineis.add(panel_mostrar_mesas);
		
		JButton btnMostraMesas = new JButton("mostra mesas");
		btnMostraMesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostraPainel(panel_mesas);
				montaTabelaMesas();
			}
		});
		btnMostraMesas.setBounds(229, 143, 146, 66);
		panel_mostrar_mesas.add(btnMostraMesas);
		JPanel painel_realizar_pedido = new JPanel();
		
			cardapio = new JTable();
			
			
			painel_realizar_pedido.setBounds(0, 0, 681, 483);
			frame.getContentPane().add(painel_realizar_pedido);
			
			JScrollPane scrollPane_2 = new JScrollPane();
			
			JLabel lblMesa = new JLabel("mesa");
			
			textField_7 = new JTextField();
			textField_7.setColumns(10);
			
			JButton btnNewButton_2 = new JButton("confirmar");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int s= Integer.parseInt(cardapio.getValueAt(cardapio.getSelectedRow(), 2).toString());
					JOptionPane.showMessageDialog(null,s);
					try{
					rn.fazerPedido(s, Integer.parseInt(textField_7.getText()));
					JOptionPane.showMessageDialog(null,"Pedido cadastrado");
					
					}
					catch(Exception e1){
						JOptionPane.showMessageDialog(null,"Pedido não cadastrado");
					}
				}
			});
			
			JButton btnFinaliza = new JButton("finaliza");
			btnFinaliza.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
						rn.encerrarPedido(Integer.parseInt(textField_7.getText()));
					}catch(Exception e1){
						e1.printStackTrace();
					}
				}
			});
			
			JButton btnCancela = new JButton("cancela");
			btnCancela.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int s= Integer.parseInt(cardapio.getValueAt(cardapio.getSelectedRow(), 2).toString());
					JOptionPane.showMessageDialog(null,s);
					try{
					rn.cancelarPedidoPrato(s, Integer.parseInt(textField_7.getText()));
					JOptionPane.showMessageDialog(null,"Pedido cancelado");
					
					}
					catch(Exception e1){
						JOptionPane.showMessageDialog(null,"Pedido não cancelado");
					}
				}
			});
			GroupLayout gl_painel_realizar_pedido = new GroupLayout(painel_realizar_pedido);
			gl_painel_realizar_pedido.setHorizontalGroup(
				gl_painel_realizar_pedido.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_painel_realizar_pedido.createSequentialGroup()
						.addContainerGap(132, Short.MAX_VALUE)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE)
						.addGap(115))
					.addGroup(gl_painel_realizar_pedido.createSequentialGroup()
						.addGap(97)
						.addComponent(lblMesa)
						.addGap(53)
						.addGroup(gl_painel_realizar_pedido.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_painel_realizar_pedido.createSequentialGroup()
								.addComponent(btnFinaliza)
								.addGap(160)
								.addComponent(btnCancela)
								.addGap(192))
							.addGroup(gl_painel_realizar_pedido.createSequentialGroup()
								.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
								.addComponent(btnNewButton_2)
								.addGap(161))))
			);
			gl_painel_realizar_pedido.setVerticalGroup(
				gl_painel_realizar_pedido.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_painel_realizar_pedido.createSequentialGroup()
						.addGap(80)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
						.addGap(37)
						.addGroup(gl_painel_realizar_pedido.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblMesa)
							.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_2))
						.addGap(18)
						.addGroup(gl_painel_realizar_pedido.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnFinaliza)
							.addComponent(btnCancela))
						.addContainerGap(30, Short.MAX_VALUE))
			);
			
			
			cardapio.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Prato", "Pre\u00E7o", "C\u00F3digo"
				}
			));
			scrollPane_2.setViewportView(cardapio);
			painel_realizar_pedido.setLayout(gl_painel_realizar_pedido);
			paineis.add(painel_realizar_pedido);
		JPanel painel_tabela_cadastrarPrato = new JPanel();
		
		
		
			
			
			painel_tabela_cadastrarPrato.setBounds(0, 0, 681, 483);
			frame.getContentPane().add(painel_tabela_cadastrarPrato);
			painel_tabela_cadastrarPrato.setLayout(null);
			
			
			textField_2 = new JTextField();
			textField_2.setBounds(205, 250, 86, 20);
			painel_tabela_cadastrarPrato.add(textField_2);
			textField_2.setColumns(10);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(127, 38, 419, 176);
			painel_tabela_cadastrarPrato.add(scrollPane_1);
			
			table_1 = new JTable();
			table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Ingrediente", "Pre\u00E7o"
				}
			));
			scrollPane_1.setViewportView(table_1);
			
			JButton btnConfirmar_2 = new JButton("Receitar Ingrediente");
			btnConfirmar_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nomeSelecionado=table_1.getValueAt(table_1.getSelectedRow(),0).toString();
					
					int q=Integer.parseInt(textField_2.getText());
					
					
					try{
					Ingrediente i=rn.devolveIngrediente(nomeSelecionado);
					Ingrediente i2= new Ingrediente(nomeSelecionado,i.getPreco(),q);
					if(VerificaIngredienteReceita(nomeSelecionado)){
						JOptionPane.showMessageDialog(null,"Ingrediente já cadastrado");}
					else{receita.add(i2);
					JOptionPane.showMessageDialog(null,"Foi armazenado "+i2.getQtd()+" unidades de "+i2.getNome()+
					"Clique em concluir para efetivar");
					}
					}
					catch(Exception e1){
						///**************************************
					}
				}
			});
			btnConfirmar_2.setBounds(90, 387, 154, 48);
			painel_tabela_cadastrarPrato.add(btnConfirmar_2);
			paineis.add(painel_tabela_cadastrarPrato);
			
			JLabel lblNewLabel = new JLabel("quantidade");
			lblNewLabel.setBounds(90, 241, 68, 39);
			painel_tabela_cadastrarPrato.add(lblNewLabel);
			
			JLabel lblNome_1 = new JLabel("Nome");
			lblNome_1.setBounds(90, 318, 46, 14);
			painel_tabela_cadastrarPrato.add(lblNome_1);
			
			textField_4 = new JTextField();
			textField_4.setBounds(205, 315, 86, 20);
			painel_tabela_cadastrarPrato.add(textField_4);
			textField_4.setColumns(10);
			
			JLabel lblPreco_1 = new JLabel("preco");
			lblPreco_1.setBounds(404, 256, 46, 14);
			painel_tabela_cadastrarPrato.add(lblPreco_1);
			
			textField_5 = new JTextField();
			textField_5.setBounds(460, 250, 86, 20);
			painel_tabela_cadastrarPrato.add(textField_5);
			textField_5.setColumns(10);
			
			JButton btnNewButton = new JButton("Finalizar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
						
						rn.cadastrarPrato(textField_4.getText(), Integer.parseInt(textField_5.getText()), receita, Integer.parseInt(textField_6.getText()));
						receita=new LinkedList<Ingrediente>();
						JOptionPane.showMessageDialog(null,"Prato cadastrado com sucesso.");
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null,"Prato já existente");
					}
				}
			});
			btnNewButton.setBounds(418, 400, 89, 23);
			painel_tabela_cadastrarPrato.add(btnNewButton);
			
			textField_6 = new JTextField();
			textField_6.setBounds(460, 318, 86, 20);
			painel_tabela_cadastrarPrato.add(textField_6);
			textField_6.setColumns(10);
			
			JLabel lblCodigo = new JLabel("codigo");
			lblCodigo.setBounds(370, 318, 46, 14);
			painel_tabela_cadastrarPrato.add(lblCodigo);
		
		
		JPanel painel_cadastrar_prato = new JPanel();
		painel_cadastrar_prato.setLayout(null);
		painel_cadastrar_prato.setBounds(0, 0, 681, 483);
		frame.getContentPane().add(painel_cadastrar_prato);
		
		JLabel label = new JLabel("Nome:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label.setBounds(106, 156, 88, 33);
		painel_cadastrar_prato.add(label);
		
		JLabel label_2 = new JLabel("Pre\u00E7o:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		label_2.setBounds(105, 254, 88, 33);
		painel_cadastrar_prato.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_1.setColumns(10);
		textField_1.setBounds(311, 141, 264, 47);
		painel_cadastrar_prato.add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField_3.setColumns(10);
		textField_3.setBounds(307, 251, 264, 47);
		painel_cadastrar_prato.add(textField_3);
		
		JTextPane txtpnCadastrarPrato = new JTextPane();
		txtpnCadastrarPrato.setText("Cadastrar Prato");
		txtpnCadastrarPrato.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnCadastrarPrato.setBounds(235, 25, 246, 47);
		painel_cadastrar_prato.add(txtpnCadastrarPrato);
		
		JButton button = new JButton("Confirmar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				esvaziaTabela2();
				montaTabelaIngredientes2();
				mostraPainel(painel_tabela_cadastrarPrato);

			}
		});
		button.setBounds(268, 372, 146, 47);
		painel_cadastrar_prato.add(button);
		paineis.add(painel_cadastrar_prato);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 681, 483);
		frame.getContentPane().add(panel);
		
		JPanel panel_garçom_mesa = new JPanel();
		panel_garçom_mesa.setBounds(0, 0, 681, 483);
		frame.getContentPane().add(panel_garçom_mesa);
		panel_garçom_mesa.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Faz pedido");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostraPainel(painel_realizar_pedido);
				montaTabelaCardapio();
			}
		});
		btnNewButton_1.setBounds(129, 95, 178, 108);
		panel_garçom_mesa.add(btnNewButton_1);
		paineis.add(panel_garçom_mesa);
		

		
		
		
		painel_gerente.setBounds(0, 0, 681, 483);
		frame.getContentPane().add(painel_gerente);
		paineis.add(painel_gerente);
		painel_gerente.setLayout(null);
		
				JButton btnHistricoDePedidos = new JButton("Hist\u00F3rico de Pedidos");
				btnHistricoDePedidos.setBounds(357, 262, 196, 55);
				painel_gerente.add(btnHistricoDePedidos);
				
				JButton btnCadastrarNovo = new JButton("Cadastrar Novo");
				btnCadastrarNovo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mostraPainel(painel_cadastrar);
					}
				});
				btnCadastrarNovo.setBounds(101, 153, 196, 55);
				painel_gerente.add(btnCadastrarNovo);
				
				JButton btnFinanceiro = new JButton("Financeiro");
				btnFinanceiro.setBounds(356, 153, 196, 55);
				painel_gerente.add(btnFinanceiro);
				
				JButton btnComprarIngrediente = new JButton("Comprar Ingrediente");
				btnComprarIngrediente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnComprarIngrediente.setBounds(101, 262, 196, 55);
				painel_gerente.add(btnComprarIngrediente);
				
				JButton btnVisualizarEstoque = new JButton("Visualizar Estoque");
				btnVisualizarEstoque.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						esvaziaTabela();
						montaTabelaIngredientes();
						mostraPainel(tabela);

					}
				});
				btnVisualizarEstoque.setBounds(229, 353, 196, 55);
				painel_gerente.add(btnVisualizarEstoque);

		paineis.add(painel_gerente);

		
//painel cadastrar ingrediente
		painel_cadastrar_ingrediente.setBounds(0, 0, 681, 483);
		frame.getContentPane().add(painel_cadastrar_ingrediente);
		paineis.add(painel_cadastrar_ingrediente);
		painel_cadastrar_ingrediente.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNome.setBounds(90, 114, 88, 33);
		painel_cadastrar_ingrediente.add(lblNome);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblQuantidade.setBounds(90, 197, 146, 54);
		painel_cadastrar_ingrediente.add(lblQuantidade);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPreco.setBounds(90, 292, 88, 33);
		painel_cadastrar_ingrediente.add(lblPreco);
		
		field_nome = new JTextField();
		field_nome.setFont(new Font("Tahoma", Font.PLAIN, 25));
		field_nome.setBounds(246, 107, 264, 47);
		painel_cadastrar_ingrediente.add(field_nome);
		field_nome.setColumns(10);
		
		field_preco = new JTextField();
		field_preco.setFont(new Font("Tahoma", Font.PLAIN, 25));
		field_preco.setColumns(10);
		field_preco.setBounds(246, 287, 264, 47);
		painel_cadastrar_ingrediente.add(field_preco);
		
		field_qtde = new JTextField();
		field_qtde.setBounds(246, 201, 264, 47);
		painel_cadastrar_ingrediente.add(field_qtde);
		field_qtde.setFont(new Font("Tahoma", Font.PLAIN, 25));
		field_qtde.setColumns(10);
		
		JTextPane txtpnCadastrarIngrediente = new JTextPane();
		txtpnCadastrarIngrediente.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnCadastrarIngrediente.setText("Cadastrar Ingrediente");
		txtpnCadastrarIngrediente.setBounds(246, 22, 246, 47);
		painel_cadastrar_ingrediente.add(txtpnCadastrarIngrediente);
		
		JButton btnConfirmar_1 = new JButton("Confirmar");
		btnConfirmar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try{
					rn.cadastrarIngrediente(field_nome.getText(), Integer.parseInt(field_preco.getText()), Integer.parseInt(field_qtde.getText()));
					JOptionPane.showMessageDialog(null,"Ingrediente cadastrado com sucesso.");
				}
				catch (Exception e1){
					JOptionPane.showMessageDialog(null,"noob.");
				}
				
			}
		});
		btnConfirmar_1.setBounds(301, 384, 146, 47);
		painel_cadastrar_ingrediente.add(btnConfirmar_1);
		
		
		// painel cadastrar novo

		painel_cadastrar.setBounds(0, 0, 681, 483);
		frame.getContentPane().add(painel_cadastrar);
		paineis.add(painel_cadastrar);
		painel_cadastrar.setLayout(null);
		
		JButton btnPrato = new JButton("Prato");
		btnPrato.setBounds(122, 225, 193, 57);
		painel_cadastrar.add(btnPrato);
		
		JButton btnIngrediente = new JButton("Ingrediente");
		btnIngrediente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraPainel(painel_cadastrar_ingrediente);
			}
		});
		
		btnIngrediente.setBounds(360, 225, 193, 57);
		painel_cadastrar.add(btnIngrediente);
		
		// painel senha gerente
		
		panel_senha_gerente.setBounds(0, 0, 681, 483);
		frame.getContentPane().add(panel_senha_gerente);
		paineis.add(panel_senha_gerente);
		panel_senha_gerente.setLayout(null);
		
		JLabel lblDigiteSuaSenha = new JLabel("Digite sua senha:");
		lblDigiteSuaSenha.setFont(new Font("Poor Richard", Font.PLAIN, 25));
		lblDigiteSuaSenha.setBounds(132, 197, 192, 68);
		panel_senha_gerente.add(lblDigiteSuaSenha);
		
		textField = new JTextField();
		textField.setFont(new Font("Poor Richard", Font.PLAIN, 25));
		textField.setBounds(325, 216, 208, 30);
		panel_senha_gerente.add(textField);
		textField.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean correto = false;
				try{
					correto = rn.confirmarSenha(textField.getText());
				}
				catch (Exception e1){
					JOptionPane.showMessageDialog(null,"noob.");
				}
				if (correto){
					mostraPainel(painel_gerente);
				}
				else{
					JOptionPane.showMessageDialog(null,"noob.");
				}
			}
		});
		
		btnConfirmar.setBounds(273, 307, 130, 45);
		panel_senha_gerente.add(btnConfirmar);
		
		// tabela

		tabela.setBounds(0, 0, 681, 483);
		frame.getContentPane().add(tabela);
		
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_tabela = new GroupLayout(tabela);
		gl_tabela.setHorizontalGroup(
			gl_tabela.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tabela.createSequentialGroup()
					.addGap(123)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(139, Short.MAX_VALUE))
		);
		gl_tabela.setVerticalGroup(
			gl_tabela.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tabela.createSequentialGroup()
					.addGap(82)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(89, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ingredientes", "quantdade", "pre\u00E7o"
			}
		));
		scrollPane.setViewportView(table);
		tabela.setLayout(gl_tabela);
		paineis.add(tabela);
		
		
		// painel bem vindo
		
		painel_bem_vindo.setBounds(0, 0, 681, 483);
		frame.getContentPane().add(painel_bem_vindo);
		painel_bem_vindo.setLayout(null);
		paineis.add(painel_bem_vindo);
		
		JTextPane txtpnBemVindoAo = new JTextPane();
		txtpnBemVindoAo.setText("Bem vindo ao Restaurante Arroz com Feij\u00E3o M\u00E1gico");
		txtpnBemVindoAo.setBounds(194, 171, 277, 27);
		painel_bem_vindo.add(txtpnBemVindoAo);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Usu\u00E1rio");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmGarcom = new JMenuItem("Gar\u00E7om");
		mnNewMenu.add(mntmGarcom);
		
		JMenuItem mntmGerente = new JMenuItem("Gerente");
		mntmGerente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostraPainel(panel_senha_gerente);
			}
		});
		mnNewMenu.add(mntmGerente);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(0, 0, 681, 483);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 0, 681, 483);
		frame.getContentPane().add(panel_2);
		mostraPainel(panel_mostra_historico);
	}
}
