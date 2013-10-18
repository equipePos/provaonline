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
@ManagedBean(name = "questao")
@RequestScoped
public class Questao {
    private int cod_questao; 
    private int cod_prova;
    private int cod_aluno;
    private String enunciado; 
    private String alternativa_a; 
    private String alternativa_b; 
    private String alternativa_c; 
    private String alternativa_d; 
    private String alternativa_e; 
    private String correta;
    private String resposta;
    private String corretaExtenso;
    private String respostaExtenso;

    public String getCorretaExtenso() {
        return corretaExtenso;
    }

    public void setCorretaExtenso(String corretaExtenso) {
        this.corretaExtenso = corretaExtenso;
    }

    public String getRespostaExtenso() {
        return respostaExtenso;
    }

    public void setRespostaExtenso(String respostaExtenso) {
        this.respostaExtenso = respostaExtenso;
    }

    public Questao() {
    }

    public int getCod_questao() {
        return cod_questao;
    }

    public void setCod_questao(int cod_questao) {
        this.cod_questao = cod_questao;
    }

    public int getCod_prova() {
        return cod_prova;
    }

    public void setCod_prova(int cod_prova) {
        this.cod_prova = cod_prova;
    }

    public int getCod_aluno() {
        return cod_aluno;
    }

    public void setCod_aluno(int cod_aluno) {
        this.cod_aluno = cod_aluno;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getAlternativa_a() {
        return alternativa_a;
    }

    public void setAlternativa_a(String alternativa_a) {
        this.alternativa_a = alternativa_a;
    }

    public String getAlternativa_b() {
        return alternativa_b;
    }

    public void setAlternativa_b(String alternativa_b) {
        this.alternativa_b = alternativa_b;
    }

    public String getAlternativa_c() {
        return alternativa_c;
    }

    public void setAlternativa_c(String alternativa_c) {
        this.alternativa_c = alternativa_c;
    }

    public String getAlternativa_d() {
        return alternativa_d;
    }

    public void setAlternativa_d(String alternativa_d) {
        this.alternativa_d = alternativa_d;
    }

    public String getAlternativa_e() {
        return alternativa_e;
    }

    public void setAlternativa_e(String alternativa_e) {
        this.alternativa_e = alternativa_e;
    }

    public String getCorreta() {
        return correta;
    }

    public void setCorreta(String correta) {
        this.correta = correta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

  
}
