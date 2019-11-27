package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class AvaliacaoTableModel  extends AbstractTableModel{

    public static final int COL_SERVICO = 0;
    public static final int COL_TIPOSERVICO = 1;
    public static final int COL_NOTA = 2;
    public static final int COL_OPINIAO = 3;
    private ArrayList<Avaliacao> arr;


public AvaliacaoTableModel(ArrayList<Avaliacao> l){
    this.arr = new ArrayList<Avaliacao>(l);
}

public int getRowCount(){
    return arr.size();
}

public int getColumnCount(){
    return 4;
}


public Object getValueAt(int linhas, int colunas){
    Avaliacao av  = this.arr.get(linhas);
    if(colunas == COL_SERVICO) return av.getServico();
    if(colunas == COL_TIPOSERVICO) return av.getTipoServico();
    if(colunas == COL_NOTA) return av.getNota();
    if(colunas == COL_OPINIAO) return av.getOpiniao();
    return "";
}

public String getColumnName(int colunas){
    if(colunas == COL_SERVICO) return "Código";
    if(colunas == COL_TIPOSERVICO) return "Nome";
    if(colunas == COL_NOTA) return "E-mail";
    if(colunas == COL_OPINIAO) return "Telefone";

    return "";
}

public Avaliacao get(int linhas){
    return arr.get(linhas);
}

}