package BaseDados;

public class TratamentoDeDados {
	
	private int var_fi[];
	private int var_Fi[];
	private float[] var_fr;
	private float[] var_Fr;
	
	void setFequencia(int[] fequencia) {
		this.var_fi = fequencia;	
		this.var_Fi = calculo_Fi(fequencia);
		this.var_fr = calculo_fr(fequencia);
		this.var_Fr = calculo_Fr(this.var_fr);
		
	}
	
	public static float somaVetor(float[] numero) {
		float soma = 0;
		for(int i = 0; i<numero.length;i++) {
			soma += numero[i];
		}
		return soma;
	}
	
	public static float somaVetor(int[] numero) {
		float soma = 0;
		for(int i = 0; i<numero.length;i++) {
			soma += numero[i];
		}
		return soma;
	}
	
	
	
	private static int[] calculo_Fi(int[] fi) {
		int[] Fi = new int[fi.length];
		Fi[0] = fi[0];
		for(int i = 1; i<fi.length;i++) {
			Fi[i] = fi[i] + Fi[i-1];
		}
		return Fi;
	}
	
	private static float[] calculo_Fr(float[] fr) {
		float[] Fr = new float[fr.length];
		Fr[0] = fr[0];
		for(int i = 1; i<fr.length;i++) {
			Fr[i] = fr[i] + Fr[i-1];
		}
		return Fr;
	}
	
	public static int[] descobrirModa(int[] xi,int[] frequencia) {
		int maior = frequencia[0];
		for(int i = 1; i< frequencia.length; i++) {
			if(frequencia[i] > maior) {
				maior = frequencia[i];
			}
		}
		int cont = 0;
		for(int i = 0; i< frequencia.length; i++) {
			if(frequencia[i] == maior) {
				cont++;
			}
		}
		int[] moda = new int[cont];
		int i = 0;
		while(cont > 0) {
			if(frequencia[i] == maior) {
				cont--;
				moda[cont] = xi[i];
			}
			i++;
		}
		return moda;
	}
	
	public static String[] descobrirModa(String[] xi,int[] frequencia) {
		int maior = frequencia[0];
		for(int i = 1; i< frequencia.length; i++) {
			if(frequencia[i] > maior) {
				maior = frequencia[i];
			}
		}
		int cont = 0;
		for(int i = 0; i< frequencia.length; i++) {
			if(frequencia[i] == maior) {
				cont++;
			}
		}
		String[] moda = new String[cont];
		int i = 0;
		while(cont > 0) {
			if(frequencia[i] == maior) {
				cont--;
				moda[cont] = xi[i];
			}
			i++;
		}
		return moda;
	}

	private static float[] calculo_fr(int[] fi) {
		float quant = somaVetor(fi);
		float[] fr = new float[(int) quant];
		for(int i = 0;i<fi.length;i++) {
			//fr[i] = arredondar((fi[i]*100)/quant, 2, 0);
			fr[i] = (fi[i]/quant);
		}
		return fr;
	}


	public int[] getVar_fi() {
		return var_fi;
	}


	public int[] getVar_Fi() {
		return var_Fi;
	}


	public float[] getVar_fr() {
		return var_fr;
	}


	public float[] getVar_Fr() {
		return var_Fr;
	}
	
}