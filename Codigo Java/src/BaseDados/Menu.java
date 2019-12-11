package BaseDados;

import java.util.ArrayList;
import java.util.Iterator;

import Model.Evasao;
 
public class Menu implements Iterator {
 
	Evasao[] itens;
    int posicao = 0;
     
    public Menu(ArrayList<Evasao> evasao) {
        this.itens =  evasao.toArray(new Evasao[ evasao.size()]);
    }
     
    public Object next() {
    	Object menuItem = itens[posicao];
        posicao++;
        return menuItem;
    }
     
    public boolean hasNext() {
        if (posicao >= itens.length || itens[posicao] == null) {
            return false;
        } else {
            return true;
        }
    }
}