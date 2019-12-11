package BaseDados;

import BaseDados.TratamentoDeDados;

public class Quantitativos extends TratamentoDeDados {
	public float getMediana() {
		return mediana;
	}

	private int var_xi[];
	private int[] var_xi_fi;
	private float media;
	private float variancia;
	private float desvioPadrao;
	private float[] var_xi_media_fi;
	private int moda[];
	private float mediana;
	
	public int[] getModa() {
		return moda;
	}

	public Quantitativos(int[] teste) {
		teste = agruparNumeros(teste);
		super.setFequencia(quantidadeNumeros(teste));
		this.var_xi = repetidoNumeros(teste);
		this.var_xi_fi = calculo_xi_fi(getVar_fi(),this.var_xi);
		this.media = somaVetor(this.var_xi_fi)/teste.length;
		this.var_xi_media_fi = qsw(this.var_xi,getVar_fi(),media);
		this.variancia = somaVetor(this.var_xi_media_fi)/(teste.length -1);
		this.desvioPadrao = (float) Math.pow(this.variancia,0.5);
		this.moda = super.descobrirModa(var_xi, getVar_fi());
		this.mediana = calculoMediana(teste);
	}
	
	
	
	public int[] getVar_xi() {
		return var_xi;
	}

	public int[] getVar_xi_fi() {
		return var_xi_fi;
	}

	public float getMedia() {
		return media;
	}

	public float getVariancia() {
		return variancia;
	}

	public float getDesvioPadrao() {
		return desvioPadrao;
	}

	public float[] getVar_xi_media_fi() {
		return var_xi_media_fi;
	}

	private static int[] calculo_xi_fi(int[] xi ,int[] fi) {
		int[] var_xi_fi = new int[xi.length];
		for(int i = 0; i<xi.length;i++) {
			var_xi_fi[i] = xi[i]*fi[i];
		}
		return var_xi_fi;	
	}
	
	private static float[] qsw(int[] xi,int[] fi,float media) { //calcula ((xi - media)^2)*fi
		float[] qsw = new float[xi.length];
		for(int i = 0;i<xi.length;i++) {
			qsw[i] = (float) (Math.pow((xi[i] - media), 2)*fi[i]);
		}
		return qsw;
	}
	
	private static int[] agruparNumeros(int[] numeros) {
		int temporario; 
		for(int i = 0; i < numeros.length - 1; i++) {
			for(int n = i + 1; n < numeros.length; n++) {
				if (numeros[i] > numeros[n]) {
					temporario = numeros[i];
					numeros[i] = numeros[n];
					numeros[n] = temporario;		
				}
			}
		}
		return numeros;		
	}
	
	private static float calculoMediana(int[] numeros) {
		if(numeros.length % 2 == 0) {
			int metade = numeros.length/2;
			return (numeros[metade - 1] + numeros[metade])/2;
		}
		else {
			int metade = (numeros.length + 1)/2;
			return numeros[metade - 1];
		}
	}
	
	private static int[] quantidadeNumeros(int[] numero) {
		int atual = numero[0];
		int soma = 1;
		for(int i = 1; i < numero.length; i++) {
			if(!(atual == numero[i])) {
				atual = numero[i];
				soma++;
			}
		}
		int[] quantidade = new int[soma];
		
		int colocar = 0;
		atual = numero[0];
		quantidade[0] = 1;
		for(int i = 1; i < numero.length; i++) {
			if(!(atual == numero[i])) {
				atual = numero[i];
				colocar++;
			}
			quantidade[colocar]++;
		}
		return(quantidade);
	}
	
	private static int[] repetidoNumeros(int[] numero) {
		int atual = numero[0];
		int soma = 1;
		for(int i = 1; i < numero.length; i++) {
			if(!(atual == numero[i])) {
				atual = numero[i];
				soma++;
			}
		}
		int[] repeti = new int[soma];
		
		int colocar = 0;
		atual = numero[0];
		repeti[0] = numero[0];
		for(int i = 1; i < numero.length; i++) {
			if(!(atual == numero[i])) {
				atual = numero[i];
				colocar++;
				repeti[colocar] = numero[i];
			}
		}
		return(repeti);
	}

}
