/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import bean.Prova;
import bean.Questao;
import dao.ProvaDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Giovani
 */
@ManagedBean(name = "provaController")
@SessionScoped
public class ProvaController {

    Prova prova;
    ProvaDAO provaDAO;
    private List<Questao> questoes;

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }
    
    public ProvaController() {
        
    }

    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        this.prova = prova;
    }

    public ProvaDAO getProvaDAO() {
        return provaDAO;
    }

    public void setProvaDAO(ProvaDAO provaDAO) {
        this.provaDAO = provaDAO;
    }

    public void atualizaQuestoes(int cod) {
        provaDAO = new ProvaDAO();
        this.setQuestoes( provaDAO.resultadoQuestoes(cod,0));
    }

    public void realizarProva(int cod_prova, int idUsuario){
        provaDAO = new ProvaDAO();
        prova = provaDAO.getProva(cod_prova, idUsuario);
        questoes = provaDAO.provaQuestoes(cod_prova);
    }
}
