/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import bean.Prova;
import static controller.UsuarioController.getSessionAttribute;
import dao.ProvaDAO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
public class Provas {
    
    Prova prova;
    ProvaDAO provaDAO;
    private TreeNode root;
    private TreeNode selectedNode;
    
    public Provas() {
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
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());  
  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 
}
