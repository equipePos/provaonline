/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import bean.Prova;
import bean.Questao;
import static controller.UsuarioController.getSessionAttribute;
import dao.ProvaDAO;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Giovani
 */
@ManagedBean(name = "provas")
@SessionScoped
public class Provas implements Serializable{
    
    private Prova prova;
    private ProvaDAO provaDAO;
    private TreeNode root;
    private TreeNode selectedNode;
    private List<Questao> questoes;
    private Questao questaoAtual;
    private int nroAtual = 0;
    
    public Provas() {
        init();
    }

    public void init(){
        provaDAO = new ProvaDAO();
        
        String[][] retorno = null ;
        String[] disciplinas = null ;
        Object uId = getSessionAttribute("usuarioId");
        retorno = provaDAO.consultaProvasDisciplinas(1,Integer.parseInt(uId.toString()));
        disciplinas = provaDAO.consultaDisciplinas(1,Integer.parseInt(uId.toString()));
        
        root = new DefaultTreeNode("Root", null);
        
        TreeNode[] nos = new TreeNode[disciplinas.length];
        TreeNode[] subNos = new TreeNode[retorno.length];
        int i = 0, j = 0;

        for (String strings : disciplinas) {
            nos[i] = new DefaultTreeNode(strings, root);
                for(String[] x : retorno){
                    if(strings.equals(x[0])){
                        subNos[j] = new DefaultTreeNode("prova", "Prova "+x[1] , nos[i]);
                        j++;
                    }
                }            
            i++;
        }        
    }
    
    
    public int getNroAtual() {
        return nroAtual;
    }

    public void setNroAtual(int nroAtual) {
        this.nroAtual = nroAtual;
    }

    public Questao getQuestaoAtual() {
        return questaoAtual;
    }

    public void setQuestaoAtual(Questao questaoAtual) {
        this.questaoAtual = questaoAtual;
    }

    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        this.prova = prova;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }
    
    public TreeNode getRoot() {  
        return root;  
    } 
    
     public TreeNode getSelectedNode() {  
        return selectedNode;  
    }  
  
    public void setSelectedNode(TreeNode selectedNode) {  
        this.selectedNode = selectedNode;  
    }     
  
    public void onNodeSelect(NodeSelectEvent event) {  
        FacesContext redireciona = FacesContext.getCurrentInstance();
        ExternalContext context = redireciona.getExternalContext();  
        
        int cod = 0;
        String no = event.getTreeNode().toString();
            if(no.substring(0, 6).equals("Prova ")){
                Object uId = getSessionAttribute("usuarioId");
                cod = Integer.parseInt(no.substring(6));              
                realizarProva(cod, Integer.parseInt(uId.toString()));
                    try { 
                        context.redirect("http://localhost:8080/provaonline/prova/index.jsf");
                    } catch (IOException ex) {
                        Logger.getLogger(Provas.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
    }
    
    public void realizarProva(int cod_prova, int idUsuario){
        provaDAO = new ProvaDAO();
        prova = provaDAO.getProva(cod_prova, idUsuario);
        questoes = provaDAO.provaQuestoes(cod_prova);
        setNroAtual(0);
        questaoAtual = questoes.get(nroAtual);
    }
    
    public void atualizarQuestaoAtual(){
        int i  = getNroAtual();
        i = i + 1 ;
        setNroAtual(i);
        setQuestaoAtual(questoes.get(nroAtual));
    }
    
    public void responderQuestao(){
        provaDAO = new ProvaDAO();
        provaDAO.responderQuestao(prova.getCod_prova(), 
                prova.getIdUsuario(), 
                this.questaoAtual.getCod_questao(), 
                this.questaoAtual.getResposta(), prova.getIdUsuario());
            FacesContext contexto = FacesContext.getCurrentInstance();
            int x = questoes.size() - 1;
            if(nroAtual < questoes.size()-1 ){
                contexto.addMessage("resposta", new FacesMessage(FacesMessage.SEVERITY_INFO, "Resposta gravada","" ));     
                atualizarQuestaoAtual();                
            }else{
                setNroAtual(nroAtual+1);
                contexto.addMessage("resposta", new FacesMessage(FacesMessage.SEVERITY_INFO, "Resposta Gravada", "Prova encerrada!"));
            }
    }    
    
}