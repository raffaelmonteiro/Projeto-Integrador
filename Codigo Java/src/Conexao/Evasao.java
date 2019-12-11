package Conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import Model.EvasaoCSV;


public class Evasao {
	public static boolean inserir(EvasaoCSV csv) {
		try {
			String sql = "insert into evasao(turno_matricula,situacao_civil_aluno,sexo_aluno,data_cadastro_requerimento,motivo_requerimento,turma,area_turma) "
					+ "values (?,?,?,?,?,?,?)";

			Connection conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, csv.getTurno());
			pstm.setString(2, csv.getEstadoCivil());
			pstm.setString(3, csv.getSexo());
			Date data = new Date(csv.getData().getTimeInMillis());
			pstm.setDate(4,data);
			pstm.setString(5, csv.getMotivo());
			pstm.setString(6, csv.getCurso());
			pstm.setString(7, csv.getArea());
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.print("Erro ao inserir! " + e.getMessage());
			return false;
		}
	}
	
	
	public static boolean inserir(ArrayList<EvasaoCSV> listaCSV) {
		try {
			String sql = "insert into evasao(turno_matricula,situacao_civil_aluno,sexo_aluno,data_cadastro_requerimento,motivo_requerimento,turma,area_turma) "
					+ "values ";

			EvasaoCSV csv = listaCSV.get(0);
			sql += csv.toString();
			
			for (int i = 1; i < listaCSV.size(); i++) {
				csv = listaCSV.get(i);
				sql += "," + csv.toString();
			}
			
			Connection conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
			
			return true;
		} catch (Exception e) {
			System.out.print("Erro ao inserir! " + e.getMessage());
			return false;
		}
	}
	
	public static boolean deletarTabela() {
		try {
			String sql = "delete from evasao";
			Connection conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.print("Erro ao deletar tabela! " + e.getMessage());
			return false;
		}
	}
}