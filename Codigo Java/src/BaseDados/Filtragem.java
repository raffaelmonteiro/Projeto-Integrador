package BaseDados;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import Model.Evasao;

public class Filtragem {
	private ArrayList<Evasao> evasaoOriginal;
	private ArrayList<Evasao> evasao;
	private ArrayList<String> motivoEvasao = new ArrayList<String>();
	private ArrayList<Integer> frequenciaEvasao = new ArrayList<Integer>();
	private ArrayList<Integer> frequenciaAcumuladaEvasao = new ArrayList<Integer>();
	private ArrayList<Float> frequenciaPorcentagemEvasao = new ArrayList<Float>();
	private ArrayList<Integer> frequenciaPorcentagemAcumuladaEvasao = new ArrayList<Integer>();
	private ArrayList<String> moda = new ArrayList<String>();
	public Calendar getDataInicio() {
		return dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public ArrayList<String> getModa() {
		return moda;
	}

	private Calendar dataInicio;
	private Calendar dataFim;
	private ArrayList<String> curso;
	
	public Filtragem(ArrayList<Evasao> evasao) {
		this.evasaoOriginal = evasao;
		this.evasao = (ArrayList<Evasao>) this.evasaoOriginal.clone();
		calcularDados(evasao);
	}
	
	public void setFiltro(ArrayList<String> curso) {
		this.curso = curso;
		filtro(dataInicio,dataFim,curso);
	}
	
	public void setFiltro(Calendar dataInicio, Calendar dataFim) {
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		filtro(dataInicio,dataFim,curso);
	}
	
	public void setFiltro(Calendar dataInicio, Calendar dataFim, ArrayList<String> cursos) {
		this.curso = cursos;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		filtro(dataInicio,dataFim,cursos);
	}
	
	private void filtro(Calendar dataInicio, Calendar dataFim, ArrayList<String> cursos) {
		this.evasao = (ArrayList<Evasao>) this.evasaoOriginal.clone();
		filtroCurso(cursos);
		filtroData(dataInicio, dataFim);
		calcularDados(this.evasao);
	}
	
	private void filtroData(Calendar dataInicio, Calendar dataFim) {
		if(dataInicio != null || dataFim != null) {
			ArrayList<Evasao> evasaoTep = (ArrayList<Evasao>) this.evasao.clone();
			for (Evasao evasao2 : evasaoTep) {
				Calendar cal = evasao2.getData();
				if(cal != null) {
					if(dataInicio != null && !(cal.compareTo(dataInicio) >= 0)) {
						this.evasao.remove(evasao2);
					}
					else if(dataFim != null && !(cal.compareTo(dataFim) <= 0)) {
						this.evasao.remove(evasao2);
					}
				}
			}
		}
	}
	
	private void filtroCurso(ArrayList<String> cursos) {
		if(cursos != null) {
			for (Menu i = new Menu(this.evasao); i.hasNext();) {
			  Evasao eva = (Evasao) i.next();
			  if (!cursos.contains(eva.getCurso())) {
			    this.evasao.remove(eva);
			  }
			}
		}
	}
	
	private void calcularDados(ArrayList<Evasao> evasao) {
		if(evasao.size() > 0 ) {
			String[] motivos = comverteListaDeEvasao(evasao);
			Qualitativos quali = new Qualitativos(motivos);
			setMotivoEvasao(quali.getVar_xi());
			setFrequenciaEvasao(quali.getVar_fi());
			float[] por = quali.getVar_fr();
			ArrayList<Float> porList = new ArrayList<Float>();
			for (Float float1 : por) {
				porList.add(float1);
			}
			this.frequenciaPorcentagemEvasao = porList;
			String[] mod = quali.getModa();
			ArrayList<String> modList = new ArrayList<String>();
			for (String s : mod) {
				modList.add(s);
			}
			this.moda = modList;
		}
		else {
			moda.clear();
			frequenciaPorcentagemEvasao.clear();
			motivoEvasao.clear();
			frequenciaEvasao.clear();
		}
	}
	
	public ArrayList<Evasao> getEvasao() {
		return evasao;
	}

	public void setEvasao(ArrayList<Evasao> evasao) {
		this.evasao = evasao;
	}

	public ArrayList<String> getMotivoEvasao() {
		return motivoEvasao;
	}

	public ArrayList<Integer> getFrequenciaEvasao() {
		return frequenciaEvasao;
	}
	
	public static String[] comverteListaDeEvasao(ArrayList<Evasao> evasao) {
		ArrayList<String> lista = new ArrayList<String>();
		for (Evasao evasao2 : evasao) {
			lista.add(evasao2.getMotivo());
		}
		return lista.toArray( new String[lista.size()]);
	}
	
	private void setMotivoEvasao(String[] novo) {
		this.motivoEvasao.clear();
		for (String string : novo) {
			this.motivoEvasao.add(string);
		}
	}
	
	private void setFrequenciaEvasao(int[] novo) {
		this.frequenciaEvasao.clear();
		for (int num : novo) {
			this.frequenciaEvasao.add(num);
		}
	}

	public ArrayList<Float> getFrequenciaPorcentagemEvasao() {
		return frequenciaPorcentagemEvasao;
	}
}