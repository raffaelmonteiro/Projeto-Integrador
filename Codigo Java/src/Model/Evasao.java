package Model;

import java.util.Calendar;
import java.util.Date;

public class Evasao {
	private Calendar data;
	private String motivo;
	private String curso;
	private String area;
	
	public Evasao() {
		
	}
	
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	
	public void setData(Date data) {
		Calendar data2 = Calendar.getInstance();
		data2.setTime(data);
		this.data = data2;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
}