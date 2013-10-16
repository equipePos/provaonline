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
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Giovani
 */
@ManagedBean(name = "provasFeitas")
@SessionScoped
public class ProvasFeitas implements Serializable{
    
    Prova prova;
    ProvaDAO provaDAO;
    private List<Questao> questoes;
    
    private TreeNode root;
    private TreeNode selectedNode;
    
    public ProvasFeitas() {
        provaDAO = new ProvaDAO();
        
        String[][] retorno = null ;
        String[] disciplinas = null ;

        Object uId = getSessionAttribute("usuarioId");
        retorno = provaDAO.consultaProvasDisciplinas(0,Integer.parseInt(uId.toString()));
        disciplinas = provaDAO.consultaDisciplinas(0,Integer.parseInt(uId.toString()));
        
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
        int cod = 0;
        String no = event.getTreeNode().toString();
            if(no.substring(0, 6).equals("Prova ")){
                cod = Integer.parseInt(no.substring(6));              
                atualizaQuestoes(cod);
                System.out.println("passou!");
            }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", no.substring(0, 6) + " -- "+String.valueOf(cod));  
  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 
    
    public int nodeClick(NodeSelectEvent event) {  
        int cod = 0;
        String no = event.getTreeNode().toString();
            if(no.substring(0, 6).equals("Prova ")){
                cod = Integer.parseInt(no.substring(6));              
            }
         return cod;
    } 

    public void atualizaQuestoes(int cod) {            
        provaDAO = new ProvaDAO();
        this.setQuestoes(provaDAO.resultadoQuestoes(cod));
    }

}