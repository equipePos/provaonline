/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import bean.Usuario;
import dao.UsuarioDAO;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Giovani
 */
@ManagedBean(name = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable{
    private Usuario usuario;
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    
    public String  inserirAluno(){
        String resultado = "falha";
        usuarioDAO = new UsuarioDAO();
        boolean retorno = usuarioDAO.addUsuario(usuario, 1);
        if(retorno){
            resultado = "index";
        }
        usuario = new Usuario();
        return resultado;
    }

    public void inicializaSessao(){
        Object ul = getSessionAttribute("usuariologado");  
        Object ut = getSessionAttribute("usuarioTipo");
        Object uId = getSessionAttribute("usuarioId");
        if(ul == null && ut == null && uId == null){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariologado", false);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioTipo", 0);            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioId", 0);            
        }
    }
    
    public static Object getSessionAttribute(String attributeName) {    
        try {    
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();    
            if (ec != null){    
                Map attrMap = ec.getSessionMap();       
                if (attrMap != null) {    
                    return attrMap.get(attributeName);    
                } else {    
                    return null;    
                }    
            } else {    
                return null;    
            }    
        } catch (Exception e) {    
            e.printStackTrace();    
            return null;    
        }    
} 
    
    public String prepararAdicionarAluno(){
        usuario = new Usuario();
        return "addUsuario";
    }
        
    public void autenticar() throws SQLException, IOException{
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariologado", false);         
        FacesContext redireciona = FacesContext.getCurrentInstance();  
        ExternalContext context = redireciona.getExternalContext();          
        usuarioDAO = new UsuarioDAO();
        String[] retorno = usuarioDAO.getAutenticacao(usuario.getLogin(), usuario.getSenha());
        if(retorno[0].equals("1")){
            this.usuario = usuarioDAO.consultarUsuarioID(Integer.parseInt(retorno[2]));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariologado", true); 
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioTipo", retorno[1]); 
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioId", retorno[2]); 
            if(retorno[1].equals("1")){
                context.redirect("http://localhost:8080/provaonline/aluno/index.jsf"); 
            }else{
                context.redirect("http://localhost:8080/provaonline/adm/index.jsf"); 
            }
        }else{
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage("erroLogin", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao logar!", "Nome de usuário ou senha inválidos!!"));            
            usuario = new Usuario();
        }
    }
    
    public void logOut() throws IOException{
        this.usuario = new Usuario();
        FacesContext redireciona = FacesContext.getCurrentInstance();          
        ExternalContext context = redireciona.getExternalContext();        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariologado", false);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioTipo", 0);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioId", 0);
        context.redirect("http://localhost:8080/provaonline/index.jsf"); 

    }    
}
