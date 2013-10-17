/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Giovani
 */
@ManagedBean(name = "prova")
@RequestScoped
public class Prova {
    
    private int cod_prova;
    private int cod_pro;
    private int cod_disciplina;
    private String Disciplina;
    private int idUsuario;
    private String[][] questoes;

    public Prova() {
    }

    public String getDisciplina() {
        return Disciplina;
    }

    public void setDisciplina(String Disciplina) {
        this.Disciplina = Disciplina;
    }

    public int getCod_prova() {
        return cod_prova;
    }

    public void setCod_prova(int cod_prova) {
        this.cod_prova = cod_prova;
    }

    public int getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(int cod_pro) {
        this.cod_pro = cod_pro;
    }

    public int getCod_disciplina() {
        return cod_disciplina;
    }

    public void setCod_disciplina(int cod_disciplina) {
        this.cod_disciplina = cod_disciplina;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String[][] getQuestoes() {
        return questoes;
    }

    public void setQuestoes(String[][] questoes) {
        this.questoes = questoes;
    }
}
