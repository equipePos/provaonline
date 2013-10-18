/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import bean.Prova;
import bean.Questao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.FabricaConexoes;

/**
 *
 * @author Giovani
 */
public class ProvaDAO {
    
    private static final String DISCIPLINAS_PROVAS = "SELECT cod_disciplina,disciplina,cod_prova, codaluno,codprova  FROM prova.provas_disciplina\n" +
    "where cod_prova not in(select cod_prova from prova.provas_disciplina where codaluno = ?);";

    private static final String DISCIPLINAS_COM_PROVAS = "SELECT cod_disciplina,disciplina,cod_prova, codaluno,codprova  FROM prova.provas_disciplina\n" +
                    "where cod_prova not in(select cod_prova from prova.provas_disciplina where codaluno = ?)"
                    + " group by disciplina ";
    
    private static final String DISCIPLINAS_PROVAS_FEITAS = "SELECT \n" +
                                                                "* \n" +
                                                                "FROM \n" +
                                                                "prova.tbl_disciplina d, \n" +
                                                                "prova.tbl_prova p, \n" +
                                                                "prova.tbl_prova_feita pf \n" +
                                                                "where \n" +
                                                                "d.cod_disciplina = p.cod_disciplina and \n" +
                                                                "p.cod_prova = pf.cod_prova and \n" +
                                                                "pf.cod_aluno = ? \n" +
                                                                "group by p.cod_prova;";

    private static final String DISCIPLINAS_COM_PROVAS_FEITAS = "SELECT \n" +
                                                                        "   * \n" +
                                                                        "   FROM \n" +
                                                                        "   prova.tbl_disciplina d, \n" +
                                                                        "   prova.tbl_prova p,\n" +
                                                                        "   prova.tbl_prova_feita pf\n" +
                                                                        "   where \n" +
                                                                        "   d.cod_disciplina = p.cod_disciplina and\n" +
                                                                        "   p.cod_prova = pf.cod_prova and \n" +
                                                                        "   pf.cod_aluno = ? \n" +
                                                                        "   group by p.cod_disciplina;";
    
    private static final String PROVA_FEITA_COD_PROVA = "select \n" +
                                                            "	* \n" +
                                                            " from\n" +
                                                            "	tbl_questao q , tbl_prova_feita pf, tbl_disciplina d,  tbl_prova p\n" +
                                                            " where\n" +
                                                            "	q.cod_prova = pf.cod_prova and\n" +
                                                            "	q.cod_questao = pf.cod_questao and \n" +
                                                            "	d.cod_disciplina = p.cod_disciplina and\n" +
                                                            "	p.cod_prova = pf.cod_prova and\n" +
                                                            "	pf.cod_prova = ? ;";
    
    private static final String PROVA_FEITA_COD_PROVA_ID = "select \n" +
                                                            "	* \n" +
                                                            " from\n" +
                                                            "	tbl_questao q , tbl_prova_feita pf, tbl_disciplina d,  tbl_prova p\n" +
                                                            " where\n" +
                                                            "	q.cod_prova = pf.cod_prova and\n" +
                                                            "	q.cod_questao = pf.cod_questao and \n" +
                                                            "	d.cod_disciplina = p.cod_disciplina and\n" +
                                                            "	p.cod_prova = pf.cod_prova and\n" +
                                                            "	pf.cod_prova = ? and pf.cod_aluno = ? ;";

    private static final String QUESTÕES_PROVA = "select * from tbl_questao q where q.cod_prova = ?";
    
    private static final String PROVA = "SELECT * FROM prova.tbl_prova p, tbl_disciplina d \n" +
                                            "where \n" +
                                            "p.cod_disciplina = d.cod_disciplina and\n" +
                                            "p.cod_prova = ?;";
    
    private static final String ISERT_PF = "INSERT INTO `prova`.`tbl_prova_feita`\n" +
                                                    "(`cod_prova`,\n" +
                                                    "`cod_aluno`,\n" +
                                                    "`cod_questao`,\n" +
                                                    "`resposta`,\n" +
                                                    "`tbl_usuario_idusuario`)\n" +
                                                    "VALUES (?,?,?,?,?);";
    
    
    public boolean responderQuestao(int cod_prova, int cod_aluno, int cod_quest, String resp, int idUser){
        boolean resultado = false;
	Connection conn = new FabricaConexoes().getConnection();
        PreparedStatement statement;
            try {
                statement = conn.prepareStatement(ISERT_PF);
                statement.setInt(1, cod_prova);
                statement.setInt(2, cod_aluno);
                statement.setInt(3, cod_quest);
                statement.setString(4, resp);
                statement.setInt(5, idUser);
                statement.executeUpdate();
                resultado = true;
            } catch (SQLException e) {
                    System.out.println("------->" + e.getMessage());
            }        
            return resultado;
    } 
    
    
    
    public String[][] consultaProvasDisciplinas(int op, int codAluno){
        String[][] retorno = null;
        Connection conn = new FabricaConexoes().getConnection();
        PreparedStatement statement;
        ResultSet result;
            try {
                if(op == 1){
                    statement = conn.prepareStatement(DISCIPLINAS_PROVAS);
                    statement.setInt(1, codAluno);
                }else{
                    statement = conn.prepareStatement(DISCIPLINAS_PROVAS_FEITAS);                    
                    statement.setInt(1, codAluno);
                }
                result = statement.executeQuery();
                result.last();
                int x = result.getRow();
                retorno = new  String[x][2];
                int i = 0;
                result.beforeFirst();
                while (result.next()) {
                        retorno[i][0] = result.getString(2);
                        retorno[i][1] = result.getString(3);
                        i++;
                    }
            }catch(SQLException e){
                    System.out.println("------->" + e.getMessage());                
            }        
        return retorno;
    }
    
    public String[] consultaDisciplinas(int op, int cod){
        String[] retorno = null;
        Connection conn = new FabricaConexoes().getConnection();
        PreparedStatement statement;
        ResultSet result;
            try {
                if(op == 1){
                    statement = conn.prepareStatement(DISCIPLINAS_COM_PROVAS);
                    statement.setInt(1, cod);
                }else{
                    statement = conn.prepareStatement(DISCIPLINAS_COM_PROVAS_FEITAS);                    
                    statement.setInt(1, cod);
                }
                result = statement.executeQuery();
                result.last();
                int x = result.getRow();
                retorno = new  String[x];
                int i = 0;
                result.beforeFirst();
                while (result.next()) {
                        retorno[i] = result.getString(2);
                        i++;
                    }
            }catch(SQLException e){
                    System.out.println("------->" + e.getMessage());                
            }        
        return retorno;
    }
    
    
    public Prova resultadoProva(int cod_prova,int cod_aluno){
        Prova retorno = new Prova();
        String[][] questoes = null;
        Connection conn = new FabricaConexoes().getConnection();
        PreparedStatement statement;
        ResultSet result;
            try {
                statement = conn.prepareStatement(PROVA_FEITA_COD_PROVA_ID);
                statement.setInt(1, cod_prova);
                statement.setInt(2, cod_aluno);
                result = statement.executeQuery();
                result.first();
                retorno.setCod_prova(result.getInt(2));
                retorno.setCod_pro(result.getInt(2));
                retorno.setIdUsuario(result.getInt(14));
                retorno.setCod_disciplina(result.getInt(15));
                retorno.setDisciplina(result.getString(16));
                result.last();
                int x = result.getRow();
                questoes = new  String[x][8];
                int i = 0;
                result.beforeFirst();
                while (result.next()) {
                    questoes[i][0] = result.getString(3);
                    questoes[i][1] = result.getString(4);
                    questoes[i][2] = result.getString(5);
                    questoes[i][3] = result.getString(6);
                    questoes[i][4] = result.getString(7);
                    questoes[i][5] = result.getString(8);
                    questoes[i][6] = result.getString(9);
                    questoes[i][7] = result.getString(13);
                    i++;
                }
                retorno.setQuestoes(questoes);
            }catch(SQLException e){
                    System.out.println("------->" + e.getMessage());                
            }        
        return retorno;
        
    }

    public List<Questao> resultadoQuestoes(int cod_prova, int cod_aluno){
        Connection conn = new FabricaConexoes().getConnection();
        PreparedStatement statement;
        List<Questao> listaQuestoes = new ArrayList<>();
        ResultSet result;
            try {
                if(cod_aluno == 0){
                    statement = conn.prepareStatement(PROVA_FEITA_COD_PROVA);
                    statement.setInt(1, cod_prova);
                }else{
                    statement = conn.prepareStatement(PROVA_FEITA_COD_PROVA_ID);
                    statement.setInt(1, cod_prova);
                    statement.setInt(2, cod_aluno);
                }
                result = statement.executeQuery();
                while (result.next()) {
                    Questao quest = new Questao();
                    quest.setCod_questao(result.getInt(1));
                    quest.setCod_prova(result.getInt(2));
                    quest.setCod_aluno(result.getInt(11));
                    quest.setEnunciado(result.getString(3));
                    quest.setAlternativa_a(result.getString(5));
                    quest.setAlternativa_b(result.getString(6));
                    quest.setAlternativa_c(result.getString(7));
                    quest.setAlternativa_d(result.getString(8));
                    quest.setAlternativa_e(result.getString(9));
                    quest.setCorreta(result.getString(4));
                    quest.setResposta(result.getString(13));
                    listaQuestoes.add(quest);
                }
 
            }catch(SQLException e){
                    System.out.println("------->" + e.getMessage());                
            }        
        return listaQuestoes;        
    }

    public List<Questao> provaQuestoes(int cod_prova){
        Connection conn = new FabricaConexoes().getConnection();
        PreparedStatement statement;
        List<Questao> listaQuestoes = new ArrayList<>();
        ResultSet result;
            try {
                statement = conn.prepareStatement(QUESTÕES_PROVA);
                statement.setInt(1, cod_prova);
                result = statement.executeQuery();
                while (result.next()) {
                    Questao quest = new Questao();
                    quest.setCod_questao(result.getInt(1));
                    quest.setCod_prova(result.getInt(2));
                    quest.setCod_aluno(0);
                    quest.setEnunciado(result.getString(3));
                    quest.setAlternativa_a(result.getString(5));
                    quest.setAlternativa_b(result.getString(6));
                    quest.setAlternativa_c(result.getString(7));
                    quest.setAlternativa_d(result.getString(8));
                    quest.setAlternativa_e(result.getString(9));
                    quest.setCorreta(null);
                    quest.setResposta(null);
                    listaQuestoes.add(quest);
                }
 
            }catch(SQLException e){
                    System.out.println("------->" + e.getMessage());                
            }        
        return listaQuestoes;
        
    }

    public Prova getProva(int cod_prova, int idUsuario){
        Connection conn = new FabricaConexoes().getConnection();
        PreparedStatement statement;
        Prova prova = null;
        ResultSet result;
            try {
                statement = conn.prepareStatement(PROVA);
                statement.setInt(1, cod_prova);
                result = statement.executeQuery();
                while (result.next()) {
                    prova = new Prova();
                    prova.setCod_prova(result.getInt(1));
                    prova.setCod_pro(result.getInt(2));
                    prova.setCod_disciplina(result.getInt(3));
                    prova.setDisciplina(result.getString(6));
                    prova.setIdUsuario(idUsuario);
                }
 
            }catch(SQLException e){
                    System.out.println("------->" + e.getMessage());                
            }        
       
        return prova;
        
    }
}
