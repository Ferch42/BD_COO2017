package beans;

public class Mesas {
	int ID;
	boolean disponivel;
	int grupo;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public boolean isDisponivel() {
		return disponivel;
	}
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public Mesas(int id, boolean b, int grupo){
		this.ID=id;
		this.disponivel=b;
		this.grupo=grupo;
	}
}
