/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import bean.Usuario;
import dao.UsuarioDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Giovani
 */
@ManagedBean(name = "usuarioController")
@RequestScoped
public class UsuarioController {
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
    
    
    public void autenticar() throws SQLException, IOException{
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariologado", false);         
        FacesContext redireciona = FacesContext.getCurrentInstance();  
        ExternalContext context = redireciona.getExternalContext();          
        usuarioDAO = new UsuarioDAO();
        String[] retorno = usuarioDAO.getAutenticacao(usuario.getLogin(), usuario.getSenha());
        System.out.println("0 --> "+retorno[0]+" 1 --> "+retorno[1]);
        if(retorno[0].equals("1")){
            if(retorno[1].equals("1")){
                System.out.println("Passou aluno");
                context.redirect("aluno\\index.jsf"); 
            }else{
                System.out.println("Passou adm");
                context.redirect("adm\\index.jsf"); 
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariologado", true); 
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioTipo", retorno[1]); 
        }else{
            context.redirect("index.jsf"); 
            //FacesContext.getCurrentInstance().addMessage("erroLogin", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro ao logar!", "Nome de usuário ou senha inválidos!!"));            
        }

    }
    
    public String logOut(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariologado", false);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioTipo", 0);
        return "index";
    }    
}
